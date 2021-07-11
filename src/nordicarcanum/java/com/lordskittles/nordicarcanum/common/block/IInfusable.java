package com.lordskittles.nordicarcanum.common.block;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public interface IInfusable {

    default boolean isValid(IWorldReader world, BlockPos pos, BlockPos right, BlockState state) { return true; }

    void infuse(World world, BlockPos pos, BlockPos right, BlockState state, Direction direction);

    BlockPos[] getInfusedPositions(World world, BlockPos pos, BlockPos right, BlockState state, Direction direction);
}
