package com.lordskittles.nordicarcanum.common.inventory.containers;

import com.lordskittles.nordicarcanum.common.inventory.ClothResultInventory;
import com.lordskittles.nordicarcanum.common.inventory.crafting.CraftingClothRecipe;
import com.lordskittles.nordicarcanum.common.utility.RecipeUtilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.container.CraftingResultSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.NonNullList;

import javax.annotation.Nonnull;

public class ClothResultSlot extends CraftingResultSlot {

    public ClothResultSlot(PlayerEntity player, CraftingInventory craftingInventory, ClothResultInventory result, int slotIndex, int xPosition, int yPosition) {

        super(player, craftingInventory, result, slotIndex, xPosition, yPosition);
    }

    @Nonnull
    @Override
    public ItemStack onTake(@Nonnull PlayerEntity thePlayer, @Nonnull ItemStack stack) {

        this.onCrafting(stack);
        net.minecraftforge.common.ForgeHooks.setCraftingPlayer(thePlayer);
        NonNullList<ItemStack> inventoryItems = thePlayer.world.getRecipeManager().getRecipeNonNull(IRecipeType.CRAFTING, this.craftMatrix, thePlayer.world);
        if(! inventoryItems.isEmpty()) {
            CraftingClothRecipe recipe = RecipeUtilities.getClothRecipeFor(thePlayer.world, this.craftMatrix, this.player);
            if(recipe != null) {
                inventoryItems.clear();
            }

            recipe = RecipeUtilities.getClothRecipeForNoArcanum(thePlayer.world, this.craftMatrix, this.player);
            if(recipe != null) {
                inventoryItems.clear();
            }
        }

        net.minecraftforge.common.ForgeHooks.setCraftingPlayer(null);
        for(int i = 0; i < inventoryItems.size(); ++ i) {
            ItemStack itemstack = this.craftMatrix.getStackInSlot(i);
            ItemStack inventoryStack = inventoryItems.get(i);
            if(! itemstack.isEmpty()) {
                this.craftMatrix.decrStackSize(i, 1);
                itemstack = this.craftMatrix.getStackInSlot(i);
            }

            if(! inventoryStack.isEmpty()) {
                if(itemstack.isEmpty()) {
                    this.craftMatrix.setInventorySlotContents(i, inventoryStack);
                }
                else
                    if(ItemStack.areItemsEqual(itemstack, inventoryStack) && ItemStack.areItemStackTagsEqual(itemstack, inventoryStack)) {
                        inventoryStack.grow(itemstack.getCount());
                        this.craftMatrix.setInventorySlotContents(i, inventoryStack);
                    }
                    else
                        if(! this.player.inventory.addItemStackToInventory(inventoryStack)) {
                            this.player.dropItem(inventoryStack, false);
                        }
            }
        }

        return stack;
    }
}
