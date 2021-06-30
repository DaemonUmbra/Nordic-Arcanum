package com.lordskittles.arcanumapi.common.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ItemTinyFuel extends ItemMod
{
    public ItemTinyFuel()
    {
        super(new Item.Properties().group(ItemGroup.MISC));
    }

    @Override
    public int getBurnTime(ItemStack itemStack)
    {
        return 200;
    }
}
