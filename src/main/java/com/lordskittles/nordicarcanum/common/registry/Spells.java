package com.lordskittles.nordicarcanum.common.registry;

import com.lordskittles.nordicarcanum.magic.schools.SchoolType;
import com.lordskittles.nordicarcanum.magic.spells.Spell;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import java.util.*;

@ObjectHolder(NordicArcanum.MODID)
@Mod.EventBusSubscriber(modid = NordicArcanum.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Spells {

    private static Map<SchoolType, List<Spell>> spells = new HashMap<>();

    public static void registerSpell(SchoolType school, Spell spell) {

        if(! spells.containsKey(school)) {
            spells.put(school, new ArrayList<>());
        }
        spells.get(school).add(spell);
    }

    public static Collection<Spell> getSpellsForSchool(SchoolType school) {

        if(spells.containsKey(school)) {
            return spells.get(school);
        }

        return Collections.emptyList();
    }

    @SubscribeEvent
    public static void registerSpells(RegistryEvent.Register<Spell> event) {

        for(List<Spell> schoolSpells : spells.values()) {
            for(Spell spell : schoolSpells) {
                event.getRegistry().register(spell);
            }
        }
    }
}
