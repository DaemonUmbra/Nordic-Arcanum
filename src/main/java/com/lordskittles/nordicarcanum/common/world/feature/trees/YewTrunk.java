package com.lordskittles.nordicarcanum.common.world.feature.trees;

import com.lordskittles.arcanumapi.common.world.feature.trees.TrunkPlacerBase;
import com.lordskittles.nordicarcanum.common.registry.TrunkPlacerType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.BiConsumer;

public class YewTrunk extends TrunkPlacerBase {

    public static final Codec<YewTrunk> codec = RecordCodecBuilder.create((trunkInstance) -> {
        return trunkPlacerParts(trunkInstance).apply(trunkInstance, YewTrunk::new);
    });

    public YewTrunk(int baseHeight, int heightRandA, int heightRandB) {

        super(baseHeight, heightRandA, heightRandB, TrunkPlacerType.yew_trunk);
    }

    @Override
    protected void placeTrunk(BlockPos position, BiConsumer<BlockPos, BlockState> changedLogs) {

        BlockPos pos = position;

        for(int iter = 0; iter < 7; iter++) {
            pos = new BlockPos(position.getX(), position.getY() + iter, position.getZ());
            placeLog(pos, changedLogs);
        }

        for(int x = - 2; x <= 2; x++) {
            pos = new BlockPos(position.getX() + x, position.getY() + 4, position.getZ());
            placeLog(pos, Direction.Axis.X, changedLogs);
        }

        for(int z = - 2; z <= 2; z++) {
            pos = new BlockPos(position.getX(), position.getY() + 4, position.getZ() + z);
            placeLog(pos, Direction.Axis.Z, changedLogs);
        }

        placeLog(new BlockPos(position.getX() + 1, position.getY() + 6, position.getZ()), Direction.Axis.X, changedLogs);
        placeLog(new BlockPos(position.getX() - 1, position.getY() + 6, position.getZ()), Direction.Axis.X, changedLogs);
        placeLog(new BlockPos(position.getX(), position.getY() + 6, position.getZ() + 1), Direction.Axis.Z, changedLogs);
        placeLog(new BlockPos(position.getX(), position.getY() + 6, position.getZ() - 1), Direction.Axis.Z, changedLogs);
    }
}