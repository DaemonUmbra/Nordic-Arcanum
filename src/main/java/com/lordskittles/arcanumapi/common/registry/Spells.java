package com.lordskittles.arcanumapi.common.registry;

import com.lordskittles.arcanumapi.magic.schools.SchoolType;
import com.lordskittles.arcanumapi.magic.spells.Spell;

import java.util.*;

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
}
