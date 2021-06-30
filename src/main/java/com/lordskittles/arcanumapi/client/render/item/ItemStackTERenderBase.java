package com.lordskittles.arcanumapi.client.render.item;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public abstract class ItemStackTERenderBase extends ItemStackTileEntityRenderer
{
    protected abstract void renderItemSpecific(ItemStack stack, MatrixStack matrix, IRenderTypeBuffer buffer, int light, int overlay, TransformType transform);
    protected abstract void renderBlockSpecific(ItemStack stack, MatrixStack matrix, IRenderTypeBuffer buffer, int light, int overlay, TransformType transform);

    protected TransformType transform;

    @Nonnull
    @Deprecated
    protected abstract TransformType getTransform(ItemStack stack);

    protected boolean earlyExit()
    {
        return false;
    }

    protected void renderWithTransform(ItemStack stack, MatrixStack matrix, IRenderTypeBuffer buffer, int light, int overlay)
    {
        TransformType transform = getTransform(stack);
        if (transform == TransformType.GUI)
        {
            matrix.rotate(Vector3f.YP.rotationDegrees(180));
        }

        renderBlockSpecific(stack, matrix, buffer, light, overlay, transform);

        if (!earlyExit())
        {
            if (transform == TransformType.GUI)
            {
                matrix.rotate(Vector3f.YP.rotationDegrees(90));
            }
            else
            {
                matrix.rotate(Vector3f.YP.rotationDegrees(180));
            }

            renderItemSpecific(stack, matrix, buffer, light, overlay, transform);
        }
    }

    @Override
    public void func_239207_a_(ItemStack stack, ItemCameraTransforms.TransformType transformType, MatrixStack matrix, IRenderTypeBuffer buffer, int light, int overlay)
    {
        this.transform = transformType;

        matrix.push();
        matrix.translate(0.5, 0.5, 0.5);
        matrix.rotate(Vector3f.YP.rotationDegrees(180));
        renderWithTransform(stack, matrix, buffer, light, overlay);
        matrix.pop();
    }
}
