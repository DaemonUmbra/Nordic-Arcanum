package com.lordskittles.nordicarcanum.client.itemgroups;

import com.lordskittles.nordicarcanum.common.registry.Blocks;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.item.ItemStack;

public class NordicResourcesItemGroup extends CreativeModeTab {

    public static final NordicResourcesItemGroup INSTANCE = new NordicResourcesItemGroup(ItemGroup.GROUPS.length, "nordicresourcestab");

    private NordicResourcesItemGroup(int index, String label) {

        super(index, label);
    }

    @Override
    public ItemStack createIcon() {

        return new ItemStack(Blocks.arcane_powder_ore.get());
    }
}
