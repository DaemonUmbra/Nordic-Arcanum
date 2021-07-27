package com.lordskittles.nordicarcanum.client.render.tileentity;

import com.lordskittles.arcanumapi.client.render.tileentity.TileEntityRendererBase;
import com.lordskittles.nordicarcanum.client.render.tileentity.model.ModelNordicAnvil;
import com.lordskittles.nordicarcanum.common.blockentity.crafting.BlockEntityNordicAnvil;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;

public class TileRendererNordicAnvil extends TileEntityRendererBase<BlockEntityNordicAnvil> {

    private static ModelNordicAnvil anvil = new ModelNordicAnvil();

    public TileRendererNordicAnvil(BlockEntityRenderDispatcher rendererDispatcherIn) {

        super(rendererDispatcherIn);
    }

    @Override
    public void render(BlockEntityNordicAnvil block, float ticks, PoseStack stack, MultiBufferSource buffer, int light, int overlay) {

//        stack.push();
//
//        Quaternion rotation = Vector3f.ZN.rotationDegrees(180);
//        rotation.multiply(Vector3f.YN.rotationDegrees(180));
//        stack.rotate(rotation);
//        stack.translate(0.53, 0, - 0.47);
//
//        anvil.render(stack, buffer, light, overlay);
//        stack.pop();
//
//        ItemStack heldItem = block.getHeldItem();
//        if(! heldItem.isEmpty()) {
//            stack.push();
//            stack.translate(0.5, 1, 0.5D);
//            stack.rotate(Vector3f.XN.rotationDegrees(90));
//            stack.scale(0.375f, 0.375f, 0.375f);
//
//            Minecraft.getInstance().getItemRenderer().renderItem(heldItem, ItemCameraTransforms.TransformType.FIXED, light, overlay, stack, buffer);
//
//            stack.pop();
//        }
//
//        ItemStack heldCast = tile.getHeldCast();
//        if(! heldCast.isEmpty()) {
//            stack.push();
//            stack.translate(0.3, 1, 0.5D);
//            stack.rotate(Vector3f.XN.rotationDegrees(90));
//            stack.rotate(Vector3f.ZP.rotationDegrees(15));
//            stack.scale(0.25f, 0.25f, 0.25f);
//
//            Minecraft.getInstance().getItemRenderer().renderItem(heldCast, ItemCameraTransforms.TransformType.FIXED, light, overlay, stack, buffer);
//
//            stack.pop();
//        }
    }
}
