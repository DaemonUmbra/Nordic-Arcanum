package com.lordskittles.arcanumapi.common.tileentity;

import com.lordskittles.arcanumapi.common.utilities.InventoryUtilities;
import com.lordskittles.arcanumapi.common.utilities.NBTUtilities;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootParameters;
import net.minecraft.loot.LootTable;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class TileEntityInventory<T extends TileEntityInventory> extends TileEntityUpdateable<T> implements IInventory, INamedContainerProvider, INameable
{
    protected int numPlayersUsing;

    @Nullable
    protected ResourceLocation lootTable;
    protected long lootTableSeed;

    protected NonNullList<ItemStack> contents;
    protected IItemHandlerModifiable items = InventoryUtilities.createHandler(this);
    protected LazyOptional<IItemHandlerModifiable> itemHandler = LazyOptional.of(() -> items);

    private final int size;
    private final ITextComponent title;
    private final String modid;

    public TileEntityInventory(TileEntityType<?> tileEntityTypeIn, int size, String containerId, String modid)
    {
        super(tileEntityTypeIn);

        this.size = size;
        this.title = new TranslationTextComponent("container." + containerId);
        this.contents = NonNullList.withSize(this.size, ItemStack.EMPTY);
        this.modid = modid;
    }

    public static void swapContents(TileEntityInventory tile, TileEntityInventory other)
    {
        NonNullList<ItemStack> list = tile.getItems();
        tile.setItems(other.getItems());
        other.setItems(list);
    }

    public static int getPlayersUsing(IBlockReader reader, BlockPos pos)
    {
        BlockState state = reader.getBlockState(pos);
        if (state.hasTileEntity())
        {
            TileEntity tile = reader.getTileEntity(pos);
            if (tile instanceof TileEntityInventory)
            {
                return ((TileEntityInventory)tile).numPlayersUsing;
            }
        }

        return 0;
    }

    public void setItems(NonNullList<ItemStack> items)
    {
        this.contents = items;
    }

    public NonNullList<ItemStack> getItems()
    {
        return this.contents;
    }

    @Override
    public int getSizeInventory()
    {
        return size;
    }

    @Override
    public boolean isEmpty()
    {
        return this.getItems().stream().allMatch(ItemStack::isEmpty);
    }

    protected void onSlotChanged() {}

    @Override
    public ItemStack getStackInSlot(int index)
    {
        return this.contents.get(index);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack)
    {
        this.contents.set(index, stack);

        onSlotChanged();
    }

    @Override
    public ItemStack decrStackSize(int index, int count)
    {
        ItemStack stack = ItemStackHelper.getAndSplit(this.contents, index, count);
        if (!stack.isEmpty())
        {
            this.markDirty();
        }

        return stack;
    }

    @Override
    public ItemStack removeStackFromSlot(int index)
    {
        return ItemStackHelper.getAndRemove(this.contents, index);
    }

    @Override
    public boolean isUsableByPlayer(PlayerEntity player)
    {
        if (this.world.getTileEntity(this.pos) != this)
        {
            return false;
        }

        return !(player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) > 64.0D);
    }

    @Override
    public void clear()
    {
        this.contents.clear();
    }

    @Override
    public ITextComponent getName()
    {
        return this.title;
    }

    @Override
    public ITextComponent getDisplayName()
    {
        return getName();
    }

    @Nullable
    @Override
    public abstract Container createMenu(int id, PlayerInventory playerInventory, PlayerEntity player);

    @Override
    public void remove()
    {
        super.remove();

        if (itemHandler != null)
        {
            itemHandler.invalidate();
        }
    }

    @Override
    public void read(BlockState state, CompoundNBT compound)
    {
        super.read(state, compound);

        CompoundNBT base = NBTUtilities.getPersistentData(modid, compound);

        this.contents = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);

        if (!this.checkLootAndRead(compound))
        {
            ItemStackHelper.loadAllItems(base, this.contents);
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound)
    {
        super.write(compound);

        CompoundNBT base = NBTUtilities.getPersistentData(modid, compound);

        if (!this.checkLootAndWrite(compound))
        {
            ItemStackHelper.loadAllItems(base, this.contents);
        }

        return compound;
    }

    @Override
    public boolean receiveClientEvent(int id, int type)
    {
        if (id == 1)
        {
            this.numPlayersUsing = type;
            return true;
        }

        return super.receiveClientEvent(id, type);
    }

    @Override
    public void updateContainingBlockInfo()
    {
        super.updateContainingBlockInfo();

        if (this.itemHandler != null)
        {
            this.itemHandler.invalidate();
            this.itemHandler = null;
        }
    }

    @Override
    public <T> LazyOptional getCapability(@Nonnull Capability<T> capability, @Nonnull Direction side)
    {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
        {
            return itemHandler.cast();
        }

        return super.getCapability(capability, side);
    }

    @Override
    public void openInventory(PlayerEntity player)
    {
        if (!player.isSpectator())
        {
            if (this.numPlayersUsing < 0)
            {
                this.numPlayersUsing = 0;
            }

            ++this.numPlayersUsing;
            this.onOpenOrClose();
        }
    }

    @Override
    public void closeInventory(PlayerEntity player)
    {
        if (!player.isSpectator())
        {
            --this.numPlayersUsing;
            this.onOpenOrClose();
        }
    }

    public void fillWithLoot(@Nullable PlayerEntity player)
    {
        if (this.lootTable != null && this.world.getServer() != null)
        {
            LootTable loottable = this.world.getServer().getLootTableManager().getLootTableFromLocation(this.lootTable);
            this.lootTable = null;
            LootContext.Builder lootcontext$builder = (new LootContext.Builder((ServerWorld)this.world)).withParameter(LootParameters.ORIGIN, new Vector3d(this.pos.getX(), this.pos.getY(), this.pos.getZ())).withSeed(this.lootTableSeed);
            if (player != null)
            {
                lootcontext$builder.withLuck(player.getLuck()).withParameter(LootParameters.THIS_ENTITY, player);
            }

            loottable.fillInventory(this, lootcontext$builder.build(LootParameterSets.CHEST));
        }
    }

    public void setLootTable(ResourceLocation lootTableIn, long seedIn)
    {
        this.lootTable = lootTableIn;
        this.lootTableSeed = seedIn;
    }

    protected boolean checkLootAndRead(CompoundNBT compound)
    {
        if (compound.contains("LootTable", 8))
        {
            this.lootTable = new ResourceLocation(compound.getString("LootTable"));
            this.lootTableSeed = compound.getLong("LootTableSeed");
            return true;
        }
        else
        {
            return false;
        }
    }

    protected boolean checkLootAndWrite(CompoundNBT compound)
    {
        if (this.lootTable == null)
        {
            return false;
        }
        else
        {
            compound.putString("LootTable", this.lootTable.toString());
            if (this.lootTableSeed != 0L)
            {
                compound.putLong("LootTableSeed", this.lootTableSeed);
            }

            return true;
        }
    }

    protected void onOpenOrClose()
    {

    }

    protected void playSound(SoundEvent sound)
    {
        double dx = (double)this.pos.getX() + 0.5D;
        double dy = (double)this.pos.getY() + 0.5D;
        double dz = (double)this.pos.getZ() + 0.5D;

        this.world.playSound((PlayerEntity)null, dx, dy, dz, sound, SoundCategory.BLOCKS, 0.5f, this.world.rand.nextFloat() * 0.1f + 0.9f);
    }
}
