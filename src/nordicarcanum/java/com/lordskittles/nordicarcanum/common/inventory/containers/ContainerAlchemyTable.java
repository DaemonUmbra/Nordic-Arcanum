package com.lordskittles.nordicarcanum.common.inventory.containers;

import com.lordskittles.arcanumapi.common.inventory.containers.ContainerBase;
import com.lordskittles.nordicarcanum.common.registry.Containers;
import com.lordskittles.nordicarcanum.core.NordicInventorySlots;
import com.lordskittles.nordicarcanum.common.tileentity.crafting.TileEntityAlchemyTable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;

import java.util.Objects;

public class ContainerAlchemyTable extends ContainerBase<TileEntityAlchemyTable>
{
    public ContainerAlchemyTable(final int windowId, final PlayerInventory playerInventory, final TileEntityAlchemyTable tile)
    {
        super(Containers.alchemy_table.get(), NordicInventorySlots.ALCHEMY_TABLE, windowId, playerInventory, tile);

        generateMainPlayerInventory(playerInventory, 8, 84);
        generatePlayerInventoryHotbar(playerInventory, 8, 142);
    }

    public ContainerAlchemyTable(final int windowId, final PlayerInventory playerInventory, final PacketBuffer packetBuffer)
    {
        this(windowId, playerInventory, getTileEntity(playerInventory, packetBuffer));
    }

    private static TileEntityAlchemyTable getTileEntity(final PlayerInventory playerInventory, final PacketBuffer packetBuffer)
    {
        Objects.requireNonNull(playerInventory, "PlayerInventory cannot be null");
        Objects.requireNonNull(packetBuffer, "Data cannot be null");

        final TileEntity tileAtPos = playerInventory.player.world.getTileEntity(packetBuffer.readBlockPos());
        if (tileAtPos instanceof TileEntityAlchemyTable)
        {
            return (TileEntityAlchemyTable)tileAtPos;
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
            if (index < this.nonPlayerSlotCount)
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
