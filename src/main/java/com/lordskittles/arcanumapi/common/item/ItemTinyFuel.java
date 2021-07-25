package com.lordskittles.arcanumapi.common.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ItemTinyFuel extends ItemMod {

    public ItemTinyFuel() {

        super(new Item.Properties().tab(CreativeModeTab.TAB_MISC));
    }

    public int getBurnTime(ItemStack itemStack) {

        return 200;
    }
}
