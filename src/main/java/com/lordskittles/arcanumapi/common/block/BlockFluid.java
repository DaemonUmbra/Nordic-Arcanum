package com.lordskittles.arcanumapi.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.Supplier;

public class BlockFluid extends LiquidBlock {

    public BlockFluid(Supplier<? extends FlowingFluid> fluidSupplier, Properties properties) {

        super(fluidSupplier, properties);
    }

    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {

        return context.isAbove(STABLE_SHAPE, pos, true) && state.getValue(LEVEL) == 0 && context.canStandOnFluid(level.getFluidState(pos.above()), this.getFluid()) ? STABLE_SHAPE : Shapes.empty();
    }

    public boolean isPathfindable(BlockState p_54704_, BlockGetter p_54705_, BlockPos p_54706_, PathComputationType p_54707_) {

        return false;
    }

    public boolean skipRendering(BlockState state, BlockState state1, Direction direction) {

        return state1.getFluidState().getType().isSame(this.getFluid());
    }

    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState output, boolean bool) {

        if(this.shouldSpreadLiquid(level, pos, state)) {
            level.getLiquidTicks().scheduleTick(pos, state.getFluidState().getType(), this.getFluid().getTickDelay(level));
        }
    }

    public BlockState updateShape(BlockState fluidState, Direction direction, BlockState fluidState1, LevelAccessor level, BlockPos pos, BlockPos pos1) {

        if(fluidState.getFluidState().isSource() || fluidState1.getFluidState().isSource()) {
            level.getLiquidTicks().scheduleTick(pos, fluidState.getFluidState().getType(), this.getFluid().getTickDelay(level));
        }

        return fluidState;
    }

    public void neighborChanged(BlockState state, Level level, BlockPos position, Block block, BlockPos end, boolean bool) {

        if(this.shouldSpreadLiquid(level, position, state)) {
            level.getLiquidTicks().scheduleTick(position, state.getFluidState().getType(), this.getFluid().getTickDelay(level));
        }

    }

    public ItemStack pickupBlock(LevelAccessor level, BlockPos pos, BlockState state) {

        if(state.getValue(LEVEL) == 0) {
            level.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);
            return new ItemStack(this.getFluid().getBucket());
        }
        else {
            return ItemStack.EMPTY;
        }
    }

    private boolean shouldSpreadLiquid(Level level, BlockPos pos, BlockState state) {

        return true;
    }
}
