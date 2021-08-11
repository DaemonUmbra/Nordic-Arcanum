package com.lordskittles.nordicarcanum.client.gui.container;

import com.lordskittles.arcanumapi.client.gui.container.ContainerScreenBase;
import com.lordskittles.nordicarcanum.common.inventory.containers.ContainerStaffWorkbench;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicNames;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ContainerScreenStaffWorkbench extends ContainerScreenBase<ContainerStaffWorkbench> {

    public ContainerScreenStaffWorkbench(ContainerStaffWorkbench container, Inventory inventory, Component title) {

        super(NordicArcanum.MODID, NordicNames.STAFF_WORKBENCH, container, inventory, title);

        setTextureRect(0, 0, 176, 166);
    }
}
