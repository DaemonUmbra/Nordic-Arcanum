package com.lordskittles.nordicarcanum.common.block.voxelshapes;

import net.minecraft.world.level.block.Block;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;

import java.util.Optional;
import java.util.stream.Stream;

public class VoxelsCrystalLight {

    public static Optional<VoxelShape> SHAPE = Stream.of(
            Block.makeCuboidShape(1.75145, 15.35714, 1.74855, 14.25145, 16.35714, 14.24855),
            Block.makeCuboidShape(6, 14.35714, 5.98366, 10, 15.35714, 9.98366),
            Block.makeCuboidShape(2.25, 15, 6.25, 5.75, 15.5, 9.75),
            Block.makeCuboidShape(6.25, 15, 10.25, 9.75, 15.5, 13.75),
            Block.makeCuboidShape(10.25, 15, 6.25, 13.75, 15.5, 9.75),
            Block.makeCuboidShape(6.25, 15, 2.25, 9.75, 15.5, 5.75)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR));
}
