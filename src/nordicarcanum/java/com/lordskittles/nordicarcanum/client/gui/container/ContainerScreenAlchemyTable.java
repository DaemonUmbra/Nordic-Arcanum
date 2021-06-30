package com.lordskittles.nordicarcanum.client.gui.container;

import com.lordskittles.arcanumapi.client.gui.container.ContainerScreenBase;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicNames;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

public class ContainerScreenAlchemyTable extends ContainerScreenBase<com.lordskittles.nordicarcanum.common.inventory.containers.ContainerAlchemyTable>
{
    public ContainerScreenAlchemyTable(com.lordskittles.nordicarcanum.common.inventory.containers.ContainerAlchemyTable container, PlayerInventory inventory, ITextComponent title)
    {
        super(NordicArcanum.MODID, NordicNames.ALCHEMY_TABLE, container, inventory, title);

        setTextureRect(0, 0, 176, 166);
    }
}
