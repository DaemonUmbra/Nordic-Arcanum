package com.lordskittles.nordicarcanum.common.world.feature.structure;

import com.google.common.collect.ImmutableMap;
import com.lordskittles.arcanumapi.common.world.feature.structure.StructureBase;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.mojang.serialization.Codec;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

import java.util.Map;

public class StructureAncientTemple extends StructureBase<NoneFeatureConfiguration> {

    private static final ResourceLocation temple = NordicArcanum.RL("norse_temple");
    public static final Map<ResourceLocation, BlockPos> offset = ImmutableMap.of(temple, new BlockPos(0, -1, 0));

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
    protected boolean isFeatureChunk(ChunkGenerator generator, BiomeSource provider, long seed, WorldgenRandom randomSeed, ChunkPos chunkPos, Biome biome, ChunkPos chunkPos2, NoneFeatureConfiguration config, LevelHeightAccessor accessor) {

        if(chunkPos.x == chunkPos2.x && chunkPos.z == chunkPos2.z) {
            return provider.canGenerateStructure(this);
        }

        return false;
    }

    public static class Start extends StructureStart<NoneFeatureConfiguration> {

        public Start(StructureFeature<NoneFeatureConfiguration> structure, ChunkPos chunkPos, int references, long seed) {

            super(structure, chunkPos, references, seed);
        }

        @Override
        public void generatePieces(RegistryAccess registry, ChunkGenerator generator, StructureManager structureManager, ChunkPos chunkPos, Biome biome, NoneFeatureConfiguration config, LevelHeightAccessor accessor) {

            int worldX = (chunkPos.x << 4) + 4;
            int worldZ = (chunkPos.z << 4) + 7;
            BlockPos pos = new BlockPos(worldX, accessor.getHeight(), worldZ);
            Rotation rotation = Rotation.getRandom(this.random);
            NordicArcanum.LOG.debug("Temple at: " + pos.toString());
            this.pieces.add(new AncientTemplePieces.Piece(structureManager, temple, pos, rotation, accessor.getHeight()));
            this.createBoundingBox();
        }
    }
}
