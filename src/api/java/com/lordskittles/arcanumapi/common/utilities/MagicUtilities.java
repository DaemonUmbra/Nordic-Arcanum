package com.lordskittles.arcanumapi.common.utilities;

import com.lordskittles.arcanumapi.core.ArcanumAPI;
import net.minecraft.world.entity.player.Player;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;

import static com.lordskittles.arcanumapi.core.NBTConstants.ARCANUM_KEY;
import static com.lordskittles.arcanumapi.core.NBTConstants.MAX_ARCANUM_KEY;

public class MagicUtilities {

    private static final float BASE_ARCANUM = 100;

    public static void replenishArcanum(Player player, float amount) {

        clampArcanum(player, amount);
    }

    public static boolean useArcanum(Player player, float cost) {

        CompoundTag nbt = getArcanumTag(player, ARCANUM_KEY);

        float arcanum = nbt.getFloat(ARCANUM_KEY);
        if(arcanum - cost < 0) {
            return false;
        }

        nbt.putFloat(ARCANUM_KEY, arcanum - cost);
        return true;
    }

    public static void setArcanum(Player player, float current, float maximum) {

        CompoundTag currentTag = getArcanumTag(player, ARCANUM_KEY);
        CompoundTag maximumTag = getArcanumTag(player, MAX_ARCANUM_KEY);

        currentTag.putFloat(ARCANUM_KEY, current);
        maximumTag.putFloat(MAX_ARCANUM_KEY, maximum);
    }

    public static boolean canUseArcanum(Player player, float cost) {

        if(cost == 0)
            return true;

        return getModifiedArcanum(player, cost) > 0;
    }

    public static float getCurrentArcanum(Player player) {

        return getArcanumTag(player, ARCANUM_KEY).getFloat(ARCANUM_KEY);
    }

    public static float getMaximumArcanum(Player player) {

        return getArcanumTag(player, MAX_ARCANUM_KEY).getFloat(MAX_ARCANUM_KEY);
    }

    private static CompoundTag getArcanumTag(Player player, String tag) {

        CompoundTag nbt = NBTUtilities.getPersistentData(ArcanumAPI.MODID, player);

        if(! nbt.contains(tag)) {
            nbt.putFloat(tag, BASE_ARCANUM);
        }

        return nbt;
    }

    private static float getModifiedArcanum(Player player, float cost) {

        CompoundTag nbt = getArcanumTag(player, ARCANUM_KEY);
        return nbt.getFloat(ARCANUM_KEY) - cost;
    }

    private static void clampArcanum(Player player, float modifier) {

        CompoundTag nbt = getArcanumTag(player, ARCANUM_KEY);
        float arcanum = Mth.clamp(nbt.getFloat(ARCANUM_KEY) + modifier, 0, 100);
        nbt.putFloat(ARCANUM_KEY, arcanum);
    }
}
