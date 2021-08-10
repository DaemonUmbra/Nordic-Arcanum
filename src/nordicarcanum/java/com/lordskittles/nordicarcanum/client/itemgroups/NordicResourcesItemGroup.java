package com.lordskittles.nordicarcanum.client.itemgroups;

import com.lordskittles.nordicarcanum.common.registry.Blocks;
import com.lordskittles.nordicarcanum.data.ICreativeTabDataProvider;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class NordicResourcesItemGroup extends CreativeModeTab implements ICreativeTabDataProvider {

    private static final String UNLOCALIZED = "nordicresourcestab";

    public static final NordicResourcesItemGroup INSTANCE = new NordicResourcesItemGroup(CreativeModeTab.TABS.length, UNLOCALIZED);

    private NordicResourcesItemGroup(int index, String label) {

        super(index, label);
    }

    @Override
    public ItemStack makeIcon() {

        return new ItemStack(Blocks.arcane_powder_ore.get());
    }

    @Override
    public String getPrettyName() {

        return "Nordic Arcanum: Resources";
    }

    @Override
    public String getUnlocalizedName() {

        return UNLOCALIZED;
    }
}
