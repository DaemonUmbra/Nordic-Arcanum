package com.lordskittles.nordicarcanum.common.block.decoration;

import com.lordskittles.arcanumapi.common.block.IItemGroupHolder;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicDecorationItemGroup;
import net.minecraft.block.Blocks;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.item.ItemGroup;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class BlockFence extends FenceBlock implements IItemGroupHolder {

    public BlockFence() {

        super(Properties.from(Blocks.OAK_FENCE));
    }

    @Override
    public ItemGroup group() {

        return NordicDecorationItemGroup.INSTANCE;
    }
}
