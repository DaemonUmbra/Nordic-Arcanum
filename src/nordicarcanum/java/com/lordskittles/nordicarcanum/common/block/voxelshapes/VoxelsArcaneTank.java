package com.lordskittles.nordicarcanum.common.block.voxelshapes;

import net.minecraft.block.Block;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;

import java.util.Optional;
import java.util.stream.Stream;

public class VoxelsArcaneTank
{
    public static final Optional<VoxelShape> SHAPE = Stream.of(
            Block.makeCuboidShape(5, 3, 5, 11, 11, 11),
            Block.makeCuboidShape(3, 2, 3, 13, 3, 13),
            Block.makeCuboidShape(3, 11, 3, 13, 12, 13),
            Block.makeCuboidShape(4, 12, 4, 12, 13, 12),
            Block.makeCuboidShape(4, 3, 4, 5, 11, 5),
            Block.makeCuboidShape(11, 3, 11, 12, 11, 12),
            Block.makeCuboidShape(11, 3, 4, 12, 11, 5),
            Block.makeCuboidShape(4, 3, 11, 5, 11, 12)
            ).reduce((v1, v2) -> { return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);});
}
