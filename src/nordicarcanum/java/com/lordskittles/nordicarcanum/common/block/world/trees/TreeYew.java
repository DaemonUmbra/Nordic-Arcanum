package com.lordskittles.nordicarcanum.common.block.world.trees;

import com.lordskittles.nordicarcanum.common.registry.Blocks;
import com.lordskittles.nordicarcanum.common.registry.Features;
import com.lordskittles.nordicarcanum.common.world.feature.trees.YewFoliage;
import com.lordskittles.nordicarcanum.common.world.feature.trees.YewTrunk;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.feature.TwoLayerFeature;

import java.util.Random;

public class TreeYew extends Tree {

    public static final BaseTreeFeatureConfig CONFIG = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.yew_log.get().getDefaultState()),
            new SimpleBlockStateProvider(Blocks.yew_leaves.get().getDefaultState()), new YewFoliage(FeatureSpread.create(2, 0), FeatureSpread.create(0, 0), 3),
            new YewTrunk(4, 2, 0), new TwoLayerFeature(1, 0, 1))).setIgnoreVines().build();

    @Override
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random rand, boolean bool) {

        return Features.yew_tree.get().withConfiguration(CONFIG);
    }
}
