package com.lordskittles.nordicarcanum.common.block.voxelshapes;

import net.minecraft.block.Block;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;

import java.util.Optional;
import java.util.stream.Stream;

public class VoxelsCrystalLamp {

    public static Optional<VoxelShape> SHAPE = Stream.of(
            Block.makeCuboidShape(7, 6, 7, 10, 10, 10),
            Block.makeCuboidShape(6.75, 10, 6.75, 10.25, 11, 10.25),
            Block.makeCuboidShape(7.5, 4, 7.5, 9.5, 6, 9.5),
            Block.makeCuboidShape(7, 0, 7, 10, 4, 10)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR));
}
