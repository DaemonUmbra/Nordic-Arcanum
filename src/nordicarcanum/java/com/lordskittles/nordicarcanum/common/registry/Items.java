package com.lordskittles.nordicarcanum.common.registry;

import com.lordskittles.arcanumapi.common.item.ItemMod;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicItemGroup;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicResourcesItemGroup;
import com.lordskittles.nordicarcanum.common.item.*;
import com.lordskittles.nordicarcanum.common.item.crafting.*;
import com.lordskittles.nordicarcanum.common.item.magic.*;
import com.lordskittles.nordicarcanum.common.item.resource.ItemCrystal;
import com.lordskittles.nordicarcanum.common.item.resource.ItemDust;
import com.lordskittles.nordicarcanum.common.item.resource.ItemIngot;
import com.lordskittles.nordicarcanum.common.item.resource.ItemRawOre;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicNames;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Items {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, NordicArcanum.MODID);

    public static final RegistryObject<Item> arcane_powder = ITEMS.register(NordicNames.ARCANE_POWDER, () -> new ItemDust(NordicResourcesItemGroup.INSTANCE));
    public static final RegistryObject<Item> feldspar_dust = ITEMS.register(NordicNames.FELDSPAR + NordicNames.DUST_SUFFIX, ItemDust::new);
    public static final RegistryObject<Item> carnelian_dust = ITEMS.register(NordicNames.CARNELIAN + NordicNames.DUST_SUFFIX, ItemDust::new);
    public static final RegistryObject<Item> garnet_dust = ITEMS.register(NordicNames.GARNET + NordicNames.DUST_SUFFIX, ItemDust::new);
    public static final RegistryObject<Item> thulite_dust = ITEMS.register(NordicNames.THULITE + NordicNames.DUST_SUFFIX, ItemDust::new);

    public static final RegistryObject<Item> carnelian_crystal = ITEMS.register(NordicNames.CARNELIAN + NordicNames.CRYSTAL_SUFFIX, ItemCrystal::new);
    public static final RegistryObject<Item> garnet_crystal = ITEMS.register(NordicNames.GARNET + NordicNames.CRYSTAL_SUFFIX, ItemCrystal::new);
    public static final RegistryObject<Item> thulite_crystal = ITEMS.register(NordicNames.THULITE + NordicNames.CRYSTAL_SUFFIX, ItemCrystal::new);

    public static final RegistryObject<Item> bismuth_ingot = ITEMS.register(NordicNames.BISMUTH + NordicNames.INGOT_SUFFIX, ItemIngot::new);
    public static final RegistryObject<Item> nickle_ingot = ITEMS.register(NordicNames.NICKLE + NordicNames.INGOT_SUFFIX, ItemIngot::new);
    public static final RegistryObject<Item> silver_ingot = ITEMS.register(NordicNames.SILVER + NordicNames.INGOT_SUFFIX, ItemIngot::new);
    public static final RegistryObject<Item> steel_ingot = ITEMS.register(NordicNames.STEEL + NordicNames.INGOT_SUFFIX, ItemIngot::new);
    public static final RegistryObject<Item> norse_ingot = ITEMS.register(NordicNames.NORSE + NordicNames.INGOT_SUFFIX, ItemIngot::new);
    public static final RegistryObject<Item> electrum_ingot = ITEMS.register(NordicNames.ELECTRUM + NordicNames.INGOT_SUFFIX, ItemIngot::new);
    public static final RegistryObject<Item> bronze_ingot = ITEMS.register(NordicNames.BRONZE + NordicNames.INGOT_SUFFIX, ItemIngot::new);
    public static final RegistryObject<Item> mithril_ingot = ITEMS.register(NordicNames.MITHRIL + NordicNames.INGOT_SUFFIX, ItemIngot::new);
    public static final RegistryObject<Item> billon_ingot = ITEMS.register(NordicNames.BILLON + NordicNames.INGOT_SUFFIX, ItemIngot::new);
    public static final RegistryObject<Item> sun_steel_ingot = ITEMS.register(NordicNames.SUN_STEEL + NordicNames.INGOT_SUFFIX, ItemIngot::new);
    public static final RegistryObject<Item> sea_steel_ingot = ITEMS.register(NordicNames.SEA_STEEL + NordicNames.INGOT_SUFFIX, ItemIngot::new);

    public static final RegistryObject<Item> bismuth_raw_ore = ITEMS.register(NordicNames.BISMUTH + NordicNames.RAW_ORE_SUFFIX, ItemRawOre::new);
    public static final RegistryObject<Item> nickle_raw_ore = ITEMS.register(NordicNames.NICKLE + NordicNames.RAW_ORE_SUFFIX, ItemRawOre::new);
    public static final RegistryObject<Item> silver_raw_ore = ITEMS.register(NordicNames.SILVER + NordicNames.RAW_ORE_SUFFIX, ItemRawOre::new);

    public static final RegistryObject<Item> raven_feather = ITEMS.register(NordicNames.RAVEN_FEATHER, () -> new ItemMod(NordicItemGroup.INSTANCE));
    public static final RegistryObject<Item> dragon_scale = ITEMS.register(NordicNames.DRAGON_SCALE, () -> new ItemMod(NordicItemGroup.INSTANCE));
    public static final RegistryObject<Item> fossegrim_hair = ITEMS.register(NordicNames.FOSSEGRIM_HAIR, () -> new ItemMod(NordicItemGroup.INSTANCE));
    public static final RegistryObject<Item> bat_wing = ITEMS.register(NordicNames.BAT_WING, () -> new ItemMod(NordicItemGroup.INSTANCE));
    public static final RegistryObject<Item> pegasus_horn = ITEMS.register(NordicNames.PEGASUS_HORN, () -> new ItemMod(NordicItemGroup.INSTANCE));
    public static final RegistryObject<Item> wolf_fang = ITEMS.register(NordicNames.WOLF_FANG, () -> new ItemMod(NordicItemGroup.INSTANCE));

    public static final RegistryObject<Item> base_core = ITEMS.register(NordicNames.BASE + NordicNames.CORE_SUFFIX, ItemCore::new);
    public static final RegistryObject<Item> raven_feature_core = ITEMS.register(NordicNames.RAVEN_FEATHER + NordicNames.CORE_SUFFIX, ItemCore::new);
    public static final RegistryObject<Item> fossegrim_hair_core = ITEMS.register(NordicNames.FOSSEGRIM_HAIR + NordicNames.CORE_SUFFIX, ItemCore::new);
    public static final RegistryObject<Item> dragon_scale_core = ITEMS.register(NordicNames.DRAGON_SCALE + NordicNames.CORE_SUFFIX, ItemCore::new);
    public static final RegistryObject<Item> bat_wing_core = ITEMS.register(NordicNames.BAT_WING + NordicNames.CORE_SUFFIX, ItemCore::new);
    public static final RegistryObject<Item> pegasus_horn_core = ITEMS.register(NordicNames.PEGASUS_HORN + NordicNames.CORE_SUFFIX, ItemCore::new);
    public static final RegistryObject<Item> wolf_fang_core = ITEMS.register(NordicNames.WOLF_FANG + NordicNames.CORE_SUFFIX, ItemCore::new);
    public static final RegistryObject<Item> ender_pearl_core = ITEMS.register(NordicNames.ENDER_PEARL + NordicNames.CORE_SUFFIX, ItemCore::new);

    public static final RegistryObject<Item> yew_rod = ITEMS.register(NordicNames.YEW + NordicNames.ROD_SUFFIX, () -> new ItemRod(RodMaterial.Yew));
    public static final RegistryObject<Item> juniper_rod = ITEMS.register(NordicNames.JUNIPER + NordicNames.ROD_SUFFIX, () -> new ItemRod(RodMaterial.Juniper));
    public static final RegistryObject<Item> pine_rod = ITEMS.register(NordicNames.PINE + NordicNames.ROD_SUFFIX, () -> new ItemRod(RodMaterial.Pine));
    public static final RegistryObject<Item> oak_rod = ITEMS.register(NordicNames.OAK + NordicNames.ROD_SUFFIX, () -> new ItemRod(RodMaterial.Oak));
    public static final RegistryObject<Item> spruce_rod = ITEMS.register(NordicNames.SPRUCE + NordicNames.ROD_SUFFIX, () -> new ItemRod(RodMaterial.Spruce));
    public static final RegistryObject<Item> birch_rod = ITEMS.register(NordicNames.BIRCH + NordicNames.ROD_SUFFIX, () -> new ItemRod(RodMaterial.Birch));
    public static final RegistryObject<Item> jungle_rod = ITEMS.register(NordicNames.JUNGLE + NordicNames.ROD_SUFFIX, () -> new ItemRod(RodMaterial.Jungle));
    public static final RegistryObject<Item> dark_oak_rod = ITEMS.register(NordicNames.DARK_OAK + NordicNames.ROD_SUFFIX, () -> new ItemRod(RodMaterial.DarkOak));
    public static final RegistryObject<Item> acacia_rod = ITEMS.register(NordicNames.ACACIA + NordicNames.ROD_SUFFIX, () -> new ItemRod(RodMaterial.Acacia));

    public static final RegistryObject<Item> iron_binding = ITEMS.register(NordicNames.IRON + NordicNames.BINDING_SUFFIX, () -> new ItemBinding(BindingMaterial.Iron));
    public static final RegistryObject<Item> gold_binding = ITEMS.register(NordicNames.GOLD + NordicNames.BINDING_SUFFIX, () -> new ItemBinding(BindingMaterial.Gold));
    public static final RegistryObject<Item> bismuth_binding = ITEMS.register(NordicNames.BISMUTH + NordicNames.BINDING_SUFFIX, () -> new ItemBinding(BindingMaterial.Bismuth));
    public static final RegistryObject<Item> nickle_binding = ITEMS.register(NordicNames.NICKLE + NordicNames.BINDING_SUFFIX, () -> new ItemBinding(BindingMaterial.Nickle));
    public static final RegistryObject<Item> silver_binding = ITEMS.register(NordicNames.SILVER + NordicNames.BINDING_SUFFIX, () -> new ItemBinding(BindingMaterial.Silver));
    public static final RegistryObject<Item> steel_binding = ITEMS.register(NordicNames.STEEL + NordicNames.BINDING_SUFFIX, () -> new ItemBinding(BindingMaterial.Steel));
    public static final RegistryObject<Item> norse_binding = ITEMS.register(NordicNames.NORSE + NordicNames.BINDING_SUFFIX, () -> new ItemBinding(BindingMaterial.Norse));

    public static final RegistryObject<Item> school_sigil = ITEMS.register(NordicNames.MAGIC_SIGIL, ItemSigil::new);
    public static final RegistryObject<Item> crafting_cloth_item = ITEMS.register(NordicNames.CRAFTING_CLOTH + "_item", ItemCraftingCloth::new);
    public static final RegistryObject<Item> shaping_hammer = ITEMS.register(NordicNames.SHAPING_HAMMER, ItemShapingHammer::new);
    public static final RegistryObject<Item> viking_saw = ITEMS.register(NordicNames.VIKING_SAW, ItemVikingSaw::new);
    public static final RegistryObject<Item> mortar_pestle = ITEMS.register(NordicNames.MORTAR_PESTLE, ItemMortarPestle::new);
    public static final RegistryObject<Item> infused_arcane_powder = ITEMS.register(NordicNames.INFUSED_ARCANE_POWDER, ItemInfusedArcanePowder::new);
    public static final RegistryObject<Item> liquid_arcanum_bucket = ITEMS.register(NordicNames.LIQUID_ARCANUM + NordicNames.BUCKET, () -> new BucketItem(Fluids.liquid_arcanum, new Item.Properties().group(NordicResourcesItemGroup.INSTANCE).maxStackSize(1)));
    public static final RegistryObject<Item> arcanum_bottle = ITEMS.register(NordicNames.ARCANUM_BOTTLE, ItemArcanumBottle::new);

    public static final RegistryObject<Item> pedestal_cast = ITEMS.register(NordicNames.PEDESTAL + NordicNames.CAST, () -> new ItemBindingCast(BindingShape.Pedestal));
    public static final RegistryObject<Item> brazier_cast = ITEMS.register(NordicNames.BRAZIER + NordicNames.CAST, () -> new ItemBindingCast(BindingShape.Brazier));
    public static final RegistryObject<Item> cane_cast = ITEMS.register(NordicNames.CANE + NordicNames.CAST, () -> new ItemBindingCast(BindingShape.Cane));
    public static final RegistryObject<Item> cage_cast = ITEMS.register(NordicNames.CAGE + NordicNames.CAST, () -> new ItemBindingCast(BindingShape.Cage));

    public static final RegistryObject<Item> straight_blueprint = ITEMS.register(NordicNames.STRAIGHT + NordicNames.BLUEPRINT, () -> new ItemRodBlueprint(RodShape.Straight));
    public static final RegistryObject<Item> twist_blueprint = ITEMS.register(NordicNames.TWIST + NordicNames.BLUEPRINT, () -> new ItemRodBlueprint(RodShape.Twist));
    public static final RegistryObject<Item> bend_blueprint = ITEMS.register(NordicNames.BEND + NordicNames.BLUEPRINT, () -> new ItemRodBlueprint(RodShape.Bend));
    public static final RegistryObject<Item> pillar_blueprint = ITEMS.register(NordicNames.PILLAR + NordicNames.BLUEPRINT, () -> new ItemRodBlueprint(RodShape.Pillar));

    public static final RegistryObject<Item> norse_hoe = ITEMS.register(NordicNames.NORSE + NordicNames.HOE_SUFFIX, () -> new HoeItem(ItemTiers.NORSE, - 2, - 1.0F, (new Item.Properties()).group(NordicItemGroup.INSTANCE)));
    public static final RegistryObject<Item> norse_shovel = ITEMS.register(NordicNames.NORSE + NordicNames.SHOVEL_SUFFIX, () -> new ShovelItem(ItemTiers.NORSE, 1.5F, - 3.0F, (new Item.Properties()).group(NordicItemGroup.INSTANCE)));
    public static final RegistryObject<Item> norse_pickaxe = ITEMS.register(NordicNames.NORSE + NordicNames.PICKAXE_SUFFIX, () -> new PickaxeItem(ItemTiers.NORSE, 1, - 2.8F, (new Item.Properties()).group(NordicItemGroup.INSTANCE)));
    public static final RegistryObject<Item> norse_axe = ITEMS.register(NordicNames.NORSE + NordicNames.AXE_SUFFIX, () -> new AxeItem(ItemTiers.NORSE, 6.5F, - 3.5F, (new Item.Properties()).group(NordicItemGroup.INSTANCE)));
    public static final RegistryObject<Item> norse_sword = ITEMS.register(NordicNames.NORSE + NordicNames.SWORD_SUFFIX, () -> new SwordItem(ItemTiers.NORSE, 3, - 2.4F, (new Item.Properties()).group(NordicItemGroup.INSTANCE)));

    public static final RegistryObject<Item> thulite_hoe = ITEMS.register(NordicNames.THULITE + NordicNames.HOE_SUFFIX, () -> new HoeItem(ItemTiers.THULITE, - 3, 0F, (new Item.Properties()).group(NordicItemGroup.INSTANCE)));
    public static final RegistryObject<Item> thulite_shovel = ITEMS.register(NordicNames.THULITE + NordicNames.SHOVEL_SUFFIX, () -> new ShovelItem(ItemTiers.THULITE, 1.5F, - 3.0F, (new Item.Properties()).group(NordicItemGroup.INSTANCE)));
    public static final RegistryObject<Item> thulite_pickaxe = ITEMS.register(NordicNames.THULITE + NordicNames.PICKAXE_SUFFIX, () -> new PickaxeItem(ItemTiers.THULITE, 1, - 2.8F, (new Item.Properties()).group(NordicItemGroup.INSTANCE)));
    public static final RegistryObject<Item> thulite_axe = ITEMS.register(NordicNames.THULITE + NordicNames.AXE_SUFFIX, () -> new AxeItem(ItemTiers.THULITE, 4.5F, - 3.4F, (new Item.Properties()).group(NordicItemGroup.INSTANCE)));
    public static final RegistryObject<Item> thulite_sword = ITEMS.register(NordicNames.THULITE + NordicNames.SWORD_SUFFIX, () -> new SwordItem(ItemTiers.THULITE, 3, - 2.4F, (new Item.Properties()).group(NordicItemGroup.INSTANCE)));

    public static final RegistryObject<Item> carnelian_hoe = ITEMS.register(NordicNames.CARNELIAN + NordicNames.HOE_SUFFIX, () -> new HoeItem(ItemTiers.CARNELIAN, - 3, 0F, (new Item.Properties()).group(NordicItemGroup.INSTANCE)));
    public static final RegistryObject<Item> carnelian_shovel = ITEMS.register(NordicNames.CARNELIAN + NordicNames.SHOVEL_SUFFIX, () -> new ShovelItem(ItemTiers.CARNELIAN, 1.5F, - 3.0F, (new Item.Properties()).group(NordicItemGroup.INSTANCE)));
    public static final RegistryObject<Item> carnelian_pickaxe = ITEMS.register(NordicNames.CARNELIAN + NordicNames.PICKAXE_SUFFIX, () -> new PickaxeItem(ItemTiers.CARNELIAN, 1, - 2.8F, (new Item.Properties()).group(NordicItemGroup.INSTANCE)));
    public static final RegistryObject<Item> carnelian_axe = ITEMS.register(NordicNames.CARNELIAN + NordicNames.AXE_SUFFIX, () -> new AxeItem(ItemTiers.CARNELIAN, 4.5F, - 3.4F, (new Item.Properties()).group(NordicItemGroup.INSTANCE)));
    public static final RegistryObject<Item> carnelian_sword = ITEMS.register(NordicNames.CARNELIAN + NordicNames.SWORD_SUFFIX, () -> new SwordItem(ItemTiers.CARNELIAN, 3, - 2.4F, (new Item.Properties()).group(NordicItemGroup.INSTANCE)));

    public static final RegistryObject<Item> garnet_hoe = ITEMS.register(NordicNames.GARNET + NordicNames.HOE_SUFFIX, () -> new HoeItem(ItemTiers.GARNET, - 3, 0F, (new Item.Properties()).group(NordicItemGroup.INSTANCE)));
    public static final RegistryObject<Item> garnet_shovel = ITEMS.register(NordicNames.GARNET + NordicNames.SHOVEL_SUFFIX, () -> new ShovelItem(ItemTiers.GARNET, 1.5F, - 3.0F, (new Item.Properties()).group(NordicItemGroup.INSTANCE)));
    public static final RegistryObject<Item> garnet_pickaxe = ITEMS.register(NordicNames.GARNET + NordicNames.PICKAXE_SUFFIX, () -> new PickaxeItem(ItemTiers.GARNET, 1, - 2.8F, (new Item.Properties()).group(NordicItemGroup.INSTANCE)));
    public static final RegistryObject<Item> garnet_axe = ITEMS.register(NordicNames.GARNET + NordicNames.AXE_SUFFIX, () -> new AxeItem(ItemTiers.GARNET, 4.5F, - 3.4F, (new Item.Properties()).group(NordicItemGroup.INSTANCE)));
    public static final RegistryObject<Item> garnet_sword = ITEMS.register(NordicNames.GARNET + NordicNames.SWORD_SUFFIX, () -> new SwordItem(ItemTiers.GARNET, 3, - 2.4F, (new Item.Properties()).group(NordicItemGroup.INSTANCE)));
}