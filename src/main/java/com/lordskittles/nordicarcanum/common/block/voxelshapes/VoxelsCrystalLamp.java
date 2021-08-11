package com.lordskittles.nordicarcanum.common.block.voxelshapes;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Optional;
import java.util.stream.Stream;

public class VoxelsCrystalLamp {

    public static Optional<VoxelShape> SHAPE = Stream.of(
            Block.box(7, 6, 7, 10, 10, 10),
            Block.box(6.75, 10, 6.75, 10.25, 11, 10.25),
            Block.box(7.5, 4, 7.5, 9.5, 6, 9.5),
            Block.box(7, 0, 7, 10, 4, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR));
}
