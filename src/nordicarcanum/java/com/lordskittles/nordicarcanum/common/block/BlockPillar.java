package com.lordskittles.nordicarcanum.common.block;

import com.lordskittles.nordicarcanum.client.itemgroups.NordicWorldItemGroup;
import com.lordskittles.arcanumapi.common.block.IItemGroupHolder;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.item.ItemGroup;

public class BlockPillar extends RotatedPillarBlock implements IItemGroupHolder
{
    public BlockPillar()
    {
        super(Block.Properties.from(Blocks.STONE));
    }

    @Override
    public ItemGroup group()
    {
        return NordicWorldItemGroup.INSTANCE;
    }
}
