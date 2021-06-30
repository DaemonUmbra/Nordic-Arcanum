package com.lordskittles.nordicarcanum.common.tileentity;

import com.lordskittles.arcanumapi.common.network.PacketBase;
import com.lordskittles.arcanumapi.common.tileentity.SmartSyncTank;
import com.lordskittles.arcanumapi.common.tileentity.TileEntityFluidInventory;
import com.lordskittles.arcanumapi.common.utilities.InventoryUtilities;
import com.lordskittles.arcanumapi.common.utilities.ItemUtilities;
import com.lordskittles.arcanumapi.common.utilities.NBTUtilities;
import com.lordskittles.nordicarcanum.common.NordicRegistry;
import com.lordskittles.nordicarcanum.common.inventory.crafting.ArcaneInfuserRecipe;
import com.lordskittles.nordicarcanum.common.registry.RecipeType;
import com.lordskittles.nordicarcanum.common.registry.TileEntities;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicFluidValues;
import com.lordskittles.nordicarcanum.core.NordicTags;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tags.BlockTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandlerModifiable;

import java.util.Objects;

public class TileEntityArcaneInfuser extends TileEntityFluidInventory<TileEntityArcaneInfuser> implements IInventory
{
    private ArcaneInfuserRecipe recipe = null;

    private int progress = 0;

    protected NonNullList<ItemStack> contents = NonNullList.withSize(1, ItemStack.EMPTY);
    protected IItemHandlerModifiable items = InventoryUtilities.createHandler(this);
    protected LazyOptional<IItemHandlerModifiable> itemHandler = LazyOptional.of(() -> items);

    public TileEntityArcaneInfuser(TileEntityType<?> tileEntityTypeIn)
    {
        super(tileEntityTypeIn);
    }

    public TileEntityArcaneInfuser()
    {
        this(TileEntities.arcane_infuser.get());
    }

    private ArcaneInfuserRecipe getRecipe(ItemStack input, FluidStack fluidInput)
    {
        return RecipeType.arcane_infuser.findFirst(world, recipe ->
        {
           return recipe.matches(input, fluidInput);
        });
    }

    public boolean setHeldItem(ItemStack stack)
    {
        if (!this.getHeldItem().isEmpty())
            return false;

        setInventorySlotContents(0, ItemUtilities.singleDeepCopy(stack));
        sendUpdatePacket();

        return true;
    }

    public ItemStack getHeldItem()
    {
        return this.contents.get(0);
    }

    public ItemStack removeItems()
    {
        if (this.getHeldItem() == ItemStack.EMPTY)
            return ItemStack.EMPTY;

        ItemStack stack = ItemUtilities.deepCopy(getHeldItem());

        resetItem();

        return stack;
    }

    @Override
    public boolean doesRetainFluid()
    {
        return false;
    }

    @Override
    public void onServerUpdate()
    {
        super.onServerUpdate();
        if (this.recipe == null)
        {
            this.recipe = getRecipe(getHeldItem(), this.tank.getFluid());
        }

        if (canProgress())
        {
            updateProgress();
        }
    }

    private boolean isAboveHeatSource()
    {
        BlockState below = this.world.getBlockState(getPos().down());
        return NordicTags.IsBlockInTag(below, NordicTags.HEAT_SOURCE);
    }

    public boolean canProgress()
    {
        if (this.getHeldItem() == ItemStack.EMPTY)
            return false;

        if (this.tank.isEmpty())
            return false;

        if (this.recipe == null)
            return false;

        if (!isAboveHeatSource())
            return false;

        return this.tank.getFluidAmount() >= this.recipe.fluidIngredient.getAmount() && this.getHeldItem().getCount() >= this.recipe.ingredient.getMatchingStacks()[0].getCount();
    }

    public void updateProgress()
    {
        if (!canProgress())
            return;

        this.progress++;
        ItemStack output = ItemStack.EMPTY;

        if (this.progress >= this.recipe.time)
        {
            output = this.recipe.getRecipeOutput();
        }

        if (output != ItemStack.EMPTY)
        {
            BlockPos pos = getPos().up();
            this.world.addEntity(new ItemEntity(this.world, pos.getX(), pos.getY(), pos.getZ(), output));
            resetItem();
            this.tank.drain(this.recipe.fluidIngredient.getAmount(), IFluidHandler.FluidAction.EXECUTE);
            this.recipe = null;
            this.markDirty();
        }
    }

    public void resetItem()
    {
        setInventorySlotContents(0, ItemStack.EMPTY);
        this.progress = 0;
    }

    @Override
    public int getCapacity()
    {
        return NordicFluidValues.ARCANE_INFUSER;
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket packet)
    {
        SmartSyncTank tank = this.tank;
        CompoundNBT nbt = packet.getNbtCompound();
        super.onDataPacket(net, packet);
        read(getBlockState(), nbt);

        if (this.world != null && this.world.isRemote)
        {
            if (!this.tank.equals(tank))
            {
                this.world.markChunkDirty(getPos(), this.getTileEntity());
            }
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound)
    {
        super.write(compound);

        CompoundNBT base = NBTUtilities.getPersistentData(NordicArcanum.MODID, compound);
        ItemStackHelper.saveAllItems(base, this.contents);

        return compound;
    }

    @Override
    public void read(BlockState state, CompoundNBT compound)
    {
        super.read(state, compound);

        CompoundNBT base = NBTUtilities.getPersistentData(NordicArcanum.MODID, compound);
        ItemStackHelper.loadAllItems(base, this.contents);
    }

    @Override
    protected void sendPacket(PacketBase packet, TileEntity tracking)
    {
        NordicArcanum.PACKET_HANDLER.sendToAllTracking(packet, tracking);
    }

    public NonNullList<ItemStack> getItems()
    {
        return this.contents;
    }

    @Override
    public int getSizeInventory()
    {
        return 1;
    }

    @Override
    public boolean isEmpty()
    {
        return this.getItems().stream().allMatch(ItemStack::isEmpty);
    }

    @Override
    public ItemStack getStackInSlot(int index)
    {
        return this.contents.get(index);
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
    public void setInventorySlotContents(int index, ItemStack stack)
    {
        this.contents.set(index, stack);
        sendUpdatePacket();
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
}
