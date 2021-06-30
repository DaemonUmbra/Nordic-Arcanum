package com.lordskittles.nordicarcanum.common.registry;

import com.lordskittles.nordicarcanum.common.world.feature.decorators.DecoratorUndergroundLake;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicNames;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Decorators
{
    public static final DeferredRegister<Placement<?>> DECORATORS = DeferredRegister.create(ForgeRegistries.DECORATORS, NordicArcanum.MODID);

    public static final RegistryObject<DecoratorUndergroundLake> arcanum_lake = DECORATORS.register(NordicNames.LIQUID_ARCANUM + NordicNames.LAKE_SUFFIX, () -> new DecoratorUndergroundLake());
}
