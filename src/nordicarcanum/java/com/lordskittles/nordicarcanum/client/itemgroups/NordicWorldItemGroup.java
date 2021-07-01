package com.lordskittles.nordicarcanum.client.itemgroups;

import com.lordskittles.nordicarcanum.common.registry.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class NordicWorldItemGroup extends ItemGroup {

    public static final NordicWorldItemGroup INSTANCE = new NordicWorldItemGroup(ItemGroup.GROUPS.length, "nordicworldtab");

    private NordicWorldItemGroup(int index, String label) {

        super(index, label);
    }

    @Override
    public ItemStack createIcon() {

        return new ItemStack(Blocks.arcane_dust_ore.get());
    }
}
