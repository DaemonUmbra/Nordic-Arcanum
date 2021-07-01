package com.lordskittles.arcanumapi.client.gui.utilities;

import com.lordskittles.arcanumapi.common.math.UVInt;
import com.lordskittles.arcanumapi.common.math.Vector2Int;
import com.lordskittles.arcanumapi.common.utilities.MultiFunction;
import com.lordskittles.arcanumapi.common.utilities.VoidFunction;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class Tab {

    protected boolean active;
    protected final int index;

    protected final Vector2Int pos;
    protected final UVInt inactiveUV;
    protected final UVInt activeUV;

    protected final Vector2Int inactiveSize;
    protected final Vector2Int activeSize;

    protected final ResourceLocation texture;
    protected final Minecraft minecraft;

    private final MultiFunction<MatrixStack, Integer, Integer, Integer, Integer, Integer, Integer> blit;
    private final VoidFunction<ResourceLocation, Minecraft> applyTexture;

    private final GUISide side;

    public Tab(boolean active, GUISide side, int index, Vector2Int pos, UVInt inactiveUV, UVInt activeUV, Vector2Int inactiveSize, Vector2Int activeSize, ResourceLocation textureLoc, Minecraft minecraft, MultiFunction<MatrixStack, Integer, Integer, Integer, Integer, Integer, Integer> blit, VoidFunction<ResourceLocation, Minecraft> applyTexture) {

        this.active = active;
        this.side = side;
        this.index = index;

        this.pos = pos;
        this.inactiveUV = inactiveUV;
        this.activeUV = activeUV;

        this.inactiveSize = inactiveSize;
        this.activeSize = activeSize;

        this.texture = textureLoc;
        this.minecraft = minecraft;

        this.blit = blit;
        this.applyTexture = applyTexture;
    }

    public void draw(MatrixStack matrixStack) {

        this.applyTexture.apply(this.texture, this.minecraft);

        UVInt uv = this.active ? this.activeUV : this.inactiveUV;
        Vector2Int size = this.active ? this.activeSize : this.inactiveSize;

        switch(this.side) {
            case Left:
                this.blit.apply(matrixStack, this.pos.x - size.x, this.pos.y, uv.u, uv.v, size.x, size.y);
                break;
            case Right:
                this.blit.apply(matrixStack, this.pos.x + size.x, this.pos.y, uv.u, uv.v, size.x, size.y);
                break;
            case Top:
                this.blit.apply(matrixStack, this.pos.x, this.pos.y - size.y, uv.u, uv.v, size.x, size.y);
                break;
            case Bottom:
                this.blit.apply(matrixStack, this.pos.x, this.pos.y + size.y, uv.u, uv.v, size.x, size.y);
                break;
        }
    }

    public boolean handleClick(double x, double y, double startX, double startY) {

        Vector2Int position = new Vector2Int(0, 0);
        Vector2Int size = this.active ? this.activeSize : this.inactiveSize;

        switch(this.side) {
            case Left:
                position = new Vector2Int(this.pos.x - size.x, this.pos.y);
                break;
            case Right:
                position = new Vector2Int(this.pos.x + size.x, this.pos.y);
                break;
            case Top:
                position = new Vector2Int(this.pos.x, this.pos.y - size.y);
                break;
            case Bottom:
                position = new Vector2Int(this.pos.x, this.pos.y + size.y);
                break;
        }

        position.update(new Vector2Int(startX, startY));

        Vector2Int end = new Vector2Int(position);
        end.update(getEnd());

        if(this.active) {
            return false;
        }

        if(x > position.x && x < end.x && y > position.y && y < end.y) {
            flagActive();
            return true;
        }

        return false;
    }

    public boolean isActive() {

        return active;
    }

    public void flagActive() {

        this.active = true;
    }

    public void flagInactive() {

        this.active = false;
    }

    public Vector2Int getPos() {

        return this.pos;
    }

    public Vector2Int getEnd() {

        return this.active ? this.activeSize : this.inactiveSize;
    }
}
