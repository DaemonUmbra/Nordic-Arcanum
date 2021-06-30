package com.lordskittles.arcanumapi.common.utilities;


import net.minecraftforge.fluids.capability.templates.FluidTank;

public class MathUtilities
{
    public static float deltaTime(float partialTicks, long worldTime)
    {
        return ((float)worldTime + partialTicks) / 10.0F;
    }

    public static float map(float value, float startMin, float startMax, float endMin, float endMax)
    {
        return endMin + (endMax - endMin) * ((value - startMin) / (startMax - startMin));
    }

    public static float getScale(float prevScale, FluidTank tank)
    {
        return getScale(prevScale, tank.getFluidAmount(), tank.getCapacity(), tank.isEmpty());
    }

    public static float getScale(float prevScale, int stored, int capacity, boolean empty)
    {
        return getScale(prevScale, capacity == 0 ? 0 : (float) stored / capacity, empty);
    }

    public static float getScale(float prevScale, float targetScale, boolean empty)
    {
        if (Math.abs(prevScale - targetScale) > 0.01)
        {
            return (9 * prevScale + targetScale) / 10;
        }
        else if (!empty && prevScale == 0)
        {
            return targetScale;
        }

        return prevScale;
    }
}
