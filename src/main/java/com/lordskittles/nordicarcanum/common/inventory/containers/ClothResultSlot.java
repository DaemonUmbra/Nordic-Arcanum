package com.lordskittles.nordicarcanum.common.inventory.containers;

import com.lordskittles.nordicarcanum.common.inventory.ClothResultInventory;
import com.lordskittles.nordicarcanum.common.inventory.crafting.CraftingClothRecipe;
import com.lordskittles.nordicarcanum.common.registry.RecipeType;
import com.lordskittles.nordicarcanum.common.utility.RecipeUtilities;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.ResultSlot;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;

public class ClothResultSlot extends ResultSlot {

    public ClothResultSlot(Player player, CraftingContainer craftingInventory, ClothResultInventory result, int slotIndex, int xPosition, int yPosition) {

        super(player, craftingInventory, result, slotIndex, xPosition, yPosition);
    }

    @Nonnull
    @Override
    public void onTake(@Nonnull Player thePlayer, @Nonnull ItemStack stack) {

//        this.onQuickCraft(stack);
        net.minecraftforge.common.ForgeHooks.setCraftingPlayer(thePlayer);
        NonNullList<ItemStack> inventoryItems = thePlayer.level.getRecipeManager().getRemainingItemsFor(RecipeType.CRAFTING, this.craftSlots, thePlayer.level);
        if(! inventoryItems.isEmpty()) {
            CraftingClothRecipe recipe = RecipeUtilities.getClothRecipeFor(thePlayer.level, this.craftSlots, this.player);
            if(recipe != null) {
                inventoryItems.clear();
            }

            recipe = RecipeUtilities.getClothRecipeForNoArcanum(thePlayer.level, this.craftSlots, this.player);
            if(recipe != null) {
                inventoryItems.clear();
            }
        }

        net.minecraftforge.common.ForgeHooks.setCraftingPlayer(null);
        for(int i = 0; i < inventoryItems.size(); ++ i) {
            ItemStack itemstack = this.craftSlots.getItem(i);
            ItemStack inventoryStack = inventoryItems.get(i);
            if(! itemstack.isEmpty()) {
                this.craftSlots.removeItem(i, 1);
                itemstack = this.craftSlots.getItem(i);
            }

            if(! inventoryStack.isEmpty()) {
                if(itemstack.isEmpty()) {
                    this.craftSlots.setItem(i, inventoryStack);
                }
                else if(ItemStack.isSame(itemstack, inventoryStack) && ItemStack.tagMatches(itemstack, inventoryStack)) {
                    inventoryStack.grow(itemstack.getCount());
                    this.craftSlots.setItem(i, inventoryStack);
                }
                else if(! this.player.getInventory().add(inventoryStack)) {
                    this.player.drop(inventoryStack, false);
                }
            }
        }
    }
}
