package com.lordskittles.nordicarcanum.common.tileentity.crafting;

import com.lordskittles.arcanumapi.common.utilities.ItemUtilities;
import com.lordskittles.arcanumapi.common.utilities.NBTUtilities;
import com.lordskittles.arcanumapi.core.NBTConstants;
import com.lordskittles.nordicarcanum.common.inventory.crafting.NordicAnvilRecipe;
import com.lordskittles.nordicarcanum.common.item.crafting.ItemBindingCast;
import com.lordskittles.nordicarcanum.common.item.magic.ItemBinding;
import com.lordskittles.nordicarcanum.common.registry.RecipeType;
import com.lordskittles.nordicarcanum.common.registry.TileEntities;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicNBTConstants;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.AirItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class TileEntityNordicAnvil extends TileEntity {

    private ItemStack heldItem = ItemStack.EMPTY;
    private ItemStack heldCast = ItemStack.EMPTY;
    private NordicAnvilRecipe recipe = null;
    private int progress;

    public TileEntityNordicAnvil(TileEntityType<?> tileEntityType) {

        super(tileEntityType);
    }

    public TileEntityNordicAnvil() {

        this(TileEntities.norse_anvil.get());
    }

    private NordicAnvilRecipe getRecipe(ItemStack input) {

        return RecipeType.nordic_anvil.findFirst(world, recipe -> recipe.matches(input));
    }

    public ItemStack setCast(ItemStack stack) {

        if(! this.heldCast.isEmpty())
            return stack;

        this.heldCast = stack;
        return ItemStack.EMPTY;
    }

    public ItemStack setHeldItem(ItemStack stack) {

        if(! this.heldItem.isEmpty())
            return stack;

        if(this.recipe != null)
            return stack;

        this.recipe = getRecipe(stack);
        this.progress = 0;

        if(this.recipe == null)
            return stack;

        if(stack.getCount() < this.recipe.requiredAmount) {
            this.recipe = null;
            return stack;
        }

        int inputCount = stack.getCount();
        this.heldItem = new ItemStack(stack.getItem(), this.recipe.requiredAmount);

        int newCount = inputCount - this.heldItem.getCount();
        if(newCount == 0)
            return ItemStack.EMPTY;

        return new ItemStack(stack.getItem(), newCount);
    }

    public ItemStack getHeldItem() {

        return this.heldItem;
    }

    public ItemStack getHeldCast() {

        return this.heldCast;
    }

    public ItemStack removeItems() {

        if(this.heldItem.getItem() instanceof AirItem) {
            if(this.heldCast.getItem() instanceof AirItem) {
                return ItemStack.EMPTY;
            }
            else {
                Item item = this.heldCast.getItem();
                int count = this.heldCast.getCount();

                this.heldCast = ItemStack.EMPTY;

                return new ItemStack(item, count);
            }
        }

        Item item = this.heldItem.getItem();
        int count = this.heldItem.getCount();

        reset();

        return new ItemStack(item, count);
    }

    public boolean canProgress() {

        if(this.heldItem == ItemStack.EMPTY)
            return false;

        if(this.recipe == null)
            return false;

        return this.heldItem.getCount() >= this.recipe.requiredAmount;
    }

    public void updateProgress(World world) {

        if(! canProgress())
            return;

        this.progress++;
        ItemStack output = ItemStack.EMPTY;

        if(this.progress >= this.recipe.hits) {
            output = ItemUtilities.deepCopy(this.recipe.getRecipeOutput());

            if(output.getItem() instanceof ItemBinding) {
                ItemBinding binding = (ItemBinding) output.getItem();
                ItemBindingCast cast = (ItemBindingCast) this.heldCast.getItem();

                binding.setShape(cast.getShape());

                CompoundNBT base = NBTUtilities.getPersistentData(NordicArcanum.MODID, output);
                base.putInt(NBTConstants.BINDING_SHAPE_KEY, cast.getShape().getValue());
                output.write(base);
            }
        }

        if(output != ItemStack.EMPTY) {
            BlockPos pos = getPos().up();
            world.addEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), output));
            reset();
        }
    }

    public void reset() {

        this.heldItem = ItemStack.EMPTY;
        this.recipe = null;
        this.progress = 0;
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket packet) {

        ItemStack stack = this.heldItem;
        CompoundNBT nbt = packet.getNbtCompound();
        super.onDataPacket(net, packet);
        read(getBlockState(), nbt);

        if(this.world != null && this.world.isRemote) {
            if(! this.heldItem.equals(stack)) {
                this.world.markChunkDirty(getPos(), this.getTileEntity());
            }
        }
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {

        CompoundNBT nbt = new CompoundNBT();
        write(nbt);
        return new SUpdateTileEntityPacket(getPos(), 1, nbt);
    }

    @Override
    public CompoundNBT getUpdateTag() {

        CompoundNBT nbt = super.getUpdateTag();
        write(nbt);
        return nbt;
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {

        super.write(compound);

        CompoundNBT baseCompound = NBTUtilities.getPersistentData(NordicArcanum.MODID, compound);
        CompoundNBT heldCompound = new CompoundNBT();
        CompoundNBT castCompound = new CompoundNBT();

        this.heldItem.write(heldCompound);
        this.heldCast.write(castCompound);

        baseCompound.put(NordicNBTConstants.ANVIL_HELD_ITEM, heldCompound);
        baseCompound.put(NordicNBTConstants.ANVIL_HELD_CAST, castCompound);

        return compound;
    }

    @Override
    public void read(BlockState state, CompoundNBT compound) {

        super.read(state, compound);

        CompoundNBT baseCompound = NBTUtilities.getPersistentData(NordicArcanum.MODID, compound);

        this.heldItem = ItemStack.read(baseCompound.getCompound(NordicNBTConstants.ANVIL_HELD_ITEM));
        this.heldCast = ItemStack.read(baseCompound.getCompound(NordicNBTConstants.ANVIL_HELD_CAST));
    }
}
