package com.lordskittles.nordicarcanum.common.world.feature.structure;

import com.lordskittles.arcanumapi.common.world.feature.structure.StructurePieceBase;
import com.lordskittles.nordicarcanum.common.block.decoration.BlockStatue;
import com.lordskittles.nordicarcanum.common.registry.Blocks;
import com.lordskittles.nordicarcanum.common.registry.Structures;
import com.lordskittles.nordicarcanum.core.NordicResourceLocations;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

import java.util.Map;
import java.util.Random;

public class ShrinePieces {

    public static class Piece extends StructurePieceBase {

        Block[] statues = new Block[]
                {
                        Blocks.bragi_statue.get(),
                        Blocks.freya_statue.get(),
                        Blocks.hel_statue.get(),
                        Blocks.loki_statue.get(),
                        Blocks.njord_statue.get(),
                        Blocks.odin_statue.get(),
                        Blocks.thor_statue.get(),
                        Blocks.tyr_statue.get()
                };

        public Piece(StructureManager manager, ResourceLocation structure, BlockPos pos, Rotation rotation, int height) {

            super(Structures.shrine_piece, height, manager, structure, structure.getPath(), makeSettings(rotation), pos, StructureShrine.offset);

            this.loot_table = NordicResourceLocations.SHRINE_LOOT;
            this.structure_block_replacement = Blocks.feldspar_pillar.get().defaultBlockState();
        }

        public Piece(ServerLevel world, CompoundTag nbt) {

            super(Structures.shrine_piece, nbt, world, (level) -> { return makeSettings(Rotation.valueOf("Rotation")); });

            this.structure_block_replacement = Blocks.feldspar_pillar.get().defaultBlockState();
        }

        @Override
        protected void handleDataMarker(String function, BlockPos pos, ServerLevelAccessor world, Random rand, BoundingBox sbb) {

            super.handleDataMarker(function, pos, world, rand, sbb);

            if("statue".equals(function)) {
                world.setBlock(pos, statues[rand.nextInt(statues.length)].defaultBlockState().setValue(BlockStatue.FACING, getDirectionFromRotation()), 2);
            }
        }

        private Direction getDirectionFromRotation() {

            switch(rotation) {
                case CLOCKWISE_90:
                    return Direction.EAST;
                case CLOCKWISE_180:
                    return Direction.SOUTH;
                case COUNTERCLOCKWISE_90:
                    return Direction.WEST;
            }

            return Direction.NORTH;
        }
    }
}
