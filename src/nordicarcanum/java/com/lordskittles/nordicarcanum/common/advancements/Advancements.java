package com.lordskittles.nordicarcanum.common.advancements;

import net.minecraft.advancements.CriteriaTriggers;

import java.util.Arrays;

public class Advancements {

    public static final CustomTrigger sigil_found = new CustomTrigger("root");
    public static final CustomTrigger arcane_dust_obtain = new CustomTrigger("arcane_dust");
    public static final CustomTrigger crafting_cloth_crafted = new CustomTrigger("crafting_cloth");

    private static final CustomTrigger[] all_triggers = new CustomTrigger[]
            {
                    sigil_found,
                    arcane_dust_obtain,
                    crafting_cloth_crafted
            };

    public static void register() {

        Arrays.stream(all_triggers).forEach(CriteriaTriggers::register);
    }
}
