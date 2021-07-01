package com.lordskittles.nordicarcanum.common.world.feature.decorators;

import com.lordskittles.nordicarcanum.core.NordicConfig;
import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.WorldDecoratingHelper;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.Placement;

import java.util.Random;
import java.util.stream.Stream;

public class DecoratorUndergroundLake extends Placement<ChanceConfig> {

    public DecoratorUndergroundLake() {

        super(Codec.unit(new ChanceConfig(NordicConfig.arcanumLakeChance.get())));
    }

    @Override
    public Stream<BlockPos> getPositions(WorldDecoratingHelper helper, Random random, ChanceConfig configIn, BlockPos pos) {

        if(random.nextInt(100) < configIn.chance) {
            int i = random.nextInt(16);
            int j = random.nextInt(random.nextInt(helper.func_242893_a(Heightmap.Type.WORLD_SURFACE_WG, pos.getX(), pos.getZ()) - 8) + 8);
            int k = random.nextInt(16);
            if((j < (helper.func_242895_b() - 40) || random.nextInt(100) < 25) && j > 0) {
                return Stream.of(pos.add(i, j, k));
            }
        }
        return Stream.empty();
    }
}
