package com.lordskittles.nordicarcanum.client.itemgroups;

import com.lordskittles.nordicarcanum.common.registry.Blocks;
import com.lordskittles.nordicarcanum.common.registry.Items;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.item.ItemStack;

public class NordicDecorationItemGroup extends CreativeModeTab {

    public static final NordicDecorationItemGroup INSTANCE = new NordicDecorationItemGroup(ItemGroup.GROUPS.length, "nordicdecorationtab");

    private NordicDecorationItemGroup(int index, String label) {

        super(index, label);
    }

    @Override
    public ItemStack createIcon() {

        return new ItemStack(Blocks.feldspar_brick.get());
    }
}
