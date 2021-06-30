package com.lordskittles.nordicarcanum.common.inventory.containers;

import com.lordskittles.arcanumapi.common.inventory.containers.ContainerStorageBase;
import com.lordskittles.nordicarcanum.common.registry.Blocks;
import com.lordskittles.nordicarcanum.common.registry.Containers;
import com.lordskittles.nordicarcanum.common.tileentity.TileEntityArcaneChest;
import com.lordskittles.nordicarcanum.core.NordicInventorySlots;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;

import java.util.Objects;

public class ContainerArcaneChest extends ContainerStorageBase<TileEntityArcaneChest>
{
    public ContainerArcaneChest(final int windowId, final PlayerInventory playerInventory, final TileEntityArcaneChest tile)
    {
        super(Containers.arcane_chest.get(), NordicInventorySlots.ARCANE_CHEST, windowId, playerInventory, tile);

        tile.openInventory(playerInventory.player);

        // Main Inventory
        generateStorageSlots(0, 6, 9, 8, 8, 18);

        // Main Player Inventory
        generateMainPlayerInventory(playerInventory, 8, 125);
        generatePlayerInventoryHotbar(playerInventory, 8, 183);
    }

    public ContainerArcaneChest(final int windowId, final PlayerInventory playerInventory, final PacketBuffer packetBuffer)
    {
        this(windowId, playerInventory, getTileEntity(playerInventory, packetBuffer));
    }

    @Override
    public boolean canInteractWith(PlayerEntity player)
    {
        return isWithinUsableDistance(canInteract, player, Blocks.arcane_chest.get());
    }

    private static TileEntityArcaneChest getTileEntity(final PlayerInventory playerInventory, final PacketBuffer packetBuffer)
    {
        Objects.requireNonNull(playerInventory, "PlayerInventory cannot be null");
        Objects.requireNonNull(packetBuffer, "Data cannot be null");

        final TileEntity tileAtPos = playerInventory.player.world.getTileEntity(packetBuffer.readBlockPos());
        if (tileAtPos instanceof TileEntityArcaneChest)
        {
            return (TileEntityArcaneChest)tileAtPos;
        }

        throw new IllegalStateException("Tile Entity is not correct! " + tileAtPos);
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity player, int index)
    {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack())
        {
            ItemStack itemStack = slot.getStack();
            stack = itemStack.copy();
            if (index < nonPlayerSlotCount)
            {
                if (!this.mergeItemStack(itemStack, this.nonPlayerSlotCount, this.inventorySlots.size(), true))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemStack, 0, this.nonPlayerSlotCount, false))
            {
                return ItemStack.EMPTY;
            }

            if (itemStack.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return stack;
    }
}
