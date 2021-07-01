package com.lordskittles.nordicarcanum.common.world.feature.structure;

import com.google.common.collect.ImmutableMap;
import com.lordskittles.arcanumapi.common.world.feature.structure.StructureBase;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicNames;
import com.lordskittles.nordicarcanum.core.NordicResourceLocations;
import com.mojang.serialization.Codec;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Map;

public class StructureShrine extends StructureBase<NoFeatureConfig> {

    protected final ResourceLocation shrine;
    protected final Map<ResourceLocation, BlockPos> offset;
    protected final ResourceLocation loot;

    public StructureShrine(Codec<NoFeatureConfig> codec) {

        super(codec);

        this.shrine = NordicArcanum.RL(NordicNames.SHRINE);
        this.offset = ImmutableMap.of(this.shrine, new BlockPos(0, - 1, 0));
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
    public Structure.IStartFactory<NoFeatureConfig> getStartFactory() {

        return StructureShrine.Start::new;
    }

    @Override
    public GenerationStage.Decoration getDecorationStage() {

        return GenerationStage.Decoration.SURFACE_STRUCTURES;
    }

    @Override
    protected boolean func_230363_a_(ChunkGenerator generator, BiomeProvider provider, long seed, SharedSeedRandom randomSeed, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig config) {

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
