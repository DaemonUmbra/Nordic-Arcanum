package com.lordskittles.nordicarcanum.common.block.decoration;

import com.lordskittles.arcanumapi.common.block.IItemGroupHolder;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicDecorationItemGroup;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicResourcesItemGroup;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.block.Blocks;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.item.ItemGroup;

public class BlockWall extends WallBlock implements IItemGroupHolder {

    public BlockWall() {

        super(BlockBehaviour.Properties.from(Blocks.STONE_BRICK_WALL));
    }

    @Override
    public ItemGroup group() {

        return NordicDecorationItemGroup.INSTANCE;
    }
}
