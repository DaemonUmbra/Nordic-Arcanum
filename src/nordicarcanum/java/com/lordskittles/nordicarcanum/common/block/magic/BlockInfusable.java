package com.lordskittles.nordicarcanum.common.block.magic;

import com.lordskittles.arcanumapi.common.block.MultiBlockPiece;
import com.lordskittles.nordicarcanum.common.block.IInfusable;
import com.lordskittles.nordicarcanum.common.block.crafting.BlockAlchemyTable;
import com.lordskittles.nordicarcanum.common.block.crafting.BlockStaffWorkbench;
import com.lordskittles.nordicarcanum.common.block.decoration.BlockDecoration;
import com.lordskittles.nordicarcanum.common.registry.Blocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class BlockInfusable extends BlockDecoration implements IInfusable {

    public enum InfusionType {
        STAFF_WORKBENCH,
        SIGIL_PODIUM
    }

    private final InfusionType type;

    public BlockInfusable(Material material, MaterialColor color, InfusionType type) {

        super(material, color);

        this.type = type;
    }

    @Override
    public boolean isValid(LevelReader world, BlockPos pos, BlockPos right, BlockState state) {

        BlockState rightState = world.getBlockState(right);
        BlockState upRightState = world.getBlockState(right.above()).getBlock().defaultBlockState();
        BlockState upState = world.getBlockState(pos.above()).getBlock().defaultBlockState();

        BlockState steel_block = Blocks.steel_metal_compact.get().defaultBlockState();
        BlockState yew_stair = Blocks.yew_stairs.get().defaultBlockState();

        switch(type) {
            case STAFF_WORKBENCH:
                return state == steel_block && rightState == steel_block && upRightState == yew_stair && upState == yew_stair;
            case SIGIL_PODIUM:
                return state == Blocks.carved_deepslate_brick.get().defaultBlockState();
        }

        return false;
    }

    @Override
    public void infuse(Level world, BlockPos pos, BlockPos right, BlockState state, Direction direction) {

        switch(type) {
            case STAFF_WORKBENCH:
                BlockState staffWorkbench = Blocks.staff_workbench.get().defaultBlockState().setValue(BlockStaffWorkbench.FACING, direction);

                world.setBlock(pos, staffWorkbench.setValue(BlockAlchemyTable.PIECE, MultiBlockPiece.BOTTOM_LEFT), 19);
                world.setBlock(pos.above(), staffWorkbench.setValue(BlockAlchemyTable.PIECE, MultiBlockPiece.TOP_LEFT), 19);
                world.setBlock(right, staffWorkbench.setValue(BlockAlchemyTable.PIECE, MultiBlockPiece.BOTTOM_RIGHT), 19);
                world.setBlock(right.above(), staffWorkbench.setValue(BlockAlchemyTable.PIECE, MultiBlockPiece.TOP_RIGHT), 19);
                break;
            case SIGIL_PODIUM:
                world.setBlock(pos, Blocks.sigil_podium.get().defaultBlockState(), 19);
                break;
        }
    }

    @Override
    public BlockPos[] getInfusedPositions(Level world, BlockPos pos, BlockPos right, BlockState state, Direction direction) {

        switch(type) {
            case STAFF_WORKBENCH:
                return new BlockPos[] { pos, pos.above(), right, right.above() };
            case SIGIL_PODIUM:
                return new BlockPos[] { pos };
        }

        return new BlockPos[] { pos };
    }
}
