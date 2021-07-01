package com.lordskittles.arcanumapi.common.utilities;

import net.minecraft.inventory.IInventory;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.InvWrapper;

public class InventoryUtilities {

    public static IItemHandlerModifiable createHandler(IInventory inventory) {

        return new InvWrapper(inventory);
    }
}
