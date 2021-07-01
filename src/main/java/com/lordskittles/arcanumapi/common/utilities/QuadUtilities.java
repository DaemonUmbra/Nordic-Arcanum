package com.lordskittles.arcanumapi.common.utilities;

import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public class QuadUtilities {

    public static BakedQuad remap(BakedQuad quad, TextureAtlasSprite sprite) {

        int[] vertexData = quad.getVertexData();
        for(int vertex = 0; vertex < 4; ++ vertex) {
            int shift = DefaultVertexFormats.BLOCK.getIntegerSize() * vertex;
            vertexData[shift + 4] = Float.floatToRawIntBits(sprite.getInterpolatedU(getUnInterpolatedU(quad.getSprite(), Float.intBitsToFloat(vertexData[shift + 4]))));
            vertexData[shift + 5] = Float.floatToRawIntBits(sprite.getInterpolatedV(getUnInterpolatedV(quad.getSprite(), Float.intBitsToFloat(vertexData[shift + 5]))));
        }
        quad.sprite = sprite;

        return quad;
    }

    public static float getUnInterpolatedU(TextureAtlasSprite sprite, float u) {

        return (u - sprite.getMinU()) / (sprite.getMaxU() - sprite.getMinU()) * 16f;
    }

    public static float getUnInterpolatedV(TextureAtlasSprite sprite, float v) {

        return (v - sprite.getMinV()) / (sprite.getMaxV() - sprite.getMinV()) * 16f;
    }
}
