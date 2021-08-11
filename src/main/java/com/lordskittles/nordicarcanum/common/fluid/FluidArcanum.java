package com.lordskittles.nordicarcanum.common.fluid;

import com.lordskittles.nordicarcanum.common.registry.Blocks;
import com.lordskittles.nordicarcanum.common.registry.Fluids;
import com.lordskittles.nordicarcanum.common.registry.Items;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public abstract class FluidArcanum {

    private static final FluidAttributes.Builder ATTR = FluidAttributes.builder(
            NordicArcanum.RL("blocks/liquid_arcanum_still"),
            NordicArcanum.RL("blocks/liquid_arcanum_flowing")
    ).density(800).viscosity(10000).temperature(100);

    private static final ForgeFlowingFluid.Properties PROPERTIES = new ForgeFlowingFluid.Properties(
            Fluids.liquid_arcanum,
            Fluids.liquid_arcanum_flowing,
            ATTR
    ).block(Blocks.liquid_arcanum).bucket(Items.liquid_arcanum_bucket);

    public static class Flowing extends ForgeFlowingFluid.Flowing {

        public Flowing() {

            super(PROPERTIES);
        }
    }

    public static class Source extends ForgeFlowingFluid.Source {

        public Source() {

            super(PROPERTIES);
        }
    }
}
