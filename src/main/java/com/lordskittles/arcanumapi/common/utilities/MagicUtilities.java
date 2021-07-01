package com.lordskittles.arcanumapi.common.utilities;

import com.lordskittles.arcanumapi.core.ArcanumAPI;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.MathHelper;

import static com.lordskittles.arcanumapi.core.NBTConstants.ARCANUM_KEY;
import static com.lordskittles.arcanumapi.core.NBTConstants.MAX_ARCANUM_KEY;

public class MagicUtilities {

    private static final float BASE_ARCANUM = 100;

    public static void replenishArcanum(PlayerEntity player, float amount) {

        clampArcanum(player, amount);
    }

    public static boolean useArcanum(PlayerEntity player, float cost) {

        CompoundNBT nbt = getArcanumTag(player, ARCANUM_KEY);

        float arcanum = nbt.getFloat(ARCANUM_KEY);
        if(arcanum - cost < 0) {
            return false;
        }

        nbt.putFloat(ARCANUM_KEY, arcanum - cost);
        return true;
    }

    public static void setArcanum(PlayerEntity player, float current, float maximum) {

        CompoundNBT currentTag = getArcanumTag(player, ARCANUM_KEY);
        CompoundNBT maximumTag = getArcanumTag(player, MAX_ARCANUM_KEY);

        currentTag.putFloat(ARCANUM_KEY, current);
        maximumTag.putFloat(MAX_ARCANUM_KEY, maximum);
    }

    public static boolean canUseArcanum(PlayerEntity player, float cost) {

        if(cost == 0)
            return true;

        return getModifiedArcanum(player, cost) > 0;
    }

    public static float getCurrentArcanum(PlayerEntity player) {

        return getArcanumTag(player, ARCANUM_KEY).getFloat(ARCANUM_KEY);
    }

    public static float getMaximumArcanum(PlayerEntity player) {

        return getArcanumTag(player, MAX_ARCANUM_KEY).getFloat(MAX_ARCANUM_KEY);
    }

    private static CompoundNBT getArcanumTag(PlayerEntity player, String tag) {

        CompoundNBT nbt = NBTUtilities.getPersistentData(ArcanumAPI.MODID, player);

        if(! nbt.contains(tag)) {
            nbt.putFloat(tag, BASE_ARCANUM);
        }

        return nbt;
    }

    private static float getModifiedArcanum(PlayerEntity player, float cost) {

        CompoundNBT nbt = getArcanumTag(player, ARCANUM_KEY);
        return nbt.getFloat(ARCANUM_KEY) - cost;
    }

    private static void clampArcanum(PlayerEntity player, float modifier) {

        CompoundNBT nbt = getArcanumTag(player, ARCANUM_KEY);
        float arcanum = MathHelper.clamp(nbt.getFloat(ARCANUM_KEY) + modifier, 0, 100);
        nbt.putFloat(ARCANUM_KEY, arcanum);
    }
}
