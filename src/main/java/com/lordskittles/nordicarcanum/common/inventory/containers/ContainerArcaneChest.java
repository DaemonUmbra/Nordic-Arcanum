package com.lordskittles.nordicarcanum.common.inventory.containers;

import com.lordskittles.arcanumapi.common.inventory.containers.ContainerStorageBase;
import com.lordskittles.nordicarcanum.common.registry.Containers;
import com.lordskittles.nordicarcanum.common.blockentity.magic.BlockEntityArcaneChest;
import com.lordskittles.nordicarcanum.core.NordicInventorySlots;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.Objects;

public class ContainerArcaneChest extends ContainerStorageBase<BlockEntityArcaneChest> {

    public ContainerArcaneChest(final int windowId, final Inventory playerInventory, final BlockEntityArcaneChest block) {

        super(Containers.arcane_chest.get(), NordicInventorySlots.ARCANE_CHEST, windowId, playerInventory, block);

        block.startOpen(playerInventory.player);

        // Main Inventory
        generateStorageSlots(0, 6, 9, 8, 8, 18);

        // Main Player Inventory
        generateMainPlayerInventory(playerInventory, 8, 125);
        generatePlayerInventoryHotbar(playerInventory, 8, 183);
    }

    public ContainerArcaneChest(final int windowId, final Inventory playerInventory, final FriendlyByteBuf packetBuffer) {

        this(windowId, playerInventory, getBlockEntity(playerInventory, packetBuffer));
    }

    @Override
    public boolean stillValid(Player player) {

        return blockEntity.stillValid(player);
    }

    private static BlockEntityArcaneChest getBlockEntity(final Inventory playerInventory, final FriendlyByteBuf packetBuffer) {

        Objects.requireNonNull(playerInventory, "PlayerInventory cannot be null");
        Objects.requireNonNull(packetBuffer, "Data cannot be null");

        final BlockEntity blockAtPos = playerInventory.player.level.getBlockEntity(packetBuffer.readBlockPos());
        if(blockAtPos instanceof BlockEntityArcaneChest) {
            return (BlockEntityArcaneChest) blockAtPos;
        }

        throw new IllegalStateException("Tile Entity is not correct! " + blockAtPos);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {

        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if(slot != null && slot.hasItem()) {
            ItemStack itemStack = slot.getItem();
            stack = itemStack.copy();
            if(index < nonPlayerSlotCount) {
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
