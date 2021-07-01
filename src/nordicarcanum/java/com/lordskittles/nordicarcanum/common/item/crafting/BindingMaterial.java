package com.lordskittles.nordicarcanum.common.item.crafting;

import com.lordskittles.nordicarcanum.core.NordicNames;

public enum BindingMaterial {
    Iron(NordicNames.IRON),
    Gold(NordicNames.GOLD),
    Bismuth(NordicNames.BISMUTH),
    Silver(NordicNames.SILVER),
    Nickle(NordicNames.NICKLE),
    Steel(NordicNames.STEEL),
    Norse(NordicNames.NORSE),
    None(NordicNames.NONE);

    public String name;

    BindingMaterial(String name) {

        this.name = name;
    }
}
