package com.lordskittles.nordicarcanum.common.world;

import com.lordskittles.nordicarcanum.common.registry.Blocks;
import com.lordskittles.nordicarcanum.core.NordicConfig;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RangeDecoratorConfiguration;
import net.minecraft.world.level.levelgen.heightproviders.TrapezoidHeight;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import org.jetbrains.annotations.NotNull;

public class OreGeneration {

    private static final BlockState arcane_dust = Blocks.arcane_powder_ore.get().defaultBlockState();
    private static final BlockState feldspar = Blocks.feldspar.get().defaultBlockState();

    private static final BlockState carnelian = Blocks.carnelian_ore.get().defaultBlockState();
    private static final BlockState garnet = Blocks.garnet_ore.get().defaultBlockState();
    private static final BlockState thulite = Blocks.thulite_ore.get().defaultBlockState();

    private static final BlockState bismuth = Blocks.bismuth_ore.get().defaultBlockState();
    private static final BlockState silver = Blocks.silver_ore.get().defaultBlockState();
    private static final BlockState nickle = Blocks.nickle_ore.get().defaultBlockState();

    public static void generateOre(@NotNull BiomeLoadingEvent event) {

        BiomeGenerationSettingsBuilder generation = event.getGeneration();

        // General ores
        addOreFeature(generation, 8, 128, 40, NordicConfig.arcaneDustPerChunk.get(), arcane_dust, NordicConfig.genArcaneDust.get());
        addOreFeature(generation, 33, 128, 20, NordicConfig.feldsparPerChunk.get(), feldspar, NordicConfig.genFeldspar.get());

        // Crystal ores
        addOreFeature(generation, 4, 20, 10, NordicConfig.carnelianPerChunk.get(), carnelian, NordicConfig.genCarnelian.get());
        addOreFeature(generation, 4, 20, 10, NordicConfig.garnetPerChunk.get(), garnet, NordicConfig.genGarnet.get());
        addOreFeature(generation, 4, 20, 10, NordicConfig.thulitePerChunk.get(), thulite, NordicConfig.genThulite.get());

        // Metal ores
        addOreFeature(generation, 8, 60, 40, NordicConfig.bismuthPerChunk.get(), bismuth, NordicConfig.genBismuth.get());
        addOreFeature(generation, 8, 30, 15, NordicConfig.silverPerChunk.get(), silver, NordicConfig.genSilver.get());
        addOreFeature(generation, 8, 20, 15, NordicConfig.nicklePerChunk.get(), nickle, NordicConfig.genNickle.get());
    }

    private static void addOreFeature(BiomeGenerationSettingsBuilder generation, int size, int top, int bottom, int perChunk, BlockState block, boolean toggle) {

        if(toggle) {
            generation.addFeature(
                    GenerationStep.Decoration.UNDERGROUND_ORES,
                    Feature.ORE.configured(
                            new OreConfiguration(OreConfiguration.Predicates.STONE_ORE_REPLACEABLES, block, size))
                            .decorated(FeatureDecorator.RANGE.configured(new RangeDecoratorConfiguration(TrapezoidHeight.of(VerticalAnchor.absolute(bottom), VerticalAnchor.absolute(top)))))
                            .count(perChunk));
        }
    }
}
