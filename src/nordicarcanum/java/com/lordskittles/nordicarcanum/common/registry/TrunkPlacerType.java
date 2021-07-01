package com.lordskittles.nordicarcanum.common.registry;

import com.lordskittles.nordicarcanum.common.world.feature.trees.JuniperTrunk;
import com.lordskittles.nordicarcanum.common.world.feature.trees.PineTrunk;
import com.lordskittles.nordicarcanum.common.world.feature.trees.YewTrunk;
import com.mojang.serialization.Codec;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.trunkplacer.AbstractTrunkPlacer;

public class TrunkPlacerType<P extends AbstractTrunkPlacer> extends net.minecraft.world.gen.trunkplacer.TrunkPlacerType<P> {

    public static final TrunkPlacerType<JuniperTrunk> juniper_trunk = register("juniper_trunk", JuniperTrunk.codec);
    public static final TrunkPlacerType<PineTrunk> pine_trunk = register("pine_trunk", PineTrunk.codec);
    public static final TrunkPlacerType<YewTrunk> yew_trunk = register("yew_trunk", YewTrunk.codec);

    public TrunkPlacerType(Codec<P> codec) {

        super(codec);
    }

    private static <P extends AbstractTrunkPlacer> TrunkPlacerType<P> register(String name, Codec<P> codec) {

        return Registry.register(Registry.TRUNK_REPLACER, name, new TrunkPlacerType<>(codec));
    }
}
