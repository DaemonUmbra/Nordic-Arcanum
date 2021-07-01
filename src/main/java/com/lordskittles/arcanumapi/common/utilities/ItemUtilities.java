package com.lordskittles.arcanumapi.common.utilities;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.common.ForgeHooks;

public class ItemUtilities {

    public static boolean isFuel(ItemStack stack) {

        return ForgeHooks.getBurnTime(stack) > 0;
    }

    public static boolean isEmptyOrAir(ItemStack stack) {

        return stack.isEmpty() || stack.getItem() == Items.AIR;
    }

    public static ItemStack deepCopy(ItemStack input) {

        ItemStack stack = input.copy();

        if(input.isDamaged()) {
            stack.setDamage(input.getDamage());
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
