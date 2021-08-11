package com.lordskittles.nordicarcanum.client.itemgroups;

import com.lordskittles.nordicarcanum.common.registry.Items;
import com.lordskittles.nordicarcanum.data.ICreativeTabDataProvider;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class NordicItemGroup extends CreativeModeTab implements ICreativeTabDataProvider {

    private static final String UNLOCALIZED = "nordictab";

    public static final NordicItemGroup INSTANCE = new NordicItemGroup(CreativeModeTab.TABS.length, UNLOCALIZED);

    private NordicItemGroup(int index, String label) {

        super(index, label);
    }

    @Override
    public ItemStack makeIcon() {

        return new ItemStack(Items.crafting_cloth_item.get());
    }

    @Override
    public String getPrettyName() {

        return "Nordic Arcanum";
    }

    @Override
    public String getUnlocalizedName() {

        return UNLOCALIZED;
    }
}
