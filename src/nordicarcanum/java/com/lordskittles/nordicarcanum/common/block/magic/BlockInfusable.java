package com.lordskittles.nordicarcanum.common.block.magic;

import com.lordskittles.arcanumapi.common.block.MultiBlockPiece;
import com.lordskittles.nordicarcanum.common.block.IInfusable;
import com.lordskittles.nordicarcanum.common.block.crafting.BlockAlchemyTable;
import com.lordskittles.nordicarcanum.common.block.crafting.BlockStaffWorkbench;
import com.lordskittles.nordicarcanum.common.block.decoration.BlockDecoration;
import com.lordskittles.nordicarcanum.common.registry.Blocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

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
    public boolean isValid(IWorldReader world, BlockPos pos, BlockPos right, BlockState state) {

        BlockState rightState = world.getBlockState(right);
        BlockState upRightState = world.getBlockState(right.up()).getBlock().getDefaultState();
        BlockState upState = world.getBlockState(pos.up()).getBlock().getDefaultState();

        BlockState steel_block = Blocks.steel_metal_compact.get().getDefaultState();
        BlockState yew_stair = Blocks.yew_stairs.get().getDefaultState();

        switch(type) {
            case STAFF_WORKBENCH:
                return state == steel_block && rightState == steel_block && upRightState == yew_stair && upState == yew_stair;
            case SIGIL_PODIUM:
                return state == Blocks.carved_deepslate_brick.get().getDefaultState();
        }

        return false;
    }

    @Override
    public void infuse(World world, BlockPos pos, BlockPos right, BlockState state, Direction direction) {

        switch(type) {
            case STAFF_WORKBENCH:
                BlockState staffWorkbench = Blocks.staff_workbench.get().getDefaultState().with(BlockStaffWorkbench.FACING, direction);

                world.setBlockState(pos, staffWorkbench.with(BlockAlchemyTable.PIECE, MultiBlockPiece.BOTTOM_LEFT));
                world.setBlockState(pos.up(), staffWorkbench.with(BlockAlchemyTable.PIECE, MultiBlockPiece.TOP_LEFT));
                world.setBlockState(right, staffWorkbench.with(BlockAlchemyTable.PIECE, MultiBlockPiece.BOTTOM_RIGHT));
                world.setBlockState(right.up(), staffWorkbench.with(BlockAlchemyTable.PIECE, MultiBlockPiece.TOP_RIGHT));
                break;
            case SIGIL_PODIUM:
                world.setBlockState(pos, Blocks.sigil_podium.get().getDefaultState());
                break;
        }
    }

    @Override
    public BlockPos[] getInfusedPositions(World world, BlockPos pos, BlockPos right, BlockState state, Direction direction) {
        switch(type) {
            case STAFF_WORKBENCH:
                return new BlockPos[] { pos, pos.up(), right, right.up() };
            case SIGIL_PODIUM:
                return new BlockPos[] { pos };
        }

        return new BlockPos[] { pos };
    }
}
