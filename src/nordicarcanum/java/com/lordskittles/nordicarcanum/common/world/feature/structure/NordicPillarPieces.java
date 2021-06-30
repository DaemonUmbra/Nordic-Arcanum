package com.lordskittles.nordicarcanum.common.world.feature.structure;

import com.lordskittles.arcanumapi.common.world.feature.structure.StructurePieceBase;
import com.lordskittles.nordicarcanum.common.block.BlockInfusablePillar;
import com.lordskittles.nordicarcanum.common.block.BlockPillar;
import com.lordskittles.nordicarcanum.common.registry.Structures;
import com.lordskittles.nordicarcanum.core.NordicResourceLocations;
import com.lordskittles.nordicarcanum.common.registry.Blocks;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class NordicPillarPieces
{
    public static class Piece extends StructurePieceBase
    {
        public Piece(TemplateManager manager, ResourceLocation structure, BlockPos pos, Rotation rotation)
        {
            super(manager, structure, pos, rotation, StructureNordicPillar.offset, Structures.pillar_piece, 0);

            this.loot_table = NordicResourceLocations.PILLAR_LOOT;
            this.structure_block_replacement = Blocks.deepslate_pillar.get().getDefaultState().with(BlockPillar.AXIS, Direction.Axis.Y).with(BlockInfusablePillar.ACTIVATED, true);
        }

        public Piece(TemplateManager manager, CompoundNBT nbt)
        {
            super(manager, nbt, Structures.pillar_piece);

            this.structure_block_replacement = Blocks.deepslate_pillar.get().getDefaultState().with(BlockPillar.AXIS, Direction.Axis.Y).with(BlockInfusablePillar.ACTIVATED, true);
        }
    }
}
