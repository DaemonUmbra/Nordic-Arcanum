package com.lordskittles.nordicarcanum.common;

import com.google.common.collect.ImmutableMap;
import com.lordskittles.arcanumapi.common.world.feature.structure.StructureBase;
import com.lordskittles.nordicarcanum.common.registry.Structures;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.StructureSettings;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = NordicArcanum.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonProxy {

    @SubscribeEvent
    public static void setup(final FMLCommonSetupEvent event) {

        NordicArcanum.PACKET_HANDLER.initialize();

        for(StructureBase<?> structure : Structures.STRUCTURE_LIST) {
            StructureSettings.DEFAULTS = ImmutableMap.<StructureFeature<?>, StructureFeatureConfiguration>builder()
                    .putAll(StructureSettings.DEFAULTS)
                    .put(structure, new StructureFeatureConfiguration(structure.getDistance(), structure.getSeparation(), structure.getSeedModifier()))
                    .build();

            NoiseGeneratorSettings.BUILTIN_OVERWORLD.structureSettings().structureConfig.put(structure, new StructureFeatureConfiguration(structure.getDistance(), structure.getSeparation(), structure.getSeedModifier()));
        }
    }
}
