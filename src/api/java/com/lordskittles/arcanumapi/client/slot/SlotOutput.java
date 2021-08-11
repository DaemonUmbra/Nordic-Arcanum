package com.lordskittles.arcanumapi.client.slot;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class SlotOutput extends Slot {

    public SlotOutput(Container inventory, int index, int xPos, int yPos) {

        super(inventory, index, xPos, yPos);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {

        return false;
    }
}
