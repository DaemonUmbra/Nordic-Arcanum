package com.lordskittles.arcanumapi.common.world.feature.trees;

import com.google.common.collect.ImmutableList;
import com.lordskittles.arcanumapi.common.utilities.BlockUtilities;
import net.minecraft.block.BlockState;
import net.minecraft.state.Property;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
import net.minecraft.world.gen.trunkplacer.AbstractTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.TrunkPlacerType;

import java.util.List;
import java.util.Random;
import java.util.Set;

public abstract class TrunkPlacerBase extends AbstractTrunkPlacer {

    protected Property logAxisProperty;

    protected Random random;
    protected IWorldGenerationReader world;

    private BaseTreeFeatureConfig config;
    private final TrunkPlacerType<?> type;

    public TrunkPlacerBase(int baseHeight, int heightRandA, int heightRandB, TrunkPlacerType<?> type) {

        super(baseHeight, heightRandA, heightRandB);

        this.type = type;
    }

    @Override
    protected TrunkPlacerType<?> getPlacerType() {

        return type;
    }

    @Override
    public List<FoliagePlacer.Foliage> getFoliages(IWorldGenerationReader world, Random random, int height, BlockPos start, Set<BlockPos> changed, MutableBoundingBox bounds, BaseTreeFeatureConfig config) {

        this.random = random;
        this.world = world;
        this.config = config;
        this.logAxisProperty = BlockUtilities.getAxisProperty(this.config.trunkProvider.getBlockState(random, start));

        func_236909_a_(this.world, start.down());

        placeTrunk(start, changed);

        return ImmutableList.of(new FoliagePlacer.Foliage(start.up(this.baseHeight), 0, false));
    }

    protected abstract void placeTrunk(BlockPos position, Set<BlockPos> changedLogs);

    protected boolean placeLog(BlockPos pos, Set<BlockPos> changedLogs) {

        return placeLog(pos, (Direction.Axis) null, changedLogs);
    }

    @SuppressWarnings("unchecked")
    protected boolean placeLog(BlockPos pos, Direction.Axis axis, Set<BlockPos> changedLogs) {

        BlockState directedLog = (axis != null && this.logAxisProperty != null) ? (BlockState) this.config.trunkProvider.getBlockState(this.random, pos).with(this.logAxisProperty, (Comparable) axis) : this.config.trunkProvider.getBlockState(this.random, pos);
        return placeBlock(pos, directedLog, changedLogs);
    }

    private boolean placeBlock(BlockPos pos, BlockState state, Set<BlockPos> changedBlocks) {

        if(TreeFeature.isReplaceableAt(world, pos)) {
            TreeFeature.setBlockStateWithoutUpdate(world, pos, state);
            changedBlocks.add(pos.toImmutable());

            return true;
        }

        return false;
    }
}
