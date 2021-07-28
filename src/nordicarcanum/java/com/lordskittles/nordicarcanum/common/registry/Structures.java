package com.lordskittles.nordicarcanum.common.registry;

import com.google.common.collect.ImmutableList;
import com.lordskittles.arcanumapi.common.world.feature.structure.StructureBase;
import com.lordskittles.nordicarcanum.common.world.feature.structure.*;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicNames;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructurePieceType;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = NordicArcanum.MODID)
public class Structures {

    public static final List<StructureBase> STRUCTURE_LIST = new ArrayList<>();

    public static final DeferredRegister<StructureFeature<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, NordicArcanum.MODID);

    //Structures
    public static final RegistryObject<StructureNordicPillar> norse_pillar = registerStructure(NordicNames.ANCIENT_NORSE + NordicNames.STRUCTURE_SUFFIX, new StructureNordicPillar(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<StructureAncientTemple> ancient_temple = registerStructure(NordicNames.ANCIENT_TEMPLE + NordicNames.STRUCTURE_SUFFIX, new StructureAncientTemple(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<StructureShrine> shrine = registerStructure(NordicNames.SHRINE + NordicNames.STRUCTURE_SUFFIX, new StructureShrine(NoneFeatureConfiguration.CODEC));

    //Structure Pieces
    public static StructurePieceType pillar_piece = StructurePieceType.setPieceId(NordicPillarPieces.Piece::new, "P");
    public static StructurePieceType temple_piece = StructurePieceType.setPieceId(AncientTemplePieces.Piece::new, "T");
    public static StructurePieceType shrine_piece = StructurePieceType.setPieceId(ShrinePieces.Piece::new, "S");

    // Structure Features
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> norse_pillar_feature;
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> temple_feature;
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> shrine_feature;

    public static void registerFeatures() {

        norse_pillar_feature = register(NordicNames.ANCIENT_NORSE + NordicNames.STRUCTURE_SUFFIX + "_feature", norse_pillar.get().configured(NoneFeatureConfiguration.INSTANCE));
        temple_feature = register(NordicNames.ANCIENT_TEMPLE + NordicNames.STRUCTURE_SUFFIX + "_feature", ancient_temple.get().configured(NoneFeatureConfiguration.INSTANCE));
        shrine_feature = register(NordicNames.SHRINE + NordicNames.STRUCTURE_SUFFIX + "_feature", shrine.get().configured(NoneFeatureConfiguration.INSTANCE));
    }

    private static <T extends StructureBase<?>> RegistryObject<T> registerStructure(String name, T structure) {

//        StructureFeature.STRUCTURES_REGISTRY.put(NordicArcanum.MODID + ":" + name, structure);
        StructureFeature.STRUCTURES_REGISTRY.put(GenerationStep.Decoration.SURFACE_STRUCTURES.name(), structure);

        StructureFeature.NOISE_AFFECTING_FEATURES = ImmutableList.<StructureFeature<?>>builder().addAll(StructureFeature.NOISE_AFFECTING_FEATURES).add(structure).build();

        STRUCTURE_LIST.add(structure);

        return STRUCTURES.register(name, () -> structure);
    }

    private static <FC extends FeatureConfiguration, F extends StructureFeature<FC>> ConfiguredStructureFeature<FC, F> register(String name, ConfiguredStructureFeature<FC, F> feature) {

        return BuiltinRegistries.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, name, feature);
    }
}
