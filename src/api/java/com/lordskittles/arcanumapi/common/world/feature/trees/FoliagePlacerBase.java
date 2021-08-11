package com.lordskittles.arcanumapi.common.world.feature.trees;

import com.mojang.datafixers.Products;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedRW;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.structure.BoundingBox;

import java.util.Random;
import java.util.Set;
import java.util.function.BiConsumer;

public abstract class FoliagePlacerBase extends FoliagePlacer {

    protected static <P extends FoliagePlacerBase> Products.P3<RecordCodecBuilder.Mu<P>, IntProvider, IntProvider, Integer> blobParts(RecordCodecBuilder.Instance<P> instance) {

        return foliagePlacerParts(instance).and(Codec.intRange(0, 16).fieldOf("height").forGetter((foliage) -> foliage.height));
    }

    protected final int height;
    protected Random random;
    protected LevelSimulatedReader world;
    protected BoundingBox bounds;

    private TreeConfiguration config;
    private final FoliagePlacerType<?> type;

    public FoliagePlacerBase(IntProvider spreadA, IntProvider spreadB, int height, FoliagePlacerType<?> type) {

        super(spreadA, spreadB);

        this.height = height;
        this.type = type;
    }

    protected abstract void placeLeaves(BlockPos position, BiConsumer<BlockPos, BlockState> changedLeaves);

    @Override
    protected FoliagePlacerType<?> type() {

        return type;
    }

    @Override
    protected void createFoliage(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> consumer, Random random, TreeConfiguration config, int p_230372_4_, FoliageAttachment foliage, int p_230372_6_, int p_230372_7_, int p_230372_9_) {

        this.world = world;
        this.random = random;
        this.config = config;

        placeLeaves(foliage.pos(), consumer);
    }

    @Override
    public int foliageHeight(Random random, int p_230374_2_, TreeConfiguration config) {

        return height;
    }

    @Override
    protected boolean shouldSkipLocation(Random rand, int p_230373_2_, int p_230373_3_, int p_230373_4_, int p_230373_5_, boolean p_230373_6_) {

        return p_230373_2_ == p_230373_5_ && p_230373_4_ == p_230373_5_ && (rand.nextInt(2) == 0 || p_230373_3_ == 0);
    }

    protected void placeLeafBox(BlockPos startPos, BlockPos endPos, BiConsumer<BlockPos, BlockState> changedLeaves) {

        for(int x = 0; x < endPos.getX(); x++) {
            for(int y = 0; y < endPos.getY(); y++) {
                for(int z = 0; z < endPos.getZ(); z++) {
                    BlockPos leafPos = new BlockPos(startPos.getX() + x, startPos.getY() + y, startPos.getZ() + z);
                    placeLeaf(leafPos, changedLeaves);
                }
            }
        }
    }

    protected void placeLeaf(BlockPos pos, BiConsumer<BlockPos, BlockState> changedLeaves) {

        if(! this.world.isStateAtPosition(pos, (state1) -> { return state1.getBlock() instanceof RotatedPillarBlock; })) {

            changedLeaves.accept(pos.immutable(), config.foliageProvider.getState(this.random, pos));
        }
    }
}
