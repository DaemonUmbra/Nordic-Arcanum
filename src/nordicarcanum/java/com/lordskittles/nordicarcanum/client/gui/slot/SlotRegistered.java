package com.lordskittles.nordicarcanum.client.gui.slot;

import com.lordskittles.nordicarcanum.common.NordicRegistry;
import com.lordskittles.nordicarcanum.common.RegistryType;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class SlotRegistered extends Slot
{
    private RegistryType type;

    public SlotRegistered(RegistryType type, IInventory inventory, int index, int xPos, int yPos)
    {
        super(inventory, index, xPos, yPos);

        this.type = type;
    }

    @Override
    public boolean isItemValid(ItemStack stack)
    {
        return NordicRegistry.isItemValid(stack.getItem(), type);
    }

    @Override
    public int getSlotStackLimit()
    {
        return 1;
    }
}
