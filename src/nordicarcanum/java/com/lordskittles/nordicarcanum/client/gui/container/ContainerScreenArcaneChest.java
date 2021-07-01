package com.lordskittles.nordicarcanum.client.gui.container;

import com.lordskittles.arcanumapi.client.gui.container.ContainerScreenBase;
import com.lordskittles.nordicarcanum.common.inventory.containers.ContainerArcaneChest;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicNames;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ContainerScreenArcaneChest extends ContainerScreenBase<ContainerArcaneChest> {

    public ContainerScreenArcaneChest(ContainerArcaneChest container, PlayerInventory inventory, ITextComponent title) {

        super(NordicArcanum.MODID, NordicNames.ARCANE_CHEST, container, inventory, title);

        setTextureRect(0, 0, 176, 222);
    }
}
