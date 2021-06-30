package com.lordskittles.arcanumapi.client.gui.utilities;

import com.lordskittles.arcanumapi.common.math.UVInt;
import com.lordskittles.arcanumapi.common.math.Vector2Int;
import com.lordskittles.arcanumapi.common.utilities.MultiFunction;
import com.lordskittles.arcanumapi.common.utilities.VoidFunction;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class Button
{
    public final Vector2Int pos;
    public final UVInt uv;
    public final Vector2Int size;

    protected final ResourceLocation texture;
    protected final Minecraft minecraft;

    private final MultiFunction<MatrixStack, Integer, Integer, Integer, Integer, Integer, Integer> blit;
    private final VoidFunction<ResourceLocation, Minecraft> applyTexture;

    public Button(Vector2Int pos, UVInt uv, Vector2Int size, ResourceLocation textureLoc, Minecraft minecraft, MultiFunction<MatrixStack, Integer, Integer, Integer, Integer, Integer, Integer> blit, VoidFunction<ResourceLocation, Minecraft> applyTexture)
    {
        this.pos = pos;
        this.uv = uv;
        this.size = size;

        this.texture = textureLoc;
        this.minecraft = minecraft;

        this.blit = blit;
        this.applyTexture = applyTexture;
    }

    public void updatePosition(Vector2Int pos)
    {
        this.pos.update(pos);
    }

    public void draw(MatrixStack matrixStack)
    {
        this.applyTexture.apply(this.texture, this.minecraft);
        this.blit.apply(matrixStack, this.pos.x, this.pos.y, this.uv.u, this.uv.v, this.size.x, this.size.y);
    }
}
