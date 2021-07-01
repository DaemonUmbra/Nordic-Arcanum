package com.lordskittles.nordicarcanum.common.registry;

import com.lordskittles.arcanumapi.common.world.feature.trees.FoliagePlacerBase;
import com.lordskittles.nordicarcanum.common.world.feature.trees.JuniperFoliage;
import com.lordskittles.nordicarcanum.common.world.feature.trees.PineFoliage;
import com.lordskittles.nordicarcanum.common.world.feature.trees.YewFoliage;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.mojang.serialization.Codec;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

public class FoliageType<P extends FoliagePlacer> extends FoliagePlacerType<P> {

    private static final List<FoliageType<? extends FoliagePlacerBase>> TYPES = new ArrayList<>();

    public static final FoliageType<JuniperFoliage> juniper_foliage = register("juniper_foliage", JuniperFoliage.codec);
    public static final FoliageType<PineFoliage> pine_foliage = register("pine_foliage", PineFoliage.codec);
    public static final FoliageType<YewFoliage> yew_foliage = register("yew_foliage", YewFoliage.codec);

    public FoliageType(Codec<P> codec) {

        super(codec);
    }

    public static void registerFoliageTypes(IForgeRegistry<FoliagePlacerType<?>> registry) {

        TYPES.forEach(type -> ForgeRegistries.FOLIAGE_PLACER_TYPES.register(type));
    }

    private static <P extends FoliagePlacerBase> FoliageType<P> register(String name, Codec<P> codec) {

        FoliageType<P> type = new FoliageType<>(codec);
        type.setRegistryName(NordicArcanum.RL(name));
        TYPES.add(type);
        return type;
    }
}
