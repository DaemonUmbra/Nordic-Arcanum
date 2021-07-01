package com.lordskittles.nordicarcanum.core;

import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;

public class NordicTags {

    public static final String STAFF_CORE = "core";
    public static final String STAFF_BINDING = "binding";
    public static final String STAFF_CRYSTAL = "crystal";
    public static final String STAFF_ROD = "rod";

    public static final String HEAT_SOURCE = "heat_source";

    public static boolean IsBlockInTag(BlockState state, String tag) {

        return BlockTags.getCollection().get(NordicArcanum.RL(tag)).contains(state.getBlock());
    }

    public static boolean IsItemInTag(Item item, String tag) {

        return ItemTags.getCollection().get(NordicArcanum.RL(tag)).contains(item);
    }
}
