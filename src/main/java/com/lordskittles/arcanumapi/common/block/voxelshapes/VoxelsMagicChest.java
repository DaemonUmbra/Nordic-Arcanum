package com.lordskittles.arcanumapi.common.block.voxelshapes;

import net.minecraft.block.Block;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;

import java.util.Optional;
import java.util.stream.Stream;

public class VoxelsMagicChest {

    public static final Optional<VoxelShape> NORTH = Stream.of(
            Block.makeCuboidShape(4, 3, 4, 12, 9, 12),
            Block.makeCuboidShape(4, 9, 4, 12, 11, 12),
            Block.makeCuboidShape(3, 8.5, 7, 4, 10.5, 9)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR));

    public static final Optional<VoxelShape> EAST = Stream.of(
            Block.makeCuboidShape(4, 3, 4, 12, 9, 12),
            Block.makeCuboidShape(4, 9, 4, 12, 11, 12),
            Block.makeCuboidShape(7, 8.5, 3, 9, 10.5, 4)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR));

    public static final Optional<VoxelShape> SOUTH = Stream.of(
            Block.makeCuboidShape(4, 3, 4, 12, 9, 12),
            Block.makeCuboidShape(4, 9, 4, 12, 11, 12),
            Block.makeCuboidShape(12, 8.5, 7, 13, 10.5, 9)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR));

    public static final Optional<VoxelShape> WEST = Stream.of(
            Block.makeCuboidShape(4, 3, 4, 12, 9, 12),
            Block.makeCuboidShape(4, 9, 4, 12, 11, 12),
            Block.makeCuboidShape(7, 8.5, 12, 9, 10.5, 13)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR));
}
