package com.lordskittles.nordicarcanum.common.events;

import com.lordskittles.arcanumapi.common.event.IServerLifecycleListener;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppedEvent;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = NordicArcanum.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ServerEvents {

    private static List<IServerLifecycleListener> lifecycleListeners = new ArrayList<>();

    public static void registerListener(IServerLifecycleListener listener) {

        if(! lifecycleListeners.contains(listener)) {
            lifecycleListeners.add(listener);
        }
    }

    @SubscribeEvent
    public static void onServerStart(FMLServerStartedEvent event) {

        for(IServerLifecycleListener listener : lifecycleListeners) {
            listener.onServerStart();
        }
    }

    @SubscribeEvent
    public static void onServerStop(FMLServerStoppedEvent event) {

        for(IServerLifecycleListener listener : lifecycleListeners) {
            listener.onServerStop();
        }
    }
}