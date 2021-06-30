package com.lordskittles.arcanumapi.magic.spells;

import com.lordskittles.arcanumapi.common.registry.Spells;
import com.lordskittles.arcanumapi.core.ArcanumAPI;
import com.lordskittles.arcanumapi.magic.schools.SchoolType;
import com.lordskittles.arcanumapi.common.utilities.MagicUtilities;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

public abstract class Spell extends ForgeRegistryEntry<Spell>
{
    protected final SchoolType school;
    protected final SpellType type;
    protected final String name;
    protected final float arcanumCost;

    public Spell(SchoolType school, SpellType type, String modID, String name, float arcanumCost)
    {
        this.school = school;
        this.type = type;
        this.name = name;
        this.arcanumCost = arcanumCost;

        this.setRegistryName(ArcanumAPI.RL(modID, name));
        Spells.registerSpell(school, this);
    }

    public SchoolType getSchool()
    {
        return this.school;
    }

    public ITextComponent getName()
    {
        return new StringTextComponent("spell." + school.name + "." + this.name + ".name");
    }

    public ITextComponent getDescription()
    {
        return new StringTextComponent("spell." + school.name + "." + this.name + ".desc");
    }

    public float getArcanumCost()
    {
        return this.arcanumCost;
    }

    public float getArcanumCostModified(float modifier)
    {
        return this.arcanumCost * modifier;
    }

    public void cast(World world, ServerPlayerEntity player, BlockPos pos, float cost)
    {
        if (MagicUtilities.canUseArcanum(player, cost))
            return;

        if (!process(world, player, pos))
            return;

        MagicUtilities.useArcanum(player, cost);
    }

    public abstract boolean process(World world, ServerPlayerEntity player, BlockPos pos);
}
