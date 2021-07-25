package com.lordskittles.arcanumapi.common.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.CreativeModeTab;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class BlockMod extends Block implements IItemGroupHolder {

    protected CreativeModeTab group;

    public BlockMod(BlockBehaviour.Properties properties, int lightLevel) {

        super(properties.lightLevel((state) -> lightLevel));
    }

    public BlockMod(Properties properties) {

        super(properties);
    }

    public CreativeModeTab group() {

        return group;
    }
}
