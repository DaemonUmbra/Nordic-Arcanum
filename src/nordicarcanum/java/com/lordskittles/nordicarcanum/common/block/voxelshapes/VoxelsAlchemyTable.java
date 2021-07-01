package com.lordskittles.nordicarcanum.common.block.voxelshapes;

import com.lordskittles.arcanumapi.common.block.MultiBlockPiece;
import net.minecraft.block.Block;
import net.minecraft.util.Direction;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;

import java.util.Optional;
import java.util.stream.Stream;

public class VoxelsAlchemyTable {

    public static VoxelShape getShape(Direction direction, MultiBlockPiece piece) {

        switch(direction) {
            case NORTH:
                switch(piece) {
                    case BOTTOM_RIGHT:
                        return VoxelsAlchemyTable.BottomRight.NORTH.get();
                    case BOTTOM_LEFT:
                        return VoxelsAlchemyTable.BottomLeft.NORTH.get();
                    case TOP_RIGHT:
                        return VoxelsAlchemyTable.TopRight.NORTH.get();
                    case TOP_LEFT:
                        return VoxelsAlchemyTable.TopLeft.NORTH.get();
                }
            case WEST:
                switch(piece) {
                    case BOTTOM_RIGHT:
                        return VoxelsAlchemyTable.BottomRight.EAST.get();
                    case BOTTOM_LEFT:
                        return VoxelsAlchemyTable.BottomLeft.EAST.get();
                    case TOP_RIGHT:
                        return VoxelsAlchemyTable.TopRight.EAST.get();
                    case TOP_LEFT:
                        return VoxelsAlchemyTable.TopLeft.EAST.get();
                }
            case SOUTH:
                switch(piece) {
                    case BOTTOM_RIGHT:
                        return VoxelsAlchemyTable.BottomRight.SOUTH.get();
                    case BOTTOM_LEFT:
                        return VoxelsAlchemyTable.BottomLeft.SOUTH.get();
                    case TOP_RIGHT:
                        return VoxelsAlchemyTable.TopRight.SOUTH.get();
                    case TOP_LEFT:
                        return VoxelsAlchemyTable.TopLeft.SOUTH.get();
                }
            case EAST:
                switch(piece) {
                    case BOTTOM_RIGHT:
                        return VoxelsAlchemyTable.BottomRight.WEST.get();
                    case BOTTOM_LEFT:
                        return VoxelsAlchemyTable.BottomLeft.WEST.get();
                    case TOP_RIGHT:
                        return VoxelsAlchemyTable.TopRight.WEST.get();
                    case TOP_LEFT:
                        return VoxelsAlchemyTable.TopLeft.WEST.get();
                }
        }

        return Block.makeCuboidShape(0, 0, 0, 16, 16, 16);
    }

    private static class BottomLeft {

        public static Optional<VoxelShape> WEST = Stream.of(
                Block.makeCuboidShape(0, 3, 3, 10, 12, 13),
                Block.makeCuboidShape(0, 0, 1, 12, 1, 15),
                Block.makeCuboidShape(0, 1, 2, 11, 3, 14),
                Block.makeCuboidShape(0, 14, 1, 12, 16, 15),
                Block.makeCuboidShape(0, 12, 2, 11, 14, 14),
                Block.makeCuboidShape(0, 10, 2.5000000000000004, 0.5, 11, 3.0000000000000004),
                Block.makeCuboidShape(0, 10, 13, 0.5, 11, 13.5)
        ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR));

        public static Optional<VoxelShape> NORTH = Stream.of(
                Block.makeCuboidShape(3, 3, 6, 13, 12, 16),
                Block.makeCuboidShape(1, 0, 4, 15, 1, 16),
                Block.makeCuboidShape(2, 1, 5, 14, 3, 16),
                Block.makeCuboidShape(1, 14, 4, 15, 16, 16),
                Block.makeCuboidShape(2, 12, 5, 14, 14, 16),
                Block.makeCuboidShape(2.5, 10, 15.5, 3, 11, 16),
                Block.makeCuboidShape(13, 10, 15.5, 13.5, 11, 16)
        ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR));

        public static Optional<VoxelShape> EAST = Stream.of(
                Block.makeCuboidShape(6, 3, 3, 16, 12, 13),
                Block.makeCuboidShape(4, 0, 1, 16, 1, 15),
                Block.makeCuboidShape(5, 1, 2, 16, 3, 14),
                Block.makeCuboidShape(4, 14, 1, 16, 16, 15),
                Block.makeCuboidShape(5, 12, 2, 16, 14, 14),
                Block.makeCuboidShape(15.5, 10, 13, 16, 11, 13.5),
                Block.makeCuboidShape(15.5, 10, 2.5, 16, 11, 3)
        ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR));

        public static Optional<VoxelShape> SOUTH = Stream.of(
                Block.makeCuboidShape(3, 3, 0, 13, 12, 10),
                Block.makeCuboidShape(1, 0, 0, 15, 1, 12),
                Block.makeCuboidShape(2, 1, 0, 14, 3, 11),
                Block.makeCuboidShape(1, 14, 0, 15, 16, 12),
                Block.makeCuboidShape(2, 12, 0, 14, 14, 11),
                Block.makeCuboidShape(13, 10, 0, 13.5, 11, 0.5),
                Block.makeCuboidShape(2.5, 10, 0, 3, 11, 0.5)
        ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR));
    }

    private static class BottomRight {

        public static Optional<VoxelShape> WEST = Stream.of(
                Block.makeCuboidShape(15, 12, 2, 16, 14, 14),
                Block.makeCuboidShape(15, 1, 2, 16, 3, 14),
                Block.makeCuboidShape(14, 14, 1, 16, 16, 15),
                Block.makeCuboidShape(14, 0, 1, 16, 1, 15),
                Block.makeCuboidShape(0, 10, 2, 13, 11, 14),
                Block.makeCuboidShape(13, 10, 2, 16, 11, 14),
                Block.makeCuboidShape(0, 0, 2, 1.6, 10, 3.6),
                Block.makeCuboidShape(0, 0, 12.4, 1.6, 10, 14),
                Block.makeCuboidShape(4.50145, 11, 7.17059, 11.00145, 12, 13.67059),
                Block.makeCuboidShape(4.00342, 11.6, 6.73035, 11.40342, 12.7, 7.73035),
                Block.makeCuboidShape(4.40342, 12.7, 6.33035, 11.80342, 16.1, 7.33035),
                Block.makeCuboidShape(3.20342, 13.7, 6.03035, 12.00342, 14.8, 7.03035),
                Block.makeCuboidShape(3.40342, 12.7, 6.33035, 4.40342, 16.1, 14.43035),
                Block.makeCuboidShape(3.20342, 13.7, 7.03035, 4.20342, 14.8, 13.73035),
                Block.makeCuboidShape(4.40342, 12.7, 13.43035, 11.80342, 16.1, 14.43035),
                Block.makeCuboidShape(3.20342, 13.7, 13.73035, 12.00342, 14.8, 14.73035),
                Block.makeCuboidShape(4.00342, 11.6, 13.03035, 11.40342, 12.7, 14.03035),
                Block.makeCuboidShape(10.40342, 11.6, 7.73035, 11.40342, 12.7, 13.03035),
                Block.makeCuboidShape(10.80342, 12.7, 7.33035, 11.80342, 16.1, 13.43035),
                Block.makeCuboidShape(11.00342, 13.7, 7.03035, 12.00342, 14.8, 13.73035),
                Block.makeCuboidShape(4.00342, 11.6, 7.73035, 5.00342, 12.7, 13.03035),
                Block.makeCuboidShape(0.5, 11, 2.5, 3.5, 16.25, 5.5),
                Block.makeCuboidShape(0.57662, 11.08198, 3.5, 2.57662, 12.08198, 4.5),
                Block.makeCuboidShape(4.78198, 10.92338, 3.5, 5.78198, 14.32338, 4.5)
        ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR));

        public static Optional<VoxelShape> NORTH = Stream.of(
                Block.makeCuboidShape(2.0000000000000036, 12, 0, 14, 14, 1),
                Block.makeCuboidShape(2.0000000000000036, 1, 0, 14, 3, 1),
                Block.makeCuboidShape(1.0000000000000036, 14, 0, 15, 16, 2),
                Block.makeCuboidShape(1.0000000000000036, 0, 0, 15, 1, 2),
                Block.makeCuboidShape(2.0000000000000036, 10, 3, 14, 11, 15.999999999999998),
                Block.makeCuboidShape(2.0000000000000036, 10, 0, 14, 11, 3),
                Block.makeCuboidShape(2.0000000000000036, 0, 14.400000000000002, 3.6000000000000014, 10, 15.999999999999998),
                Block.makeCuboidShape(12.399999999999999, 0, 14.400000000000002, 14, 10, 15.999999999999998),
                Block.makeCuboidShape(7.1705900000000025, 11, 4.99855, 13.67059, 12, 11.498550000000002),
                Block.makeCuboidShape(6.730350000000001, 11.6, 4.596580000000001, 7.730350000000001, 12.7, 11.996580000000002),
                Block.makeCuboidShape(6.330350000000003, 12.7, 4.196580000000003, 7.330350000000003, 16.1, 11.59658),
                Block.makeCuboidShape(6.030350000000002, 13.7, 3.99658, 7.030350000000002, 14.8, 12.796580000000002),
                Block.makeCuboidShape(6.330350000000003, 12.7, 11.59658, 14.43035, 16.1, 12.59658),
                Block.makeCuboidShape(7.030350000000002, 13.7, 11.796579999999999, 13.730349999999998, 14.8, 12.796580000000002),
                Block.makeCuboidShape(13.43035, 12.7, 4.196580000000003, 14.43035, 16.1, 11.59658),
                Block.makeCuboidShape(13.730349999999998, 13.7, 3.99658, 14.730349999999998, 14.8, 12.796580000000002),
                Block.makeCuboidShape(13.030349999999999, 11.6, 4.596580000000001, 14.030349999999999, 12.7, 11.996580000000002),
                Block.makeCuboidShape(7.730350000000001, 11.6, 4.596580000000001, 13.030349999999999, 12.7, 5.596580000000001),
                Block.makeCuboidShape(7.330350000000003, 12.7, 4.196580000000003, 13.43035, 16.1, 5.196580000000003),
                Block.makeCuboidShape(7.030350000000002, 13.7, 3.99658, 13.730349999999998, 14.8, 4.99658),
                Block.makeCuboidShape(7.730350000000001, 11.6, 10.996580000000002, 13.030349999999999, 12.7, 11.996580000000002),
                Block.makeCuboidShape(2.5000000000000036, 11, 12.5, 5.5000000000000036, 16.25, 15.5),
                Block.makeCuboidShape(3.5000000000000036, 11.08198, 13.423380000000002, 4.5000000000000036, 12.08198, 15.423380000000002),
                Block.makeCuboidShape(3.5000000000000036, 10.92338, 10.21802, 4.5000000000000036, 14.32338, 11.21802)
        ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR));

        public static Optional<VoxelShape> EAST = Stream.of(
                Block.makeCuboidShape(0, 12, 2, 1, 14, 13.999999999999996),
                Block.makeCuboidShape(0, 1, 2, 1, 3, 13.999999999999996),
                Block.makeCuboidShape(0, 14, 1, 2, 16, 14.999999999999996),
                Block.makeCuboidShape(0, 0, 1, 2, 1, 14.999999999999996),
                Block.makeCuboidShape(3, 10, 2, 15.999999999999998, 11, 13.999999999999996),
                Block.makeCuboidShape(0, 10, 2, 3, 11, 13.999999999999996),
                Block.makeCuboidShape(14.400000000000002, 0, 12.399999999999999, 15.999999999999998, 10, 13.999999999999996),
                Block.makeCuboidShape(14.400000000000002, 0, 2, 15.999999999999998, 10, 3.6000000000000014),
                Block.makeCuboidShape(4.99855, 11, 2.3294099999999993, 11.498550000000002, 12, 8.829409999999998),
                Block.makeCuboidShape(4.596580000000001, 11.6, 8.269649999999999, 11.996580000000002, 12.7, 9.269649999999999),
                Block.makeCuboidShape(4.196580000000003, 12.7, 8.669649999999997, 11.59658, 16.1, 9.669649999999997),
                Block.makeCuboidShape(3.99658, 13.7, 8.969649999999998, 12.796580000000002, 14.8, 9.969649999999998),
                Block.makeCuboidShape(11.59658, 12.7, 1.5696499999999993, 12.59658, 16.1, 9.669649999999997),
                Block.makeCuboidShape(11.796579999999999, 13.7, 2.269650000000002, 12.796580000000002, 14.8, 8.969649999999998),
                Block.makeCuboidShape(4.196580000000003, 12.7, 1.5696499999999993, 11.59658, 16.1, 2.5696499999999993),
                Block.makeCuboidShape(3.99658, 13.7, 1.2696500000000022, 12.796580000000002, 14.8, 2.269650000000002),
                Block.makeCuboidShape(4.596580000000001, 11.6, 1.9696500000000015, 11.996580000000002, 12.7, 2.9696500000000015),
                Block.makeCuboidShape(4.596580000000001, 11.6, 2.9696500000000015, 5.596580000000001, 12.7, 8.269649999999999),
                Block.makeCuboidShape(4.196580000000003, 12.7, 2.5696499999999993, 5.196580000000003, 16.1, 8.669649999999997),
                Block.makeCuboidShape(3.99658, 13.7, 2.269650000000002, 4.99658, 14.8, 8.969649999999998),
                Block.makeCuboidShape(10.996580000000002, 11.6, 2.9696500000000015, 11.996580000000002, 12.7, 8.269649999999999),
                Block.makeCuboidShape(12.5, 11, 10.499999999999996, 15.5, 16.25, 13.499999999999996),
                Block.makeCuboidShape(13.423380000000002, 11.08198, 11.499999999999996, 15.423380000000002, 12.08198, 12.499999999999996),
                Block.makeCuboidShape(10.21802, 10.92338, 11.499999999999996, 11.21802, 14.32338, 12.499999999999996)
        ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR));

        public static Optional<VoxelShape> SOUTH = Stream.of(
                Block.makeCuboidShape(2, 12, 15, 13.999999999999996, 14, 16),
                Block.makeCuboidShape(2, 1, 15, 13.999999999999996, 3, 16),
                Block.makeCuboidShape(1, 14, 14, 14.999999999999996, 16, 16),
                Block.makeCuboidShape(1, 0, 14, 14.999999999999996, 1, 16),
                Block.makeCuboidShape(2, 10, 1.7763568394002505e-15, 13.999999999999996, 11, 13),
                Block.makeCuboidShape(2, 10, 13, 13.999999999999996, 11, 16),
                Block.makeCuboidShape(12.399999999999999, 0, 1.7763568394002505e-15, 13.999999999999996, 10, 1.5999999999999979),
                Block.makeCuboidShape(2, 0, 1.7763568394002505e-15, 3.6000000000000014, 10, 1.5999999999999979),
                Block.makeCuboidShape(2.3294099999999993, 11, 4.501449999999998, 8.829409999999998, 12, 11.00145),
                Block.makeCuboidShape(8.269649999999999, 11.6, 4.003419999999998, 9.269649999999999, 12.7, 11.403419999999999),
                Block.makeCuboidShape(8.669649999999997, 12.7, 4.403420000000001, 9.669649999999997, 16.1, 11.803419999999997),
                Block.makeCuboidShape(8.969649999999998, 13.7, 3.2034199999999977, 9.969649999999998, 14.8, 12.00342),
                Block.makeCuboidShape(1.5696499999999993, 12.7, 3.4034200000000006, 9.669649999999997, 16.1, 4.403420000000001),
                Block.makeCuboidShape(2.269650000000002, 13.7, 3.2034199999999977, 8.969649999999998, 14.8, 4.203420000000001),
                Block.makeCuboidShape(1.5696499999999993, 12.7, 4.403420000000001, 2.5696499999999993, 16.1, 11.803419999999997),
                Block.makeCuboidShape(1.2696500000000022, 13.7, 3.2034199999999977, 2.269650000000002, 14.8, 12.00342),
                Block.makeCuboidShape(1.9696500000000015, 11.6, 4.003419999999998, 2.9696500000000015, 12.7, 11.403419999999999),
                Block.makeCuboidShape(2.9696500000000015, 11.6, 10.403419999999999, 8.269649999999999, 12.7, 11.403419999999999),
                Block.makeCuboidShape(2.5696499999999993, 12.7, 10.803419999999997, 8.669649999999997, 16.1, 11.803419999999997),
                Block.makeCuboidShape(2.269650000000002, 13.7, 11.00342, 8.969649999999998, 14.8, 12.00342),
                Block.makeCuboidShape(2.9696500000000015, 11.6, 4.003419999999998, 8.269649999999999, 12.7, 5.003419999999998),
                Block.makeCuboidShape(10.499999999999996, 11, 0.5, 13.499999999999996, 16.25, 3.5),
                Block.makeCuboidShape(11.499999999999996, 11.08198, 0.5766199999999984, 12.499999999999996, 12.08198, 2.5766199999999984),
                Block.makeCuboidShape(11.499999999999996, 10.92338, 4.781980000000001, 12.499999999999996, 14.32338, 5.781980000000001)
        ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR));
    }

    private static class TopLeft {

        public static Optional<VoxelShape> WEST = Stream.of(
                Block.makeCuboidShape(0, 7, 13.299999999999999, 15, 8.5, 14.399999999999999),
                Block.makeCuboidShape(15, 7.699999999999999, 13.299999999999999, 16, 8.5, 14.399999999999999),
                Block.makeCuboidShape(0.6431457505076104, 12.90685424949239, 13.299999999999999, 9.34314575050761, 14.15685424949239, 14.549999999999999),
                Block.makeCuboidShape(4.399999999999999, 14.100000000000001, 13.399999999999999, 5.399999999999999, 15.600000000000001, 14.399999999999999),
                Block.makeCuboidShape(8, 14.100000000000001, 13.399999999999999, 9, 15.600000000000001, 14.399999999999999),
                Block.makeCuboidShape(10.699999999999989, 11.799999999999997, 13.399999999999999, 11.699999999999989, 13.299999999999997, 14.399999999999999),
                Block.makeCuboidShape(13.100000000000044, 9.39999999999997, 13.399999999999999, 14.100000000000044, 10.89999999999997, 14.399999999999999),
                Block.makeCuboidShape(1, 14.100000000000001, 13.399999999999999, 2, 15.600000000000001, 14.399999999999999),
                Block.makeCuboidShape(10, 0, 1, 11, 1.3999999999999986, 3),
                Block.makeCuboidShape(0, 0, 12.899999999999999, 1, 7.5, 14.7),
                Block.makeCuboidShape(9, 0, 12.899999999999999, 11, 7.5, 14.7),
                Block.makeCuboidShape(8.5, 0, 12.499999999999998, 11.5, 0.8999999999999986, 14.999999999999998),
                Block.makeCuboidShape(0, 0, 12.499999999999998, 1.5, 0.8999999999999986, 14.999999999999998)
        ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR));

        public static Optional<VoxelShape> NORTH = Stream.of(
                Block.makeCuboidShape(13.299999999999999, 7, 1, 14.399999999999999, 8.5, 16),
                Block.makeCuboidShape(13.299999999999999, 7.699999999999999, 0, 14.399999999999999, 8.5, 1),
                Block.makeCuboidShape(13.299999999999999, 12.90685424949239, 6.65685424949239, 14.549999999999999, 14.15685424949239, 15.35685424949239),
                Block.makeCuboidShape(13.399999999999999, 14.100000000000001, 10.600000000000001, 14.399999999999999, 15.600000000000001, 11.600000000000001),
                Block.makeCuboidShape(13.399999999999999, 14.100000000000001, 7, 14.399999999999999, 15.600000000000001, 8),
                Block.makeCuboidShape(13.399999999999999, 11.799999999999997, 4.300000000000011, 14.399999999999999, 13.299999999999997, 5.300000000000011),
                Block.makeCuboidShape(13.399999999999999, 9.39999999999997, 1.899999999999956, 14.399999999999999, 10.89999999999997, 2.899999999999956),
                Block.makeCuboidShape(13.399999999999999, 14.100000000000001, 14, 14.399999999999999, 15.600000000000001, 15),
                Block.makeCuboidShape(1, 0, 5, 3, 1.3999999999999986, 6),
                Block.makeCuboidShape(12.899999999999999, 0, 15, 14.7, 7.5, 16),
                Block.makeCuboidShape(12.899999999999999, 0, 5, 14.7, 7.5, 7),
                Block.makeCuboidShape(12.499999999999998, 0, 4.5, 14.999999999999998, 0.8999999999999986, 7.5),
                Block.makeCuboidShape(12.499999999999998, 0, 14.5, 14.999999999999998, 0.8999999999999986, 16)
        ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR));

        public static Optional<VoxelShape> EAST = Stream.of(
                Block.makeCuboidShape(1, 7, 1.6000000000000014, 16, 8.5, 2.700000000000001),
                Block.makeCuboidShape(0, 7.699999999999999, 1.6000000000000014, 1, 8.5, 2.700000000000001),
                Block.makeCuboidShape(6.65685424949239, 12.90685424949239, 1.450000000000001, 15.35685424949239, 14.15685424949239, 2.700000000000001),
                Block.makeCuboidShape(10.600000000000001, 14.100000000000001, 1.6000000000000014, 11.600000000000001, 15.600000000000001, 2.6000000000000014),
                Block.makeCuboidShape(7, 14.100000000000001, 1.6000000000000014, 8, 15.600000000000001, 2.6000000000000014),
                Block.makeCuboidShape(4.300000000000011, 11.799999999999997, 1.6000000000000014, 5.300000000000011, 13.299999999999997, 2.6000000000000014),
                Block.makeCuboidShape(1.899999999999956, 9.39999999999997, 1.6000000000000014, 2.899999999999956, 10.89999999999997, 2.6000000000000014),
                Block.makeCuboidShape(14, 14.100000000000001, 1.6000000000000014, 15, 15.600000000000001, 2.6000000000000014),
                Block.makeCuboidShape(5, 0, 13, 6, 1.3999999999999986, 15),
                Block.makeCuboidShape(15, 0, 1.3000000000000007, 16, 7.5, 3.1000000000000014),
                Block.makeCuboidShape(5, 0, 1.3000000000000007, 7, 7.5, 3.1000000000000014),
                Block.makeCuboidShape(4.5, 0, 1.0000000000000018, 7.5, 0.8999999999999986, 3.5000000000000018),
                Block.makeCuboidShape(14.5, 0, 1.0000000000000018, 16, 0.8999999999999986, 3.5000000000000018)
        ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR));

        public static Optional<VoxelShape> SOUTH = Stream.of(
                Block.makeCuboidShape(1.6000000000000014, 7, 0, 2.700000000000001, 8.5, 15),
                Block.makeCuboidShape(1.6000000000000014, 7.699999999999999, 15, 2.700000000000001, 8.5, 16),
                Block.makeCuboidShape(1.450000000000001, 12.90685424949239, 0.6431457505076104, 2.700000000000001, 14.15685424949239, 9.34314575050761),
                Block.makeCuboidShape(1.6000000000000014, 14.100000000000001, 4.399999999999999, 2.6000000000000014, 15.600000000000001, 5.399999999999999),
                Block.makeCuboidShape(1.6000000000000014, 14.100000000000001, 8, 2.6000000000000014, 15.600000000000001, 9),
                Block.makeCuboidShape(1.6000000000000014, 11.799999999999997, 10.699999999999989, 2.6000000000000014, 13.299999999999997, 11.699999999999989),
                Block.makeCuboidShape(1.6000000000000014, 9.39999999999997, 13.100000000000044, 2.6000000000000014, 10.89999999999997, 14.100000000000044),
                Block.makeCuboidShape(1.6000000000000014, 14.100000000000001, 1, 2.6000000000000014, 15.600000000000001, 2),
                Block.makeCuboidShape(13, 0, 10, 15, 1.3999999999999986, 11),
                Block.makeCuboidShape(1.3000000000000007, 0, 0, 3.1000000000000014, 7.5, 1),
                Block.makeCuboidShape(1.3000000000000007, 0, 9, 3.1000000000000014, 7.5, 11),
                Block.makeCuboidShape(1.0000000000000018, 0, 8.5, 3.5000000000000018, 0.8999999999999986, 11.5),
                Block.makeCuboidShape(1.0000000000000018, 0, 0, 3.5000000000000018, 0.8999999999999986, 1.5)
        ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR));
    }

    private static class TopRight {

        public static Optional<VoxelShape> WEST = Stream.of(
                Block.makeCuboidShape(4.00342, 0.1, 6.73035, 11.40342, 1.2, 7.73035),
                Block.makeCuboidShape(4.00342, 0.1, 7.73035, 5.00342, 1.2, 13.13035),
                Block.makeCuboidShape(9.90342, 1.2, 8.13035, 10.90342, 2.2, 12.53035),
                Block.makeCuboidShape(4.40342, 1.2, 12.53035, 10.90342, 2.2, 13.53035),
                Block.makeCuboidShape(4.40342, 1.2, 7.13035, 10.90342, 2.2, 8.13035),
                Block.makeCuboidShape(4.40342, 1.2, 8.13035, 5.40342, 2.2, 12.53035),
                Block.makeCuboidShape(4.00342, 0.1, 13.13035, 11.40342, 1.2, 14.03035),
                Block.makeCuboidShape(10.40342, 0.1, 7.73035, 11.40342, 1.2, 13.13035),
                Block.makeCuboidShape(11, 7, 13.3, 16, 8.5, 14.4),
                Block.makeCuboidShape(10, 7.5, 13.3, 11, 8.5, 14.4),
                Block.makeCuboidShape(11.9, 9.4, 13.4, 12.9, 10.9, 14.4),
                Block.makeCuboidShape(14.4, 11.8, 13.4, 15.4, 13.3, 14.4),
                Block.makeCuboidShape(15, 0, 1, 16, 1.4, 3),
                Block.makeCuboidShape(15, 0, 12.9, 16, 7.5, 14.7),
                Block.makeCuboidShape(14.5, 0, 12.5, 16, 1, 15)
        ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR));

        public static Optional<VoxelShape> NORTH = Stream.of(
                Block.makeCuboidShape(6.73035, 0.10000000000000009, 4.596579999999999, 7.73035, 1.2, 11.99658),
                Block.makeCuboidShape(7.73035, 0.10000000000000009, 10.99658, 13.13035, 1.2, 11.99658),
                Block.makeCuboidShape(8.13035, 1.2, 5.096579999999999, 12.53035, 2.2, 6.096579999999999),
                Block.makeCuboidShape(12.53035, 1.2, 5.096579999999999, 13.53035, 2.2, 11.59658),
                Block.makeCuboidShape(7.13035, 1.2, 5.096579999999999, 8.13035, 2.2, 11.59658),
                Block.makeCuboidShape(8.13035, 1.2, 10.59658, 12.53035, 2.2, 11.59658),
                Block.makeCuboidShape(13.13035, 0.10000000000000009, 4.596579999999999, 14.03035, 1.2, 11.99658),
                Block.makeCuboidShape(7.73035, 0.10000000000000009, 4.596579999999999, 13.13035, 1.2, 5.596579999999999),
                Block.makeCuboidShape(13.3, 7, 0, 14.4, 8.5, 5),
                Block.makeCuboidShape(13.3, 7.5, 5, 14.4, 8.5, 6),
                Block.makeCuboidShape(13.4, 9.4, 3.0999999999999996, 14.4, 10.9, 4.1),
                Block.makeCuboidShape(13.4, 11.8, 0.5999999999999996, 14.4, 13.3, 1.5999999999999996),
                Block.makeCuboidShape(1, 0, 0, 3, 1.4, 1),
                Block.makeCuboidShape(12.9, 0, 0, 14.7, 7.5, 1),
                Block.makeCuboidShape(12.5, 0, 0, 15, 1, 1.5)
        ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR));

        public static Optional<VoxelShape> EAST = Stream.of(
                Block.makeCuboidShape(4.596579999999999, 0.10000000000000009, 8.26965, 11.99658, 1.2, 9.26965),
                Block.makeCuboidShape(10.99658, 0.10000000000000009, 2.86965, 11.99658, 1.2, 8.26965),
                Block.makeCuboidShape(5.096579999999999, 1.2, 3.4696499999999997, 6.096579999999999, 2.2, 7.86965),
                Block.makeCuboidShape(5.096579999999999, 1.2, 2.4696499999999997, 11.59658, 2.2, 3.4696499999999997),
                Block.makeCuboidShape(5.096579999999999, 1.2, 7.86965, 11.59658, 2.2, 8.86965),
                Block.makeCuboidShape(10.59658, 1.2, 3.4696499999999997, 11.59658, 2.2, 7.86965),
                Block.makeCuboidShape(4.596579999999999, 0.10000000000000009, 1.9696499999999997, 11.99658, 1.2, 2.86965),
                Block.makeCuboidShape(4.596579999999999, 0.10000000000000009, 2.86965, 5.596579999999999, 1.2, 8.26965),
                Block.makeCuboidShape(0, 7, 1.5999999999999996, 5, 8.5, 2.6999999999999993),
                Block.makeCuboidShape(5, 7.5, 1.5999999999999996, 6, 8.5, 2.6999999999999993),
                Block.makeCuboidShape(3.0999999999999996, 9.4, 1.5999999999999996, 4.1, 10.9, 2.5999999999999996),
                Block.makeCuboidShape(0.5999999999999996, 11.8, 1.5999999999999996, 1.5999999999999996, 13.3, 2.5999999999999996),
                Block.makeCuboidShape(0, 0, 13, 1, 1.4, 15),
                Block.makeCuboidShape(0, 0, 1.3000000000000007, 1, 7.5, 3.0999999999999996),
                Block.makeCuboidShape(0, 0, 1, 1.5, 1, 3.5)
        ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR));

        public static Optional<VoxelShape> SOUTH = Stream.of(
                Block.makeCuboidShape(8.26965, 0.10000000000000009, 4.00342, 9.26965, 1.2, 11.40342),
                Block.makeCuboidShape(2.86965, 0.10000000000000009, 4.00342, 8.26965, 1.2, 5.00342),
                Block.makeCuboidShape(3.4696499999999997, 1.2, 9.90342, 7.86965, 2.2, 10.90342),
                Block.makeCuboidShape(2.4696499999999997, 1.2, 4.403420000000001, 3.4696499999999997, 2.2, 10.90342),
                Block.makeCuboidShape(7.86965, 1.2, 4.403420000000001, 8.86965, 2.2, 10.90342),
                Block.makeCuboidShape(3.4696499999999997, 1.2, 4.403420000000001, 7.86965, 2.2, 5.403420000000001),
                Block.makeCuboidShape(1.9696499999999997, 0.10000000000000009, 4.00342, 2.86965, 1.2, 11.40342),
                Block.makeCuboidShape(2.86965, 0.10000000000000009, 10.40342, 8.26965, 1.2, 11.40342),
                Block.makeCuboidShape(1.5999999999999996, 7, 11, 2.6999999999999993, 8.5, 16),
                Block.makeCuboidShape(1.5999999999999996, 7.5, 10, 2.6999999999999993, 8.5, 11),
                Block.makeCuboidShape(1.5999999999999996, 9.4, 11.9, 2.5999999999999996, 10.9, 12.9),
                Block.makeCuboidShape(1.5999999999999996, 11.8, 14.4, 2.5999999999999996, 13.3, 15.4),
                Block.makeCuboidShape(13, 0, 15, 15, 1.4, 16),
                Block.makeCuboidShape(1.3000000000000007, 0, 15, 3.0999999999999996, 7.5, 16),
                Block.makeCuboidShape(1, 0, 14.5, 3.5, 1, 16)
        ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR));
    }
}
