package com.lordskittles.nordicarcanum.common.registry;

import com.lordskittles.nordicarcanum.common.world.feature.decorators.DecoratorUndergroundLake;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicNames;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Decorators {

    public static final DeferredRegister<FeatureDecorator<?>> DECORATORS = DeferredRegister.create(ForgeRegistries.DECORATORS, NordicArcanum.MODID);

    public static final RegistryObject<DecoratorUndergroundLake> arcanum_lake = DECORATORS.register(NordicNames.LIQUID_ARCANUM + NordicNames.LAKE_SUFFIX, () -> new DecoratorUndergroundLake());
}
