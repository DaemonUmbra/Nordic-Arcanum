package com.lordskittles.nordicarcanum.client.render.tileentity;

import com.lordskittles.arcanumapi.client.render.tileentity.TileEntityRendererBase;
import com.lordskittles.nordicarcanum.client.render.tileentity.model.ModelCrystalMatrix;
import com.lordskittles.nordicarcanum.common.blockentity.magic.BlockEntityCrystalMatrix;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;

public class TileRendererCrystalMatrix extends TileEntityRendererBase<BlockEntityCrystalMatrix> {

    private final ModelCrystalMatrix model = new ModelCrystalMatrix();

    public TileRendererCrystalMatrix(BlockEntityRenderDispatcher dispatcher) {

        super(dispatcher);
    }

    @Override
    public void render(BlockEntityCrystalMatrix tile, float ticks, PoseStack stack, MultiBufferSource buffer, int light, int overlay) {

//        stack.push();
//        stack.translate(0.5D, 1.5D, 0.5D);
//        stack.rotate(Vector3f.XN.rotationDegrees(180));
//        model.render(stack, buffer, light, overlay);
//        stack.pop();
    }
}
