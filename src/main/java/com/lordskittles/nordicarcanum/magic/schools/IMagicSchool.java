package com.lordskittles.nordicarcanum.magic.schools;

import com.lordskittles.arcanumapi.core.NBTConstants;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import net.minecraft.ChatFormatting;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.nbt.CompoundTag;

import net.minecraft.network.chat.*;

public interface IMagicSchool extends Comparable<IMagicSchool> {

    SchoolType getSchool();

    String getUnlocalizedName();

    static String getDefaultSaveKey() {

        return NBTConstants.SCHOOL_KEY;
    }

    static IMagicSchool readFromNBT(CompoundTag nbt) {

        return readFromNBT(nbt, getDefaultSaveKey());
    }

    static IMagicSchool readFromNBT(CompoundTag nbt, String key) {

        return SchoolType.getSchoolFromId(nbt.getInt(key));
    }

    default TextComponent getDiscoveredText() {

        return new TextComponent(getColor() + I18n.get(getUnlocalizedInfo()));
    }

    static TextComponent getUndiscoveredText() {

        return new TextComponent(ChatFormatting.BLUE + I18n.get(NordicArcanum.MODID + ".misc.noinfo"));
    }

    static MutableComponent getDiscoveredChatMessage(MagicSchool school) {

        return new TranslatableComponent(NordicArcanum.MODID + ".school.discovered.chat").setStyle(Style.EMPTY.applyFormat(ChatFormatting.WHITE))
                .append(new TranslatableComponent(school.getUnlocalizedInfo()).setStyle(Style.EMPTY.applyFormat(school.getColor())));
    }

    default String getUnlocalizedInfo() {

        return NordicArcanum.MODID + "." + getUnlocalizedName() + ".info";
    }

    default ChatFormatting getColor() {

        return getSchool().color;
    }

    default void save(CompoundTag nbt) {

        save(nbt, getDefaultSaveKey());
    }

    default void save(CompoundTag nbt, String key) {

        nbt.putInt(key, getSchool().id);
    }
}
