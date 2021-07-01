package com.lordskittles.nordicarcanum.common.inventory.containers;

import com.lordskittles.arcanumapi.common.inventory.containers.ContainerBase;
import com.lordskittles.nordicarcanum.client.gui.slot.SlotRegistered;
import com.lordskittles.nordicarcanum.common.RegistryType;
import com.lordskittles.nordicarcanum.common.registry.Containers;
import com.lordskittles.nordicarcanum.common.tileentity.crafting.TileEntityStaffWorkbench;
import com.lordskittles.nordicarcanum.core.NordicInventorySlots;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;

import java.util.Objects;

public class ContainerStaffWorkbench extends ContainerBase<TileEntityStaffWorkbench> {

    public ContainerStaffWorkbench(final int windowId, final PlayerInventory playerInventory, final TileEntityStaffWorkbench tile) {

        super(Containers.staff_workbench.get(), NordicInventorySlots.STAFF_WORKBENCH, windowId, playerInventory, tile);

        createTypeSlot(Tile, RegistryType.ROD, 0, 20, 55);
        createTypeSlot(Tile, RegistryType.CORE, 1, 54, 40);
        createTypeSlot(Tile, RegistryType.BINDING, 2, 85, 25);
        createTypeSlot(Tile, RegistryType.CRYSTAL, 3, 111, 12);
        createOutputSlot(Tile, 4, 136, 46);

        generateMainPlayerInventory(playerInventory, 8, 85);
        generatePlayerInventoryHotbar(playerInventory, 8, 143);
    }

    public ContainerStaffWorkbench(final int windowId, final PlayerInventory playerInventory, final PacketBuffer packetBuffer) {

        this(windowId, playerInventory, getTileEntity(playerInventory, packetBuffer));
    }

    protected void createTypeSlot(IInventory inventory, RegistryType type, int index, int xPos, int yPos) {

        this.addSlot(new SlotRegistered(type, inventory, index, xPos, yPos));
    }

    private static TileEntityStaffWorkbench getTileEntity(final PlayerInventory playerInventory, final PacketBuffer packetBuffer) {

        Objects.requireNonNull(playerInventory, "PlayerInventory cannot be null");
        Objects.requireNonNull(packetBuffer, "Data cannot be null");

        final TileEntity tileAtPos = playerInventory.player.world.getTileEntity(packetBuffer.readBlockPos());
        if(tileAtPos instanceof TileEntityStaffWorkbench) {
            return (TileEntityStaffWorkbench) tileAtPos;
        }

        throw new IllegalStateException("Tile Entity is not correct! " + tileAtPos);
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity player, int index) {

        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if(slot != null && slot.getHasStack()) {
            ItemStack itemStack = slot.getStack();
            stack = itemStack.copy();
            if(index < this.nonPlayerSlotCount) {
                if(! this.mergeItemStack(itemStack, this.nonPlayerSlotCount, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            }
            else
                if(! this.mergeItemStack(itemStack, 0, this.nonPlayerSlotCount, false)) {
                    return ItemStack.EMPTY;
                }

            if(itemStack.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            }
            else {
                slot.onSlotChanged();
            }
        }

        return stack;
    }
}
