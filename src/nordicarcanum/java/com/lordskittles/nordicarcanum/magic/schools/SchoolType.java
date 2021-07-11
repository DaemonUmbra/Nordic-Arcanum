package com.lordskittles.nordicarcanum.magic.schools;

import com.lordskittles.nordicarcanum.common.registry.MagicSchools;
import com.lordskittles.arcanumapi.core.ArcanumNames;
import com.lordskittles.nordicarcanum.core.NordicNames;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nullable;

public enum SchoolType {
    Undiscovered(NordicNames.UNDISCOVERED, 0, TextFormatting.WHITE),
    Ur(NordicNames.UR, 1, TextFormatting.AQUA),
    Kaun(NordicNames.KAUN, 2, TextFormatting.RED),
    Ar(NordicNames.AR, 3, TextFormatting.YELLOW),
    Hagal(NordicNames.HAGAL, 4, TextFormatting.GRAY),
    Yr(NordicNames.YR, 5, TextFormatting.GREEN),
    Fe(NordicNames.FE, 6, TextFormatting.LIGHT_PURPLE);

    public String name;
    public int id;
    public TextFormatting color;

    SchoolType(String name, int id, TextFormatting color) {

        this.name = name;
        this.id = id;
        this.color = color;
    }

    @Nullable
    public static MagicSchool getSchoolFromId(int id) {

        if(MagicSchools.getSchoolFor(id) == MagicSchools.undiscovered)
            return null;

        return MagicSchools.getSchoolFor(id);
    }
}
