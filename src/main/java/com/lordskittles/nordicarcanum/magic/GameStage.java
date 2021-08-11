package com.lordskittles.nordicarcanum.magic;

import com.lordskittles.nordicarcanum.core.NordicNames;

public enum GameStage {
    Midgard(NordicNames.TIER_1, "tier_1"),
    Muspelheim(NordicNames.TIER_2, "tier_2"),
    Asgardian(NordicNames.TIER_3, "tier_3");

    public String name;
    public String resource_id;

    GameStage(String name, String resource_id) {

        this.name = name;
        this.resource_id = resource_id;
    }
}
