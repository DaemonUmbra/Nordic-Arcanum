package com.lordskittles.nordicarcanum.common.utility;

import com.lordskittles.nordicarcanum.common.inventory.crafting.CraftingClothRecipe;
import com.lordskittles.nordicarcanum.common.registry.RecipeType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.world.World;

public class RecipeUtilities {

    public static CraftingClothRecipe getClothRecipeFor(World world, CraftingInventory inventory, PlayerEntity player) {

        return RecipeType.crafting_cloth.findFirst(world, recipe -> recipe.matches(inventory, player));
    }

    public static CraftingClothRecipe getClothRecipeForNoArcanum(World world, CraftingInventory inventory, PlayerEntity player) {

        return RecipeType.crafting_cloth.findFirst(world, recipe -> recipe.matchesNoArcanum(inventory, player));
    }
}
