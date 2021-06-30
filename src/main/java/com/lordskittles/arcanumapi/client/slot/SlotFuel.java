package com.lordskittles.arcanumapi.client.slot;

import com.lordskittles.arcanumapi.common.utilities.ItemUtilities;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class SlotFuel extends Slot
{
    public SlotFuel(IInventory inventoryIn, int index, int xPosition, int yPosition)
    {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack)
    {
        return ItemUtilities.isFuel(stack);
    }
}
