package com.lordskittles.nordicarcanum.common.block.world.trees;

import com.lordskittles.nordicarcanum.common.registry.Blocks;
import com.lordskittles.nordicarcanum.common.registry.Features;
import com.lordskittles.nordicarcanum.common.world.feature.trees.YewFoliage;
import com.lordskittles.nordicarcanum.common.world.feature.trees.YewTrunk;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;

import java.util.Random;

public class TreeYew extends NordicTree {

    public static final TreeConfiguration CONFIG = (new TreeConfiguration.TreeConfigurationBuilder(new SimpleStateProvider(Blocks.yew_log.get().defaultBlockState()),
            new YewTrunk(4, 2, 0), new SimpleStateProvider(Blocks.yew_leaves.get().defaultBlockState()), new SimpleStateProvider(Blocks.yew_sapling.get().defaultBlockState()),
            new YewFoliage(UniformInt.of(0, 2), UniformInt.of(0, 2), 3), new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build();

    @Override
    protected ConfiguredFeature<TreeConfiguration, ?> getConfiguredFeature(Random rand, boolean bool) {

        return Features.yew_tree.get().configured(CONFIG);
    }

    @Override
    public TreeConfiguration getConfig() {

        return CONFIG;
    }
}
