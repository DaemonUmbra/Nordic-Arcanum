package com.lordskittles.nordicarcanum.client.render.tileentity;

import com.lordskittles.arcanumapi.client.render.ArcaneRenderer;
import com.lordskittles.arcanumapi.client.render.tileentity.TileEntityFluidRendererBase;
import com.lordskittles.nordicarcanum.client.render.tileentity.model.ModelArcaneTank;
import com.lordskittles.nordicarcanum.common.tileentity.magic.TileEntityArcaneTank;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.math.vector.Vector3f;

public class TileRendererArcaneTank extends TileEntityFluidRendererBase<TileEntityArcaneTank> {

    private final ModelArcaneTank ArcaneTank = new ModelArcaneTank();

    public TileRendererArcaneTank(TileEntityRendererDispatcher rendererDispatcherIn) {

        super(rendererDispatcherIn);
    }

    @Override
    public void render(TileEntityArcaneTank tileEntity, float ticks, MatrixStack stack, IRenderTypeBuffer buffer, int light, int overlay) {

        stack.push();
        stack.translate(0.5D, 1.5D, 0.5D);
        stack.rotate(Vector3f.XN.rotationDegrees(180));
        ArcaneTank.render(stack, buffer, light, overlay);
        stack.pop();

        renderFluid(tileEntity, stack, buffer, light);
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