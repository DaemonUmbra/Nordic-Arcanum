package com.lordskittles.arcanumapi.common.inventory.containers;

import com.lordskittles.arcanumapi.common.blockentity.BlockEntityInventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.ContainerLevelAccess;

public abstract class ContainerStorageBase<T extends BlockEntityInventory> extends AbstractContainerMenu implements INordicContainer {

    public final T blockEntity;
    protected final ContainerLevelAccess canInteract;

    protected int nonPlayerSlotCount = 0;

    public ContainerStorageBase(MenuType<?> container, int nonPlayerSlotCount, final int windowId, final Inventory playerInventory, final T blockEntity) {

        super(container, windowId);

        this.blockEntity = blockEntity;
        this.blockEntity.startOpen(playerInventory.player);
        this.canInteract = ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos());
        this.nonPlayerSlotCount = nonPlayerSlotCount;
    }

    public Container getTileInventory() {

        return blockEntity;
    }

    protected int generateStorageSlots(int startIndex, int rows, int columns, int startX, int startY, int spacing) {

        int index = startIndex;

        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < columns; col++) {
                index = startIndex + ((row * columns) + col);
                this.addSlot(new Slot(blockEntity, index, startX + (col * spacing), startY + (row * spacing)));
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

        this.blockEntity.stopOpen(playerIn);
    }
}
