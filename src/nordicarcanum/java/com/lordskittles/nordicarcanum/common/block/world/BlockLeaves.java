package com.lordskittles.nordicarcanum.common.block.world;

import com.lordskittles.nordicarcanum.client.itemgroups.NordicWorldItemGroup;
import com.lordskittles.arcanumapi.common.block.IItemGroupHolder;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.item.ItemGroup;

public class BlockLeaves extends LeavesBlock implements IItemGroupHolder
{
	public BlockLeaves()
	{
		super(Block.Properties.from(Blocks.OAK_LEAVES));
	}

	@Override
	public ItemGroup group()
	{
		return NordicWorldItemGroup.INSTANCE;
	}
}
