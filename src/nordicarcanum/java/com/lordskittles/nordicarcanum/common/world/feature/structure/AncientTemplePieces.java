package com.lordskittles.nordicarcanum.common.world.feature.structure;

import com.lordskittles.arcanumapi.common.world.feature.structure.StructurePieceBase;
import com.lordskittles.nordicarcanum.common.registry.Structures;
import com.lordskittles.nordicarcanum.core.NordicResourceLocations;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.structure.IglooPieces;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Random;

public class AncientTemplePieces {

    public static class Piece extends StructurePieceBase {

        public Piece(TemplateManager manager, ResourceLocation structure, BlockPos pos, Rotation rotation) {

            super(manager, structure, pos, rotation, StructureAncientTemple.offset, Structures.temple_piece, 0);

            this.loot_table = NordicResourceLocations.PILLAR_LOOT;
            this.structure_block_replacement = net.minecraft.block.Blocks.AIR.getDefaultState();
        }

        public Piece(TemplateManager manager, CompoundNBT nbt) {

            super(manager, nbt, Structures.temple_piece);

            this.structure_block_replacement = net.minecraft.block.Blocks.AIR.getDefaultState();
        }
    }
}
