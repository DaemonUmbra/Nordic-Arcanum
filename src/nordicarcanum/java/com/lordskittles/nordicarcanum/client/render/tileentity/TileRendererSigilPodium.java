package com.lordskittles.nordicarcanum.client.render.tileentity;

import com.lordskittles.arcanumapi.client.render.ArcaneRenderer;
import com.lordskittles.arcanumapi.client.render.tileentity.TileEntityRendererBase;
import com.lordskittles.nordicarcanum.client.render.tileentity.model.ModelSigilPodium;
import com.lordskittles.nordicarcanum.common.item.magic.ItemSigil;
import com.lordskittles.nordicarcanum.common.tileentity.magic.TileEntitySigilPodium;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class TileRendererSigilPodium extends TileEntityRendererBase<TileEntitySigilPodium> {

    private final ModelSigilPodium PodiumModel = new ModelSigilPodium();

    public TileRendererSigilPodium(TileEntityRendererDispatcher rendererDispatcherIn) {

        super(rendererDispatcherIn);
    }

    @Override
    public void render(TileEntitySigilPodium tile, float ticks, MatrixStack stack, @Nonnull IRenderTypeBuffer buffer, int light, int overlay) {

        World world = tile.getWorld();

        stack.push();
        stack.translate(0.5D, 1.5D, 0.5D);
        stack.rotate(Vector3f.XN.rotationDegrees(180));
        PodiumModel.render(stack, buffer, light, overlay);
        stack.pop();

        ItemStack sigil = tile.getHeldSigil();
        if(sigil.getItem() instanceof ItemSigil) {
            ItemEntity item = new ItemEntity(world, 0, 0, 0, sigil);
            item.age = tile.getTicksExisted();
            item.hoverStart = 0;
            ArcaneRenderer.renderItemEntity(item, new ArcaneRenderer.DVector3(0.5, 1, 0.5), new ArcaneRenderer.DVector3(2, 2, 2), ticks, stack, buffer, light);
        }
    }
}