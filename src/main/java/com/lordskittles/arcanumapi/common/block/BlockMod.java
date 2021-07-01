package com.lordskittles.arcanumapi.common.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;

public class BlockMod extends Block implements IItemGroupHolder {

    protected ItemGroup group;

    public BlockMod(AbstractBlock.Properties properties, int lightLevel) {

        super(properties.setLightLevel((state) -> lightLevel));
    }

    public BlockMod(Properties properties) {

        super(properties);
    }

    public ItemGroup group() {

        return group;
    }
}
