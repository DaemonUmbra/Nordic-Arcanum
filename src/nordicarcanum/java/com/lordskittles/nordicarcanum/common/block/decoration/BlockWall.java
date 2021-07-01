package com.lordskittles.nordicarcanum.common.block.decoration;

import com.lordskittles.arcanumapi.common.block.IItemGroupHolder;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicWorldItemGroup;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.WallBlock;
import net.minecraft.item.ItemGroup;

public class BlockWall extends WallBlock implements IItemGroupHolder {

    public BlockWall() {

        super(AbstractBlock.Properties.from(Blocks.STONE_BRICK_WALL));
    }

    @Override
    public ItemGroup group() {

        return NordicWorldItemGroup.INSTANCE;
    }
}
