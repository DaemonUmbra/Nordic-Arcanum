package com.lordskittles.nordicarcanum.common.item.crafting;

import com.lordskittles.arcanumapi.common.item.ItemMod;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicItemGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ItemMortarPestle extends ItemMod {

    public ItemMortarPestle() {

        super(new Item.Properties().tab(NordicItemGroup.INSTANCE).stacksTo(1));
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {

        return true;
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {

        return new ItemStack(this);
    }
}
