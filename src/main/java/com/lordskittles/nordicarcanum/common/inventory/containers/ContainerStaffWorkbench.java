package com.lordskittles.nordicarcanum.common.inventory.containers;

import com.lordskittles.arcanumapi.common.inventory.containers.ContainerBase;
import com.lordskittles.nordicarcanum.client.gui.slot.SlotRegistered;
import com.lordskittles.nordicarcanum.common.RegistryType;
import com.lordskittles.nordicarcanum.common.registry.Containers;
import com.lordskittles.nordicarcanum.common.blockentity.crafting.BlockEntityStaffWorkbench;
import com.lordskittles.nordicarcanum.core.NordicInventorySlots;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.Objects;

public class ContainerStaffWorkbench extends ContainerBase<BlockEntityStaffWorkbench> {

    public ContainerStaffWorkbench(final int windowId, final Inventory playerInventory, final BlockEntityStaffWorkbench tile) {

        super(Containers.staff_workbench.get(), NordicInventorySlots.STAFF_WORKBENCH, windowId, playerInventory, tile);

        createTypeSlot(blockEntity, RegistryType.ROD, 0, 20, 55);
        createTypeSlot(blockEntity, RegistryType.CORE, 1, 54, 40);
        createTypeSlot(blockEntity, RegistryType.BINDING, 2, 85, 25);
        createTypeSlot(blockEntity, RegistryType.CRYSTAL, 3, 111, 12);
        createOutputSlot(blockEntity, 4, 136, 46);

        generateMainPlayerInventory(playerInventory, 8, 85);
        generatePlayerInventoryHotbar(playerInventory, 8, 143);
    }

    public ContainerStaffWorkbench(final int windowId, final Inventory playerInventory, final FriendlyByteBuf packetBuffer) {

        this(windowId, playerInventory, getTileEntity(playerInventory, packetBuffer));
    }

    protected void createTypeSlot(Container inventory, RegistryType type, int index, int xPos, int yPos) {

        this.addSlot(new SlotRegistered(type, inventory, index, xPos, yPos));
    }

    private static BlockEntityStaffWorkbench getTileEntity(final Inventory playerInventory, final FriendlyByteBuf packetBuffer) {

        Objects.requireNonNull(playerInventory, "PlayerInventory cannot be null");
        Objects.requireNonNull(packetBuffer, "Data cannot be null");

        final BlockEntity tileAtPos = playerInventory.player.level.getBlockEntity(packetBuffer.readBlockPos());
        if(tileAtPos instanceof BlockEntityStaffWorkbench) {
            return (BlockEntityStaffWorkbench) tileAtPos;
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
