package com.lordskittles.nordicarcanum.common.world.feature.trees;

import com.lordskittles.arcanumapi.common.world.feature.trees.TrunkPlacerBase;
import com.lordskittles.nordicarcanum.common.registry.TrunkPlacerType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;

import java.util.Set;

public class PineTrunk extends TrunkPlacerBase {

    public static final Codec<PineTrunk> codec = RecordCodecBuilder.create((instance) -> getAbstractTrunkCodec(instance).apply(instance, PineTrunk::new));

    public PineTrunk(int baseHeight, int heightRandA, int heightRandB) {

        super(baseHeight, heightRandA, heightRandB, TrunkPlacerType.pine_trunk);
    }

    @Override
    protected void placeTrunk(BlockPos position, Set<BlockPos> changedLogs, MutableBoundingBox area) {

        BlockPos pos = position;

        for(int iter = 0; iter < 9; iter++) {
            pos = new BlockPos(position.getX(), position.getY() + iter, position.getZ());
            placeLog(pos, changedLogs, area);
        }

        placeLog(new BlockPos(position.getX() + 1, position.getY() + 4, position.getZ()), Direction.Axis.X, changedLogs, area);
        placeLog(new BlockPos(position.getX() - 1, position.getY() + 4, position.getZ()), Direction.Axis.X, changedLogs, area);
        placeLog(new BlockPos(position.getX(), position.getY() + 4, position.getZ() + 1), Direction.Axis.Z, changedLogs, area);
        placeLog(new BlockPos(position.getX(), position.getY() + 4, position.getZ() - 1), Direction.Axis.Z, changedLogs, area);
    }
}
