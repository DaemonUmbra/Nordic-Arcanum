package com.lordskittles.nordicarcanum.common.block.decoration;

import com.lordskittles.arcanumapi.common.block.BlockMod;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicDecorationItemGroup;
import com.lordskittles.nordicarcanum.common.block.SidePlacement;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BlockLamp extends BlockMod {

    private final SidePlacement placement;
    private final VoxelShape shape;

    public BlockLamp(SidePlacement placement, VoxelShape shape) {

        super(BlockBehaviour.Properties.of(Material.GLASS)
                .strength(0.3F)
                .sound(SoundType.GLASS), 15);

        this.placement = placement;
        this.shape = shape;
        this.group = NordicDecorationItemGroup.INSTANCE;
    }

    @Override
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {

        BlockState superState = super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
        BlockState air = Blocks.AIR.defaultBlockState();

        return canSurvive(stateIn, worldIn, currentPos) ? superState : air;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {

        switch(this.placement) {
            case NORMAL:
                return true;
            case BOTTOM_ONLY:
                return canSupportCenter(world, pos.above(), Direction.DOWN);
            case TOP_ONLY:
                return canSupportCenter(world, pos.below(), Direction.UP);
        }

        return true;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {

        if(this.shape == null) {
            return super.getShape(state, worldIn, pos, context);
        }
        return this.shape;
    }
}
