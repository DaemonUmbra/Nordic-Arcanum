package com.lordskittles.arcanumapi.common.utilities;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.common.ForgeHooks;

public class ItemUtilities {

    public static boolean isFuel(ItemStack stack, RecipeType<?> recipeType) {

        return ForgeHooks.getBurnTime(stack, recipeType) > 0;
    }

    public static boolean isEmptyOrAir(ItemStack stack) {

        return stack.isEmpty() || stack.getItem() == Items.AIR;
    }

    public static ItemStack deepCopy(ItemStack input) {

        ItemStack stack = input.copy();

        if(input.isDamaged()) {
            stack.setDamageValue(input.getDamageValue());
        }

        return stack;
    }

    public static ItemStack decreasedDeepCopy(ItemStack input, int reduction) {

        ItemStack stack = deepCopy(input);
        stack.shrink(reduction);

        return stack;
    }

    public static ItemStack singleDeepCopy(ItemStack input) {

        ItemStack stack = deepCopy(input);

        stack.shrink(stack.getCount() - 1);

        return stack;
    }
}
