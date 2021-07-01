package com.lordskittles.arcanumapi.common.world.feature.trees;

import com.mojang.datafixers.Products;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;

import java.util.Random;
import java.util.Set;

public abstract class FoliagePlacerBase extends FoliagePlacer {

    protected static <P extends FoliagePlacerBase> Products.P3<RecordCodecBuilder.Mu<P>, FeatureSpread, FeatureSpread, Integer> func_236740_a_(RecordCodecBuilder.Instance<P> instance) {

        return func_242830_b(instance).and(Codec.intRange(0, 16).fieldOf("height").forGetter((foliage) -> foliage.height));
    }

    protected final int height;
    protected Random random;
    protected IWorldGenerationReader world;
    protected MutableBoundingBox bounds;

    private BaseTreeFeatureConfig config;
    private final FoliagePlacerType<?> type;

    public FoliagePlacerBase(FeatureSpread spreadA, FeatureSpread spreadB, int height, FoliagePlacerType<?> type) {

        super(spreadA, spreadB);

        this.height = height;
        this.type = type;
    }

    protected abstract void placeLeaves(BlockPos position, Set<BlockPos> changedLeaves);

    @Override
    protected FoliagePlacerType<?> getPlacerType() {

        return type;
    }

    @Override
    protected void func_230372_a_(IWorldGenerationReader world, Random random, BaseTreeFeatureConfig config, int p_230372_4_, Foliage foliage, int p_230372_6_, int p_230372_7_, Set<BlockPos> changedLeaves, int p_230372_9_, MutableBoundingBox bounds) {

        this.world = world;
        this.random = random;
        this.bounds = bounds;
        this.config = config;

        placeLeaves(foliage.func_236763_a_(), changedLeaves);
    }

    @Override
    public int func_230374_a_(Random random, int p_230374_2_, BaseTreeFeatureConfig config) {

        return height;
    }

    @Override
    protected boolean func_230373_a_(Random rand, int p_230373_2_, int p_230373_3_, int p_230373_4_, int p_230373_5_, boolean p_230373_6_) {

        return p_230373_2_ == p_230373_5_ && p_230373_4_ == p_230373_5_ && (rand.nextInt(2) == 0 || p_230373_3_ == 0);
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

        if(! this.world.hasBlockState(pos, (state1) -> { return state1.getBlock() instanceof RotatedPillarBlock; })) {
            this.world.setBlockState(pos, config.leavesProvider.getBlockState(this.random, pos), 13);

            changedLeaves.add(pos.toImmutable());
        }
    }
}
