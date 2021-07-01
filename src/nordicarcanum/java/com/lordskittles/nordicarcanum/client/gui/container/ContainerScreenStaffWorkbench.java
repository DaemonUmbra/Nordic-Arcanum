package com.lordskittles.nordicarcanum.client.gui.container;

import com.lordskittles.arcanumapi.client.gui.container.ContainerScreenBase;
import com.lordskittles.nordicarcanum.common.inventory.containers.ContainerStaffWorkbench;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicNames;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ContainerScreenStaffWorkbench extends ContainerScreenBase<ContainerStaffWorkbench> {

    public ContainerScreenStaffWorkbench(ContainerStaffWorkbench container, PlayerInventory inventory, ITextComponent title) {

        super(NordicArcanum.MODID, NordicNames.STAFF_WORKBENCH, container, inventory, title);

        setTextureRect(0, 0, 176, 166);
    }
}
