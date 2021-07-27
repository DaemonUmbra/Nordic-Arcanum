package com.lordskittles.nordicarcanum.common.item.resource;

import com.lordskittles.arcanumapi.common.item.ItemMod;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicResourcesItemGroup;
import net.minecraft.world.item.Item;

public class ItemRawOre extends ItemMod {

    public ItemRawOre() {

        super(new Item.Properties().tab(NordicResourcesItemGroup.INSTANCE));
    }
}
