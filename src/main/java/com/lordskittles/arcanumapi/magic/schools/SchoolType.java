package com.lordskittles.arcanumapi.magic.schools;

import com.lordskittles.arcanumapi.common.registry.MagicSchools;
import com.lordskittles.arcanumapi.core.ArcanumNames;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nullable;

public enum SchoolType
{
    Undiscovered(ArcanumNames.UNDISCOVERED, 0, TextFormatting.WHITE),
    Ur(ArcanumNames.UR, 1, TextFormatting.AQUA),
    Kaun(ArcanumNames.KAUN, 2, TextFormatting.RED),
    Ar(ArcanumNames.AR, 3, TextFormatting.YELLOW),
    Hagal(ArcanumNames.HAGAL, 4, TextFormatting.GRAY),
    Yr(ArcanumNames.YR, 5, TextFormatting.GREEN),
    Fe(ArcanumNames.FE, 6, TextFormatting.LIGHT_PURPLE);

    public String name;
    public int id;
    public TextFormatting color;

    SchoolType(String name, int id, TextFormatting color)
    {
        this.name = name;
        this.id = id;
        this.color = color;
    }

    @Nullable
    public static MagicSchool getSchoolFromId(int id)
    {
        if (MagicSchools.getSchoolFor(id) == MagicSchools.undiscovered)
            return null;

        return MagicSchools.getSchoolFor(id);
    }
}
