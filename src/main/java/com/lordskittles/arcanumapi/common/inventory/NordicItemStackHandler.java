package com.lordskittles.arcanumapi.common.inventory;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public class NordicItemStackHandler extends ItemStackHandler
{
    protected final TileEntity tile;

    public NordicItemStackHandler(int size)
    {
        this(null, size);
    }

    public NordicItemStackHandler(TileEntity tile, int size)
    {
        super(size);
        this.tile = tile;
    }

    @Override
    protected void onContentsChanged(int slot)
    {
        super.onContentsChanged(slot);
        if (this.tile != null)
            this.tile.markDirty();
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate)
    {
        return isItemValid(slot, stack) ? super.insertItem(slot, stack, simulate) : stack;
    }
}
