package com.lordskittles.nordicarcanum.client.render.item;

import com.lordskittles.arcanumapi.client.render.item.ItemModelWrapper;
import com.lordskittles.arcanumapi.client.render.item.ItemStackTERenderBase;
import com.lordskittles.nordicarcanum.client.render.tileentity.model.ModelAttunementAltar;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class ItemStackAttunementAltarRender extends ItemStackTERenderBase {

    private static final ModelAttunementAltar attunement_altar = new ModelAttunementAltar();
    public static ItemModelWrapper model;

    @Override
    protected void renderItemSpecific(ItemStack stack, MatrixStack matrix, IRenderTypeBuffer buffer, int light, int overlay, ItemCameraTransforms.TransformType transform) {

    }

    @Override
    protected void renderBlockSpecific(ItemStack stack, MatrixStack matrix, IRenderTypeBuffer buffer, int light, int overlay, ItemCameraTransforms.TransformType transform) {

        float scale = 0.7f;

        matrix.translate(0, 0.5D, 0);
        matrix.rotate(Vector3f.ZP.rotationDegrees(180));

        matrix.scale(scale, scale, scale);
        attunement_altar.render(matrix, buffer, light, overlay);
    }

    @Nonnull
    @Override
    protected ItemCameraTransforms.TransformType getTransform(ItemStack stack) {

        return model.getTransform();
    }
}
