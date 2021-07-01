package com.lordskittles.nordicarcanum.common.inventory;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.container.Container;

public class ClothInventory extends CraftingInventory {

    public ClothInventory(Container eventHandlerIn) {

        super(eventHandlerIn, 3, 3);
    }
}
