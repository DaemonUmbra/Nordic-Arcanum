package com.lordskittles.nordicarcanum.common.world.feature.trees;

import com.lordskittles.arcanumapi.common.world.feature.trees.TrunkPlacerBase;
import com.lordskittles.nordicarcanum.common.registry.TrunkPlacerType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;

import java.util.Set;

public class JuniperTrunk extends TrunkPlacerBase {

    public static final Codec<JuniperTrunk> codec = RecordCodecBuilder.create((instance) -> getAbstractTrunkCodec(instance).apply(instance, JuniperTrunk::new));

    public JuniperTrunk(int baseHeight, int heightRandA, int heightRandB) {

        super(baseHeight, 0, 0, TrunkPlacerType.juniper_trunk);
    }

    @Override
    protected void placeTrunk(BlockPos position, Set<BlockPos> changedLogs, MutableBoundingBox area) {

        BlockPos pos = new BlockPos(position.getX(), position.getY(), position.getZ());
        placeLog(pos, changedLogs, area);

        pos = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
        placeLog(pos, changedLogs, area);

        pos = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ());
        placeLog(pos, changedLogs, area);

        pos = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
        placeLog(pos, changedLogs, area);

        pos = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ());
        placeLog(pos, changedLogs, area);

        pos = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
        placeLog(pos, changedLogs, area);

        pos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1);
        placeLog(pos, Direction.Axis.Z, changedLogs, area);

        pos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1);
        placeLog(pos, changedLogs, area);

        pos = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ());
        for(int y = 0; y < 4; y++) {
            BlockPos logPos = new BlockPos(pos.getX(), pos.getY() + y, pos.getZ());
            placeLog(logPos, changedLogs, area);
        }

        pos = new BlockPos(pos.getX(), pos.getY() + 2, pos.getZ() - 2);
        for(int z = 0; z < 5; z++) {
            BlockPos logPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() + z);
            placeLog(logPos, Direction.Axis.Z, changedLogs, area);
        }

        pos = new BlockPos(pos.getX() - 2, pos.getY(), pos.getZ() + 2);
        for(int x = 0; x < 5; x++) {
            BlockPos logPos = new BlockPos(pos.getX() + x, pos.getY(), pos.getZ());
            placeLog(logPos, Direction.Axis.X, changedLogs, area);
        }
    }
}
