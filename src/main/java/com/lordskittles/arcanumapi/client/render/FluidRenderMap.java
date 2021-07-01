package com.lordskittles.arcanumapi.client.render;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenCustomHashMap;
import net.minecraftforge.fluids.FluidStack;

public class FluidRenderMap<V> extends Object2ObjectOpenCustomHashMap<FluidStack, V> {

    public FluidRenderMap() {

        super(FluidHashStrategy.INSTANCE);
    }

    public static class FluidHashStrategy implements Strategy<FluidStack> {

        public static FluidHashStrategy INSTANCE = new FluidHashStrategy();

        @Override
        public int hashCode(FluidStack stack) {

            if(stack == null || stack.isEmpty()) {
                return 0;
            }

            int code = 1;
            if(stack.hasTag()) {
                code = 31 * code + stack.getTag().hashCode();
            }
            else {
                code = 31 * code + stack.getFluid().hashCode();
            }

            return code;
        }

        @Override
        public boolean equals(FluidStack a, FluidStack b) {

            return a == null ? b == null : b != null && a.isFluidEqual(b);
        }
    }
}
