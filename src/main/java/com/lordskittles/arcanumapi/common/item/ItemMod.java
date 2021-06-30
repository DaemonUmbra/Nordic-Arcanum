package com.lordskittles.arcanumapi.common.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ItemMod extends Item
{
	public ItemMod(Properties properties)
	{
		super(properties);
	}

	public ItemMod(ItemGroup group)
	{
		super(new Properties().group(group));
	}
}
