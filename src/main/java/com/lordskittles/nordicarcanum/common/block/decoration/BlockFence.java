package com.lordskittles.nordicarcanum.common.block.decoration;

import com.lordskittles.arcanumapi.common.block.IItemGroupHolder;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicDecorationItemGroup;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FenceBlock;

public class BlockFence extends FenceBlock implements IItemGroupHolder {

    public BlockFence() {

        super(Properties.copy(Blocks.OAK_FENCE));
    }

    @Override
    public CreativeModeTab group() {

        return NordicDecorationItemGroup.INSTANCE;
    }
}
