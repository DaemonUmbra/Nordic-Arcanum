package com.lordskittles.nordicarcanum.common.world.feature.decorators;

import com.lordskittles.nordicarcanum.core.NordicConfig;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.placement.DecorationContext;
import net.minecraft.world.level.levelgen.placement.ChanceDecoratorConfiguration;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;

import java.util.Random;
import java.util.stream.Stream;

public class DecoratorUndergroundLake extends FeatureDecorator<ChanceDecoratorConfiguration> {

    public DecoratorUndergroundLake() {

        super(Codec.unit(new ChanceDecoratorConfiguration(NordicConfig.arcanumLakeChance.get())));
    }

    @Override
    public Stream<BlockPos> getPositions(DecorationContext helper, Random random, ChanceDecoratorConfiguration configIn, BlockPos pos) {

        if(random.nextInt(100) < configIn.chance) {
            int i = random.nextInt(16);
            int j = random.nextInt(random.nextInt(helper.getHeight(Heightmap.Types.WORLD_SURFACE_WG, pos.getX(), pos.getZ()) - 8) + 8);
            int k = random.nextInt(16);
            if((j < (helper.getLevel().getSeaLevel() - 40) || random.nextInt(100) < 25) && j > 0) {
                return Stream.of(pos.offset(i, j, k));
            }
        }
        return Stream.empty();
    }
}
