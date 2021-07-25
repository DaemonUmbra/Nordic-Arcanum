package com.lordskittles.arcanumapi.client.render.item;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.block.model.ItemTransforms.TransformType;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.world.item.ItemStack;
import com.mojang.math.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public abstract class ItemStackTERenderBase extends BlockEntityWithoutLevelRenderer {

    public ItemStackTERenderBase(BlockEntityRenderDispatcher dispatcher, EntityModelSet modelSet) {

        super(dispatcher, modelSet);
    }

    protected abstract void renderItemSpecific(ItemStack stack, PoseStack matrix, MultiBufferSource buffer, int light, int overlay, TransformType transform);

    protected abstract void renderBlockSpecific(ItemStack stack, PoseStack matrix, MultiBufferSource buffer, int light, int overlay, TransformType transform);

    protected TransformType transform;

    @Nonnull
    @Deprecated
    protected abstract TransformType getTransform(ItemStack stack);

    protected boolean earlyExit() {

        return false;
    }

    protected void renderWithTransform(ItemStack stack, PoseStack matrix, MultiBufferSource buffer, int light, int overlay) {

        TransformType transform = getTransform(stack);
        if(transform == TransformType.GUI) {
            matrix.mulPose(Vector3f.YP.rotationDegrees(180));
        }

        renderBlockSpecific(stack, matrix, buffer, light, overlay, transform);

        if(! earlyExit()) {
            if(transform == TransformType.GUI) {
                matrix.mulPose(Vector3f.YP.rotationDegrees(90));
            }
            else {
                matrix.mulPose(Vector3f.YP.rotationDegrees(180));
            }

            renderItemSpecific(stack, matrix, buffer, light, overlay, transform);
        }
    }

    @Override
    public void renderByItem(ItemStack stack, ItemTransforms.TransformType transformType, PoseStack matrix, MultiBufferSource buffer, int light, int overlay) {

        this.transform = transformType;

        matrix.pushPose();
        matrix.translate(0.5, 0.5, 0.5);
        matrix.mulPose(Vector3f.YP.rotationDegrees(180));
        renderWithTransform(stack, matrix, buffer, light, overlay);
        matrix.popPose();
    }
}
