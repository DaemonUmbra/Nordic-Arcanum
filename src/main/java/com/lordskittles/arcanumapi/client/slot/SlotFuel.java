package com.lordskittles.arcanumapi.client.slot;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.common.ForgeHooks;

public class SlotFuel extends Slot {

    private final RecipeType<?> recipeType;

    public SlotFuel(Container inventoryIn, int index, int xPosition, int yPosition, RecipeType<?> recipeType) {

        super(inventoryIn, index, xPosition, yPosition);

        this.recipeType = recipeType;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {

        return ForgeHooks.getBurnTime(stack, null) > 0;
    }
}
