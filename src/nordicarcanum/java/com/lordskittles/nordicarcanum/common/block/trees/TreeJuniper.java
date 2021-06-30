package com.lordskittles.nordicarcanum.common.block.trees;

import java.util.Random;

import com.lordskittles.nordicarcanum.common.registry.Blocks;
import com.lordskittles.nordicarcanum.common.registry.Features;

import com.lordskittles.nordicarcanum.common.world.feature.trees.JuniperFoliage;
import com.lordskittles.nordicarcanum.common.world.feature.trees.JuniperTrunk;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.feature.TwoLayerFeature;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.JungleFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

public class TreeJuniper extends Tree
{
	public static final BaseTreeFeatureConfig CONFIG = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.juniper_log.get().getDefaultState()),
            new SimpleBlockStateProvider(Blocks.juniper_leaves.get().getDefaultState()), new JuniperFoliage(FeatureSpread.create(2, 0), FeatureSpread.create(0, 0), 3),
            new JuniperTrunk(4, 2, 0), new TwoLayerFeature(1, 0, 1))).setIgnoreVines().build();

	@Override
	protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random rand, boolean bool)
	{
		return Features.juniper_tree.get().withConfiguration(CONFIG);
	}
}
