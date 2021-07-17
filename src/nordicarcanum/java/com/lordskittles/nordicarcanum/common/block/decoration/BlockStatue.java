package com.lordskittles.nordicarcanum.common.block.decoration;

import com.lordskittles.arcanumapi.common.block.BlockMod;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicDecorationItemGroup;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicResourcesItemGroup;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class BlockStatue extends BlockMod {

    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

    public BlockStatue(Block propertyBlock) {

        this(AbstractBlock.Properties.from(propertyBlock));
    }

    public BlockStatue(Block.Properties properties) {

        super(properties);

        this.group = NordicDecorationItemGroup.INSTANCE;
        this.setDefaultState(this.getStateContainer().getBaseState().with(FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {

        return Block.makeCuboidShape(1, 0, 1, 15, 1, 15);
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {

        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {

        builder.add(FACING);
    }
}
