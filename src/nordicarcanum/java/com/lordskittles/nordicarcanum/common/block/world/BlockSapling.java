package com.lordskittles.nordicarcanum.common.block.world;

import com.lordskittles.arcanumapi.common.block.IItemGroupHolder;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicResourcesItemGroup;
import net.minecraft.block.*;
import net.minecraft.block.trees.Tree;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemGroup;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.ForgeEventFactory;

import java.util.Random;
import java.util.function.Supplier;

public class BlockSapling extends BushBlock implements IGrowable, IItemGroupHolder {

    public static final IntegerProperty STAGE = BlockStateProperties.STAGE_0_1;
    protected static final VoxelShape SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);

    private final Supplier<Tree> tree;

    public BlockSapling(Supplier<Tree> tree) {

        super(Block.Properties.from(Blocks.DEAD_BUSH));

        this.tree = tree;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {

        return SHAPE;
    }

    @Override
    public boolean isReplaceable(BlockState state, BlockItemUseContext useContext) {

        return false;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void tick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {

        super.tick(state, world, pos, rand);
        if(! world.isAreaLoaded(pos, 1)) {
            return;
        }

        if(world.getLight(pos.up()) >= 9 && rand.nextInt(7) == 0) {
            this.grow(world, pos, state, rand);
        }
    }

    public void grow(ServerWorld world, BlockPos pos, BlockState state, Random rand) {

        if(state.get(STAGE) == 0) {
            world.setBlockState(pos, state.cycleValue(STAGE), 4);
        }
        else {
            if(! ForgeEventFactory.saplingGrowTree(world, rand, pos) || ! world.canBlockSeeSky(pos)) {
                return;
            }

            this.tree.get().attemptGrowTree(world, world.getChunkProvider().getChunkGenerator(), pos, state, rand);
        }
    }

    @Override
    public void grow(ServerWorld world, Random rand, BlockPos pos, BlockState state) {

        this.grow(world, pos, state, rand);
    }

    @Override
    public boolean canGrow(IBlockReader reader, BlockPos pos, BlockState state, boolean isClient) {

        return true;
    }

    @Override
    public boolean canUseBonemeal(World world, Random rand, BlockPos pos, BlockState state) {

        return (double) world.rand.nextFloat() < 0.45D;
    }

    @Override
    public void fillStateContainer(Builder<Block, BlockState> builder) {

        builder.add(STAGE);
    }

    @Override
    public ItemGroup group() {

        return NordicResourcesItemGroup.INSTANCE;
    }
}
