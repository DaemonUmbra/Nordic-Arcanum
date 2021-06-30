package com.lordskittles.arcanumapi.common.registry;

import com.lordskittles.arcanumapi.core.ArcanumAPI;
import com.lordskittles.arcanumapi.core.ArcanumNames;
import com.lordskittles.arcanumapi.magic.schools.MagicSchool;
import com.lordskittles.arcanumapi.magic.spells.Spell;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.RegistryBuilder;

@ObjectHolder(ArcanumAPI.MODID)
public class Registries
{
    public static IForgeRegistry<MagicSchool> MAGIC_SCHOOLS = null;
    public static IForgeRegistry<Spell> SPELLS = null;

    @Mod.EventBusSubscriber(modid = ArcanumAPI.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class Registration
    {
        @SubscribeEvent
        public static void register(RegistryEvent.NewRegistry event)
        {
            MAGIC_SCHOOLS = makeRegistry(ArcanumNames.MAGIC_SCHOOLS, MagicSchool.class).create();
            SPELLS = makeRegistry(ArcanumNames.SPELLS, Spell.class).create();
        }

        private static <T extends IForgeRegistryEntry<T>>RegistryBuilder makeRegistry(String name, Class<T> type)
        {
            return new RegistryBuilder<T>().setName(ArcanumAPI.RL(name)).setType(type).setMaxID(Integer.MAX_VALUE - 1).disableSaving();
        }
    }
}
