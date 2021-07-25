package com.lordskittles.nordicarcanum.common.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;

public interface IInfusable {

    default boolean isValid(LevelReader world, BlockPos pos, BlockPos right, BlockState state) { return true; }

    void infuse(Level world, BlockPos pos, BlockPos right, BlockState state, Direction direction);

    BlockPos[] getInfusedPositions(Level world, BlockPos pos, BlockPos right, BlockState state, Direction direction);
}
