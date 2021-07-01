package com.lordskittles.nordicarcanum.client.render.tileentity;

import com.lordskittles.arcanumapi.client.render.tileentity.TileEntityRendererBase;
import com.lordskittles.nordicarcanum.client.render.tileentity.model.ModelAttunementAltar;
import com.lordskittles.nordicarcanum.common.tileentity.magic.TileEntityAttunementAltar;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.math.vector.Vector3f;

public class TileRendererAttunementAltar extends TileEntityRendererBase<TileEntityAttunementAltar> {

    private final ModelAttunementAltar model = new ModelAttunementAltar();

    public TileRendererAttunementAltar(TileEntityRendererDispatcher dispatcher) {

        super(dispatcher);
    }

    @Override
    public void render(TileEntityAttunementAltar tile, float ticks, MatrixStack stack, IRenderTypeBuffer buffer, int light, int overlay) {

        stack.push();
        stack.translate(0.5D, 1.5D, 0.5D);
        stack.rotate(Vector3f.XN.rotationDegrees(180));
        model.render(stack, buffer, light, overlay);
        stack.pop();
    }
}
