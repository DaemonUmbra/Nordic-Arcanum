package com.lordskittles.arcanumapi.client.slot;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;

public class SlotFuel extends Slot {

    private RecipeType<?> recipeType;

    public SlotFuel(Container inventoryIn, int index, int xPosition, int yPosition, RecipeType<?> recipeType) {

        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {

        return stack.getBurnTime(recipeType) > 0;
    }
}
