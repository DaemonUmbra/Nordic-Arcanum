package com.lordskittles.nordicarcanum.common.block.world.trees;

import com.lordskittles.nordicarcanum.common.registry.Blocks;
import com.lordskittles.nordicarcanum.common.registry.Features;
import com.lordskittles.nordicarcanum.common.world.feature.trees.JuniperFoliage;
import com.lordskittles.nordicarcanum.common.world.feature.trees.JuniperTrunk;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;

import java.util.Random;

public class TreeJuniper extends NordicTree {

    public static final TreeConfiguration CONFIG = (new TreeConfiguration.TreeConfigurationBuilder(new SimpleStateProvider(Blocks.juniper_log.get().defaultBlockState()),
            new JuniperTrunk(4, 2, 0), new SimpleStateProvider(Blocks.juniper_leaves.get().defaultBlockState()), new SimpleStateProvider(Blocks.juniper_sapling.get().defaultBlockState()),
            new JuniperFoliage(UniformInt.of(2, 0), UniformInt.of(0, 0), 3), new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build();

    @Override
    protected ConfiguredFeature<TreeConfiguration, ?> getConfiguredFeature(Random rand, boolean bool) {

        return Features.juniper_tree.get().configured(CONFIG);
    }

    @Override
    public TreeConfiguration getConfig() {

        return CONFIG;
    }
}
