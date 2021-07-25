package com.lordskittles.arcanumapi.common.utilities;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionHand;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fluids.capability.templates.FluidTank;

public class MathUtilities {

    public static Vec3 posToHandPos(Entity entity, InteractionHand hand) {

        double px = entity.xo;
        double py = entity.yo + entity.getBoundingBox().getYsize() * 0.6D;
        double pz = entity.zo;
        float m = (hand == InteractionHand.MAIN_HAND) ? 0.0F : 180.0F;
        px += (- Mth.cos((entity.getYRot() + m) / 180.0F * 3.141593F) * 0.3F);
        pz += (- Mth.sin((entity.getYRot() + m) / 180.0F * 3.141593F) * 0.3F);
        Vec3 vec3d = entity.getViewVector(1.0F);
        px += vec3d.x * 0.3D;
        py += vec3d.y * 0.3D;
        pz += vec3d.z * 0.3D;
        return new Vec3(px, py, pz);
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
