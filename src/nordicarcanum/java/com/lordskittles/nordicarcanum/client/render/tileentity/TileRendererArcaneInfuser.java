package com.lordskittles.nordicarcanum.client.render.tileentity;

import com.lordskittles.arcanumapi.client.render.ArcaneRenderer;
import com.lordskittles.arcanumapi.client.render.tileentity.TileEntityFluidRendererBase;
import com.lordskittles.nordicarcanum.client.render.tileentity.model.ModelArcaneInfuser;
import com.lordskittles.nordicarcanum.common.tileentity.crafting.TileEntityArcaneInfuser;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3f;

public class TileRendererArcaneInfuser extends TileEntityFluidRendererBase<TileEntityArcaneInfuser> {

    private final ModelArcaneInfuser InfuserModel = new ModelArcaneInfuser();

    public TileRendererArcaneInfuser(BlockEntityRenderDispatcher rendererDispatcherIn) {

        super(rendererDispatcherIn);
    }

    @Override
    public void render(TileEntityArcaneInfuser tileEntity, float ticks, PoseStack stack, MultiBufferSource buffer, int light, int overlay) {

        stack.push();
        stack.translate(0.5D, 1.5D, 0.5D);
        stack.rotate(Vector3f.XN.rotationDegrees(180));
        InfuserModel.render(stack, buffer, light, overlay);
        stack.pop();

        ItemStack heldItem = tileEntity.getHeldItem();
        if(! heldItem.isEmpty()) {
            stack.push();
            stack.translate(0.5, 1, 0.5D);
            stack.rotate(Vector3f.XN.rotationDegrees(90));
            stack.scale(0.375f, 0.375f, 0.375f);

            Minecraft.getInstance().getItemRenderer().renderItem(heldItem, ItemCameraTransforms.TransformType.FIXED, light, overlay, stack, buffer);

            stack.pop();
        }

        renderFluid(tileEntity, stack, buffer, light);
    }

    @Override
    protected ArcaneRenderer.DVector3 getMin(int stage) {

        return new ArcaneRenderer.DVector3(.25 + .01, .7 + .01, .25 + .01);
    }

    @Override
    protected ArcaneRenderer.DVector3 getMax(int stage) {

        return new ArcaneRenderer.DVector3(.75 - .01, .85 + ((float) stage / (float) stages) * .1 - .01, .75 - .01);
    }
}