package com.lordskittles.nordicarcanum.common.world.feature.structure;

import com.google.common.collect.ImmutableMap;
import com.lordskittles.arcanumapi.common.world.feature.structure.StructureBase;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicConfig;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.StructureFeatureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

import java.util.Map;
import java.util.Random;

public class StructureAncientTemple extends StructureBase<NoneFeatureConfiguration> {

    private static final ResourceLocation temple = NordicArcanum.RL("norse_temple");
    public static final Map<ResourceLocation, BlockPos> offset = ImmutableMap.of(temple, new BlockPos(0, - 1, 0));

    public StructureAncientTemple(Codec<NoneFeatureConfiguration> codec) {

        super(codec);
    }

    @Override
    public int getDistance() {

        return 45;
    }

    @Override
    public int getSeparation() {

        return 8;
    }

    @Override
    public int getSeedModifier() {

        return 14357619;
    }

    @Override
    public StructureStartFactory<NoneFeatureConfiguration> getStartFactory() {

        return StructureAncientTemple.Start::new;
    }

    @Override
    public GenerationStep.Decoration step() {

        return GenerationStep.Decoration.SURFACE_STRUCTURES;
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator generator, BiomeSource source, long seed, WorldgenRandom random, ChunkPos chunkPos, Biome chunkBiome, ChunkPos chunkPos1, NoneFeatureConfiguration configuration, LevelHeightAccessor heightAccessor) {

        for(Biome biome : source.getBiomesWithin(chunkPos.x * 16, generator.getSpawnHeight(heightAccessor), chunkPos.z * 16, 64)) {
            if(! biome.getGenerationSettings().isValidStart(this)) {
                return false;
            }
        }
        return true;
    }

    public static class Start extends StructureStart<NoneFeatureConfiguration> {

        public Start(StructureFeature<NoneFeatureConfiguration> structure, ChunkPos chunkPos, int references, long seed) {

            super(structure, chunkPos, references, seed);
        }

        @Override
        public void generatePieces(RegistryAccess registry, ChunkGenerator generator, StructureManager structureManager, ChunkPos chunkPos, Biome biome, NoneFeatureConfiguration config, LevelHeightAccessor accessor) {

            int worldX = (chunkPos.x << 4) + 4;
            int worldZ = (chunkPos.z << 4) + 7;
            BlockPos pos = new BlockPos(worldX, generator.getBaseHeight(worldX, worldZ, Heightmap.Types.WORLD_SURFACE_WG, accessor), worldZ);
            Rotation rotation = Rotation.getRandom(this.random);
            NordicArcanum.LOG.info("Starting shrine generation at {}", pos.toString());
            this.addPiece(new AncientTemplePieces.Piece(structureManager, temple, pos, rotation, accessor.getHeight()));
            this.createBoundingBox();
        }

        @Override
        public void placeInChunk(WorldGenLevel level, StructureFeatureManager manager, ChunkGenerator generator, Random random, BoundingBox boundingBox, ChunkPos chunkPos) {

            if(NordicConfig.extendedLogging.get()) {
                NordicArcanum.LOG.info("Starting temple generation in chunk [{},{}]", chunkPos.x, chunkPos.z);
            }

            super.placeInChunk(level, manager, generator, random, boundingBox, chunkPos);

            if(NordicConfig.extendedLogging.get()) {
                NordicArcanum.LOG.info("Finished temple generation in chunk [{},{}]", chunkPos.x, chunkPos.z);
            }
        }
    }
}
