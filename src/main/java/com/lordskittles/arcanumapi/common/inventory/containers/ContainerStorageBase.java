package com.lordskittles.arcanumapi.common.inventory.containers;

import com.lordskittles.arcanumapi.common.tileentity.TileEntityInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.util.IWorldPosCallable;

public abstract class ContainerStorageBase<T extends TileEntityInventory> extends Container implements INordicContainer
{
    public final T Tile;
    protected final IWorldPosCallable canInteract;

    protected int nonPlayerSlotCount = 0;

    public ContainerStorageBase(ContainerType<?> container, int nonPlayerSlotCount, final int windowId, final PlayerInventory playerInventory, final T tile)
    {
        super(container, windowId);

        this.Tile = tile;
        this.Tile.openInventory(playerInventory.player);
        this.canInteract = IWorldPosCallable.of(tile.getWorld(), tile.getPos());
        this.nonPlayerSlotCount = nonPlayerSlotCount;
    }

    public IInventory getTileInventory()
    {
        return Tile;
    }

    protected int generateStorageSlots(int startIndex, int rows, int columns, int startX, int startY, int spacing)
    {
        int index = startIndex;

        for (int row = 0; row < rows; row++)
        {
            for (int col = 0; col < columns; col++)
            {
                index = startIndex + ((row * columns) + col);
                this.addSlot(new Slot(Tile, index, startX + (col * spacing), startY + (row * spacing)));
            }
        }

        return index;
    }

    protected int generateMainPlayerInventory(IInventory inventory, int startX, int startY)
    {
        int startIndex = 9;
        int index = startIndex;
        int rows = 3;
        int cols = 9;
        int interval = 18;

        for (int row = 0; row < rows; row++)
        {
            for (int col = 0; col < cols; col++)
            {
                index = startIndex + ((row * cols) + col);
                this.addSlot(new Slot(inventory, index, startX + (col * interval), startY + (row * interval)));
            }
        }

        return index;
    }

    protected int generatePlayerInventoryHotbar(IInventory inventory, int startX, int startY)
    {
        int index = 0;
        int cols = 9;
        int interval = 18;

        for (int col = 0; col < cols; col++)
        {
            index = col;
            this.addSlot(new Slot(inventory, index, startX + (col * interval), startY));
        }

        return index;
    }

    public void onContainerClosed(PlayerEntity playerIn)
    {
        this.Tile.closeInventory(playerIn);
    }
}
