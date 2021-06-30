package com.lordskittles.nordicarcanum.common.world.feature.trees;

import com.lordskittles.arcanumapi.common.world.feature.trees.FoliagePlacerBase;
import com.lordskittles.nordicarcanum.common.registry.FoliageType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.FeatureSpread;

import java.util.Set;

public class YewFoliage extends FoliagePlacerBase
{
    public static final Codec<YewFoliage> codec = RecordCodecBuilder.create((instance) ->
    {
        return func_236740_a_(instance).apply(instance, YewFoliage::new);
    });

    public YewFoliage(FeatureSpread spreadA, FeatureSpread spreadB, int height)
    {
        super(spreadA, spreadB, height, FoliageType.yew_foliage);
    }

    @Override
    protected void placeLeaves(BlockPos position, Set<BlockPos> changedLeaves)
    {
        // Main Leaf Box
        BlockPos pos = new BlockPos(position.getX() - 2, position.getY(), position.getZ() - 2);
        placeLeafBox(pos, new BlockPos(5, 2, 5), changedLeaves);

        // Secondary Leaf Box
        pos = new BlockPos(position.getX() - 1, position.getY() + 2, position.getZ() - 2);
        placeLeafBox(pos, new BlockPos(3, 2, 5), changedLeaves);

        pos = new BlockPos(position.getX() - 2, position.getY() + 2, position.getZ() - 1);
        placeLeafBox(pos, new BlockPos(5, 2, 3), changedLeaves);

        // Top Leaf Box
        pos = new BlockPos(position.getX() - 1, position.getY() + 4, position.getZ() - 1);
        placeLeafBox(pos, new BlockPos(3, 1, 3), changedLeaves);

        // Side Leaf Boxes
        pos = new BlockPos(position.getX() + 3, position.getY(), position.getZ() - 1);
        placeLeafBox(pos, new BlockPos(1, 2, 3), changedLeaves);

        pos = new BlockPos(position.getX() - 3, position.getY(), position.getZ() - 1);
        placeLeafBox(pos, new BlockPos(1, 2, 3), changedLeaves);

        pos = new BlockPos(position.getX() - 1, position.getY(), position.getZ() - 3);
        placeLeafBox(pos, new BlockPos(3, 2, 1), changedLeaves);

        pos = new BlockPos(position.getX() - 1, position.getY(), position.getZ() + 3);
        placeLeafBox(pos, new BlockPos(3, 2, 1), changedLeaves);

        // Side Leaf Extensions
        placeLeaf(new BlockPos(position.getX() - 3, position.getY() + 2, position.getZ()), changedLeaves);
        placeLeaf(new BlockPos(position.getX(), position.getY() + 2, position.getZ() + 3), changedLeaves);
        placeLeaf(new BlockPos(position.getX() + 3, position.getY() + 2, position.getZ()), changedLeaves);
        placeLeaf(new BlockPos(position.getX(), position.getY() + 2, position.getZ() - 3), changedLeaves);
    }
}
