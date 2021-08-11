package com.lordskittles.nordicarcanum.common.world.feature.trees;

import com.lordskittles.arcanumapi.common.world.feature.trees.TrunkPlacerBase;
import com.lordskittles.nordicarcanum.common.registry.TrunkPlacerType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.BiConsumer;

public class PineTrunk extends TrunkPlacerBase {

    public static final Codec<PineTrunk> codec = RecordCodecBuilder.create((trunkInstance) -> {
        return trunkPlacerParts(trunkInstance).apply(trunkInstance, PineTrunk::new);
    });

    public PineTrunk(int baseHeight, int heightRandA, int heightRandB) {

        super(baseHeight, heightRandA, heightRandB, TrunkPlacerType.pine_trunk);
    }

    @Override
    protected void placeTrunk(BlockPos position, BiConsumer<BlockPos, BlockState> changedLogs) {

        BlockPos pos = position;

        for(int iter = 0; iter < 9; iter++) {
            pos = new BlockPos(position.getX(), position.getY() + iter, position.getZ());
            placeLog(pos, changedLogs);
        }

        placeLog(new BlockPos(position.getX() + 1, position.getY() + 4, position.getZ()), Direction.Axis.X, changedLogs);
        placeLog(new BlockPos(position.getX() - 1, position.getY() + 4, position.getZ()), Direction.Axis.X, changedLogs);
        placeLog(new BlockPos(position.getX(), position.getY() + 4, position.getZ() + 1), Direction.Axis.Z, changedLogs);
        placeLog(new BlockPos(position.getX(), position.getY() + 4, position.getZ() - 1), Direction.Axis.Z, changedLogs);
    }
}
