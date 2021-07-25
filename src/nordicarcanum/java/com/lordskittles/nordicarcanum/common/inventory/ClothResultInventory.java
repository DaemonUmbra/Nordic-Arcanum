package com.lordskittles.nordicarcanum.common.inventory;

import com.lordskittles.nordicarcanum.common.inventory.crafting.CraftingClothRecipe;
import com.lordskittles.nordicarcanum.common.tileentity.crafting.TileEntityCraftingCloth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ResultContainer;

import javax.annotation.Nonnull;

public class ClothResultInventory extends ResultContainer {

    private final TileEntityCraftingCloth tile;

    public ClothResultInventory(TileEntityCraftingCloth tile) {

        super();

        this.tile = tile;
    }

    @Override
    public void onCrafting(@Nonnull Player player) {

        if(this.getRecipeUsed() instanceof CraftingClothRecipe) {
            if(! this.tile.getWorld().isRemote) {
                CraftingClothRecipe clothRecipe = (CraftingClothRecipe) this.getRecipeUsed();
                this.tile.useArcanum(player, clothRecipe.getArcanum());
            }
        }

        super.onCrafting(player);
    }
}
