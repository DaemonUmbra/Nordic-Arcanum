package com.lordskittles.nordicarcanum.common.item.crafting;

import com.lordskittles.arcanumapi.common.item.ItemMod;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemMortarPestle extends ItemMod
{
    public ItemMortarPestle()
    {
        super(new Item.Properties().group(NordicItemGroup.INSTANCE).maxStackSize(1));
    }

    @Override
    public boolean hasContainerItem(ItemStack stack)
    {
        return true;
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack)
    {
        return new ItemStack(this);
    }
}
