package com.lordskittles.nordicarcanum.magic.schools;

import com.lordskittles.nordicarcanum.common.registry.MagicSchools;
import com.lordskittles.arcanumapi.core.ArcanumAPI;
import com.lordskittles.nordicarcanum.common.registry.Spells;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.magic.spells.Spell;
import net.minecraftforge.registries.ForgeRegistryEntry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MagicSchool extends ForgeRegistryEntry<MagicSchool> implements IMagicSchool {

    protected List<Spell> spells = new ArrayList<>();

    protected final SchoolType school;

    public MagicSchool(String modId, SchoolType school) {

        this.school = school;
        this.setRegistryName(NordicArcanum.RL(modId, school.name));
        MagicSchools.registerSchool(this);
    }

    public void loadSpells() {

        Collection<Spell> spells = Spells.getSpellsForSchool(school);
        if(! spells.isEmpty()) {
            for(Spell spell : spells) {
                addSpell(spell);
            }
        }
    }

    public void addSpell(Spell spell) {

        this.spells.add(spell);
    }

    public List<Spell> getSpells() {

        return this.spells;
    }

    @Override
    public SchoolType getSchool() {

        return school;
    }

    @Override
    public String getUnlocalizedName() {

        return school.name;
    }

    @Override
    public int compareTo(IMagicSchool o) {

        return 0;
    }
}
