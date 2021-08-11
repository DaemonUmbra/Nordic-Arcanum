package com.lordskittles.arcanumapi.client.render;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlas;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.resources.ResourceLocation;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.RenderStateShard.TextureStateShard;
import net.minecraft.client.renderer.RenderType.CompositeState;

public class ArcaneRenderType extends RenderType {

    //private static final TransparencyStateShard CUBOID_ALPHA = new TransparencyStateShard(0.1F);

    private ArcaneRenderType(String name, VertexFormat format, VertexFormat.Mode drawMode, int bufferSize, boolean useDelegate, boolean needsSorting, Runnable setupTask, Runnable clearTask) {

        super(name, format, drawMode, bufferSize, useDelegate, needsSorting, setupTask, clearTask);
    }

    private static CompositeState.CompositeStateBuilder preset(ResourceLocation location) {

        return CompositeState.builder()
                .setTextureState(new TextureStateShard(location, false, false))
                .setCullState(CULL)
                .setTransparencyState(TRANSLUCENT_TRANSPARENCY);
    }

    public static RenderType resizableCuboid() {

        CompositeState.CompositeStateBuilder builder = preset(TextureAtlas.LOCATION_BLOCKS);//.setTransparencyState(CUBOID_ALPHA);

        return create("resizable_cuboid", DefaultVertexFormat.POSITION_COLOR_TEX_LIGHTMAP, VertexFormat.Mode.QUADS, 256, true, false, builder.createCompositeState(true));
    }
}
