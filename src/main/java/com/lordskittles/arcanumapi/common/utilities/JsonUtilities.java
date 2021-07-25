package com.lordskittles.arcanumapi.common.utilities;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.util.GsonHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

public class JsonUtilities {

    public static FluidStack fluidStackFromJson(JsonObject json) {

        String fluidName = GsonHelper.getAsString(json, "fluid");
        Fluid fluid = ForgeRegistries.FLUIDS.getValue(new ResourceLocation(fluidName));
        if(fluid == null || fluid == Fluids.EMPTY)
            throw new JsonSyntaxException("Unknown fluid: " + fluidName);

        int amount = GsonHelper.getAsInt(json, "amount", 1000);
        return new FluidStack(fluid, amount);
    }
}
