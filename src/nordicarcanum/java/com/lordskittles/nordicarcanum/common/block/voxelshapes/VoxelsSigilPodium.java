package com.lordskittles.nordicarcanum.common.block.voxelshapes;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Optional;
import java.util.stream.Stream;

public class VoxelsSigilPodium {

    public static Optional<VoxelShape> SHAPE = Stream.of(
            Block.box(0, 0, 0, 16, 3, 16),
            Block.box(2, 3, 2, 14, 9, 14),
            Block.box(1, 9, 1, 15, 11, 15),
            Block.box(1, 11, 1, 5, 14, 5),
            Block.box(11, 11, 1, 15, 14, 5),
            Block.box(11, 11, 11, 15, 14, 15),
            Block.box(1, 11, 11, 5, 14, 15),
            Block.box(1, 11, 5, 2, 12, 11),
            Block.box(14, 11, 5, 15, 12, 11),
            Block.box(5, 11, 1, 11, 12, 2),
            Block.box(5, 11, 14, 11, 12, 15),
            Block.box(1, 14, 1, 3, 16, 3),
            Block.box(1, 14, 13, 3, 16, 15),
            Block.box(13, 14, 13, 15, 16, 15),
            Block.box(13, 14, 1, 15, 16, 3),
            Block.box(1, 3, 14, 2, 9, 15),
            Block.box(1, 3, 1, 2, 9, 2),
            Block.box(14, 3, 1, 15, 9, 2),
            Block.box(14, 3, 14, 15, 9, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR));
}
