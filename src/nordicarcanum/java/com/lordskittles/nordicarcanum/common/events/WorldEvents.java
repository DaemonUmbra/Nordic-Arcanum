package com.lordskittles.nordicarcanum.common.events;

import com.lordskittles.nordicarcanum.common.registry.Structures;
import com.lordskittles.nordicarcanum.common.tileentity.TileEntityAttunementAltar;
import com.lordskittles.nordicarcanum.common.world.FeatureGeneration;
import com.lordskittles.nordicarcanum.common.world.OreGeneration;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = NordicArcanum.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class WorldEvents
{
    @SubscribeEvent
    public static void biomeLoadEvent(final BiomeLoadingEvent event)
    {
        Structures.registerFeatures();

        OreGeneration.generateOre(event);
        FeatureGeneration.generateLakes(event);
        FeatureGeneration.generateStructures(event);
        FeatureGeneration.generateTrees(event);
    }
}
