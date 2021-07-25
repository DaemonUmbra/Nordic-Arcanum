package com.lordskittles.nordicarcanum.common.inventory;

import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.AbstractContainerMenu;

public class ClothInventory extends CraftingContainer {

    public ClothInventory(AbstractContainerMenu eventHandlerIn) {

        super(eventHandlerIn, 3, 3);
    }
}
