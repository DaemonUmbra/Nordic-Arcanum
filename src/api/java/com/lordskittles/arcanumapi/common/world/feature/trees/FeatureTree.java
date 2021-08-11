package com.lordskittles.arcanumapi.common.world.feature.trees;

import com.google.common.collect.Sets;
import com.lordskittles.arcanumapi.common.utilities.BlockUtilities;
import com.mojang.serialization.Codec;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.tags.BlockTags;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.LevelSimulatedRW;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.TreeFeature;

import java.util.Random;
import java.util.Set;

@SuppressWarnings("rawtypes")
public abstract class FeatureTree<T extends TreeConfiguration> extends Feature<T> {

    protected Property logAxisProperty;

    private final BlockState log;
    private final BlockState leaves;

    protected TreeConfiguration config;
    protected Random random;
    protected WorldGenLevel world;

    public FeatureTree(BlockState log, BlockState leaves, Codec<T> config) {

        super(config);

        this.log = log;
        this.leaves = leaves;
        this.logAxisProperty = BlockUtilities.getAxisProperty(this.log);
    }

    public boolean place(FeaturePlaceContext<T> context) {

        this.random = context.random();

        Set<BlockPos> changedLeaves = Sets.newHashSet();
        Set<BlockPos> changedLogs = Sets.newHashSet();

        if(isGrassOrDirt(world, context.origin().below()) && isAirOrLeavesAt(world, context.origin())) {
            placeTrunk(context.origin(), changedLogs);
            placeLeaves(context.origin(), changedLeaves);
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

        this.world.setBlock(pos, leaves, 13);

        changedLeaves.add(pos.immutable());
    }

    protected boolean placeLog(BlockPos pos, Set<BlockPos> changedLogs) {

        return placeLog(pos, (Direction.Axis) null, changedLogs);
    }

    @SuppressWarnings("unchecked")
    protected boolean placeLog(BlockPos pos, Direction.Axis axis, Set<BlockPos> changedLogs) {

        BlockState directedLog = (axis != null && this.logAxisProperty != null) ? (BlockState) log.setValue(this.logAxisProperty, (Comparable) axis) : log;
        return placeBlock(pos, directedLog, changedLogs);
    }

    private boolean placeBlock(BlockPos pos, BlockState state, Set<BlockPos> changedBlocks) {

        if(isReplaceableAt(world, pos)) {
            this.setBlock(world, pos, state);
            changedBlocks.add(pos.immutable());

            return true;
        }

        return false;
    }

    protected abstract void placeTrunk(BlockPos position, Set<BlockPos> changedLogs);

    protected abstract void placeLeaves(BlockPos position, Set<BlockPos> changedLeaves);

    protected void setDirtAt(LevelSimulatedRW world, BlockPos pos) {

        world.setBlock(pos, Blocks.DIRT.defaultBlockState(), 3);
    }

    protected void placeDirt(BlockPos pos) {

        if(! TreeFeature.validTreePos(world, pos)) {
            world.setBlock(pos, Blocks.DIRT.defaultBlockState(), 19);
        }
    }

    public static boolean isReplaceableAt(LevelSimulatedReader reader, BlockPos position) {

        return isAirOrLeavesAt(reader, position) || isTallPlantAt(reader, position) || isWaterAt(reader, position);
    }

    public static boolean isLogAt(LevelSimulatedReader reader, BlockPos position) {

        return isReplaceableAt(reader, position) || reader.isStateAtPosition(position, (world) ->
        {
            return world.is(BlockTags.LOGS);
        });
    }

    private static boolean isVineAt(LevelSimulatedReader reader, BlockPos position) {

        return reader.isStateAtPosition(position, (state) ->
        {
            return state.is(Blocks.VINE);
        });
    }

    private static boolean isWaterAt(LevelSimulatedReader reader, BlockPos position) {

        return reader.isStateAtPosition(position, (state) ->
        {
            return state.is(Blocks.WATER);
        });
    }

    public static boolean isAirOrLeavesAt(LevelSimulatedReader reader, BlockPos position) {

        return reader.isStateAtPosition(position, (world) ->
        {
            return world.isAir() || world.is(BlockTags.LEAVES);
        });
    }

    private static boolean isDirtOrFarmlandAt(LevelSimulatedReader reader, BlockPos position) {

        return reader.isStateAtPosition(position, (world) ->
        {
            Block block = world.getBlock();
            return isDirt(block) || block == Blocks.FARMLAND;
        });
    }

    private static boolean isTallPlantAt(LevelSimulatedReader reader, BlockPos position) {

        return reader.isStateAtPosition(position, (world) ->
        {
            Material material = world.getMaterial();
            return material == Material.REPLACEABLE_PLANT;
        });
    }

    protected static boolean isStone(Block block) {

        return net.minecraftforge.common.Tags.Blocks.STONE.contains(block);
    }

    public static boolean isDirt(Block block) {

        return net.minecraftforge.common.Tags.Blocks.DIRT.contains(block);
    }
}
