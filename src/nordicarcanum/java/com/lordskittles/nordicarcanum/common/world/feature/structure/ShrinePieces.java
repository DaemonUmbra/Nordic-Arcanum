package com.lordskittles.nordicarcanum.common.world.feature.structure;

import com.lordskittles.arcanumapi.common.world.feature.structure.StructurePieceBase;
import com.lordskittles.nordicarcanum.common.block.world.BlockStatue;
import com.lordskittles.nordicarcanum.common.registry.Blocks;
import com.lordskittles.nordicarcanum.common.registry.Structures;
import net.minecraft.block.Block;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.gen.feature.template.TemplateManager;

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

        public Piece(TemplateManager manager, ResourceLocation structure, BlockPos pos, Rotation rotation, Map<ResourceLocation, BlockPos> offsetMap, ResourceLocation loot) {

            super(manager, structure, pos, rotation, offsetMap, Structures.shrine_piece, 0);

            this.loot_table = loot;
            this.structure_block_replacement = net.minecraft.block.Blocks.AIR.getDefaultState();
        }

        public Piece(TemplateManager manager, CompoundNBT nbt) {

            super(manager, nbt, Structures.pillar_piece);

            this.structure_block_replacement = net.minecraft.block.Blocks.AIR.getDefaultState();
        }

        @Override
        protected void handleDataMarker(String function, BlockPos pos, IServerWorld world, Random rand, MutableBoundingBox sbb) {

            super.handleDataMarker(function, pos, world, rand, sbb);

            if("statue".equals(function)) {
                world.setBlockState(pos, statues[rand.nextInt(statues.length)].getDefaultState().with(BlockStatue.FACING, getDirectionFromRotation()), 3);
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
