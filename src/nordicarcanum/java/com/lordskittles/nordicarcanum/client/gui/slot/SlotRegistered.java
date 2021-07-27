package com.lordskittles.nordicarcanum.client.gui.slot;

import com.lordskittles.nordicarcanum.common.NordicRegistry;
import com.lordskittles.nordicarcanum.common.RegistryType;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class SlotRegistered extends Slot {

    private RegistryType type;

    public SlotRegistered(RegistryType type, Container inventory, int index, int xPos, int yPos) {

        super(inventory, index, xPos, yPos);

        this.type = type;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {

        return NordicRegistry.isItemValid(stack.getItem(), type);
    }

    @Override
    public int getMaxStackSize() {

        return 1;
    }
}
