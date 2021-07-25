package com.lordskittles.nordicarcanum.common.block.magic;

import com.lordskittles.nordicarcanum.common.block.IInfusable;
import com.lordskittles.nordicarcanum.common.block.decoration.BlockPillar;
import com.lordskittles.nordicarcanum.common.registry.Blocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class BlockInfusablePillar extends BlockPillar implements IInfusable {

    public static BooleanProperty ACTIVATED = BooleanProperty.create("activated");

    public BlockInfusablePillar() {

        super();

        this.registerDefaultState(this.getStateDefinition().any().setValue(AXIS, Direction.Axis.Y).setValue(ACTIVATED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {

        builder.add(AXIS).add(ACTIVATED);
    }

    @Override
    public boolean isValid(LevelReader world, BlockPos pos, BlockPos right, BlockState state) {

        if(state.getValue(ACTIVATED) && world.getBlockState(pos.above()).getBlock() instanceof BlockCrystalMatrix)
            return true;
        else
            return ! state.getValue(ACTIVATED);
    }

    @Override
    public void infuse(Level world, BlockPos pos, BlockPos right, BlockState state, Direction direction) {

        if(state.getValue(ACTIVATED) && world.getBlockState(pos.above()).getBlock() instanceof BlockCrystalMatrix) {
            world.setBlock(pos.above(), net.minecraft.world.level.block.Blocks.AIR.defaultBlockState(), 19);
            world.setBlock(pos, Blocks.attunement_altar.get().defaultBlockState(), 19);
        }
        else {
            world.setBlock(pos, state.setValue(ACTIVATED, true), 19);
        }
    }

    @Override
    public BlockPos[] getInfusedPositions(Level world, BlockPos pos, BlockPos right, BlockState state, Direction direction) {

        if(state.getValue(ACTIVATED)) {
            return new BlockPos[] { pos, pos.above() };
        }

        return new BlockPos[] { pos };
    }
}
