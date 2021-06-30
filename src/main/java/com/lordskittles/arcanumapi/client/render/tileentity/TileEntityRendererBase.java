package com.lordskittles.arcanumapi.client.render.tileentity;

import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;

public abstract class TileEntityRendererBase<T extends TileEntity> extends TileEntityRenderer<T>
{
    public TileEntityRendererBase(TileEntityRendererDispatcher rendererDispatcherIn)
    {
        super(rendererDispatcherIn);
    }
}
