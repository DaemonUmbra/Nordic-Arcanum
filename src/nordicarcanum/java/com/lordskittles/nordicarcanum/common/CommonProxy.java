package com.lordskittles.nordicarcanum.common;

import com.google.common.collect.ImmutableMap;
import com.lordskittles.arcanumapi.common.world.feature.structure.StructureBase;
import com.lordskittles.nordicarcanum.common.registry.Structures;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import net.minecraft.world.gen.DimensionSettings;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = NordicArcanum.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonProxy {

    @SubscribeEvent
    public static void setup(final FMLCommonSetupEvent event) {

        NordicArcanum.PACKET_HANDLER.initialize();

        for(StructureBase<?> structure : Structures.STRUCTURE_LIST) {
            DimensionStructuresSettings.field_236191_b_ = ImmutableMap.<Structure<?>, StructureSeparationSettings>builder()
                    .putAll(DimensionStructuresSettings.field_236191_b_)
                    .put(structure, new StructureSeparationSettings(structure.getDistance(), structure.getSeparation(), structure.getSeedModifier()))
                    .build();

            DimensionSettings.DEFAULT_SETTINGS.getStructures().field_236193_d_.put(structure, new StructureSeparationSettings(structure.getDistance(), structure.getSeparation(), structure.getSeedModifier()));
        }
    }
}
