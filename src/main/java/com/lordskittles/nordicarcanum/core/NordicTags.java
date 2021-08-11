package com.lordskittles.nordicarcanum.core;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;

public class NordicTags {

    public static final String STAFF_CORE = "core";
    public static final String STAFF_BINDING = "binding";
    public static final String STAFF_CRYSTAL = "crystal";
    public static final String STAFF_ROD = "rod";

    public static final String HEAT_SOURCE = "heat_source";

    public static boolean IsBlockInTag(BlockState state, String tag) {

        return BlockTags.getAllTags().getTag(NordicArcanum.RL(tag)).contains(state.getBlock());
    }

    public static boolean IsItemInTag(Item item, String tag) {

        return ItemTags.getAllTags().getTag(NordicArcanum.RL(tag)).contains(item);
    }
}
