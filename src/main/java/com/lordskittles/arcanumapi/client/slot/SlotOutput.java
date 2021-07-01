package com.lordskittles.arcanumapi.client.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class SlotOutput extends Slot {

    public SlotOutput(IInventory inventory, int index, int xPos, int yPos) {

        super(inventory, index, xPos, yPos);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {

        return false;
    }
}
