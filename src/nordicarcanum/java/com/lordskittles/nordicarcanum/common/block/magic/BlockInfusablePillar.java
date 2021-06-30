package com.lordskittles.nordicarcanum.common.block.magic;

import com.lordskittles.nordicarcanum.common.block.IInfusable;
import com.lordskittles.nordicarcanum.common.block.decoration.BlockPillar;
import com.lordskittles.nordicarcanum.common.registry.Blocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class BlockInfusablePillar extends BlockPillar implements IInfusable
{
    public static BooleanProperty ACTIVATED = BooleanProperty.create("activated");

    public BlockInfusablePillar()
    {
        super();

        this.setDefaultState(this.stateContainer.getBaseState().with(AXIS, Direction.Axis.Y).with(ACTIVATED, false));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(AXIS).add(ACTIVATED);
    }

    @Override
    public boolean isValid(IWorldReader world, BlockPos pos, BlockPos right, BlockState state)
    {
        if(state.get(ACTIVATED) && world.getBlockState(pos.up()).getBlock() instanceof BlockCrystalMatrix)
            return true;
        else
            return !state.get(ACTIVATED);
    }

    @Override
    public void infuse(World world, BlockPos pos, BlockPos right, BlockState state, Direction direction)
    {
        if(state.get(ACTIVATED) && world.getBlockState(pos.up()).getBlock() instanceof BlockCrystalMatrix)
        {
            world.setBlockState(pos.up(), net.minecraft.block.Blocks.AIR.getDefaultState());
            world.setBlockState(pos, Blocks.attunement_altar.get().getDefaultState());
        }
        else
        {
            world.setBlockState(pos, state.with(ACTIVATED, true));
        }
    }
}
