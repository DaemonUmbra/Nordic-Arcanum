package com.lordskittles.nordicarcanum.common.block.voxelshapes;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Optional;
import java.util.stream.Stream;

public class VoxelsAttunementAltar {

    public static final Optional<VoxelShape> SHAPE = Stream.of(
            Block.box(1, 16, 1, 15, 18, 15),
            Block.box(0, 0, 0, 16, 3, 16),
            Block.box(4, 3, 4, 12, 14, 12),
            Block.box(2, 14, 2, 14, 16, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR));
}
