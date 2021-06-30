package com.lordskittles.nordicarcanum.common.world.feature.trees;

import com.lordskittles.arcanumapi.common.world.feature.trees.FoliagePlacerBase;
import com.lordskittles.nordicarcanum.common.registry.FoliageType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.FeatureSpread;
import com.lordskittles.nordicarcanum.common.registry.Blocks;

import java.util.Set;

public class JuniperFoliage extends FoliagePlacerBase
{
    public static final Codec<JuniperFoliage> codec = RecordCodecBuilder.create((instance) -> {
        return func_236740_a_(instance).apply(instance, JuniperFoliage::new);
    });

    public JuniperFoliage(FeatureSpread spreadA, FeatureSpread spreadB, int height)
    {
        super(spreadA, spreadB, height, FoliageType.juniper_foliage);
    }

    @Override
    protected void placeLeaves(BlockPos position, Set<BlockPos> changedLeaves)
    {
        // Bottom Leaf Layer
        BlockPos pos = position;
        placeLeafBox(pos, new BlockPos(3, 1, 5), changedLeaves);

        pos = new BlockPos(position.getX() - 1, pos.getY(), position.getZ() + 1);
        placeLeafBox(pos, new BlockPos(5, 1, 3), changedLeaves);

        // Second Leaf Layer
        pos = new BlockPos(position.getX() - 1, pos.getY() + 1, position.getZ());
        placeLeafBox(pos, new BlockPos(5, 1, 5), changedLeaves);

        pos = new BlockPos(position.getX() - 2, pos.getY(), position.getZ() + 1);
        placeLeafBox(pos, new BlockPos(7, 1, 3), changedLeaves);

        pos = new BlockPos(position.getX(), pos.getY(), position.getZ() - 1);
        placeLeafBox(pos, new BlockPos(3, 1, 7), changedLeaves);

        // Third Leaf Layer
        pos = new BlockPos(position.getX(), pos.getY() + 1, position.getZ() + 1);
        placeLeafBox(pos, new BlockPos(3, 1, 3), changedLeaves);

        placeLeaf(new BlockPos(position.getX() + 1, pos.getY(), position.getZ()), changedLeaves);
        placeLeaf(new BlockPos(position.getX() + 3, pos.getY(), position.getZ() + 2), changedLeaves);
        placeLeaf(new BlockPos(position.getX() - 1, pos.getY(), position.getZ() + 2), changedLeaves);
        placeLeaf(new BlockPos(position.getX() + 1, pos.getY(), position.getZ() + 4), changedLeaves);

        // Top Leaf Layer
        pos = new BlockPos(position.getX() + 1, pos.getY() + 1, position.getZ() + 1);
        placeLeafBox(pos, new BlockPos(1, 1, 3), changedLeaves);

        pos = new BlockPos(position.getX(), pos.getY(), position.getZ() + 2);
        placeLeafBox(pos, new BlockPos(3, 1, 1), changedLeaves);
    }
}
