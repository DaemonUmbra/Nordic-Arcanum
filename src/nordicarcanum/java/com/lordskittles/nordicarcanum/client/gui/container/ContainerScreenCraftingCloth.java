package com.lordskittles.nordicarcanum.client.gui.container;

import com.lordskittles.arcanumapi.client.gui.container.ContainerScreenBase;
import com.lordskittles.nordicarcanum.common.inventory.containers.ContainerCraftingCloth;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicNames;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.chat.Component;

public class ContainerScreenCraftingCloth extends ContainerScreenBase<ContainerCraftingCloth> {

    private final Player player;

    public ContainerScreenCraftingCloth(ContainerCraftingCloth container, Inventory inventory, Component title) {

        super(NordicArcanum.MODID, NordicNames.CRAFTING_CLOTH, container, inventory, title);

        this.player = inventory.player;

        setTextureRect(0, 0, 176, 190);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(PoseStack stack, float partialTicks, int mouseX, int mouseY) {

        super.drawGuiContainerBackgroundLayer(stack, partialTicks, mouseX, mouseY);
        int left = this.guiLeft;
        int top = this.guiTop;

        // Bar positioned at: 153, 11
        float arcanumPercent = this.container.getCurrentArcanum() / this.container.getMaximumArcanum();
        int offset = (int) (61 * arcanumPercent);
        this.blit(stack, left + 105, top + 13, 63, 190, offset, 12);

        //border
        this.blit(stack, left + 104, top + 12, 0, 190, 63, 14);
    }
}
