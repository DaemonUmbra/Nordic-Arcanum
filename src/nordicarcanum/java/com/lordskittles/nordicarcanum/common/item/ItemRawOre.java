package com.lordskittles.nordicarcanum.common.item;

import com.lordskittles.arcanumapi.common.item.ItemMod;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicWorldItemGroup;
import net.minecraft.item.Item;

public class ItemRawOre extends ItemMod
{
    public ItemRawOre()
    {
        super(new Item.Properties().group(NordicWorldItemGroup.INSTANCE));
    }
}
