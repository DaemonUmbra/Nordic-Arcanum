package com.lordskittles.nordicarcanum.common.utility;

import com.lordskittles.nordicarcanum.common.inventory.crafting.CraftingClothRecipe;
import com.lordskittles.nordicarcanum.common.registry.RecipeType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.level.Level;

public class RecipeUtilities {

    public static CraftingClothRecipe getClothRecipeFor(Level world, CraftingContainer inventory, Player player) {

        return RecipeType.crafting_cloth.findFirst(world, recipe -> recipe.matches(inventory, player));
    }

    public static CraftingClothRecipe getClothRecipeForNoArcanum(Level world, CraftingContainer inventory, Player player) {

        return RecipeType.crafting_cloth.findFirst(world, recipe -> recipe.matchesNoArcanum(inventory, player));
    }
}
