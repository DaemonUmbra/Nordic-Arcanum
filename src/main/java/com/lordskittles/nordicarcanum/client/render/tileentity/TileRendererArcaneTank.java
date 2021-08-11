package com.lordskittles.nordicarcanum.client.render.tileentity;

import com.lordskittles.arcanumapi.client.render.ArcaneRenderer;
import com.lordskittles.arcanumapi.client.render.tileentity.TileEntityFluidRendererBase;
import com.lordskittles.nordicarcanum.client.render.tileentity.model.ModelArcaneTank;
import com.lordskittles.nordicarcanum.common.blockentity.magic.BlockEntityArcaneTank;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;

public class TileRendererArcaneTank extends TileEntityFluidRendererBase<BlockEntityArcaneTank> {

    private final ModelArcaneTank ArcaneTank = new ModelArcaneTank();

    public TileRendererArcaneTank(BlockEntityRenderDispatcher rendererDispatcherIn) {

        super(rendererDispatcherIn);
    }

    @Override
    public void render(BlockEntityArcaneTank tileEntity, float ticks, PoseStack stack, MultiBufferSource buffer, int light, int overlay) {

//        stack.push();
//        stack.translate(0.5D, 1.5D, 0.5D);
//        stack.rotate(Vector3f.XN.rotationDegrees(180));
//        ArcaneTank.render(stack, buffer, light, overlay);
//        stack.pop();
//
//        renderFluid(tileEntity, stack, buffer, light);
    }

    @Override
    protected ArcaneRenderer.DVector3 getMin(int stage) {

        return new ArcaneRenderer.DVector3(.25 + .01, .2 + .01, .25 + .01);
    }

    @Override
    protected ArcaneRenderer.DVector3 getMax(int stage) {

        return new ArcaneRenderer.DVector3(.75 - .01, .65 + ((float) stage / (float) stages) * .1 - .01, .75 - .01);
    }
}