package com.lordskittles.nordicarcanum.common.world.feature.structure;

import com.lordskittles.arcanumapi.common.world.feature.structure.StructurePieceBase;
import com.lordskittles.nordicarcanum.common.registry.Structures;
import com.lordskittles.nordicarcanum.core.NordicResourceLocations;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

public class AncientTemplePieces {

    public static class Piece extends StructurePieceBase {

        public Piece(StructureManager manager, ResourceLocation structure, BlockPos pos, Rotation rotation, int height) {

            super(Structures.temple_piece, height, manager, structure, structure.getPath(), makeSettings(rotation), pos, StructureAncientTemple.offset);

            this.loot_table = NordicResourceLocations.PILLAR_LOOT;
            this.structure_block_replacement = net.minecraft.world.level.block.Blocks.AIR.defaultBlockState();
        }

        public Piece(ServerLevel world, CompoundTag nbt) {

            super(Structures.temple_piece, nbt, world, (level) -> { return makeSettings(Rotation.valueOf(nbt.getString("Rot"))); });

            this.structure_block_replacement = net.minecraft.world.level.block.Blocks.AIR.defaultBlockState();
        }
    }
}
