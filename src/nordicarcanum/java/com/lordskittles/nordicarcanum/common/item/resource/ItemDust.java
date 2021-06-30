package com.lordskittles.nordicarcanum.common.item.resource;

import com.lordskittles.arcanumapi.common.item.ItemMod;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicItemGroup;
import net.minecraft.item.ItemGroup;

public class ItemDust extends ItemMod
{
	public ItemDust()
	{		
		super(NordicItemGroup.INSTANCE);
	}

	public ItemDust(ItemGroup overrideGroup)
	{
		super(overrideGroup);
	}
}
