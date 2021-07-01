package com.lordskittles.arcanumapi.magic.schools;

import com.lordskittles.arcanumapi.core.ArcanumAPI;
import com.lordskittles.arcanumapi.core.NBTConstants;
import net.minecraft.client.resources.I18n;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.*;

public interface IMagicSchool extends Comparable<IMagicSchool> {

    SchoolType getSchool();

    String getUnlocalizedName();

    static String getDefaultSaveKey() {

        return NBTConstants.SCHOOL_KEY;
    }

    static IMagicSchool readFromNBT(CompoundNBT nbt) {

        return readFromNBT(nbt, getDefaultSaveKey());
    }

    static IMagicSchool readFromNBT(CompoundNBT nbt, String key) {

        return SchoolType.getSchoolFromId(nbt.getInt(key));
    }

    default StringTextComponent getDiscoveredText() {

        return new StringTextComponent(getColor() + I18n.format(getUnlocalizedInfo()));
    }

    static StringTextComponent getUndiscoveredText() {

        return new StringTextComponent(TextFormatting.BLUE + I18n.format(ArcanumAPI.MODID + ".misc.noinfo"));
    }

    static ITextComponent getDiscoveredChatMessage(MagicSchool school) {

        return new TranslationTextComponent(ArcanumAPI.MODID + ".school.discovered.chat").setStyle(Style.EMPTY.setFormatting(TextFormatting.WHITE))
                .appendSibling(new TranslationTextComponent(school.getUnlocalizedInfo()).setStyle(Style.EMPTY.setFormatting(school.getColor())));
    }

    default String getUnlocalizedInfo() {

        return ArcanumAPI.MODID + "." + getUnlocalizedName() + ".info";
    }

    default TextFormatting getColor() {

        return getSchool().color;
    }

    default void writeToNBT(CompoundNBT nbt) {

        writeToNBT(nbt, getDefaultSaveKey());
    }

    default void writeToNBT(CompoundNBT nbt, String key) {

        nbt.putInt(key, getSchool().id);
    }
}
