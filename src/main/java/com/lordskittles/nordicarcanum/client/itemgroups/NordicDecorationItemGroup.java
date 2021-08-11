package com.lordskittles.nordicarcanum.client.itemgroups;

import com.lordskittles.nordicarcanum.common.registry.Blocks;
import com.lordskittles.nordicarcanum.data.ICreativeTabDataProvider;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class NordicDecorationItemGroup extends CreativeModeTab implements ICreativeTabDataProvider {

    private static final String UNLOCALIZED = "nordicdecorationtab";

    public static final NordicDecorationItemGroup INSTANCE = new NordicDecorationItemGroup(CreativeModeTab.TABS.length, UNLOCALIZED);

    private NordicDecorationItemGroup(int index, String label) {

        super(index, label);
    }

    @Override
    public ItemStack makeIcon() {

        return new ItemStack(Blocks.feldspar_brick.get());
    }

    @Override
    public String getPrettyName() {

        return "Nordic Arcanum: Decoration";
    }

    @Override
    public String getUnlocalizedName() {

        return UNLOCALIZED;
    }
}
