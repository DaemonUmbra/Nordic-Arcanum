package com.lordskittles.nordicarcanum.common.block.voxelshapes;

import net.minecraft.world.level.block.Block;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;

import java.util.Optional;
import java.util.stream.Stream;

public class VoxelsAttunementAltar {

    public static final Optional<VoxelShape> SHAPE = Stream.of(
            Block.makeCuboidShape(1, 16, 1, 15, 18, 15),
            Block.makeCuboidShape(0, 0, 0, 16, 3, 16),
            Block.makeCuboidShape(4, 3, 4, 12, 14, 12),
            Block.makeCuboidShape(2, 14, 2, 14, 16, 14)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR));
}
