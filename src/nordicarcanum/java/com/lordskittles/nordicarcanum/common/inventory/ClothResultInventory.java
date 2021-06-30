package com.lordskittles.nordicarcanum.common.inventory;

import com.lordskittles.nordicarcanum.common.inventory.crafting.CraftingClothRecipe;
import com.lordskittles.nordicarcanum.common.tileentity.crafting.TileEntityCraftingCloth;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftResultInventory;

import javax.annotation.Nonnull;

public class ClothResultInventory extends CraftResultInventory
{
    private final TileEntityCraftingCloth tile;

    public ClothResultInventory(TileEntityCraftingCloth tile)
    {
        super();

        this.tile = tile;
    }

    @Override
    public void onCrafting(@Nonnull PlayerEntity player)
    {
        if (this.getRecipeUsed() instanceof CraftingClothRecipe)
        {
            if (!this.tile.getWorld().isRemote)
            {
                CraftingClothRecipe clothRecipe = (CraftingClothRecipe) this.getRecipeUsed();
                this.tile.useArcanum(player, clothRecipe.getArcanum());
            }
        }

        super.onCrafting(player);
    }
}
