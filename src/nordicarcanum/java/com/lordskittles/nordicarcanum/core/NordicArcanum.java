package com.lordskittles.nordicarcanum.core;

import com.lordskittles.nordicarcanum.common.advancements.Advancements;
import com.lordskittles.nordicarcanum.common.events.ServerEvents;
import com.lordskittles.nordicarcanum.common.network.PacketHandler;
import com.lordskittles.nordicarcanum.common.registry.*;
import com.lordskittles.nordicarcanum.magic.progression.ProgressionIOThread;
import com.lordskittles.nordicarcanum.magic.schools.MagicSchool;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;

@Mod(NordicArcanum.MODID)
@Mod.EventBusSubscriber(modid = NordicArcanum.MODID, bus = Bus.MOD)
public class NordicArcanum {

    public static final Logger LOG = LogManager.getLogger();
    public static final String MODID = "nordicarcanum";
    public static NordicArcanum INSTANCE;
    public static PacketHandler PACKET_HANDLER = new PacketHandler();

    public final Version version;

    public NordicArcanum() {

        INSTANCE = this;

        version = new Version(ModLoadingContext.get().getActiveContainer().getModInfo().getVersion());

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, NordicConfig.SPEC);
        NordicConfig.load();

        Sounds.SOUNDS.register(modEventBus);

        Items.ITEMS.register(modEventBus);
        Blocks.BLOCKS.register(modEventBus);

        Fluids.FLUIDS.register(modEventBus);
        RecipeSerializers.RECIPE_SERIALIZER.register(modEventBus);

        Containers.CONTAINERS.register(modEventBus);
        TileEntities.TILE_ENTITIES.register(modEventBus);
        Entities.ENTITIES.register(modEventBus);

        Decorators.DECORATORS.register(modEventBus);
        Structures.STRUCTURES.register(modEventBus);
        Features.FEATURES.register(modEventBus);

        Particles.PARTICLES.register(modEventBus);

        Advancements.register();

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

        return new ResourceLocation(MODID, location);
    }
}
