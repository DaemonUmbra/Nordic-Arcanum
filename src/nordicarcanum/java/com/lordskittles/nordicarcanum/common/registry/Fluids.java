package com.lordskittles.nordicarcanum.common.registry;

import com.lordskittles.nordicarcanum.common.fluid.FluidArcanum;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicNames;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Fluids {

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, NordicArcanum.MODID);

    public static final RegistryObject<Fluid> liquid_arcanum_flowing = FLUIDS.register(NordicNames.LIQUID_ARCANUM + NordicNames.FLOWING, FluidArcanum.Flowing::new);
    public static final RegistryObject<Fluid> liquid_arcanum = FLUIDS.register(NordicNames.LIQUID_ARCANUM, FluidArcanum.Source::new);
}
