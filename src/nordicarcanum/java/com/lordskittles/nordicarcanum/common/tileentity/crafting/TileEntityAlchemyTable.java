package com.lordskittles.nordicarcanum.common.tileentity.crafting;

import com.lordskittles.arcanumapi.common.tileentity.TileEntityInventory;
import com.lordskittles.nordicarcanum.common.inventory.containers.ContainerAlchemyTable;
import com.lordskittles.nordicarcanum.common.registry.TileEntities;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicInventorySlots;
import com.lordskittles.nordicarcanum.core.NordicNames;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.tileentity.TileEntityType;

import javax.annotation.Nullable;

public class TileEntityAlchemyTable extends TileEntityInventory<TileEntityAlchemyTable> {

    public TileEntityAlchemyTable(TileEntityType<?> type) {

        super(type, NordicInventorySlots.ALCHEMY_TABLE, NordicNames.ALCHEMY_TABLE, NordicArcanum.MODID);
    }

    public TileEntityAlchemyTable() {

        this(TileEntities.alchemy_table.get());
    }

    @Nullable
    @Override
    public Container createMenu(int id, PlayerInventory playerInventory, PlayerEntity player) {

        return new ContainerAlchemyTable(id, playerInventory, this);
    }
}
