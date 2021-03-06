package com.lordskittles.nordicarcanum.common.world;

import com.lordskittles.nordicarcanum.common.block.world.trees.TreeJuniper;
import com.lordskittles.nordicarcanum.common.block.world.trees.TreePine;
import com.lordskittles.nordicarcanum.common.block.world.trees.TreeYew;
import com.lordskittles.nordicarcanum.common.registry.Blocks;
import com.lordskittles.nordicarcanum.common.registry.Decorators;
import com.lordskittles.nordicarcanum.common.registry.Features;
import com.lordskittles.nordicarcanum.common.registry.Structures;
import com.lordskittles.nordicarcanum.core.NordicConfig;
import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.placement.*;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import javax.annotation.Nonnull;
import java.util.Arrays;

public class FeatureGeneration {

    private static Biome.Category[] temple_blacklist = new Biome.Category[]
            {
                    Biome.Category.BEACH,
                    Biome.Category.NETHER,
                    Biome.Category.OCEAN,
                    Biome.Category.DESERT,
                    Biome.Category.MESA,
                    Biome.Category.MUSHROOM,
                    Biome.Category.RIVER,
                    Biome.Category.THEEND
            };

    public static void generateStructures(BiomeLoadingEvent event) {

        Biome.Category category = event.getCategory();
        if(category != Biome.Category.OCEAN && category != Biome.Category.NETHER && category != Biome.Category.THEEND) {
            addStructureToBiome(event.getGeneration(), Structures.norse_pillar_feature, NordicConfig.genPillars.get());
            addStructureToBiome(event.getGeneration(), Structures.shrine_feature, NordicConfig.genShrines.get());
        }

        if(! Arrays.asList(temple_blacklist).contains(category)) {
            addStructureToBiome(event.getGeneration(), Structures.temple_feature, NordicConfig.genTemples.get());
        }
    }

    public static void generateLakes(BiomeLoadingEvent event) {

        if(event.getCategory() != Biome.Category.THEEND && event.getCategory() != Biome.Category.NETHER) {
            addLakeToBiome(event.getGeneration(), Blocks.liquid_arcanum.get().getDefaultState(), Decorators.arcanum_lake.get().configure(new ChanceConfig(NordicConfig.arcanumLakeChance.get())), NordicConfig.genArcanumLakes.get());
        }
    }

    public static void generateTrees(BiomeLoadingEvent event) {

        Biome.Category category = event.getCategory();

        if(category == Biome.Category.FOREST) {
            addTreeToBiome(event.getGeneration(), Features.yew_tree.get(), TreeYew.CONFIG, 0, 0.06f, 1, NordicConfig.genYew.get());
        }
        if(category == Biome.Category.PLAINS) {
            addTreeToBiome(event.getGeneration(), Features.juniper_tree.get(), TreeJuniper.CONFIG, 0, 0.04f, 1, NordicConfig.genJuniper.get());
        }
        if(category == Biome.Category.TAIGA) {
            addTreeToBiome(event.getGeneration(), Features.pine_tree.get(), TreePine.CONFIG, 0, 0.06f, 1, NordicConfig.genPine.get());
        }
    }

    private static <C extends NoFeatureConfig> void addStructureToBiome(BiomeGenerationSettingsBuilder generation, StructureFeature<NoFeatureConfig, ? extends Structure<C>> feature, boolean toggle) {

        if(toggle) {
            generation.withStructure(feature);
        }
    }

    private static void addLakeToBiome(BiomeGenerationSettingsBuilder generation, BlockState state, ConfiguredPlacement placement, boolean toggle) {

        if(toggle) {
            generation.withFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Feature.LAKE.withConfiguration(new BlockStateFeatureConfig(state)).withPlacement(placement));
        }
    }

    @SuppressWarnings("rawtypes")
    private static void addTreeToBiome(BiomeGenerationSettingsBuilder generation, @Nonnull TreeFeature tree, BaseTreeFeatureConfig config, int count, float extraChance, int extraCount, boolean toggle) {

        if(toggle) {
            generation.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                    tree.withConfiguration(config)
                            .withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(count, extraChance, extraCount)))
                            .withPlacement(Placement.HEIGHTMAP_WORLD_SURFACE.configure(NoPlacementConfig.NO_PLACEMENT_CONFIG)));
        }
    }
}
