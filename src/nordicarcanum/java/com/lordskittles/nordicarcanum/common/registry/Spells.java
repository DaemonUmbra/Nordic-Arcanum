package com.lordskittles.nordicarcanum.common.registry;

import com.lordskittles.arcanumapi.magic.schools.SchoolType;
import com.lordskittles.arcanumapi.magic.spells.Spell;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ObjectHolder(NordicArcanum.MODID)
@Mod.EventBusSubscriber(modid = NordicArcanum.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Spells {

    private static Map<SchoolType, List<Spell>> spells = new HashMap<>();

    @SubscribeEvent
    public static void registerSpells(RegistryEvent.Register<Spell> event) {

        for(List<Spell> schoolSpells : spells.values()) {
            for(Spell spell : schoolSpells) {
                event.getRegistry().register(spell);
            }
        }
    }
}
