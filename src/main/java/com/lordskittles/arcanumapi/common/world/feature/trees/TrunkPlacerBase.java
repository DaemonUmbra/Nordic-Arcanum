package com.lordskittles.arcanumapi.common.world.feature.trees;

import com.google.common.collect.ImmutableList;
import com.lordskittles.arcanumapi.common.utilities.BlockUtilities;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.LevelSimulatedRW;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.BiConsumer;

public abstract class TrunkPlacerBase extends TrunkPlacer {

    protected Property logAxisProperty;

    protected Random random;
    protected LevelSimulatedReader world;

    private TreeConfiguration config;
    private final TrunkPlacerType<?> type;

    public TrunkPlacerBase(int baseHeight, int heightRandA, int heightRandB, TrunkPlacerType<?> type) {

        super(baseHeight, heightRandA, heightRandB);

        this.type = type;
    }

    @Override
    protected TrunkPlacerType<?> type() {

        return type;
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> consumer, Random random, int p_161871_, BlockPos start, TreeConfiguration config) {

        this.random = random;
        this.world = world;
        this.config = config;
        this.logAxisProperty = BlockUtilities.getAxisProperty(this.config.trunkProvider.getState(this.random, start));

        setDirtAt(this.world, consumer, random, start.below(), config);

        placeTrunk(start, consumer);

        return ImmutableList.of(new FoliagePlacer.FoliageAttachment(start.above(this.baseHeight), 0, false));
    }

    protected abstract void placeTrunk(BlockPos position, BiConsumer<BlockPos, BlockState> changedLogs);

    protected boolean placeLog(BlockPos pos, BiConsumer<BlockPos, BlockState> consumer) {

        return placeLog(pos, (Direction.Axis) null, consumer);
    }

    @SuppressWarnings("unchecked")
    protected boolean placeLog(BlockPos pos, Direction.Axis axis, BiConsumer<BlockPos, BlockState> consumer) {

        BlockState directedLog = (axis != null && this.logAxisProperty != null) ? (BlockState) this.config.trunkProvider.getState(this.random, pos).setValue(this.logAxisProperty, (Comparable) axis) : this.config.trunkProvider.getState(this.random, pos);
        return placeBlock(pos, directedLog, consumer);
    }

    private boolean placeBlock(BlockPos pos, BlockState state, BiConsumer<BlockPos, BlockState> consumer) {

        if(TreeFeature.validTreePos(world, pos)) {
            consumer.accept(pos.immutable(), state);

            return true;
        }

        return false;
    }
}
