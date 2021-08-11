package com.lordskittles.arcanumapi.client.gui.utilities;

import com.lordskittles.arcanumapi.common.math.UVInt;
import com.lordskittles.arcanumapi.common.math.Vector2Int;
import com.lordskittles.arcanumapi.common.utilities.MultiFunction;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class TabGroup {

    protected List<Tab> tabs = new ArrayList<>();

    private int activeIndex;

    private final Vector2Int startPos;
    private final UVInt inactiveUV;
    private final UVInt activeUV;
    private final Vector2Int activeSize;
    private final Vector2Int inactiveSize;

    private final ResourceLocation texture;
    private final Minecraft minecraft;
    private final MultiFunction<PoseStack, Integer, Integer, Integer, Integer, Integer, Integer> blit;

    public TabGroup(Vector2Int startPos, UVInt inactiveUV, UVInt activeUV, Vector2Int activeSize, Vector2Int inactiveSize, ResourceLocation texture, Minecraft minecraft, MultiFunction<PoseStack, Integer, Integer, Integer, Integer, Integer, Integer> blit) {

        this.startPos = startPos;
        this.inactiveUV = inactiveUV;
        this.activeUV = activeUV;
        this.inactiveSize = inactiveSize;
        this.activeSize = activeSize;

        this.texture = texture;
        this.minecraft = minecraft;
        this.blit = blit;
    }

    public void setup(int tabCount, int startActive, GUISide side, Class<? extends Tab> clazz) {

        this.tabs.clear();
        Vector2Int offset = new Vector2Int(0, 0);

        for(int i = 0; i < tabCount; i++) {
            Vector2Int pos = new Vector2Int(this.startPos);
            pos.update(offset);

            this.tabs.add(clazz.cast(new Tab(
                    false,
                    side,
                    i,
                    pos,
                    this.inactiveUV,
                    this.activeUV,
                    this.inactiveSize,
                    this.activeSize,
                    this.texture,
                    this.minecraft,
                    this.blit,
                    (location, minecraft) -> minecraft.getTextureManager().bindForSetup(this.texture)
            )));

            switch(side) {
                case Left:
                case Right:
                    offset.update(new Vector2Int(0, this.inactiveSize.y));
                    break;
                case Top:
                case Bottom:
                    offset.update(new Vector2Int(this.inactiveSize.x, 0));
                    break;
            }
        }

        if(this.tabs.size() > 0) {
            this.tabs.get(startActive).flagActive();
        }

        this.activeIndex = startActive;
    }

    public void draw(PoseStack matrixStack) {

        for(Tab tab : this.tabs) {
            tab.draw(matrixStack);
        }
    }

    public boolean handleClick(double x, double y, double startX, double startY) {

        int activeIndex = getActiveIndex();
        boolean tabUpdated = false;

        for(Tab tab : this.tabs) {
            if(tab.handleClick(x, y, startX, startY)) {
                tabUpdated = true;
                this.activeIndex = tab.index;
                break;
            }
        }

        if(tabUpdated) {
            if(this.tabs.size() > 0) {
                this.tabs.get(activeIndex).flagActive();
            }
            return true;
        }

        return false;
    }

    public int getActiveIndex() {

        return this.activeIndex;
    }
}
