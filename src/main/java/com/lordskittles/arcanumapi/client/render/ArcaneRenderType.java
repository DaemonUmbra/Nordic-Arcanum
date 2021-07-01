package com.lordskittles.arcanumapi.client.render;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class ArcaneRenderType extends RenderType {

    private static final AlphaState CUBOID_ALPHA = new AlphaState(0.1F);

    private ArcaneRenderType(String name, VertexFormat format, int drawMode, int bufferSize, boolean useDelegate, boolean needsSorting, Runnable setupTask, Runnable clearTask) {

        super(name, format, drawMode, bufferSize, useDelegate, needsSorting, setupTask, clearTask);
    }

    private static State.Builder preset(ResourceLocation location) {

        return State.getBuilder()
                .texture(new TextureState(location, false, false))
                .cull(CULL_ENABLED)
                .transparency(TRANSLUCENT_TRANSPARENCY)
                .shadeModel(SHADE_ENABLED);
    }

    public static RenderType resizableCuboid() {

        State.Builder builder = preset(AtlasTexture.LOCATION_BLOCKS_TEXTURE).alpha(CUBOID_ALPHA);

        return makeType("resizable_cuboid", DefaultVertexFormats.POSITION_COLOR_TEX_LIGHTMAP, GL11.GL_QUADS, 256, true, false, builder.build(true));
    }
}
