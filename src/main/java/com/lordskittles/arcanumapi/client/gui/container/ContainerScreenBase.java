package com.lordskittles.arcanumapi.client.gui.container;

import com.lordskittles.arcanumapi.core.ArcanumAPI;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class ContainerScreenBase<T extends Container> extends ContainerScreen<T>
{
    protected ResourceLocation texture = null;
    protected int textColor = 4210752;

    protected int playerInvTextX = 0;
    protected int playerInvTextY = 0;

    protected float containerNamePosX = 0;
    protected float containerNamePosY = 0;

    protected float red = 1.0f;
    protected float green = 1.0f;
    protected float blue = 1.0f;
    protected float alpha = 1.0f;

    protected boolean drawPlayerText = false;
    protected boolean drawContainerText = false;

    public ContainerScreenBase(String modID, String textureName, T container, PlayerInventory inventory, ITextComponent textComponent)
    {
        super(container, inventory, textComponent);

        this.texture = ArcanumAPI.RL(modID, "textures/gui/" + textureName + ".png");
    }

    protected void setContainerNamePos(float posX, float posY)
    {
        this.containerNamePosX = posX;
        this.containerNamePosY = posY;
        this.drawContainerText = true;
    }

    protected void setTextureRect(int left, int top, int width, int height)
    {
        this.guiLeft = left;
        this.guiTop = top;
        this.xSize = width;
        this.ySize = height;
    }

    protected void setTextColor(int textColor)
    {
        this.textColor = textColor;
    }

    protected void setPlayerInvTexPos(int posX, int posY)
    {
        this.playerInvTextX = posX;
        this.playerInvTextY = posY;
        this.drawPlayerText = true;
    }

    protected void setScreenTint(float red, float green, float blue, float alpha)
    {
        this.red = red;
        this.green = red;
        this.blue = blue;
        this.alpha = alpha;
    }

    @Override
    public void render(MatrixStack matrixStack, final int mouseX, final int mouseY, final float partialTicks)
    {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);

        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int mouseX, int mouseY)
    {
        if (this.drawContainerText)
        {
            this.font.drawString(matrixStack, this.title.getString(), this.containerNamePosX, this.containerNamePosY, this.textColor);
        }

        if (this.drawPlayerText)
        {
            this.font.drawString(matrixStack, this.playerInventory.getDisplayName().getString(), this.playerInvTextX, this.playerInvTextY, this.textColor);
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY)
    {
        RenderSystem.color4f(this.red, this.green, this.blue, this.alpha);
        this.minecraft.getTextureManager().bindTexture(this.texture);

        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.blit(matrixStack, x, y, 0, 0, this.xSize, this.ySize);
    }
}
