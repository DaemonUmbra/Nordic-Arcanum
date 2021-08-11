package com.lordskittles.arcanumapi.common.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;

import net.minecraft.world.item.Item.Properties;

public class ItemMod extends Item {

    public ItemMod(Properties properties) {

        super(properties);
    }

    public ItemMod(CreativeModeTab group) {

        super(new Properties().tab(group));
    }
}
