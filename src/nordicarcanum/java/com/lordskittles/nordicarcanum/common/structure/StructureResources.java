package com.lordskittles.nordicarcanum.common.structure;

import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.magic.GameStage;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

public class StructureResources {

    private static final ResourceLocation attunement_altar_tier_1 = NordicArcanum.RL("attunement_altar_tier_1");
    private static final ResourceLocation attunement_altar_tier_2 = NordicArcanum.RL("attunement_altar_tier_2");
    private static final ResourceLocation attunement_altar_tier_3 = NordicArcanum.RL("attunement_altar_tier_3");

    @Nonnull
    public static ResourceLocation getTieredAltar(GameStage stage) {

        switch(stage) {
            case Midgard:
                return attunement_altar_tier_1;
            case Muspelheim:
                return attunement_altar_tier_2;
            case Asgardian:
                return attunement_altar_tier_3;
        }

        return attunement_altar_tier_1;
    }
}
