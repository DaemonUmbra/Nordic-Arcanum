package com.lordskittles.nordicarcanum.core;

import com.lordskittles.nordicarcanum.common.item.crafting.BindingMaterial;
import com.lordskittles.nordicarcanum.common.item.crafting.RodMaterial;
import net.minecraft.util.ResourceLocation;

public class NordicResourceLocations
{
    public static final String TILE_ENTITY_TEXTURE_LOC = "textures/blocks/entities/";
    public static final String ITEM_LOC = "textures/items/";
    public static final String GUI_LOC = "textures/gui/";
    public static final String ENTITY_TEXTURE_LOC = "textures/entities/";

    public static final String BINDING_LOC = ITEM_LOC + "binding/";
    public static final String ROD_LOC = ITEM_LOC + "rods/";

    // Tile Entity Textures
    public static final ResourceLocation ARCANE_CHEST = NordicArcanum.RL(TILE_ENTITY_TEXTURE_LOC + "arcane_chest.png");
    public static final ResourceLocation IMPOSSIBLE_CHEST = NordicArcanum.RL(TILE_ENTITY_TEXTURE_LOC + "impossible_chest.png");
    public static final ResourceLocation ARCANE_TANK = NordicArcanum.RL(TILE_ENTITY_TEXTURE_LOC + "arcane_tank.png");
    public static final ResourceLocation IMPOSSIBLE_TANK = NordicArcanum.RL(TILE_ENTITY_TEXTURE_LOC + "impossible_tank.png");
    public static final ResourceLocation SIGIL_PODIUM = NordicArcanum.RL(TILE_ENTITY_TEXTURE_LOC + "sigil_podium.png");
    public static final ResourceLocation ATTUNEMENT_ALTAR = NordicArcanum.RL(TILE_ENTITY_TEXTURE_LOC + "attunement_altar.png");
    public static final ResourceLocation NORDIC_ANVIL = NordicArcanum.RL(TILE_ENTITY_TEXTURE_LOC + "nordic_anvil.png");
    public static final ResourceLocation ARCANE_INFUSER = NordicArcanum.RL(TILE_ENTITY_TEXTURE_LOC + "arcane_infuser.png");

    // GUI Textures
    public static final ResourceLocation BUTTON_TEXTURES = NordicArcanum.RL(GUI_LOC + "buttons.png");

    // Staff Textures
    public static ResourceLocation getBindingTexture(BindingMaterial material, int value) {
        return NordicArcanum.RL(BINDING_LOC + material.name + "_" + value + ".png");
    }

    public static ResourceLocation getRodTexture(RodMaterial material, int value) {
        return NordicArcanum.RL(ROD_LOC + material.name + "_" + value + ".png");
    }

    // Item Overrides
    public static final ResourceLocation SIGIL_OVERRIDE = NordicArcanum.RL("school");
    public static final ResourceLocation SHAPE_OVERRIDE = NordicArcanum.RL("shape");

    // Chest Loot
    public static final ResourceLocation PILLAR_LOOT = NordicArcanum.RL("chests/nordic_pillar");
    public static final ResourceLocation SHRINE_LOOT = NordicArcanum.RL("chests/shrine");

    // Phoenix Locations
    public static final ResourceLocation PHOENIX_ID = NordicArcanum.RL(NordicNames.PHOENIX);
    public static final ResourceLocation PHOENIX_AMBIENT = NordicArcanum.RL(NordicNames.PHOENIX_AMBIENT);
}
