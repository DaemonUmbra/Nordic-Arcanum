package com.lordskittles.nordicarcanum.core;

import net.minecraft.util.ResourceLocation;

public class NordicTextures
{
    private static final String ICON_LOCATION = NordicArcanum.MODID + ":";

    private static final String GUI_LOCATION = ICON_LOCATION + "textures/gui/";

    public static final ResourceLocation JEI_CRAFTING_CLOTH = guiTexture("jei/crafting_cloth.png");
    public static final ResourceLocation JEI_NORDIC_FURNACE = guiTexture("jei/nordic_furnace.png");

    private static ResourceLocation guiTexture(String img)
    {
        return new ResourceLocation(GUI_LOCATION + img);
    }
}
