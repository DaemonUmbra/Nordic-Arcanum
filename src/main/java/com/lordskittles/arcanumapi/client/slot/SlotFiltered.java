package com.lordskittles.arcanumapi.client.slot;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class SlotFiltered extends Slot {

    private Item item;
    private int size;

    public SlotFiltered(Item item, int size, Container inventory, int index, int xPosition, int yPosition) {

        super(inventory, index, xPosition, yPosition);

        this.item = item;
        this.size = size;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {

        return stack.getItem().equals(this.item);
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {

        return this.size;
    }
}
