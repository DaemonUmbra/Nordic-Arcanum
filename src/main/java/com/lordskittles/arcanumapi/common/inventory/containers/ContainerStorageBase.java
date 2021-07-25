package com.lordskittles.arcanumapi.common.inventory.containers;

import com.lordskittles.arcanumapi.common.tileentity.TileEntityInventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.ContainerLevelAccess;

public abstract class ContainerStorageBase<T extends TileEntityInventory> extends AbstractContainerMenu implements INordicContainer {

    public final T Tile;
    protected final ContainerLevelAccess canInteract;

    protected int nonPlayerSlotCount = 0;

    public ContainerStorageBase(MenuType<?> container, int nonPlayerSlotCount, final int windowId, final Inventory playerInventory, final T tile) {

        super(container, windowId);

        this.Tile = tile;
        this.Tile.startOpen(playerInventory.player);
        this.canInteract = ContainerLevelAccess.create(tile.getLevel(), tile.getBlockPos());
        this.nonPlayerSlotCount = nonPlayerSlotCount;
    }

    public Container getTileInventory() {

        return Tile;
    }

    protected int generateStorageSlots(int startIndex, int rows, int columns, int startX, int startY, int spacing) {

        int index = startIndex;

        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < columns; col++) {
                index = startIndex + ((row * columns) + col);
                this.addSlot(new Slot(Tile, index, startX + (col * spacing), startY + (row * spacing)));
            }
        }

        return index;
    }

    protected int generateMainPlayerInventory(Container inventory, int startX, int startY) {

        int startIndex = 9;
        int index = startIndex;
        int rows = 3;
        int cols = 9;
        int interval = 18;

        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                index = startIndex + ((row * cols) + col);
                this.addSlot(new Slot(inventory, index, startX + (col * interval), startY + (row * interval)));
            }
        }

        return index;
    }

    protected int generatePlayerInventoryHotbar(Container inventory, int startX, int startY) {

        int index = 0;
        int cols = 9;
        int interval = 18;

        for(int col = 0; col < cols; col++) {
            index = col;
            this.addSlot(new Slot(inventory, index, startX + (col * interval), startY));
        }

        return index;
    }

    public void removed(Player playerIn) {

        this.Tile.stopOpen(playerIn);
    }
}
