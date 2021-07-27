package com.lordskittles.nordicarcanum.common.inventory.containers;

import com.lordskittles.arcanumapi.common.inventory.containers.ContainerBase;
import com.lordskittles.nordicarcanum.common.registry.Containers;
import com.lordskittles.nordicarcanum.common.blockentity.crafting.BlockEntityAlchemyTable;
import com.lordskittles.nordicarcanum.core.NordicInventorySlots;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.Objects;

public class ContainerAlchemyTable extends ContainerBase<BlockEntityAlchemyTable> {

    public ContainerAlchemyTable(final int windowId, final Inventory playerInventory, final BlockEntityAlchemyTable tile) {

        super(Containers.alchemy_table.get(), NordicInventorySlots.ALCHEMY_TABLE, windowId, playerInventory, tile);

        generateMainPlayerInventory(playerInventory, 8, 84);
        generatePlayerInventoryHotbar(playerInventory, 8, 142);
    }

    public ContainerAlchemyTable(final int windowId, final Inventory playerInventory, final FriendlyByteBuf packetBuffer) {

        this(windowId, playerInventory, getBlockEntity(playerInventory, packetBuffer));
    }

    private static BlockEntityAlchemyTable getBlockEntity(final Inventory playerInventory, final FriendlyByteBuf packetBuffer) {

        Objects.requireNonNull(playerInventory, "PlayerInventory cannot be null");
        Objects.requireNonNull(packetBuffer, "Data cannot be null");

        final BlockEntity tileAtPos = playerInventory.player.level.getBlockEntity(packetBuffer.readBlockPos());
        if(tileAtPos instanceof BlockEntityAlchemyTable) {
            return (BlockEntityAlchemyTable) tileAtPos;
        }

        throw new IllegalStateException("Tile Entity is not correct! " + tileAtPos);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {

        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if(slot != null && slot.hasItem()) {
            ItemStack itemStack = slot.getItem();
            stack = itemStack.copy();
            if(index < this.nonPlayerSlotCount) {
                if(! this.moveItemStackTo(itemStack, this.nonPlayerSlotCount, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            }
            else
                if(! this.moveItemStackTo(itemStack, 0, this.nonPlayerSlotCount, false)) {
                    return ItemStack.EMPTY;
                }

            if(itemStack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            }
            else {
                slot.setChanged();
            }
        }

        return stack;
    }
}
