package com.lordskittles.nordicarcanum.common.item.crafting;

import com.lordskittles.nordicarcanum.core.NordicNames;

public enum RodMaterial
{
    Yew(NordicNames.YEW),
    Juniper(NordicNames.JUNIPER),
    Pine(NordicNames.PINE),
    Oak(NordicNames.OAK),
    Spruce(NordicNames.SPRUCE),
    Birch(NordicNames.BIRCH),
    Jungle(NordicNames.JUNGLE),
    DarkOak(NordicNames.DARK_OAK),
    Acacia(NordicNames.ACACIA),
    None(NordicNames.NONE);

    public String name;

    RodMaterial(String name)
    {
        this.name = name;
    }
}
