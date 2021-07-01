package com.lordskittles.arcanumapi.client.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SlotFiltered extends Slot {

    private Item item;
    private int size;

    public SlotFiltered(Item item, int size, IInventory inventory, int index, int xPosition, int yPosition) {

        super(inventory, index, xPosition, yPosition);

        this.item = item;
        this.size = size;
    }

    @Override
    public boolean isItemValid(ItemStack stack) {

        return stack.getItem().equals(this.item);
    }

    @Override
    public int getItemStackLimit(ItemStack stack) {

        return this.size;
    }
}
