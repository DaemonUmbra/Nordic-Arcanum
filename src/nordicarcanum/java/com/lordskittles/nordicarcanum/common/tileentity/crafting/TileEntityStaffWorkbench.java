package com.lordskittles.nordicarcanum.common.tileentity.crafting;

import com.lordskittles.arcanumapi.common.tileentity.TileEntityInventory;
import com.lordskittles.nordicarcanum.common.inventory.containers.ContainerStaffWorkbench;
import com.lordskittles.nordicarcanum.common.registry.TileEntities;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicInventorySlots;
import com.lordskittles.nordicarcanum.core.NordicNames;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class TileEntityStaffWorkbench extends TileEntityInventory<TileEntityStaffWorkbench> {

    public TileEntityStaffWorkbench(BlockPos pos, BlockState state) {

        super(TileEntities.staff_workbench.get(), pos, state, NordicInventorySlots.STAFF_WORKBENCH, NordicNames.STAFF_WORKBENCH, NordicArcanum.MODID);
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player player) {

        return new ContainerStaffWorkbench(id, playerInventory, this);
    }
}
