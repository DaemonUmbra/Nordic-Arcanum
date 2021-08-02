package com.lordskittles.nordicarcanum.common.world.feature.structure;

import com.lordskittles.arcanumapi.common.world.feature.structure.StructurePieceBase;
import com.lordskittles.nordicarcanum.common.block.decoration.BlockPillar;
import com.lordskittles.nordicarcanum.common.block.magic.BlockInfusablePillar;
import com.lordskittles.nordicarcanum.common.registry.Blocks;
import com.lordskittles.nordicarcanum.common.registry.Structures;
import com.lordskittles.nordicarcanum.core.NordicResourceLocations;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

public class NordicPillarPieces {

    public static class Piece extends StructurePieceBase {

        public Piece(StructureManager manager, ResourceLocation structure, BlockPos pos, Rotation rotation, int height) {

            super(Structures.pillar_piece, height, manager, structure, structure.getPath(), makeSettings(rotation), pos, StructureNordicPillar.offset);

            this.loot_table = NordicResourceLocations.PILLAR_LOOT;
            this.structure_block_replacement = Blocks.deepslate_pillar.get().defaultBlockState().setValue(BlockPillar.AXIS, Direction.Axis.Y).setValue(BlockInfusablePillar.ACTIVATED, true);
        }

        public Piece(ServerLevel level, CompoundTag nbt) {

            super(Structures.pillar_piece, nbt, level, (_level) -> { return makeSettings(Rotation.valueOf(nbt.getString("Rotation"))); });

            this.structure_block_replacement = Blocks.deepslate_pillar.get().defaultBlockState().setValue(BlockPillar.AXIS, Direction.Axis.Y).setValue(BlockInfusablePillar.ACTIVATED, true);
        }
    }
}
