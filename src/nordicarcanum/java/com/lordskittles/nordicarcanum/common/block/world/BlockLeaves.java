package com.lordskittles.nordicarcanum.common.block.world;

import com.lordskittles.arcanumapi.common.block.IItemGroupHolder;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicResourcesItemGroup;
import net.minecraft.world.level.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.item.ItemGroup;

public class BlockLeaves extends LeavesBlock implements IItemGroupHolder {

    public BlockLeaves() {

        super(Block.Properties.from(Blocks.OAK_LEAVES));
    }

    @Override
    public ItemGroup group() {

        return NordicResourcesItemGroup.INSTANCE;
    }
}
