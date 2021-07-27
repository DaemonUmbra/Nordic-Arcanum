package com.lordskittles.nordicarcanum.client.render.tileentity;

import com.lordskittles.arcanumapi.client.render.tileentity.TileEntityRendererBase;
import com.lordskittles.nordicarcanum.client.render.tileentity.model.ModelSigilPodium;
import com.lordskittles.nordicarcanum.common.blockentity.magic.BlockEntitySigilPodium;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;

import javax.annotation.Nonnull;

public class TileRendererSigilPodium extends TileEntityRendererBase<BlockEntitySigilPodium> {

    private final ModelSigilPodium PodiumModel = new ModelSigilPodium();

    public TileRendererSigilPodium(BlockEntityRenderDispatcher rendererDispatcherIn) {

        super(rendererDispatcherIn);
    }

    @Override
    public void render(BlockEntitySigilPodium block, float ticks, PoseStack stack, @Nonnull MultiBufferSource buffer, int light, int overlay) {

//        Level level = block.getWorld();
//
//        stack.push();
//        stack.translate(0.5D, 1.5D, 0.5D);
//        stack.rotate(Vector3f.XN.rotationDegrees(180));
//        PodiumModel.render(stack, buffer, light, overlay);
//        stack.pop();
//
//        ItemStack sigil = block.getHeldSigil();
//        if(sigil.getItem() instanceof ItemSigil) {
//            ItemEntity item = new ItemEntity(level, 0, 0, 0, sigil);
//            item.age = block.getTicksExisted();
//            item.hoverStart = 0;
//            ArcaneRenderer.renderItemEntity(item, new ArcaneRenderer.DVector3(0.5, 1, 0.5), new ArcaneRenderer.DVector3(2, 2, 2), ticks, stack, buffer, light);
//        }
    }
}