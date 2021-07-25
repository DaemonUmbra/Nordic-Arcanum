package com.lordskittles.nordicarcanum.client.render.item;

import com.lordskittles.arcanumapi.client.render.item.ItemModelWrapper;
import com.lordskittles.arcanumapi.client.render.item.ItemStackTERenderBase;
import com.lordskittles.nordicarcanum.client.render.tileentity.model.ModelArcaneChest;
import com.lordskittles.nordicarcanum.core.NordicResourceLocations;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.world.item.ItemStack;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class ItemStackImpossibleChestRender extends ItemStackTERenderBase {

    private static final ModelArcaneChest impossible_chest = new ModelArcaneChest(NordicResourceLocations.IMPOSSIBLE_CHEST);
    public static ItemModelWrapper model;

    @Override
    protected void renderItemSpecific(ItemStack stack, PoseStack matrix, MultiBufferSource buffer, int light, int overlay, ItemTransforms.TransformType transform) {

    }

    @Override
    protected void renderBlockSpecific(ItemStack stack, PoseStack matrix, MultiBufferSource buffer, int light, int overlay, ItemTransforms.TransformType transform) {

        float scale = 1.25f;

        matrix.translate(0, 1.2D, 0);
        matrix.rotate(Vector3f.ZP.rotationDegrees(180));

        matrix.scale(scale, scale, scale);
        impossible_chest.render(matrix, buffer, light, overlay);
    }

    @Nonnull
    @Override
    protected ItemCameraTransforms.TransformType getTransform(ItemStack stack) {

        return model.getTransform();
    }
}
