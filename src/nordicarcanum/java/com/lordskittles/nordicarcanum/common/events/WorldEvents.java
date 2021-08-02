package com.lordskittles.nordicarcanum.common.events;

import com.lordskittles.arcanumapi.common.world.feature.structure.StructureBase;
import com.lordskittles.nordicarcanum.common.registry.Structures;
import com.lordskittles.nordicarcanum.common.world.FeatureGeneration;
import com.lordskittles.nordicarcanum.common.world.OreGeneration;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.FlatLevelSource;
import net.minecraft.world.level.levelgen.StructureSettings;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = NordicArcanum.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class WorldEvents {

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void biomeLoadEvent(final BiomeLoadingEvent event) {

        OreGeneration.generateOre(event);
//        FeatureGeneration.generateLakes(event);
        FeatureGeneration.generateStructures(event);
//        FeatureGeneration.generateTrees(event);
    }

    @SubscribeEvent
    public static void onWorldLoad(final WorldEvent.Load event) {

        if(event.getWorld() instanceof ServerLevel) {

            ServerLevel serverLevel = (ServerLevel) event.getWorld();

            if(serverLevel.getChunkSource().getGenerator() instanceof FlatLevelSource &&
               serverLevel.dimension().equals(Level.OVERWORLD)) {
                return;
            }

            Map<StructureFeature<?>, StructureFeatureConfiguration> tempMap = new HashMap<>(serverLevel.getChunkSource().generator.getSettings().structureConfig());

            for(StructureBase structure : Structures.STRUCTURE_LIST) {

                tempMap.putIfAbsent(structure, StructureSettings.DEFAULTS.get(structure));
            }

            serverLevel.getChunkSource().generator.getSettings().structureConfig = tempMap;
        }
        else {
            NordicArcanum.LOG.info("Skipping world {}", event.getWorld().getClass());
        }
    }
}
