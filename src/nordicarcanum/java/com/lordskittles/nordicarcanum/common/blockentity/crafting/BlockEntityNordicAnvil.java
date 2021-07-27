package com.lordskittles.nordicarcanum.common.blockentity.crafting;

import com.lordskittles.arcanumapi.common.utilities.ItemUtilities;
import com.lordskittles.arcanumapi.common.utilities.NBTUtilities;
import com.lordskittles.arcanumapi.core.NBTConstants;
import com.lordskittles.nordicarcanum.common.inventory.crafting.NordicAnvilRecipe;
import com.lordskittles.nordicarcanum.common.item.crafting.ItemBindingCast;
import com.lordskittles.nordicarcanum.common.item.magic.ItemBinding;
import com.lordskittles.nordicarcanum.common.registry.RecipeType;
import com.lordskittles.nordicarcanum.common.registry.BlockEntities;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicNBTConstants;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.AirItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class BlockEntityNordicAnvil extends BlockEntity {

    private ItemStack heldItem = ItemStack.EMPTY;
    private ItemStack heldCast = ItemStack.EMPTY;
    private NordicAnvilRecipe recipe = null;
    private int progress;

    public BlockEntityNordicAnvil(BlockPos pos, BlockState state) {

        super(BlockEntities.norse_anvil.get(), pos, state);
    }

    private NordicAnvilRecipe getRecipe(ItemStack input) {

        return RecipeType.nordic_anvil.findFirst(level, recipe -> recipe.matches(input));
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

    public void updateProgress(Level world) {

        if(! canProgress())
            return;

        this.progress++;
        ItemStack output = ItemStack.EMPTY;

        if(this.progress >= this.recipe.hits) {
            output = ItemUtilities.deepCopy(this.recipe.getResultItem());

            if(output.getItem() instanceof ItemBinding) {
                ItemBinding binding = (ItemBinding) output.getItem();
                ItemBindingCast cast = (ItemBindingCast) this.heldCast.getItem();

                binding.setShape(cast.getShape());

                CompoundTag base = NBTUtilities.getPersistentData(NordicArcanum.MODID, output);
                base.putInt(NBTConstants.BINDING_SHAPE_KEY, cast.getShape().getValue());
                output.save(base);
            }
        }

        if(output != ItemStack.EMPTY) {
            BlockPos pos = getBlockPos().above();
            world.addFreshEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), output));
            reset();
        }
    }

    public void reset() {

        this.heldItem = ItemStack.EMPTY;
        this.recipe = null;
        this.progress = 0;
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket packet) {

        ItemStack stack = this.heldItem;
        CompoundTag nbt = packet.getTag();
        super.onDataPacket(net, packet);
        load(nbt);

        if(this.level != null && this.level.isClientSide) {
            if(! this.heldItem.equals(stack)) {
                this.level.blockEntityChanged(getBlockPos());
            }
        }
    }

    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {

        CompoundTag nbt = new CompoundTag();
        save(nbt);
        return new ClientboundBlockEntityDataPacket(getBlockPos(), 1, nbt);
    }

    @Override
    public CompoundTag getUpdateTag() {

        CompoundTag nbt = super.getUpdateTag();
        save(nbt);
        return nbt;
    }

    @Override
    public CompoundTag save(CompoundTag compound) {

        super.save(compound);

        CompoundTag baseCompound = NBTUtilities.getPersistentData(NordicArcanum.MODID, compound);
        CompoundTag heldCompound = new CompoundTag();
        CompoundTag castCompound = new CompoundTag();

        this.heldItem.save(heldCompound);
        this.heldCast.save(castCompound);

        baseCompound.put(NordicNBTConstants.ANVIL_HELD_ITEM, heldCompound);
        baseCompound.put(NordicNBTConstants.ANVIL_HELD_CAST, castCompound);

        return compound;
    }

    @Override
    public void load(CompoundTag compound) {

        super.load(compound);

        CompoundTag baseCompound = NBTUtilities.getPersistentData(NordicArcanum.MODID, compound);

        this.heldItem = ItemStack.of(baseCompound.getCompound(NordicNBTConstants.ANVIL_HELD_ITEM));
        this.heldCast = ItemStack.of(baseCompound.getCompound(NordicNBTConstants.ANVIL_HELD_CAST));
    }
}
