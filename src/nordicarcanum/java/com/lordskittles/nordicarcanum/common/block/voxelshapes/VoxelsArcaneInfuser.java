package com.lordskittles.nordicarcanum.common.block.voxelshapes;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Optional;
import java.util.stream.Stream;

public class VoxelsArcaneInfuser {

    public static final Optional<VoxelShape> SHAPE = Stream.of(
            Block.box(0, 0, 0, 16, 1, 16),
            Block.box(1, 1, 1, 15, 3, 15),
            Block.box(12, 11, 3, 13, 15, 13),
            Block.box(3, 11, 3, 4, 15, 13),
            Block.box(4, 11, 3, 12, 15, 4),
            Block.box(4, 11, 12, 12, 15, 13),
            Block.box(2, 15, 13, 14, 16, 14),
            Block.box(2, 15, 2, 14, 16, 3),
            Block.box(2, 15, 3, 3, 16, 13),
            Block.box(13, 15, 3, 14, 16, 13),
            Block.box(11, 15, 3, 12, 16, 13),
            Block.box(9, 15, 3, 10, 16, 13),
            Block.box(6, 15, 3, 7, 16, 13),
            Block.box(3, 15, 4, 13, 16, 5),
            Block.box(3, 15, 6, 13, 16, 7),
            Block.box(3, 15, 11, 13, 16, 12),
            Block.box(3, 15, 9, 13, 16, 10),
            Block.box(4, 15, 3, 5, 16, 13),
            Block.box(3, 3, 3, 13, 8, 13),
            Block.box(4, 8, 4, 12, 9, 12),
            Block.box(4, 10, 4, 12, 11, 12),
            Block.box(3, 9, 3, 13, 10, 13),
            Block.box(0, 11, 7, 3, 13, 9),
            Block.box(0, 4, 7, 3, 6, 9),
            Block.box(0, 6, 7, 2, 11, 9),
            Block.box(14, 6, 7, 16, 11, 9),
            Block.box(13, 4, 7, 16, 6, 9),
            Block.box(13, 11, 7, 16, 13, 9),
            Block.box(14, 6, 7, 16, 11, 9),
            Block.box(13, 4, 7, 16, 6, 9),
            Block.box(13, 11, 7, 16, 13, 9),
            Block.box(7, 11, 13, 9, 13, 16),
            Block.box(7, 6, 0, 9, 11, 2),
            Block.box(7, 4, 0, 9, 6, 3),
            Block.box(7, 11, 0, 9, 13, 3),
            Block.box(7, 4, 13, 9, 6, 16),
            Block.box(7, 6, 14, 9, 11, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR));
}
