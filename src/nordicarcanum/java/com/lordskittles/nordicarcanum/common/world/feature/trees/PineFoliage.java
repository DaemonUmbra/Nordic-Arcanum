package com.lordskittles.nordicarcanum.common.world.feature.trees;

import com.lordskittles.arcanumapi.common.world.feature.trees.FoliagePlacerBase;
import com.lordskittles.nordicarcanum.common.registry.FoliageType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.BiConsumer;

public class PineFoliage extends FoliagePlacerBase {

    public static final Codec<PineFoliage> codec = RecordCodecBuilder.create((instance) -> blobParts(instance).apply(instance, PineFoliage::new));

    public PineFoliage(IntProvider spreadA, IntProvider spreadB, int height) {

        super(spreadA, spreadB, height, FoliageType.pine_foliage);
    }

    @Override
    protected void placeLeaves(BlockPos position, BiConsumer<BlockPos, BlockState> changedLeaves) {
        // Main Leaf Part
        BlockPos pos = new BlockPos(position.getX() - 1, position.getY(), position.getZ() - 1);
        placeLeafBox(pos, new BlockPos(3, 4, 3), changedLeaves);

        // Side Tall Leaves
        pos = new BlockPos(position.getX() - 2, pos.getY(), position.getZ());
        placeLeafBox(pos, new BlockPos(1, 2, 1), changedLeaves);

        pos = new BlockPos(position.getX() + 2, pos.getY(), position.getZ());
        placeLeafBox(pos, new BlockPos(1, 2, 1), changedLeaves);

        pos = new BlockPos(position.getX(), pos.getY(), position.getZ() - 2);
        placeLeafBox(pos, new BlockPos(1, 2, 1), changedLeaves);

        pos = new BlockPos(position.getX(), pos.getY(), position.getZ() + 2);
        placeLeafBox(pos, new BlockPos(1, 2, 1), changedLeaves);

        // Top Tall Leaves
        pos = new BlockPos(position.getX() + 1, position.getY() + 4, position.getZ());
        placeLeafBox(pos, new BlockPos(1, 2, 1), changedLeaves);

        pos = new BlockPos(position.getX() - 1, position.getY() + 4, position.getZ());
        placeLeafBox(pos, new BlockPos(1, 2, 1), changedLeaves);

        pos = new BlockPos(position.getX(), position.getY() + 4, position.getZ() + 1);
        placeLeafBox(pos, new BlockPos(1, 2, 1), changedLeaves);

        pos = new BlockPos(position.getX(), position.getY() + 4, position.getZ() - 1);
        placeLeafBox(pos, new BlockPos(1, 2, 1), changedLeaves);

        // Middle Tall Leaves
        pos = new BlockPos(position.getX(), position.getY() + 5, position.getZ());
        placeLeafBox(pos, new BlockPos(1, 3, 1), changedLeaves);

        // Bottom Leaves
        placeLeaf(new BlockPos(position.getX() + 1, position.getY() - 1, position.getZ()), changedLeaves);
        placeLeaf(new BlockPos(position.getX() - 1, position.getY() - 1, position.getZ()), changedLeaves);
        placeLeaf(new BlockPos(position.getX(), position.getY() - 1, position.getZ() + 1), changedLeaves);
        placeLeaf(new BlockPos(position.getX(), position.getY() - 1, position.getZ() - 1), changedLeaves);
    }
}
