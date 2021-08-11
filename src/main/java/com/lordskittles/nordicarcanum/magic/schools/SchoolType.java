package com.lordskittles.nordicarcanum.magic.schools;

import com.lordskittles.nordicarcanum.common.registry.MagicSchools;
import com.lordskittles.arcanumapi.core.ArcanumNames;
import com.lordskittles.nordicarcanum.core.NordicNames;
import net.minecraft.ChatFormatting;

import javax.annotation.Nullable;

public enum SchoolType {
    Undiscovered(NordicNames.UNDISCOVERED, 0, ChatFormatting.WHITE),
    Ur(NordicNames.UR, 1, ChatFormatting.AQUA),
    Kaun(NordicNames.KAUN, 2, ChatFormatting.RED),
    Ar(NordicNames.AR, 3, ChatFormatting.YELLOW),
    Hagal(NordicNames.HAGAL, 4, ChatFormatting.GRAY),
    Yr(NordicNames.YR, 5, ChatFormatting.GREEN),
    Fe(NordicNames.FE, 6, ChatFormatting.LIGHT_PURPLE);

    public String name;
    public int id;
    public ChatFormatting color;

    SchoolType(String name, int id, ChatFormatting color) {

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
