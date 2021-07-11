package com.lordskittles.arcanumapi.common.utilities;

import net.minecraft.entity.Entity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.fluids.capability.templates.FluidTank;

public class MathUtilities {

    public static Vector3d posToHandPos(Entity entity, Hand hand) {

        double px = entity.prevPosX;
        double py = entity.prevPosY + entity.getBoundingBox().getYSize() * 0.6D;
        double pz = entity.prevPosZ;
        float m = (hand == Hand.MAIN_HAND) ? 0.0F : 180.0F;
        px += (- MathHelper.cos((entity.rotationYaw + m) / 180.0F * 3.141593F) * 0.3F);
        pz += (- MathHelper.sin((entity.rotationYaw + m) / 180.0F * 3.141593F) * 0.3F);
        Vector3d vec3d = entity.getLook(1.0F);
        px += vec3d.x * 0.3D;
        py += vec3d.y * 0.3D;
        pz += vec3d.z * 0.3D;
        return new Vector3d(px, py, pz);
    }

    public static float deltaTime(float partialTicks, long worldTime) {

        return ((float) worldTime + partialTicks) / 10.0F;
    }

    public static float map(float value, float startMin, float startMax, float endMin, float endMax) {

        return endMin + (endMax - endMin) * ((value - startMin) / (startMax - startMin));
    }

    public static double map(double value, float startMin, float startMax, float endMin, float endMax) {

        return endMin + (endMax - endMin) * ((value - startMin) / (startMax - startMin));
    }

    public static float getScale(float prevScale, FluidTank tank) {

        return getScale(prevScale, tank.getFluidAmount(), tank.getCapacity(), tank.isEmpty());
    }

    public static float getScale(float prevScale, int stored, int capacity, boolean empty) {

        return getScale(prevScale, capacity == 0 ? 0 : (float) stored / capacity, empty);
    }

    public static float getScale(float prevScale, float targetScale, boolean empty) {

        if(Math.abs(prevScale - targetScale) > 0.01) {
            return (9 * prevScale + targetScale) / 10;
        }
        else
            if(! empty && prevScale == 0) {
                return targetScale;
            }

        return prevScale;
    }
}
