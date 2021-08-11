package com.lordskittles.nordicarcanum.common.block.world;

import com.lordskittles.arcanumapi.common.block.IItemGroupHolder;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicResourcesItemGroup;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;

public class BlockLeaves extends LeavesBlock implements IItemGroupHolder {

    public BlockLeaves() {

        super(Block.Properties.copy(Blocks.OAK_LEAVES));
    }

    @Override
    public CreativeModeTab group() {

        return NordicResourcesItemGroup.INSTANCE;
    }
}
