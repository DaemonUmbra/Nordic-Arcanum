package com.lordskittles.nordicarcanum.common.blockentity.crafting;

import com.lordskittles.arcanumapi.common.blockentity.BlockEntityInventory;
import com.lordskittles.nordicarcanum.common.inventory.containers.ContainerAlchemyTable;
import com.lordskittles.nordicarcanum.common.registry.BlockEntities;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicInventorySlots;
import com.lordskittles.nordicarcanum.core.NordicNames;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class BlockEntityAlchemyTable extends BlockEntityInventory<BlockEntityAlchemyTable> {

    public BlockEntityAlchemyTable(BlockPos pos, BlockState state) {

        super(BlockEntities.alchemy_table.get(), pos, state, NordicInventorySlots.ALCHEMY_TABLE, NordicNames.ALCHEMY_TABLE, NordicArcanum.MODID);
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player player) {

        return new ContainerAlchemyTable(id, playerInventory, this);
    }
}
