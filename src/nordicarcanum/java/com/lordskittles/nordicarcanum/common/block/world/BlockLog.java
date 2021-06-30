package com.lordskittles.nordicarcanum.common.block.world;

import com.lordskittles.nordicarcanum.client.itemgroups.NordicWorldItemGroup;
import com.lordskittles.arcanumapi.common.block.IItemGroupHolder;
import com.lordskittles.nordicarcanum.common.registry.Blocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class BlockLog extends RotatedPillarBlock implements IItemGroupHolder
{
	public BlockLog()
	{
		super(Block.Properties.from(net.minecraft.block.Blocks.OAK_LOG));
	}

	@Override
	public ItemGroup group()
	{
		return NordicWorldItemGroup.INSTANCE;
	}

	@Nullable
	@Override
	public BlockState getToolModifiedState(BlockState state, World world, BlockPos pos, PlayerEntity player, ItemStack stack, ToolType toolType)
	{
		if(toolType == ToolType.AXE && state.getBlock() instanceof BlockLog)
		{
			BlockLog log = (BlockLog) (state.getBlock());
			Direction.Axis axis = state.get(AXIS);
			if(log == Blocks.yew_log.get())
			{
				return Blocks.yew_log_stripped.get().getDefaultState().with(AXIS, axis);
			}
			else if(log == Blocks.pine_log.get())
			{
				return Blocks.pine_log_stripped.get().getDefaultState().with(AXIS, axis);
			}
			else if(log == Blocks.juniper_log.get())
			{
				return Blocks.juniper_log_stripped.get().getDefaultState().with(AXIS, axis);
			}
		}

		return super.getToolModifiedState(state, world, pos, player, stack, toolType);
	}
}
