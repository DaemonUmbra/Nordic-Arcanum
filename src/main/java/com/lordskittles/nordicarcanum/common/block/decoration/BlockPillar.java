package com.lordskittles.nordicarcanum.common.block.decoration;

import com.lordskittles.arcanumapi.common.block.IItemGroupHolder;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicDecorationItemGroup;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;

public class BlockPillar extends RotatedPillarBlock implements IItemGroupHolder {

    public BlockPillar() {

        super(Block.Properties.copy(Blocks.STONE));
    }

    @Override
    public CreativeModeTab group() {

        return NordicDecorationItemGroup.INSTANCE;
    }
}
