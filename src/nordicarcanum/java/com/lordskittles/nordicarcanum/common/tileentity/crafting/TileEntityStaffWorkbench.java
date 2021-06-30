package com.lordskittles.nordicarcanum.common.tileentity.crafting;

import com.lordskittles.arcanumapi.common.tileentity.TileEntityInventory;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicInventorySlots;
import com.lordskittles.nordicarcanum.core.NordicNames;
import com.lordskittles.nordicarcanum.common.inventory.containers.ContainerStaffWorkbench;
import com.lordskittles.nordicarcanum.common.registry.TileEntities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.tileentity.TileEntityType;

import javax.annotation.Nullable;

public class TileEntityStaffWorkbench extends TileEntityInventory<TileEntityStaffWorkbench>
{
    public TileEntityStaffWorkbench(TileEntityType<?> type)
    {
        super(type, NordicInventorySlots.STAFF_WORKBENCH, NordicNames.STAFF_WORKBENCH, NordicArcanum.MODID);
    }

    public TileEntityStaffWorkbench()
    {
        this(TileEntities.staff_workbench.get());
    }

    @Nullable
    @Override
    public Container createMenu(int id, PlayerInventory playerInventory, PlayerEntity player)
    {
        return new ContainerStaffWorkbench(id, playerInventory, this);
    }
}
