package com.lordskittles.nordicarcanum.client.itemgroups;

import com.lordskittles.nordicarcanum.common.registry.Blocks;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class NordicDecorationItemGroup extends CreativeModeTab {

    public static final NordicDecorationItemGroup INSTANCE = new NordicDecorationItemGroup(CreativeModeTab.TABS.length, "nordicdecorationtab");

    private NordicDecorationItemGroup(int index, String label) {

        super(index, label);
    }

    @Override
    public ItemStack makeIcon() {

        return new ItemStack(Blocks.feldspar_brick.get());
    }
}
