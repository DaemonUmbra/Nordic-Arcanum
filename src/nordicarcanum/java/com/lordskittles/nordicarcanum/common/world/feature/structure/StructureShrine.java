package com.lordskittles.nordicarcanum.common.world.feature.structure;

import com.google.common.collect.ImmutableMap;
import com.lordskittles.arcanumapi.common.world.feature.structure.StructureBase;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicNames;
import com.lordskittles.nordicarcanum.core.NordicResourceLocations;
import com.mojang.serialization.Codec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Map;

public class StructureShrine extends StructureBase<NoneFeatureConfiguration> {

    private static final ResourceLocation shrine = NordicArcanum.RL(NordicNames.SHRINE);
    public static final Map<ResourceLocation, BlockPos> offset = ImmutableMap.of(shrine, new BlockPos(0, - 1, 0));;
    protected final ResourceLocation loot;

    public StructureShrine(Codec<NoneFeatureConfiguration> codec) {

        super(codec);

        this.loot = NordicResourceLocations.SHRINE_LOOT;
    }

    @Override
    public int getDistance() {

        return 15;
    }

    @Override
    public int getSeparation() {

        return 7;
    }

    @Override
    public int getSeedModifier() {

        return 13951913;
    }

    @Override
    public StructureFeature.StructureStartFactory<NoneFeatureConfiguration> getStartFactory() {

        return StructureShrine.Start::new;
    }

    @Override
    public GenerationStep.Decoration getDecorationStage() {

        return GenerationStep.Decoration.SURFACE_STRUCTURES;
    }

    @Override
    protected boolean func_230363_a_(ChunkGenerator generator, BiomeSource provider, long seed, WorldgenRandom randomSeed, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoneFeatureConfiguration config) {

        if(chunkX == chunkPos.x && chunkZ == chunkPos.z) {
            return provider.hasStructure(this);
        }

        return false;
    }

    public static class Start extends StructureStart<NoFeatureConfig> {

        private StructureShrine shrineStructure;

        public Start(Structure<NoFeatureConfig> structure, int chunkX, int chunkZ, MutableBoundingBox bounds, int references, long seed) {

            super(structure, chunkX, chunkZ, bounds, references, seed);

            shrineStructure = (StructureShrine) structure;
        }

        @Override
        public void func_230364_a_(DynamicRegistries registry, ChunkGenerator generator, TemplateManager templateManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig config) {

            int worldX = (chunkX << 4) + 4;
            int worldZ = (chunkZ << 4) + 7;
            BlockPos pos = new BlockPos(worldX, generator.getHeight(worldX, worldZ, Heightmap.Type.WORLD_SURFACE_WG), worldZ);
            Rotation rotation = Rotation.randomRotation(this.rand);
            this.components.add(new ShrinePieces.Piece(templateManager, shrineStructure.shrine, pos, rotation, shrineStructure.offset, shrineStructure.loot));
            this.recalculateStructureSize();
        }
    }
}
