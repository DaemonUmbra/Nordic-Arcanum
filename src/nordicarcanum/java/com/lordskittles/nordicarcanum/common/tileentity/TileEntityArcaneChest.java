package com.lordskittles.nordicarcanum.common.tileentity;

import com.lordskittles.arcanumapi.common.tileentity.TileEntityMagicChest;
import com.lordskittles.nordicarcanum.common.inventory.containers.ContainerArcaneChest;
import com.lordskittles.nordicarcanum.common.registry.TileEntities;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicInventorySlots;
import com.lordskittles.nordicarcanum.core.NordicNames;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.tileentity.TileEntityType;

public class TileEntityArcaneChest extends TileEntityMagicChest<TileEntityArcaneChest>
{
    public TileEntityArcaneChest(TileEntityType<?> tileEntityTypeIn)
    {
        super(tileEntityTypeIn, NordicInventorySlots.ARCANE_CHEST, NordicNames.ARCANE_CHEST, 10f, NordicArcanum.MODID, NordicArcanum.PACKET_HANDLER);
    }

    public TileEntityArcaneChest()
    {
        this(TileEntities.arcane_chest.get());
    }

    @Override
    public Container createMenu(int id, PlayerInventory playerInventory, PlayerEntity player)
    {
        return new ContainerArcaneChest(id, playerInventory, this);
    }
}
