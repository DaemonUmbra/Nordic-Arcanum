package com.lordskittles.arcanumapi.common.block.voxelshapes;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;

import java.util.Optional;
import java.util.stream.Stream;

public class VoxelsMagicChest {

    public static final Optional<VoxelShape> NORTH = Stream.of(
            Block.box(4, 3, 4, 12, 9, 12),
            Block.box(4, 9, 4, 12, 11, 12),
            Block.box(3, 8.5, 7, 4, 10.5, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR));

    public static final Optional<VoxelShape> EAST = Stream.of(
            Block.box(4, 3, 4, 12, 9, 12),
            Block.box(4, 9, 4, 12, 11, 12),
            Block.box(7, 8.5, 3, 9, 10.5, 4)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR));

    public static final Optional<VoxelShape> SOUTH = Stream.of(
            Block.box(4, 3, 4, 12, 9, 12),
            Block.box(4, 9, 4, 12, 11, 12),
            Block.box(12, 8.5, 7, 13, 10.5, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR));

    public static final Optional<VoxelShape> WEST = Stream.of(
            Block.box(4, 3, 4, 12, 9, 12),
            Block.box(4, 9, 4, 12, 11, 12),
            Block.box(7, 8.5, 12, 9, 10.5, 13)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR));
}
