package com.lordskittles.arcanumapi.common.utilities;

import java.util.Collection;

import net.minecraft.block.BlockState;
import net.minecraft.state.Property;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

public class BlockUtilities
{
	@SuppressWarnings("rawtypes")
	public static Property getAxisProperty(BlockState log)
	{
		for (Property property : log.getProperties())
		{
			Collection allowedValues = property.getAllowedValues();
			if (allowedValues.contains(Direction.Axis.X) && allowedValues.contains(Direction.Axis.Y) && allowedValues.contains(Direction.Axis.Z))
			{
				return property;
			}
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public static Property getPersistantProperty(BlockState leaves)
	{
		for	(Property property : leaves.getProperties())
		{
			Collection allowedValues = property.getAllowedValues();
			if (allowedValues.contains(Boolean.valueOf(true)) && allowedValues.contains(Boolean.valueOf(false)))
			{
				return property;
			}
		}

		return null;
	}

	public static BlockPos getLeftPos(Direction direction, BlockPos pos)
	{
		BlockPos left = pos;

		switch (direction)
		{
			case NORTH:
				left = pos.north();
				break;
			case WEST:
				left = pos.west();
				break;
			case EAST:
				left = pos.east();
				break;
			case SOUTH:
				left = pos.south();
				break;
			default:
				break;
		}

		return left;
	}

	public static BlockPos getRightPos(Direction direction, BlockPos pos)
	{
		BlockPos right = pos;

		switch (direction)
		{
			case NORTH:
				right = pos.south();
				break;
			case WEST:
				right = pos.east();
				break;
			case EAST:
				right = pos.west();
				break;
			case SOUTH:
				right = pos.north();
				break;
			default:
				break;
		}

		return right;
	}
}
