package com.lordskittles.nordicarcanum.common.registry;

import com.lordskittles.nordicarcanum.common.world.feature.trees.JuniperTrunk;
import com.lordskittles.nordicarcanum.common.world.feature.trees.PineTrunk;
import com.lordskittles.nordicarcanum.common.world.feature.trees.YewTrunk;
import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;

public class TrunkPlacerType<P extends TrunkPlacer> extends net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType<P> {

    public static final TrunkPlacerType<JuniperTrunk> juniper_trunk = register("juniper_trunk", JuniperTrunk.codec);
    public static final TrunkPlacerType<PineTrunk> pine_trunk = register("pine_trunk", PineTrunk.codec);
    public static final TrunkPlacerType<YewTrunk> yew_trunk = register("yew_trunk", YewTrunk.codec);

    public TrunkPlacerType(Codec<P> codec) {

        super(codec);
    }

    private static <P extends TrunkPlacer> TrunkPlacerType<P> register(String name, Codec<P> codec) {

        return Registry.register(Registry.TRUNK_REPLACER, name, new TrunkPlacerType<>(codec));
    }
}
