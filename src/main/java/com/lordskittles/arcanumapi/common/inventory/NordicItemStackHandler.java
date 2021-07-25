package com.lordskittles.arcanumapi.common.inventory;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public class NordicItemStackHandler extends ItemStackHandler {

    protected final BlockEntity tile;

    public NordicItemStackHandler(int size) {

        this(null, size);
    }

    public NordicItemStackHandler(BlockEntity tile, int size) {

        super(size);
        this.tile = tile;
    }

    @Override
    protected void onContentsChanged(int slot) {

        super.onContentsChanged(slot);
        if(this.tile != null)
            this.tile.setChanged();
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {

        return isItemValid(slot, stack) ? super.insertItem(slot, stack, simulate) : stack;
    }
}
