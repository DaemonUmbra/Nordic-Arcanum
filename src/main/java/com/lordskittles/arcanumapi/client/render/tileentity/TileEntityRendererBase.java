package com.lordskittles.arcanumapi.client.render.tileentity;

import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.world.level.block.entity.BlockEntity;

public abstract class TileEntityRendererBase<T extends BlockEntity> implements BlockEntityRenderer<T> {

    protected BlockEntityRenderDispatcher renderDispatcher;

    public TileEntityRendererBase(BlockEntityRenderDispatcher rendererDispatcherIn) {

        rendererDispatcherIn = rendererDispatcherIn;
    }
}
