package com.lordskittles.nordicarcanum.client.render.tileentity;

import com.lordskittles.arcanumapi.client.render.tileentity.TileEntityRendererBase;
import com.lordskittles.nordicarcanum.client.render.tileentity.model.ModelCrystalMatrix;
import com.lordskittles.nordicarcanum.common.tileentity.TileEntityCrystalMatrix;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;

public class TileRendererCrystalMatrix extends TileEntityRendererBase<TileEntityCrystalMatrix>
{
    private final ModelCrystalMatrix model = new ModelCrystalMatrix();

    public TileRendererCrystalMatrix(TileEntityRendererDispatcher dispatcher)
    {
        super(dispatcher);
    }

    @Override
    public void render(TileEntityCrystalMatrix tile, float ticks, MatrixStack stack, IRenderTypeBuffer buffer, int light, int overlay)
    {
        stack.push();
        stack.translate(0.5D, 1.5D, 0.5D);
        stack.rotate(Vector3f.XN.rotationDegrees(180));
        model.render(stack, buffer, light, overlay);
        stack.pop();
    }
}
