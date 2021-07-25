package com.lordskittles.nordicarcanum.magic.spells;

import com.lordskittles.arcanumapi.common.utilities.MagicUtilities;
import com.lordskittles.arcanumapi.core.ArcanumAPI;
import com.lordskittles.nordicarcanum.common.registry.Spells;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.magic.schools.SchoolType;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistryEntry;

public abstract class Spell extends ForgeRegistryEntry<Spell> {

    protected final SchoolType school;
    protected final SpellType type;
    protected final String name;
    protected final float arcanumCost;

    public Spell(SchoolType school, SpellType type, String modID, String name, float arcanumCost) {

        this.school = school;
        this.type = type;
        this.name = name;
        this.arcanumCost = arcanumCost;

        this.setRegistryName(NordicArcanum.RL(modID, name));
        Spells.registerSpell(school, this);
    }

    public SchoolType getSchool() {

        return this.school;
    }

    public Component getName() {

        return new TextComponent("spell." + school.name + "." + this.name + ".name");
    }

    public Component getDescription() {

        return new TextComponent("spell." + school.name + "." + this.name + ".desc");
    }

    public float getArcanumCost() {

        return this.arcanumCost;
    }

    public float getArcanumCostModified(float modifier) {

        return this.arcanumCost * modifier;
    }

    public void cast(Level world, ServerPlayer player, BlockPos pos, float cost) {

        if(MagicUtilities.canUseArcanum(player, cost))
            return;

        if(! process(world, player, pos))
            return;

        MagicUtilities.useArcanum(player, cost);
    }

    public abstract boolean process(Level world, ServerPlayer player, BlockPos pos);
}
