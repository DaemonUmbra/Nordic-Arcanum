package com.lordskittles.nordicarcanum.common.registry;

import com.google.common.collect.ImmutableList;
import com.lordskittles.arcanumapi.common.world.feature.structure.StructureBase;
import com.lordskittles.nordicarcanum.common.world.feature.structure.NordicPillarPieces;
import com.lordskittles.nordicarcanum.common.world.feature.structure.ShrinePieces;
import com.lordskittles.nordicarcanum.common.world.feature.structure.StructureNordicPillar;
import com.lordskittles.nordicarcanum.common.world.feature.structure.StructureShrine;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicNames;
import com.lordskittles.nordicarcanum.core.NordicResourceLocations;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = NordicArcanum.MODID)
public class Structures
{
    public static final List<StructureBase> STRUCTURE_LIST = new ArrayList<>();

    public static final DeferredRegister<Structure<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, NordicArcanum.MODID);

    //Structures
    public static final RegistryObject<StructureNordicPillar> norse_pillar = registerStructure(NordicNames.ANCIENT_NORSE + NordicNames.STRUCTURE_SUFFIX, new StructureNordicPillar(NoFeatureConfig.CODEC));

    public static final RegistryObject<StructureShrine> shrine_baldur = registerStructure(NordicNames.SHRINE + NordicNames.STRUCTURE_SUFFIX, new StructureShrine(NoFeatureConfig.CODEC));

    //Structure Pieces
    public static IStructurePieceType pillar_piece = NordicPillarPieces.Piece::new;
    public static IStructurePieceType shrine_piece = ShrinePieces.Piece::new;

    // Structure Features
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> norse_pillar_feature;

    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> shrine_baldur_feature;

    @SubscribeEvent
    public static void registerStructurePieces(RegistryEvent.Register<Feature<?>> event)
    {
        Registry.register(Registry.STRUCTURE_PIECE, NordicArcanum.RL(NordicNames.ANCIENT_NORSE + NordicNames.STRUCTURE_PIECE_SUFFIX), pillar_piece);
        Registry.register(Registry.STRUCTURE_PIECE, NordicArcanum.RL(NordicNames.SHRINE + NordicNames.STRUCTURE_PIECE_SUFFIX), shrine_piece);
    }

    public static void registerFeatures()
    {
        norse_pillar_feature = register(NordicNames.ANCIENT_NORSE + NordicNames.STRUCTURE_SUFFIX + "_feature", norse_pillar.get().withConfiguration(NoFeatureConfig.INSTANCE));

        shrine_baldur_feature = register(NordicNames.SHRINE + NordicNames.STRUCTURE_SUFFIX + "_feature", shrine_baldur.get().withConfiguration(NoFeatureConfig.INSTANCE));
    }

    private static <T extends StructureBase<?>> RegistryObject<T> registerStructure(String name, T structure)
    {
        Structure.NAME_STRUCTURE_BIMAP.put(NordicArcanum.MODID + ":" + name, structure);
        Structure.STRUCTURE_DECORATION_STAGE_MAP.put(structure, GenerationStage.Decoration.SURFACE_STRUCTURES);

        Structure.field_236384_t_ = ImmutableList.<Structure<?>>builder().addAll(Structure.field_236384_t_).add(structure).build();

        STRUCTURE_LIST.add(structure);

        return STRUCTURES.register(name, () -> structure);
    }

    private static <FC extends IFeatureConfig, F extends Structure<FC>> StructureFeature<FC, F> register(String name, StructureFeature<FC, F> feature)
    {
        return WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE, name, feature);
    }
}
