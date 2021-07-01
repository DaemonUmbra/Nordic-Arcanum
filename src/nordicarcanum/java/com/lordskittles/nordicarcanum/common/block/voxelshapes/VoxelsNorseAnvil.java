package com.lordskittles.nordicarcanum.common.block.voxelshapes;

import net.minecraft.block.Block;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;

import java.util.Optional;
import java.util.stream.Stream;

public class VoxelsNorseAnvil {

    public static final Optional<VoxelShape> NORTH_SOUTH = Stream.of(
            Block.makeCuboidShape(2.4705882352941178, 0, 3.5294117647058822, 14.470588235294118, 4, 11.529411764705882),
            Block.makeCuboidShape(4.470588235294118, 4, 4.529411764705882, 12.470588235294118, 5, 10.529411764705882),
            Block.makeCuboidShape(5.470588235294118, 5, 5.529411764705882, 11.470588235294118, 10, 9.529411764705882),
            Block.makeCuboidShape(11.470588235294118, 10, 9.529411764705882, 12.470588235294118, 16, 10.529411764705882),
            Block.makeCuboidShape(11.470588235294118, 10, 4.529411764705882, 12.470588235294118, 16, 5.529411764705882),
            Block.makeCuboidShape(2.4705882352941178, 15, 4.529411764705882, 11.470588235294118, 16, 5.529411764705882),
            Block.makeCuboidShape(2.4705882352941178, 10, 4.529411764705882, 11.470588235294118, 11, 5.529411764705882),
            Block.makeCuboidShape(2.4705882352941178, 10, 9.529411764705882, 11.470588235294118, 11, 10.529411764705882),
            Block.makeCuboidShape(2.4705882352941178, 15, 9.529411764705882, 11.470588235294118, 16, 10.529411764705882),
            Block.makeCuboidShape(1.4705882352941178, 10, 4.529411764705882, 2.4705882352941178, 16, 5.529411764705882),
            Block.makeCuboidShape(1.4705882352941178, 10, 9.529411764705882, 2.4705882352941178, 16, 10.529411764705882),
            Block.makeCuboidShape(1.4705882352941178, 15, 5.529411764705882, 2.4705882352941178, 16, 9.529411764705882),
            Block.makeCuboidShape(1.4705882352941178, 10, 5.529411764705882, 2.4705882352941178, 11, 9.529411764705882),
            Block.makeCuboidShape(2.4705882352941178, 10, 5.529411764705882, 12.470588235294118, 16, 9.529411764705882),
            Block.makeCuboidShape(12.470588235294118, 11, 5.529411764705882, 13.470588235294118, 16, 9.529411764705882),
            Block.makeCuboidShape(13.470588235294118, 11.75, 6.029411764705882, 15.470588235294118, 15.75, 9.029411764705882),
            Block.makeCuboidShape(15.470588235294118, 13.5, 6.529411764705882, 16.470588235294116, 15.5, 8.529411764705882)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR));

    public static final Optional<VoxelShape> EAST_WEST = Stream.of(
            Block.makeCuboidShape(4, 0, 2, 12, 4, 14),
            Block.makeCuboidShape(5, 4, 4, 11, 5, 12),
            Block.makeCuboidShape(6, 5, 5, 10, 10, 11),
            Block.makeCuboidShape(5, 10, 11, 6, 16, 12),
            Block.makeCuboidShape(10, 10, 11, 11, 16, 12),
            Block.makeCuboidShape(10, 15, 2, 11, 16, 11),
            Block.makeCuboidShape(10, 10, 2, 11, 11, 11),
            Block.makeCuboidShape(5, 10, 2, 6, 11, 11),
            Block.makeCuboidShape(5, 15, 2, 6, 16, 11),
            Block.makeCuboidShape(10, 10, 1, 11, 16, 2),
            Block.makeCuboidShape(5, 10, 1, 6, 16, 2),
            Block.makeCuboidShape(6, 15, 1, 10, 16, 2),
            Block.makeCuboidShape(6, 10, 1, 10, 11, 2),
            Block.makeCuboidShape(6, 10, 2, 10, 16, 12),
            Block.makeCuboidShape(6, 11, 12, 10, 16, 13),
            Block.makeCuboidShape(6.5, 11.75, 13, 9.5, 15.75, 15),
            Block.makeCuboidShape(7, 13.5, 15, 9, 15.5, 15.999999999999998)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR));
}
