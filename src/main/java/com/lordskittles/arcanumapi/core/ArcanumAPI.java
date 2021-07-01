package com.lordskittles.arcanumapi.core;

import com.lordskittles.arcanumapi.common.event.ServerEvents;
import com.lordskittles.arcanumapi.common.registry.Items;
import com.lordskittles.arcanumapi.common.registry.MagicSchools;
import com.lordskittles.arcanumapi.magic.progression.ProgressionIOThread;
import com.lordskittles.arcanumapi.magic.schools.MagicSchool;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;

@Mod(ArcanumAPI.MODID)
@Mod.EventBusSubscriber(modid = ArcanumAPI.MODID, bus = Bus.MOD)
public class ArcanumAPI {

    public static final Logger LOG = LogManager.getLogger();
    public static final String MODID = "arcanumapi";

    public ArcanumAPI() {

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);

        Items.ITEMS.register(modEventBus);

        ServerEvents.registerListener(ProgressionIOThread.startup());

        MinecraftForge.EVENT_BUS.register(this);
    }

    public void setup(FMLCommonSetupEvent event) {

        Collection<MagicSchool> schools = MagicSchools.getIdSchools();
        for(MagicSchool school : schools) {
            school.loadSpells();
        }
    }

    public static ResourceLocation RL(String modId, String location) {

        return new ResourceLocation(modId, location);
    }

    public static ResourceLocation RL(String location) {

        return RL(MODID, location);
    }
}
