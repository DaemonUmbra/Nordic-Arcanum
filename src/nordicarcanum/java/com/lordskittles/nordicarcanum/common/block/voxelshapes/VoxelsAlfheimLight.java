package com.lordskittles.nordicarcanum.common.block.voxelshapes;

import net.minecraft.world.level.block.Block;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;

import java.util.Optional;
import java.util.stream.Stream;

public class VoxelsAlfheimLight {

    public static Optional<VoxelShape> SHAPE = Stream.of(
            Block.makeCuboidShape(5, 5, 5, 11, 11, 11),
            Block.makeCuboidShape(6, 3, 6, 10, 5, 10),
            Block.makeCuboidShape(6, 6, 11, 10, 10, 13),
            Block.makeCuboidShape(11, 6, 6, 13, 10, 10),
            Block.makeCuboidShape(6, 11, 6, 10, 13, 10),
            Block.makeCuboidShape(6, 6, 3, 10, 10, 5),
            Block.makeCuboidShape(3, 6, 6, 5, 10, 10),
            Block.makeCuboidShape(7, 1, 7, 9, 3, 9),
            Block.makeCuboidShape(7, 7, 13, 9, 9, 15),
            Block.makeCuboidShape(13, 7, 7, 15, 9, 9),
            Block.makeCuboidShape(7, 13, 7, 9, 15, 9),
            Block.makeCuboidShape(7, 7, 1, 9, 9, 3),
            Block.makeCuboidShape(1, 7, 7, 3, 9, 9)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR));
}
