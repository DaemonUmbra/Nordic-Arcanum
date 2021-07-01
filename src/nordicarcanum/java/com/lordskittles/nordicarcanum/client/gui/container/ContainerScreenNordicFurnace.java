package com.lordskittles.nordicarcanum.client.gui.container;

import com.lordskittles.arcanumapi.client.gui.container.ContainerScreenBase;
import com.lordskittles.nordicarcanum.common.inventory.containers.ContainerNordicFurnace;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicNames;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

public class ContainerScreenNordicFurnace extends ContainerScreenBase<ContainerNordicFurnace> {

    public ContainerScreenNordicFurnace(ContainerNordicFurnace container, PlayerInventory inventory, ITextComponent textComponent) {

        super(NordicArcanum.MODID, NordicNames.NORDIC_FURNACE, container, inventory, textComponent);

        setTextureRect(0, 0, 176, 166);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack stack, float partialTicks, int mouseX, int mouseY) {

        super.drawGuiContainerBackgroundLayer(stack, partialTicks, mouseX, mouseY);
        int left = this.guiLeft;
        int top = this.guiTop;

        if(this.container.isBurning()) {
            int burnTime = this.container.getBurnLeftScaled();
            this.blit(stack, left + 38, top + 35 + 12 - burnTime, 176, 12 - burnTime, 14, burnTime + 1);
        }

        int progression = this.container.getCookProgressionScaled();
        this.blit(stack, left + 94, top + 35, 176, 14, progression + 1, 16);
    }
}
