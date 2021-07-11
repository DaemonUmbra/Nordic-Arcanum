package com.lordskittles.nordicarcanum.common.block.decoration;

import com.lordskittles.arcanumapi.common.block.IItemGroupHolder;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicResourcesItemGroup;
import net.minecraft.block.Blocks;
import net.minecraft.block.FenceBlock;
import net.minecraft.item.ItemGroup;

public class BlockFence extends FenceBlock implements IItemGroupHolder {

    public BlockFence() {

        super(Properties.from(Blocks.OAK_FENCE));
    }

    @Override
    public ItemGroup group() {

        return NordicResourcesItemGroup.INSTANCE;
    }
}
