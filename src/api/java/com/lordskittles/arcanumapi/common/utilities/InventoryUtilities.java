package com.lordskittles.arcanumapi.common.utilities;

import net.minecraft.world.Container;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.InvWrapper;

public class InventoryUtilities {

    public static IItemHandlerModifiable createHandler(Container inventory) {

        return new InvWrapper(inventory);
    }
}
