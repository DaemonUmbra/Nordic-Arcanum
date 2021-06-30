package com.lordskittles.nordicarcanum.common.block.voxelshapes;

import net.minecraft.block.Block;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;

import java.util.Optional;
import java.util.stream.Stream;

public class VoxelsSigilPodium
{
    public static Optional<VoxelShape> SHAPE = Stream.of(
            Block.makeCuboidShape(0, 0, 0, 16, 3, 16),
            Block.makeCuboidShape(2, 3, 2, 14, 9, 14),
            Block.makeCuboidShape(1, 9, 1, 15, 11, 15),
            Block.makeCuboidShape(1, 11, 1, 5, 14, 5),
            Block.makeCuboidShape(11, 11, 1, 15, 14, 5),
            Block.makeCuboidShape(11, 11, 11, 15, 14, 15),
            Block.makeCuboidShape(1, 11, 11, 5, 14, 15),
            Block.makeCuboidShape(1, 11, 5, 2, 12, 11),
            Block.makeCuboidShape(14, 11, 5, 15, 12, 11),
            Block.makeCuboidShape(5, 11, 1, 11, 12, 2),
            Block.makeCuboidShape(5, 11, 14, 11, 12, 15),
            Block.makeCuboidShape(1, 14, 1, 3, 16, 3),
            Block.makeCuboidShape(1, 14, 13, 3, 16, 15),
            Block.makeCuboidShape(13, 14, 13, 15, 16, 15),
            Block.makeCuboidShape(13, 14, 1, 15, 16, 3),
            Block.makeCuboidShape(1, 3, 14, 2, 9, 15),
            Block.makeCuboidShape(1, 3, 1, 2, 9, 2),
            Block.makeCuboidShape(14, 3, 1, 15, 9, 2),
            Block.makeCuboidShape(14, 3, 14, 15, 9, 15)
            ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);});
}
