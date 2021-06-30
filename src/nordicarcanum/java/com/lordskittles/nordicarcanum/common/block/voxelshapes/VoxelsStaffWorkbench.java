package com.lordskittles.nordicarcanum.common.block.voxelshapes;

import com.lordskittles.arcanumapi.common.block.MultiBlockPiece;
import net.minecraft.block.Block;
import net.minecraft.util.Direction;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;

import java.util.Optional;
import java.util.stream.Stream;

public class VoxelsStaffWorkbench
{
    public static VoxelShape getShape(Direction direction, MultiBlockPiece piece)
    {
        switch (direction)
        {
            case NORTH:
                switch (piece)
                {
                    case BOTTOM_RIGHT:
                        return BottomRight.NORTH.get();
                    case BOTTOM_LEFT:
                        return BottomLeft.NORTH.get();
                    case TOP_RIGHT:
                        return TopRight.NORTH.get();
                    case TOP_LEFT:
                        return TopLeft.NORTH.get();
                }
            case WEST:
                switch (piece)
                {
                    case BOTTOM_RIGHT:
                        return BottomRight.EAST.get();
                    case BOTTOM_LEFT:
                        return BottomLeft.EAST.get();
                    case TOP_RIGHT:
                        return TopRight.EAST.get();
                    case TOP_LEFT:
                        return TopLeft.EAST.get();
                }
            case SOUTH:
                switch (piece)
                {
                    case BOTTOM_RIGHT:
                        return BottomRight.SOUTH.get();
                    case BOTTOM_LEFT:
                        return BottomLeft.SOUTH.get();
                    case TOP_RIGHT:
                        return TopRight.SOUTH.get();
                    case TOP_LEFT:
                        return TopLeft.SOUTH.get();
                }
            case EAST:
                switch (piece)
                {
                    case BOTTOM_RIGHT:
                        return BottomRight.WEST.get();
                    case BOTTOM_LEFT:
                        return BottomLeft.WEST.get();
                    case TOP_RIGHT:
                        return TopRight.WEST.get();
                    case TOP_LEFT:
                        return TopLeft.WEST.get();
                }
        }

        return Block.makeCuboidShape(0,0,0,16,16,16);
    }

    private static class BottomLeft
    {
        public static Optional<VoxelShape> WEST = Stream.of(
                Block.makeCuboidShape(0, 13, 0, 16, 16, 16),
                Block.makeCuboidShape(12, 11, 0, 16, 13, 4),
                Block.makeCuboidShape(12, 11, 12, 16, 13, 16),
                Block.makeCuboidShape(13, 3, 13, 15, 11, 15),
                Block.makeCuboidShape(12, 0, 12, 16, 3, 16),
                Block.makeCuboidShape(0, 1, 2, 14, 2, 14),
                Block.makeCuboidShape(12, 0, 0, 16, 3, 4),
                Block.makeCuboidShape(13, 3, 1, 15, 11, 3)
        ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);});

        public static Optional<VoxelShape> NORTH = Stream.of(
                Block.makeCuboidShape(0, 13, 0, 16, 16, 16),
                Block.makeCuboidShape(0, 11, 0, 4, 13, 4),
                Block.makeCuboidShape(12, 11, 0, 16, 13, 4),
                Block.makeCuboidShape(13, 3, 1, 15, 11, 3),
                Block.makeCuboidShape(12, 0, 0, 16, 3, 4),
                Block.makeCuboidShape(2, 1, 2, 14, 2, 16),
                Block.makeCuboidShape(0, 0, 0, 4, 3, 4),
                Block.makeCuboidShape(1, 3, 1, 3, 11, 3)
        ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);});

        public static Optional<VoxelShape> EAST = Stream.of(
                Block.makeCuboidShape(0, 13, 0, 16, 16, 16),
                Block.makeCuboidShape(0, 11, 12, 4, 13, 16),
                Block.makeCuboidShape(0, 11, 0, 4, 13, 4),
                Block.makeCuboidShape(1, 3, 1, 3, 11, 3),
                Block.makeCuboidShape(0, 0, 0, 4, 3, 4),
                Block.makeCuboidShape(2, 1, 2, 16, 2, 14),
                Block.makeCuboidShape(0, 0, 12, 4, 3, 16),
                Block.makeCuboidShape(1, 3, 13, 3, 11, 15)
        ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);});

        public static Optional<VoxelShape> SOUTH = Stream.of(
                Block.makeCuboidShape(0, 13, 0, 16, 16, 16),
                Block.makeCuboidShape(12, 11, 12, 16, 13, 16),
                Block.makeCuboidShape(0, 11, 12, 4, 13, 16),
                Block.makeCuboidShape(1, 3, 13, 3, 11, 15),
                Block.makeCuboidShape(0, 0, 12, 4, 3, 16),
                Block.makeCuboidShape(2, 1, 0, 14, 2, 14),
                Block.makeCuboidShape(12, 0, 12, 16, 3, 16),
                Block.makeCuboidShape(13, 3, 13, 15, 11, 15)
        ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);});
    }

    private static class BottomRight
    {
        public static Optional<VoxelShape> WEST = Stream.of(
                Block.makeCuboidShape(0, 13, 0, 16, 16, 16),
                Block.makeCuboidShape(2, 1, 2, 16, 2, 14),
                Block.makeCuboidShape(0, 0, 0, 4, 3, 4),
                Block.makeCuboidShape(0, 0, 12, 4, 3, 16),
                Block.makeCuboidShape(0, 11, 0, 4, 13, 4),
                Block.makeCuboidShape(0, 11, 12, 4, 13, 16),
                Block.makeCuboidShape(1, 3, 1, 3, 11, 3),
                Block.makeCuboidShape(1, 3, 13, 3, 11, 15)
        ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);});

        public static Optional<VoxelShape> NORTH = Stream.of(
                Block.makeCuboidShape(0, 13, 0, 16, 16, 16),
                Block.makeCuboidShape(2, 1, 0, 14, 2, 14),
                Block.makeCuboidShape(0, 0, 12, 4, 3, 16),
                Block.makeCuboidShape(12, 0, 12, 16, 3, 16),
                Block.makeCuboidShape(0, 11, 12, 4, 13, 16),
                Block.makeCuboidShape(12, 11, 12, 16, 13, 16),
                Block.makeCuboidShape(1, 3, 13, 3, 11, 15),
                Block.makeCuboidShape(13, 3, 13, 15, 11, 15)
        ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);});

        public static Optional<VoxelShape> EAST = Stream.of(
                Block.makeCuboidShape(0, 13, 0, 16, 16, 16),
                Block.makeCuboidShape(0, 1, 2, 14, 2, 14),
                Block.makeCuboidShape(12, 0, 12, 16, 3, 16),
                Block.makeCuboidShape(12, 0, 0, 16, 3, 4),
                Block.makeCuboidShape(12, 11, 12, 16, 13, 16),
                Block.makeCuboidShape(12, 11, 0, 16, 13, 4),
                Block.makeCuboidShape(13, 3, 13, 15, 11, 15),
                Block.makeCuboidShape(13, 3, 1, 15, 11, 3)
        ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);});

        public static Optional<VoxelShape> SOUTH = Stream.of(
                Block.makeCuboidShape(0, 13, 0, 16, 16, 16),
                Block.makeCuboidShape(2, 1, 2, 14, 2, 16),
                Block.makeCuboidShape(12, 0, 0, 16, 3, 4),
                Block.makeCuboidShape(0, 0, 0, 4, 3, 4),
                Block.makeCuboidShape(12, 11, 0, 16, 13, 4),
                Block.makeCuboidShape(0, 11, 0, 4, 13, 4),
                Block.makeCuboidShape(13, 3, 1, 15, 11, 3),
                Block.makeCuboidShape(1, 3, 1, 3, 11, 3)
        ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);});
    }

    private static class TopLeft
    {
        public static Optional<VoxelShape> WEST = Stream.of(
                Block.makeCuboidShape(15, 0, 4, 16, 1, 6),
                Block.makeCuboidShape(15, 0, 6, 16, 3, 8),
                Block.makeCuboidShape(15, 0, 8, 16, 4, 13),
                Block.makeCuboidShape(0, 0, 13, 16, 16, 16),
                Block.makeCuboidShape(14, 5.200000000000003, 11, 15, 6.200000000000003, 13),
                Block.makeCuboidShape(14, 6.200000000000003, 10, 15, 8.200000000000003, 13),
                Block.makeCuboidShape(0, 8.200000000000003, 8, 16, 9.200000000000003, 13),
                Block.makeCuboidShape(1, 6.200000000000003, 10, 2, 8.200000000000003, 13),
                Block.makeCuboidShape(1, 5.200000000000003, 11, 2, 6.200000000000003, 13),
                Block.makeCuboidShape(0, 15, 12, 16, 16, 13),
                Block.makeCuboidShape(9.826141668407374, 9.156813244944438, 8.057578708719507, 12.326141668407375, 11.456813244944438, 12.657578708719514),
                Block.makeCuboidShape(11.926141668407373, 9.588560490582903, 9.799119580716154, 12.926141668407373, 10.588560490582903, 10.799119580716154),
                Block.makeCuboidShape(4.826141668407338, 9.588560490582903, 9.799119580716154, 9.826141668407374, 10.588560490582903, 10.799119580716154),
                Block.makeCuboidShape(8.85213542967394, 2.844073595615363, 5.429229611253834, 9.85213542967394, 4.044073595615366, 6.929229611253834),
                Block.makeCuboidShape(13.452135429673941, 2.844073595615363, 5.429229611253834, 14.452135429673941, 4.044073595615366, 6.929229611253834),
                Block.makeCuboidShape(10.852135429673947, 1.9960499145277737, 4.402553260098334, 12.452135429673948, 5.196049914527769, 7.902553260098336),
                Block.makeCuboidShape(9.852135429673947, 2.4960499145277737, 4.902553260098334, 13.452135429673948, 4.696049914527769, 7.402553260098334),
                Block.makeCuboidShape(7.5521354296739425, 0.5999999999999943, 3.315943893988489, 8.552135429673942, 6.399999999999995, 4.315943893988489),
                Block.makeCuboidShape(6.652135429673944, 0.1999999999999993, 2.8159438939884875, 7.652135429673944, 6.800000000000001, 9.41594389398849),
                Block.makeCuboidShape(5.652135429673944, 2, 4.615943893988489, 6.652135429673944, 5, 7.615943893988489),
                Block.makeCuboidShape(4.652135429673943, 1.3999999999999986, 4.115943893988488, 5.652135429673944, 5.5, 8.215943893988488),
                Block.makeCuboidShape(3.652135429673943, 1, 3.6159438939884883, 4.652135429673943, 6, 8.61594389398849),
                Block.makeCuboidShape(2.652135429673943, 2, 4.615943893988489, 3.652135429673943, 5, 7.615943893988489),
                Block.makeCuboidShape(0.652135429673943, 2, 4.615943893988489, 1.652135429673943, 5, 7.615943893988489),
                Block.makeCuboidShape(-0.3633628270730265, 3, 5.615943893988489, 2.652135429673943, 4, 6.615943893988489),
                Block.makeCuboidShape(7.5521354296739425, 5.3999999999999915, 4.315943893988489, 8.552135429673942, 6.3999999999999915, 7.815943893988487),
                Block.makeCuboidShape(7.5521354296739425, 0.6000000000000014, 7.815943893988491, 8.552135429673942, 6.399999999999999, 8.815943893988491),
                Block.makeCuboidShape(7.5521354296739425, 0.5999999999999943, 4.31594389398849, 8.552135429673942, 1.5999999999999943, 7.815943893988488)
        ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);});

        public static Optional<VoxelShape> NORTH = Stream.of(
                Block.makeCuboidShape(4, 0, 0, 6, 1, 1),
                Block.makeCuboidShape(6, 0, 0, 8, 3, 1),
                Block.makeCuboidShape(8, 0, 0, 13, 4, 1),
                Block.makeCuboidShape(13, 0, 0, 16, 16, 16),
                Block.makeCuboidShape(11, 5.200000000000003, 1, 13, 6.200000000000003, 2),
                Block.makeCuboidShape(10, 6.200000000000003, 1, 13, 8.200000000000003, 2),
                Block.makeCuboidShape(8, 8.200000000000003, 0, 13, 9.200000000000003, 16),
                Block.makeCuboidShape(10, 6.200000000000003, 14, 13, 8.200000000000003, 15),
                Block.makeCuboidShape(11, 5.200000000000003, 14, 13, 6.200000000000003, 15),
                Block.makeCuboidShape(12, 15, 0, 13, 16, 16),
                Block.makeCuboidShape(8.057578708719507, 9.156813244944438, 3.6738583315926245, 12.657578708719514, 11.456813244944438, 6.173858331592626),
                Block.makeCuboidShape(9.799119580716154, 9.588560490582903, 3.0738583315926267, 10.799119580716154, 10.588560490582903, 4.073858331592627),
                Block.makeCuboidShape(9.799119580716154, 9.588560490582903, 6.173858331592626, 10.799119580716154, 10.588560490582903, 11.173858331592662),
                Block.makeCuboidShape(5.4292296112538345, 2.844073595615363, 6.14786457032606, 6.9292296112538345, 4.044073595615366, 7.14786457032606),
                Block.makeCuboidShape(5.4292296112538345, 2.844073595615363, 1.547864570326059, 6.9292296112538345, 4.044073595615366, 2.547864570326059),
                Block.makeCuboidShape(4.402553260098333, 1.9960499145277737, 3.547864570326052, 7.902553260098337, 5.196049914527769, 5.147864570326053),
                Block.makeCuboidShape(4.902553260098333, 2.4960499145277737, 2.547864570326052, 7.402553260098333, 4.696049914527769, 6.147864570326053),
                Block.makeCuboidShape(3.3159438939884893, 0.5999999999999943, 7.4478645703260575, 4.315943893988489, 6.399999999999995, 8.447864570326058),
                Block.makeCuboidShape(2.8159438939884875, 0.1999999999999993, 8.347864570326056, 9.41594389398849, 6.800000000000001, 9.347864570326056),
                Block.makeCuboidShape(4.61594389398849, 2, 9.347864570326056, 7.61594389398849, 5, 10.347864570326056),
                Block.makeCuboidShape(4.115943893988488, 1.3999999999999986, 10.347864570326056, 8.215943893988488, 5.5, 11.347864570326056),
                Block.makeCuboidShape(3.6159438939884883, 1, 11.347864570326056, 8.61594389398849, 6, 12.347864570326056),
                Block.makeCuboidShape(4.61594389398849, 2, 12.347864570326056, 7.61594389398849, 5, 13.347864570326056),
                Block.makeCuboidShape(4.61594389398849, 2, 14.347864570326056, 7.61594389398849, 5, 15.347864570326056),
                Block.makeCuboidShape(5.61594389398849, 3, 13.347864570326056, 6.61594389398849, 4, 16.363362827073026),
                Block.makeCuboidShape(4.315943893988489, 5.3999999999999915, 7.4478645703260575, 7.815943893988486, 6.3999999999999915, 8.447864570326058),
                Block.makeCuboidShape(7.815943893988491, 0.6000000000000014, 7.4478645703260575, 8.815943893988491, 6.399999999999999, 8.447864570326058),
                Block.makeCuboidShape(4.315943893988489, 0.5999999999999943, 7.4478645703260575, 7.815943893988489, 1.5999999999999943, 8.447864570326058)
        ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);});

        public static Optional<VoxelShape> EAST = Stream.of(
                Block.makeCuboidShape(0, 0, 10, 1, 1, 12),
                Block.makeCuboidShape(0, 0, 8, 1, 3, 10),
                Block.makeCuboidShape(0, 0, 3, 1, 4, 8),
                Block.makeCuboidShape(0, 0, 0, 16, 16, 3),
                Block.makeCuboidShape(1, 5.200000000000003, 3, 2, 6.200000000000003, 5),
                Block.makeCuboidShape(1, 6.200000000000003, 3, 2, 8.200000000000003, 6),
                Block.makeCuboidShape(0, 8.200000000000003, 3, 16, 9.200000000000003, 8),
                Block.makeCuboidShape(14, 6.200000000000003, 3, 15, 8.200000000000003, 6),
                Block.makeCuboidShape(14, 5.200000000000003, 3, 15, 6.200000000000003, 5),
                Block.makeCuboidShape(0, 15, 3, 16, 16, 4),
                Block.makeCuboidShape(3.6738583315926245, 9.156813244944438, 3.342421291280486, 6.173858331592626, 11.456813244944438, 7.942421291280493),
                Block.makeCuboidShape(3.0738583315926267, 9.588560490582903, 5.2008804192838465, 4.073858331592627, 10.588560490582903, 6.2008804192838465),
                Block.makeCuboidShape(6.173858331592626, 9.588560490582903, 5.2008804192838465, 11.173858331592662, 10.588560490582903, 6.2008804192838465),
                Block.makeCuboidShape(6.14786457032606, 2.844073595615363, 9.070770388746165, 7.14786457032606, 4.044073595615366, 10.570770388746165),
                Block.makeCuboidShape(1.547864570326059, 2.844073595615363, 9.070770388746165, 2.547864570326059, 4.044073595615366, 10.570770388746165),
                Block.makeCuboidShape(3.547864570326052, 1.9960499145277737, 8.097446739901663, 5.147864570326053, 5.196049914527769, 11.597446739901667),
                Block.makeCuboidShape(2.547864570326052, 2.4960499145277737, 8.597446739901667, 6.147864570326053, 4.696049914527769, 11.097446739901667),
                Block.makeCuboidShape(7.4478645703260575, 0.5999999999999943, 11.68405610601151, 8.447864570326058, 6.399999999999995, 12.68405610601151),
                Block.makeCuboidShape(8.347864570326056, 0.1999999999999993, 6.584056106011509, 9.347864570326056, 6.800000000000001, 13.184056106011512),
                Block.makeCuboidShape(9.347864570326056, 2, 8.38405610601151, 10.347864570326056, 5, 11.38405610601151),
                Block.makeCuboidShape(10.347864570326056, 1.3999999999999986, 7.784056106011512, 11.347864570326056, 5.5, 11.884056106011512),
                Block.makeCuboidShape(11.347864570326056, 1, 7.38405610601151, 12.347864570326056, 6, 12.384056106011512),
                Block.makeCuboidShape(12.347864570326056, 2, 8.38405610601151, 13.347864570326056, 5, 11.38405610601151),
                Block.makeCuboidShape(14.347864570326056, 2, 8.38405610601151, 15.347864570326056, 5, 11.38405610601151),
                Block.makeCuboidShape(13.347864570326056, 3, 9.38405610601151, 16.363362827073026, 4, 10.38405610601151),
                Block.makeCuboidShape(7.4478645703260575, 5.3999999999999915, 8.184056106011514, 8.447864570326058, 6.3999999999999915, 11.68405610601151),
                Block.makeCuboidShape(7.4478645703260575, 0.6000000000000014, 7.184056106011509, 8.447864570326058, 6.399999999999999, 8.184056106011509),
                Block.makeCuboidShape(7.4478645703260575, 0.5999999999999943, 8.18405610601151, 8.447864570326058, 1.5999999999999943, 11.68405610601151)
        ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);});

        public static Optional<VoxelShape> SOUTH = Stream.of(
                Block.makeCuboidShape(10, 0, 15, 12, 1, 16),
                Block.makeCuboidShape(8, 0, 15, 10, 3, 16),
                Block.makeCuboidShape(3, 0, 15, 8, 4, 16),
                Block.makeCuboidShape(0, 0, 0, 3, 16, 16),
                Block.makeCuboidShape(3, 5.200000000000003, 14, 5, 6.200000000000003, 15),
                Block.makeCuboidShape(3, 6.200000000000003, 14, 6, 8.200000000000003, 15),
                Block.makeCuboidShape(3, 8.200000000000003, 0, 8, 9.200000000000003, 16),
                Block.makeCuboidShape(3, 6.200000000000003, 1, 6, 8.200000000000003, 2),
                Block.makeCuboidShape(3, 5.200000000000003, 1, 5, 6.200000000000003, 2),
                Block.makeCuboidShape(3, 15, 0, 4, 16, 16),
                Block.makeCuboidShape(3.342421291280486, 9.156813244944438, 9.826141668407374, 7.942421291280493, 11.456813244944438, 12.326141668407375),
                Block.makeCuboidShape(5.2008804192838465, 9.588560490582903, 11.926141668407373, 6.2008804192838465, 10.588560490582903, 12.926141668407373),
                Block.makeCuboidShape(5.2008804192838465, 9.588560490582903, 4.826141668407338, 6.2008804192838465, 10.588560490582903, 9.826141668407374),
                Block.makeCuboidShape(9.070770388746165, 2.844073595615363, 8.85213542967394, 10.570770388746165, 4.044073595615366, 9.85213542967394),
                Block.makeCuboidShape(9.070770388746165, 2.844073595615363, 13.452135429673941, 10.570770388746165, 4.044073595615366, 14.452135429673941),
                Block.makeCuboidShape(8.097446739901663, 1.9960499145277737, 10.852135429673947, 11.597446739901667, 5.196049914527769, 12.452135429673948),
                Block.makeCuboidShape(8.597446739901667, 2.4960499145277737, 9.852135429673947, 11.097446739901667, 4.696049914527769, 13.452135429673948),
                Block.makeCuboidShape(11.68405610601151, 0.5999999999999943, 7.5521354296739425, 12.68405610601151, 6.399999999999995, 8.552135429673942),
                Block.makeCuboidShape(6.584056106011509, 0.1999999999999993, 6.652135429673944, 13.184056106011512, 6.800000000000001, 7.652135429673944),
                Block.makeCuboidShape(8.38405610601151, 2, 5.652135429673944, 11.38405610601151, 5, 6.652135429673944),
                Block.makeCuboidShape(7.784056106011512, 1.3999999999999986, 4.652135429673944, 11.884056106011512, 5.5, 5.652135429673944),
                Block.makeCuboidShape(7.38405610601151, 1, 3.652135429673944, 12.384056106011512, 6, 4.652135429673944),
                Block.makeCuboidShape(8.38405610601151, 2, 2.652135429673944, 11.38405610601151, 5, 3.652135429673944),
                Block.makeCuboidShape(8.38405610601151, 2, 0.6521354296739439, 11.38405610601151, 5, 1.652135429673944),
                Block.makeCuboidShape(9.38405610601151, 3, -0.3633628270730256, 10.38405610601151, 4, 2.652135429673944),
                Block.makeCuboidShape(8.184056106011514, 5.3999999999999915, 7.5521354296739425, 11.68405610601151, 6.3999999999999915, 8.552135429673942),
                Block.makeCuboidShape(7.184056106011509, 0.6000000000000014, 7.5521354296739425, 8.184056106011509, 6.399999999999999, 8.552135429673942),
                Block.makeCuboidShape(8.18405610601151, 0.5999999999999943, 7.5521354296739425, 11.68405610601151, 1.5999999999999943, 8.552135429673942)
        ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);});
    }

    private static class TopRight
    {
        public static Optional<VoxelShape> WEST = Stream.of(
                Block.makeCuboidShape(0, 0, 13, 16, 16, 16),
                Block.makeCuboidShape(3.6000000000000014, 0, 4.100000000000003, 4.600000000000001, 3, 8.100000000000003),
                Block.makeCuboidShape(3.6000000000000014, 3, 4.100000000000003, 4.600000000000001, 4, 5.100000000000003),
                Block.makeCuboidShape(3.6000000000000014, 3, 7.100000000000003, 4.600000000000001, 4, 8.100000000000003),
                Block.makeCuboidShape(0, 15, 12, 16, 16, 13),
                Block.makeCuboidShape(7.499999999999998, -7.105427357601002e-15, 7.799999999999997, 12.5, 0.9999999999999929, 12.799999999999997),
                Block.makeCuboidShape(7.499999999999998, 5.999999999999993, 7.799999999999997, 12.5, 6.999999999999993, 12.799999999999997),
                Block.makeCuboidShape(8.499999999999998, 0.9999999999999929, 8.799999999999997, 11.5, 5.999999999999993, 11.799999999999997),
                Block.makeCuboidShape(7.999999999999991, 0.9999999999999929, 8.299999999999997, 8.999999999999993, 5.999999999999993, 9.299999999999997),
                Block.makeCuboidShape(7.999999999999991, 0.9999999999999929, 11.299999999999997, 8.999999999999993, 5.999999999999993, 12.299999999999997),
                Block.makeCuboidShape(10.999999999999991, 0.9999999999999929, 8.299999999999997, 11.999999999999993, 5.999999999999993, 9.299999999999997),
                Block.makeCuboidShape(10.999999999999991, 0.9999999999999929, 11.299999999999997, 11.999999999999993, 5.999999999999993, 12.299999999999997),
                Block.makeCuboidShape(6.999999999999998, 5.8999999999999915, 9.799999999999997, 7.999999999999998, 6.8999999999999915, 10.799999999999997),
                Block.makeCuboidShape(11.999999999999998, 5.79999999999999, 9.799999999999997, 12.999999999999998, 6.79999999999999, 10.799999999999997),
                Block.makeCuboidShape(8, 6.999999999999993, 8.299999999999999, 11.999999999999998, 7.999999999999993, 12.299999999999999),
                Block.makeCuboidShape(8.999999999999996, 7.999999999999993, 9.299999999999994, 10.999999999999993, 8.999999999999993, 11.299999999999994),
                Block.makeCuboidShape(0.7366371729269758, 3, 5.615943893988489, 15.65213542967394, 4, 6.615943893988489)
        ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);});

        public static Optional<VoxelShape> NORTH = Stream.of(
                Block.makeCuboidShape(13, 0, 0, 16, 16, 16),
                Block.makeCuboidShape(4.100000000000003, 0, 11.399999999999999, 8.100000000000003, 3, 12.399999999999999),
                Block.makeCuboidShape(4.100000000000003, 3, 11.399999999999999, 5.100000000000003, 4, 12.399999999999999),
                Block.makeCuboidShape(7.100000000000003, 3, 11.399999999999999, 8.100000000000003, 4, 12.399999999999999),
                Block.makeCuboidShape(12, 15, 0, 13, 16, 16),
                Block.makeCuboidShape(7.799999999999997, -7.105427357601002e-15, 3.5, 12.799999999999997, 0.9999999999999929, 8.500000000000002),
                Block.makeCuboidShape(7.799999999999997, 5.999999999999993, 3.5, 12.799999999999997, 6.999999999999993, 8.500000000000002),
                Block.makeCuboidShape(8.799999999999997, 0.9999999999999929, 4.5, 11.799999999999997, 5.999999999999993, 7.500000000000002),
                Block.makeCuboidShape(8.299999999999997, 0.9999999999999929, 7.000000000000007, 9.299999999999997, 5.999999999999993, 8.000000000000009),
                Block.makeCuboidShape(11.299999999999997, 0.9999999999999929, 7.000000000000007, 12.299999999999997, 5.999999999999993, 8.000000000000009),
                Block.makeCuboidShape(8.299999999999997, 0.9999999999999929, 4.000000000000007, 9.299999999999997, 5.999999999999993, 5.000000000000009),
                Block.makeCuboidShape(11.299999999999997, 0.9999999999999929, 4.000000000000007, 12.299999999999997, 5.999999999999993, 5.000000000000009),
                Block.makeCuboidShape(9.799999999999997, 5.8999999999999915, 8.000000000000002, 10.799999999999997, 6.8999999999999915, 9.000000000000002),
                Block.makeCuboidShape(9.799999999999997, 5.79999999999999, 3.0000000000000018, 10.799999999999997, 6.79999999999999, 4.000000000000002),
                Block.makeCuboidShape(8.299999999999999, 6.999999999999993, 4.000000000000002, 12.299999999999999, 7.999999999999993, 8),
                Block.makeCuboidShape(9.299999999999994, 7.999999999999993, 5.000000000000007, 11.299999999999994, 8.999999999999993, 7.0000000000000036),
                Block.makeCuboidShape(5.61594389398849, 3, 0.34786457032605966, 6.61594389398849, 4, 15.263362827073024)
        ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);});

        public static Optional<VoxelShape> EAST = Stream.of(
                Block.makeCuboidShape(0, 0, 0, 16, 16, 3),
                Block.makeCuboidShape(11.399999999999999, 0, 7.899999999999997, 12.399999999999999, 3, 11.899999999999997),
                Block.makeCuboidShape(11.399999999999999, 3, 10.899999999999997, 12.399999999999999, 4, 11.899999999999997),
                Block.makeCuboidShape(11.399999999999999, 3, 7.899999999999997, 12.399999999999999, 4, 8.899999999999997),
                Block.makeCuboidShape(0, 15, 3, 16, 16, 4),
                Block.makeCuboidShape(3.5, -7.105427357601002e-15, 3.200000000000003, 8.500000000000002, 0.9999999999999929, 8.200000000000003),
                Block.makeCuboidShape(3.5, 5.999999999999993, 3.200000000000003, 8.500000000000002, 6.999999999999993, 8.200000000000003),
                Block.makeCuboidShape(4.5, 0.9999999999999929, 4.200000000000003, 7.500000000000002, 5.999999999999993, 7.200000000000003),
                Block.makeCuboidShape(7.000000000000007, 0.9999999999999929, 6.700000000000003, 8.000000000000009, 5.999999999999993, 7.700000000000003),
                Block.makeCuboidShape(7.000000000000007, 0.9999999999999929, 3.700000000000003, 8.000000000000009, 5.999999999999993, 4.700000000000003),
                Block.makeCuboidShape(4.000000000000007, 0.9999999999999929, 6.700000000000003, 5.000000000000009, 5.999999999999993, 7.700000000000003),
                Block.makeCuboidShape(4.000000000000007, 0.9999999999999929, 3.700000000000003, 5.000000000000009, 5.999999999999993, 4.700000000000003),
                Block.makeCuboidShape(8.000000000000002, 5.8999999999999915, 5.200000000000003, 9.000000000000002, 6.8999999999999915, 6.200000000000003),
                Block.makeCuboidShape(3.0000000000000018, 5.79999999999999, 5.200000000000003, 4.000000000000002, 6.79999999999999, 6.200000000000003),
                Block.makeCuboidShape(4.000000000000002, 6.999999999999993, 3.700000000000001, 8, 7.999999999999993, 7.700000000000001),
                Block.makeCuboidShape(5.000000000000007, 7.999999999999993, 4.700000000000006, 7.0000000000000036, 8.999999999999993, 6.700000000000006),
                Block.makeCuboidShape(0.34786457032605966, 3, 9.38405610601151, 15.263362827073024, 4, 10.38405610601151)
        ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);});

        public static Optional<VoxelShape> SOUTH = Stream.of(
                Block.makeCuboidShape(0, 0, 0, 3, 16, 16),
                Block.makeCuboidShape(7.899999999999997, 0, 3.6000000000000014, 11.899999999999997, 3, 4.600000000000001),
                Block.makeCuboidShape(10.899999999999997, 3, 3.6000000000000014, 11.899999999999997, 4, 4.600000000000001),
                Block.makeCuboidShape(7.899999999999997, 3, 3.6000000000000014, 8.899999999999997, 4, 4.600000000000001),
                Block.makeCuboidShape(3, 15, 0, 4, 16, 16),
                Block.makeCuboidShape(3.200000000000003, -7.105427357601002e-15, 7.499999999999998, 8.200000000000003, 0.9999999999999929, 12.5),
                Block.makeCuboidShape(3.200000000000003, 5.999999999999993, 7.499999999999998, 8.200000000000003, 6.999999999999993, 12.5),
                Block.makeCuboidShape(4.200000000000003, 0.9999999999999929, 8.499999999999998, 7.200000000000003, 5.999999999999993, 11.5),
                Block.makeCuboidShape(6.700000000000003, 0.9999999999999929, 7.999999999999991, 7.700000000000003, 5.999999999999993, 8.999999999999993),
                Block.makeCuboidShape(3.700000000000003, 0.9999999999999929, 7.999999999999991, 4.700000000000003, 5.999999999999993, 8.999999999999993),
                Block.makeCuboidShape(6.700000000000003, 0.9999999999999929, 10.999999999999991, 7.700000000000003, 5.999999999999993, 11.999999999999993),
                Block.makeCuboidShape(3.700000000000003, 0.9999999999999929, 10.999999999999991, 4.700000000000003, 5.999999999999993, 11.999999999999993),
                Block.makeCuboidShape(5.200000000000003, 5.8999999999999915, 6.999999999999998, 6.200000000000003, 6.8999999999999915, 7.999999999999998),
                Block.makeCuboidShape(5.200000000000003, 5.79999999999999, 11.999999999999998, 6.200000000000003, 6.79999999999999, 12.999999999999998),
                Block.makeCuboidShape(3.700000000000001, 6.999999999999993, 8, 7.700000000000001, 7.999999999999993, 11.999999999999998),
                Block.makeCuboidShape(4.700000000000006, 7.999999999999993, 8.999999999999996, 6.700000000000006, 8.999999999999993, 10.999999999999993),
                Block.makeCuboidShape(9.38405610601151, 3, 0.7366371729269758, 10.38405610601151, 4, 15.65213542967394)
        ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);});
    }
}
