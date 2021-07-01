package com.lordskittles.arcanumapi.common.utilities;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

public class JsonUtilities {

    public static FluidStack fluidStackFromJson(JsonObject json) {

        String fluidName = JSONUtils.getString(json, "fluid");
        Fluid fluid = ForgeRegistries.FLUIDS.getValue(new ResourceLocation(fluidName));
        if(fluid == null || fluid == Fluids.EMPTY)
            throw new JsonSyntaxException("Unknown fluid: " + fluidName);

        int amount = JSONUtils.getInt(json, "amount", 1000);
        return new FluidStack(fluid, amount);
    }
}
