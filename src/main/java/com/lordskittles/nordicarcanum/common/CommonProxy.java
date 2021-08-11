package com.lordskittles.nordicarcanum.common;

import com.lordskittles.nordicarcanum.common.registry.Structures;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = NordicArcanum.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonProxy {

    @SubscribeEvent
    public static void setup(final FMLCommonSetupEvent event) {

        NordicArcanum.PACKET_HANDLER.initialize();

        Structures.registerPieces();
    }
}
