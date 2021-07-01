package com.lordskittles.arcanumapi.common.world.feature.trees;

import com.google.common.collect.Sets;
import com.lordskittles.arcanumapi.common.utilities.BlockUtilities;
import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.state.Property;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeature;

import java.util.Random;
import java.util.Set;

@SuppressWarnings("rawtypes")
public abstract class FeatureTree<T extends BaseTreeFeatureConfig> extends Feature<T> {

    protected Property logAxisProperty;

    private final BlockState log;
    private final BlockState leaves;

    protected BaseTreeFeatureConfig config;
    protected Random random;
    protected IWorld world;

    public FeatureTree(BlockState log, BlockState leaves, Codec<T> config) {

        super(config);

        this.log = log;
        this.leaves = leaves;
        this.logAxisProperty = BlockUtilities.getAxisProperty(this.log);
    }

    @Override
    public boolean generate(ISeedReader world, ChunkGenerator generator, Random rand, BlockPos pos, T config) {

        this.random = rand;
        this.config = config;
        this.world = (IWorld) world;

        Set<BlockPos> changedLeaves = Sets.newHashSet();
        Set<BlockPos> changedLogs = Sets.newHashSet();

        if(isDirtAt(world, pos.down()) && isAirOrLeavesAt(world, pos)) {
            placeTrunk(pos, changedLogs);
            placeLeaves(pos, changedLeaves);
            return true;
        }

        return false;
    }

    protected void placeLeafBox(BlockPos startPos, BlockPos endPos, Set<BlockPos> changedLeaves) {

        for(int x = 0; x < endPos.getX(); x++) {
            for(int y = 0; y < endPos.getY(); y++) {
                for(int z = 0; z < endPos.getZ(); z++) {
                    BlockPos leafPos = new BlockPos(startPos.getX() + x, startPos.getY() + y, startPos.getZ() + z);
                    placeLeaf(leafPos, changedLeaves);
                }
            }
        }
    }

    protected void placeLeaf(BlockPos pos, Set<BlockPos> changedLeaves) {

        this.world.setBlockState(pos, leaves, 13);

        changedLeaves.add(pos.toImmutable());
    }

    protected boolean placeLog(BlockPos pos, Set<BlockPos> changedLogs) {

        return placeLog(pos, (Direction.Axis) null, changedLogs);
    }

    @SuppressWarnings("unchecked")
    protected boolean placeLog(BlockPos pos, Direction.Axis axis, Set<BlockPos> changedLogs) {

        BlockState directedLog = (axis != null && this.logAxisProperty != null) ? (BlockState) log.with(this.logAxisProperty, (Comparable) axis) : log;
        return placeBlock(pos, directedLog, changedLogs);
    }

    private boolean placeBlock(BlockPos pos, BlockState state, Set<BlockPos> changedBlocks) {

        if(isReplaceableAt(world, pos)) {
            this.setBlockState(world, pos, state);
            changedBlocks.add(pos.toImmutable());

            return true;
        }

        return false;
    }

    protected abstract void placeTrunk(BlockPos position, Set<BlockPos> changedLogs);

    protected abstract void placeLeaves(BlockPos position, Set<BlockPos> changedLeaves);

    protected void setDirtAt(IWorldGenerationReader world, BlockPos pos) {

        world.setBlockState(pos, Blocks.DIRT.getDefaultState(), 3);
    }

    protected void placeDirt(BlockPos pos) {

        if(! TreeFeature.isReplaceableAt(world, pos)) {
            TreeFeature.setBlockStateWithoutUpdate(world, pos, Blocks.DIRT.getDefaultState());
        }
    }

    public static boolean isReplaceableAt(IWorldGenerationBaseReader reader, BlockPos position) {

        return isAirOrLeavesAt(reader, position) || isTallPlantAt(reader, position) || isWaterAt(reader, position);
    }

    public static boolean isLogAt(IWorldGenerationBaseReader reader, BlockPos position) {

        return isReplaceableAt(reader, position) || reader.hasBlockState(position, (world) ->
        {
            return world.isIn(BlockTags.LOGS);
        });
    }

    private static boolean isVineAt(IWorldGenerationBaseReader reader, BlockPos position) {

        return reader.hasBlockState(position, (state) ->
        {
            return state.matchesBlock(Blocks.VINE);
        });
    }

    private static boolean isWaterAt(IWorldGenerationBaseReader reader, BlockPos position) {

        return reader.hasBlockState(position, (state) ->
        {
            return state.matchesBlock(Blocks.WATER);
        });
    }

    public static boolean isAirOrLeavesAt(IWorldGenerationBaseReader reader, BlockPos position) {

        return reader.hasBlockState(position, (world) ->
        {
            return world.isAir() || world.isIn(BlockTags.LEAVES);
        });
    }

    private static boolean isDirtOrFarmlandAt(IWorldGenerationBaseReader reader, BlockPos position) {

        return reader.hasBlockState(position, (world) ->
        {
            Block block = world.getBlock();
            return isDirt(block) || block == Blocks.FARMLAND;
        });
    }

    private static boolean isTallPlantAt(IWorldGenerationBaseReader reader, BlockPos position) {

        return reader.hasBlockState(position, (world) ->
        {
            Material material = world.getMaterial();
            return material == Material.TALL_PLANTS;
        });
    }

    protected static boolean isStone(Block block) {

        return net.minecraftforge.common.Tags.Blocks.STONE.contains(block);
    }

    public static boolean isDirt(Block block) {

        return net.minecraftforge.common.Tags.Blocks.DIRT.contains(block);
    }
}
