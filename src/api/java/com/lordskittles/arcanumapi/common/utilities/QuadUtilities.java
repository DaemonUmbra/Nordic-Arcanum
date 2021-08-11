package com.lordskittles.arcanumapi.common.utilities;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;

public class QuadUtilities {

    public static BakedQuad remap(BakedQuad quad, TextureAtlasSprite sprite) {

        int[] vertexData = quad.getVertices();
        for(int vertex = 0; vertex < 4; ++ vertex) {
            int shift = DefaultVertexFormat.BLOCK.getIntegerSize() * vertex;
            vertexData[shift + 4] = Float.floatToRawIntBits(sprite.getU(getUnInterpolatedU(quad.getSprite(), Float.intBitsToFloat(vertexData[shift + 4]))));
            vertexData[shift + 5] = Float.floatToRawIntBits(sprite.getV(getUnInterpolatedV(quad.getSprite(), Float.intBitsToFloat(vertexData[shift + 5]))));
        }
        quad.sprite = sprite;

        return quad;
    }

    public static float getUnInterpolatedU(TextureAtlasSprite sprite, float u) {

        return (u - sprite.getU0()) / (sprite.getU1() - sprite.getU0()) * 16f;
    }

    public static float getUnInterpolatedV(TextureAtlasSprite sprite, float v) {

        return (v - sprite.getV0()) / (sprite.getV1() - sprite.getV0()) * 16f;
    }
}
