package com.lordskittles.nordicarcanum.client.itemgroups;

import com.lordskittles.nordicarcanum.common.registry.Items;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class NordicItemGroup extends ItemGroup {

    public static final NordicItemGroup INSTANCE = new NordicItemGroup(ItemGroup.GROUPS.length, "nordictab");

    private NordicItemGroup(int index, String label) {

        super(index, label);
    }

    @Override
    public ItemStack createIcon() {

        return new ItemStack(Items.crafting_cloth_item.get());
    }
}
