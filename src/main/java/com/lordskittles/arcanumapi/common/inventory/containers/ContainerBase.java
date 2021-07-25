package com.lordskittles.arcanumapi.common.inventory.containers;

import com.lordskittles.arcanumapi.client.slot.SlotOutput;
import com.lordskittles.arcanumapi.common.tileentity.TileEntityInventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.ContainerLevelAccess;

public abstract class ContainerBase<T extends TileEntityInventory> extends AbstractContainerMenu implements INordicContainer {

    public final T Tile;
    protected final ContainerLevelAccess canInteract;

    protected int nonPlayerSlotCount = 0;

    public ContainerBase(MenuType<?> container, final int nonPlayerSlotCount, final int windowId, final Inventory inventory, final T tile) {

        super(container, windowId);

        this.Tile = tile;
        this.canInteract = ContainerLevelAccess.create(Tile.getLevel(), Tile.getBlockPos());
        this.nonPlayerSlotCount = nonPlayerSlotCount;
    }

    public Container getTileInventory() {

        return Tile;
    }

    @Override
    public boolean stillValid(Player player) {

        return true;
    }

    protected void createSlot(Container inventory, int index, int xPos, int yPos) {

        this.addSlot(new Slot(inventory, index, xPos, yPos));
    }

    protected void createOutputSlot(Container inventory, int index, int xPos, int yPos) {

        this.addSlot(new SlotOutput(inventory, index, xPos, yPos));
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
                createSlot(inventory, index, startX + (col * interval), startY + (row * interval));
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
            createSlot(inventory, index, startX + (col * interval), startY);
        }

        return index;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {

        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if(slot != null && slot.hasItem()) {
            ItemStack itemStack = slot.getItem();
            stack = itemStack.copy();
            if(index < this.nonPlayerSlotCount) {
                if(! this.moveItemStackTo(itemStack, this.nonPlayerSlotCount, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            }
            else
                if(! this.moveItemStackTo(itemStack, 0, this.nonPlayerSlotCount, false)) {
                    return ItemStack.EMPTY;
                }

            if(itemStack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            }
            else {
                slot.setChanged();
            }
        }

        return stack;
    }
}
