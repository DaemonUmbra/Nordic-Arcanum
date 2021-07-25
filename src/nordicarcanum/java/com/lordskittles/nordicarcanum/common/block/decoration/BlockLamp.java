package com.lordskittles.nordicarcanum.common.block.decoration;

import com.lordskittles.arcanumapi.common.block.BlockMod;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicDecorationItemGroup;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicItemGroup;
import com.lordskittles.nordicarcanum.common.block.SidePlacement;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;

public class BlockLamp extends BlockMod {

    private final SidePlacement placement;
    private final VoxelShape shape;

    public BlockLamp(SidePlacement placement, VoxelShape shape) {

        super(BlockBehaviour.Properties.create(Material.GLASS)
                .hardnessAndResistance(0.3F)
                .sound(SoundType.GLASS), 15);

        this.placement = placement;
        this.shape = shape;
        this.group = NordicDecorationItemGroup.INSTANCE;
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {

        BlockState superState = super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
        BlockState air = Blocks.AIR.getDefaultState();

        return isValidPosition(stateIn, worldIn, currentPos) ? superState : air;
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {

        switch(this.placement) {
            case NORMAL:
                return true;
            case BOTTOM_ONLY:
                return hasEnoughSolidSide(worldIn, pos.up(), Direction.DOWN);
            case TOP_ONLY:
                return hasSolidSideOnTop(worldIn, pos.down());
        }

        return true;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {

        if(this.shape == null) {
            return super.getShape(state, worldIn, pos, context);
        }
        return this.shape;
    }
}
