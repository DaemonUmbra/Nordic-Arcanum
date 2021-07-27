package com.lordskittles.nordicarcanum.common.block.voxelshapes;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Optional;
import java.util.stream.Stream;

public class VoxelsArcaneTank {

    public static final Optional<VoxelShape> SHAPE = Stream.of(
            Block.box(5, 3, 5, 11, 11, 11),
            Block.box(3, 2, 3, 13, 3, 13),
            Block.box(3, 11, 3, 13, 12, 13),
            Block.box(4, 12, 4, 12, 13, 12),
            Block.box(4, 3, 4, 5, 11, 5),
            Block.box(11, 3, 11, 12, 11, 12),
            Block.box(11, 3, 4, 12, 11, 5),
            Block.box(4, 3, 11, 5, 11, 12)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR));
}
