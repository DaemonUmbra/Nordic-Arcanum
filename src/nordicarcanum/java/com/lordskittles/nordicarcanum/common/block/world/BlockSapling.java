package com.lordskittles.nordicarcanum.common.block.world;

import com.lordskittles.arcanumapi.common.block.IItemGroupHolder;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicResourcesItemGroup;
import com.lordskittles.nordicarcanum.common.block.world.trees.NordicTree;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.event.ForgeEventFactory;

import java.util.Random;
import java.util.function.Supplier;

public class BlockSapling extends BushBlock implements BonemealableBlock, IItemGroupHolder {

    public static final IntegerProperty STAGE = BlockStateProperties.STAGE;
    protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);

    private final Supplier<NordicTree> tree;

    public BlockSapling(Supplier<NordicTree> tree) {

        super(Block.Properties.copy(Blocks.DEAD_BUSH));

        this.tree = tree;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {

        return SHAPE;
    }

    @Override
    public boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {

        return false;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, Random rand) {

        super.tick(state, level, pos, rand);
        if(! level.isAreaLoaded(pos, 1)) {
            return;
        }

        if(level.getLightEmission(pos.above()) >= 9 && rand.nextInt(7) == 0) {
            this.grow(level, pos, state, rand);
        }
    }

    public void grow(ServerLevel level, BlockPos pos, BlockState state, Random rand) {

        if(state.getValue(STAGE) == 0) {
            level.setBlock(pos, state.cycle(STAGE), 4);
        }
        else {
            if(! ForgeEventFactory.saplingGrowTree(level, rand, pos) || ! level.canSeeSky(pos)) {
                return;
            }

            if(this.tree.get().growTree(level, level.getChunkSource().getGenerator(), pos, state, rand)) {
                level.setBlock(pos, this.tree.get().getConfig().trunkProvider.getState(rand, pos), 19);
            }
        }
    }

    @Override
    public void performBonemeal(ServerLevel world, Random rand, BlockPos pos, BlockState state) {

        this.grow(world, pos, state, rand);
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter reader, BlockPos pos, BlockState state, boolean isClient) {

        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level level, Random rand, BlockPos pos, BlockState state) {

        return (double) rand.nextFloat() < 0.45D;
    }

    @Override
    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {

        builder.add(STAGE);
    }

    @Override
    public CreativeModeTab group() {

        return NordicResourcesItemGroup.INSTANCE;
    }
}
