package com.lordskittles.nordicarcanum.common.inventory;

import com.lordskittles.nordicarcanum.common.inventory.crafting.CraftingClothRecipe;
import com.lordskittles.nordicarcanum.common.blockentity.crafting.BlockEntityCraftingCloth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ResultContainer;

import javax.annotation.Nonnull;

public class ClothResultInventory extends ResultContainer {

    private final BlockEntityCraftingCloth tile;

    public ClothResultInventory(BlockEntityCraftingCloth tile) {

        super();

        this.tile = tile;
    }

    @Override
    public void awardUsedRecipes(@Nonnull Player player) {

        if(this.getRecipeUsed() instanceof CraftingClothRecipe) {
            if(! this.tile.getLevel().isClientSide) {
                CraftingClothRecipe clothRecipe = (CraftingClothRecipe) this.getRecipeUsed();
                this.tile.useArcanum(player, clothRecipe.getArcanum());
            }
        }

        super.awardUsedRecipes(player);
    }
}
