package com.lordskittles.nordicarcanum.common.tileentity.magic;

import com.lordskittles.arcanumapi.common.tileentity.TileEntityMagicChest;
import com.lordskittles.nordicarcanum.common.inventory.containers.ContainerArcaneChest;
import com.lordskittles.nordicarcanum.common.registry.TileEntities;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicInventorySlots;
import com.lordskittles.nordicarcanum.core.NordicNames;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;

public class TileEntityArcaneChest extends TileEntityMagicChest<TileEntityArcaneChest> {

    public TileEntityArcaneChest(BlockPos pos, BlockState state) {

        super(TileEntities.arcane_chest.get(), pos, state, NordicInventorySlots.ARCANE_CHEST, NordicNames.ARCANE_CHEST, 10f, NordicArcanum.MODID, NordicArcanum.PACKET_HANDLER);
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player player) {

        return new ContainerArcaneChest(id, playerInventory, this);
    }
}
