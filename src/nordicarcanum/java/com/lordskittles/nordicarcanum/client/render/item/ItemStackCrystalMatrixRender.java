package com.lordskittles.nordicarcanum.client.render.item;

import com.lordskittles.arcanumapi.client.render.item.ItemModelWrapper;
import com.lordskittles.arcanumapi.client.render.item.ItemStackTERenderBase;
import com.lordskittles.nordicarcanum.client.render.tileentity.model.ModelCrystalMatrix;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class ItemStackCrystalMatrixRender extends ItemStackTERenderBase {

    private static final ModelCrystalMatrix crystal_matrix = new ModelCrystalMatrix();
    public static ItemModelWrapper model;

    public ItemStackCrystalMatrixRender(BlockEntityRenderDispatcher dispatcher, EntityModelSet modelSet) {

        super(dispatcher, modelSet);
    }

    @Override
    protected void renderItemSpecific(ItemStack stack, PoseStack matrix, MultiBufferSource buffer, int light, int overlay, ItemTransforms.TransformType transform) {

    }

    @Override
    protected void renderBlockSpecific(ItemStack stack, PoseStack matrix, MultiBufferSource buffer, int light, int overlay, ItemTransforms.TransformType transform) {

//        float scale = 1.5f;
//
//        matrix.translate(0, 1.5, 0);
//        matrix.rotate(Vector3f.ZP.rotationDegrees(180));
//
//        matrix.scale(scale, scale, scale);
//        crystal_matrix.render(matrix, buffer, light, overlay);
    }

    @Nonnull
    @Override
    protected ItemTransforms.TransformType getTransform(ItemStack stack) {

        return model.getTransform();
    }
}
