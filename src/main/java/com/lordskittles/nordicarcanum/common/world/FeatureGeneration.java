package com.lordskittles.nordicarcanum.common.world;

import com.lordskittles.nordicarcanum.common.block.world.trees.TreeJuniper;
import com.lordskittles.nordicarcanum.common.block.world.trees.TreePine;
import com.lordskittles.nordicarcanum.common.block.world.trees.TreeYew;
import com.lordskittles.nordicarcanum.common.registry.Blocks;
import com.lordskittles.nordicarcanum.common.registry.Decorators;
import com.lordskittles.nordicarcanum.common.registry.Features;
import com.lordskittles.nordicarcanum.common.registry.Structures;
import com.lordskittles.nordicarcanum.core.NordicConfig;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.HeightmapConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.placement.ChanceDecoratorConfiguration;
import net.minecraft.world.level.levelgen.placement.ConfiguredDecorator;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;
import net.minecraft.world.level.levelgen.placement.FrequencyWithExtraChanceDecoratorConfiguration;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import javax.annotation.Nonnull;
import java.util.Arrays;

public class FeatureGeneration {

    private static Biome.BiomeCategory[] temple_blacklist = new Biome.BiomeCategory[]
            {
                    Biome.BiomeCategory.BEACH,
                    Biome.BiomeCategory.NETHER,
                    Biome.BiomeCategory.OCEAN,
                    Biome.BiomeCategory.DESERT,
                    Biome.BiomeCategory.MESA,
                    Biome.BiomeCategory.MUSHROOM,
                    Biome.BiomeCategory.RIVER,
                    Biome.BiomeCategory.THEEND
            };

    public static void generateStructures(BiomeLoadingEvent event) {

        Biome.BiomeCategory category = event.getCategory();
        if(category != Biome.BiomeCategory.OCEAN && category != Biome.BiomeCategory.NETHER && category != Biome.BiomeCategory.THEEND) {
            addStructureToBiome(event.getGeneration(), Structures.norse_pillar_feature, NordicConfig.genPillars.get());
            addStructureToBiome(event.getGeneration(), Structures.shrine_feature, NordicConfig.genShrines.get());
        }

        if(! Arrays.asList(temple_blacklist).contains(category)) {
            addStructureToBiome(event.getGeneration(), Structures.temple_feature, NordicConfig.genTemples.get());
        }
    }

    public static void generateLakes(BiomeLoadingEvent event) {

        if(event.getCategory() != Biome.BiomeCategory.THEEND && event.getCategory() != Biome.BiomeCategory.NETHER) {
            addLakeToBiome(event.getGeneration(), Blocks.liquid_arcanum.get().defaultBlockState(), Decorators.arcanum_lake.get().configured(new ChanceDecoratorConfiguration(NordicConfig.arcanumLakeChance.get())), NordicConfig.genArcanumLakes.get());
        }
    }

    public static void generateTrees(BiomeLoadingEvent event) {

        Biome.BiomeCategory category = event.getCategory();

        if(category == Biome.BiomeCategory.FOREST) {
            addTreeToBiome(event.getGeneration(), Features.yew_tree.get(), TreeYew.CONFIG, 0, 0.06f, 1, NordicConfig.genYew.get());
        }
        if(category == Biome.BiomeCategory.PLAINS) {
            addTreeToBiome(event.getGeneration(), Features.juniper_tree.get(), TreeJuniper.CONFIG, 0, 0.04f, 1, NordicConfig.genJuniper.get());
        }
        if(category == Biome.BiomeCategory.TAIGA) {
            addTreeToBiome(event.getGeneration(), Features.pine_tree.get(), TreePine.CONFIG, 0, 0.06f, 1, NordicConfig.genPine.get());
        }
    }

    private static <C extends NoneFeatureConfiguration> void addStructureToBiome(BiomeGenerationSettingsBuilder generation, ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<C>> feature, boolean toggle) {

        if(toggle) {
            generation.addStructureStart(feature);
        }
    }

    private static void addLakeToBiome(BiomeGenerationSettingsBuilder generation, BlockState state, ConfiguredDecorator placement, boolean toggle) {

        if(toggle) {
            generation.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, Feature.LAKE.configured(new BlockStateConfiguration(state)).decorated(placement));
        }
    }

    @SuppressWarnings("rawtypes")
    private static void addTreeToBiome(BiomeGenerationSettingsBuilder generation, @Nonnull TreeFeature tree, TreeConfiguration config, int count, float extraChance, int extraCount, boolean toggle) {

        if(toggle) {
            generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                    tree.configured(config)
                            .decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(count, extraChance, extraCount)))
                            .decorated(FeatureDecorator.HEIGHTMAP.configured(new HeightmapConfiguration(Heightmap.Types.WORLD_SURFACE))));
        }
    }
}
