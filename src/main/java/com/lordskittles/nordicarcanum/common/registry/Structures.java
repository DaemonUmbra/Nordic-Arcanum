package com.lordskittles.nordicarcanum.common.registry;

import com.google.common.collect.ImmutableMap;
import com.lordskittles.arcanumapi.common.world.feature.structure.StructureBase;
import com.lordskittles.nordicarcanum.common.world.feature.structure.*;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicNames;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.StructureSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.StructurePieceType;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = NordicArcanum.MODID)
public class Structures {

    public static List<StructureBase> STRUCTURE_LIST = new ArrayList<>();

    //Structures
    public static final StructureNordicPillar norse_pillar = new StructureNordicPillar(NoneFeatureConfiguration.CODEC);
    public static final StructureAncientTemple ancient_temple = new StructureAncientTemple(NoneFeatureConfiguration.CODEC);
    public static final StructureShrine shrine = new StructureShrine(NoneFeatureConfiguration.CODEC);

    //Structure Pieces
    public static StructurePieceType pillar_piece;
    public static StructurePieceType temple_piece;
    public static StructurePieceType shrine_piece;

    // Structure Features
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> norse_pillar_feature;
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> temple_feature;
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> shrine_feature;

    public static void registerPieces() {

        pillar_piece = StructurePieceType.setPieceId(NordicPillarPieces.Piece::new, createKey("pillar"));
        temple_piece = StructurePieceType.setPieceId(AncientTemplePieces.Piece::new, createKey("temple"));
        shrine_piece = StructurePieceType.setPieceId(ShrinePieces.Piece::new, createKey("shrine"));
    }

    public static void registerFeatures() {

        norse_pillar_feature = register(NordicNames.ANCIENT_NORSE + NordicNames.STRUCTURE_SUFFIX + "_feature", norse_pillar.configured(NoneFeatureConfiguration.INSTANCE));
        temple_feature = register(NordicNames.ANCIENT_TEMPLE + NordicNames.STRUCTURE_SUFFIX + "_feature", ancient_temple.configured(NoneFeatureConfiguration.INSTANCE));
        shrine_feature = register(NordicNames.SHRINE + NordicNames.STRUCTURE_SUFFIX + "_feature", shrine.configured(NoneFeatureConfiguration.INSTANCE));
    }

    private static <FC extends FeatureConfiguration, F extends StructureFeature<FC>> ConfiguredStructureFeature<FC, F> register(String name, ConfiguredStructureFeature<FC, F> feature) {

        ResourceLocation registryName = NordicArcanum.RL(name.toLowerCase(Locale.ROOT));
        StructureBase structureBase = (StructureBase) feature.feature;

        STRUCTURE_LIST.add(structureBase);

        feature.feature.setRegistryName(registryName);

        StructureFeature.STRUCTURES_REGISTRY.put(registryName.toString().toLowerCase(Locale.ROOT), structureBase);
        ForgeRegistries.STRUCTURE_FEATURES.register(structureBase);

        StructureSettings.DEFAULTS = ImmutableMap.<StructureFeature<?>, StructureFeatureConfiguration>builder()
                .putAll(StructureSettings.DEFAULTS)
                .put(structureBase, new StructureFeatureConfiguration(structureBase.getDistance(), structureBase.getSeparation(), structureBase.getSeedModifier()))
                .build();

        Registry<ConfiguredStructureFeature<?, ?>> registry = BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE;
        ConfiguredStructureFeature<FC, F> configured = Registry.register(registry, registryName, feature);

        return configured;
    }

    private static String createKey(String path) {

        return NordicArcanum.MODID + ":" + path;
    }
}
