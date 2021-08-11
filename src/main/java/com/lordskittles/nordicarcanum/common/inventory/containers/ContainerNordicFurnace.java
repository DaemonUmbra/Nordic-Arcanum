package com.lordskittles.nordicarcanum.common.inventory.containers;

import com.lordskittles.arcanumapi.client.slot.SlotFuel;
import com.lordskittles.arcanumapi.common.inventory.containers.ContainerBase;
import com.lordskittles.nordicarcanum.common.registry.Containers;
import com.lordskittles.nordicarcanum.common.registry.RecipeType;
import com.lordskittles.nordicarcanum.common.blockentity.crafting.BlockEntityNordicFurnace;
import com.lordskittles.nordicarcanum.core.NordicInventorySlots;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Objects;

public class ContainerNordicFurnace extends ContainerBase<BlockEntityNordicFurnace> {

    private final ContainerData furnaceData;

    public ContainerNordicFurnace(final int windowId, final Inventory playerInventory, final BlockEntityNordicFurnace tile, final ContainerData furnaceData) {

        super(Containers.nordic_furnace.get(), NordicInventorySlots.NORDIC_FURNACE, windowId, playerInventory, tile);

        this.furnaceData = furnaceData;

        createInputSlot(blockEntity, 0, 11, 19);
        createInputSlot(blockEntity, 1, 37, 19);
        createFuelSlot(blockEntity, 2, 24, 59);
        createOutputSlot(blockEntity, 3, 121, 22);

        generateMainPlayerInventory(playerInventory, 8, 84);
        generatePlayerInventoryHotbar(playerInventory, 8, 142);

        this.addDataSlots(furnaceData);
    }

    public ContainerNordicFurnace(final int windowId, final Inventory playerInventory, final FriendlyByteBuf packetBuffer) {

        this(windowId, playerInventory, getTileEntity(playerInventory, packetBuffer), new SimpleContainerData(4));
    }

    private void createInputSlot(Container inventory, int index, int x, int y) {

        this.addSlot(new Slot(inventory, index, x, y));
    }

    private void createFuelSlot(Container inventory, int index, int x, int y) {

        this.addSlot(new SlotFuel(inventory, index, x, y, RecipeType.nordic_furnace));
    }

    private static BlockEntityNordicFurnace getTileEntity(final Inventory playerInventory, final FriendlyByteBuf packetBuffer) {

        Objects.requireNonNull(playerInventory, "PlayerInventory cannot be null");
        Objects.requireNonNull(packetBuffer, "Data cannot be null");

        final BlockEntity blockEntityAtPos = playerInventory.player.level.getBlockEntity(packetBuffer.readBlockPos());
        if(blockEntityAtPos instanceof BlockEntityNordicFurnace) {
            return (BlockEntityNordicFurnace) blockEntityAtPos;
        }

        throw new IllegalStateException("Tile Entity is not correct! " + blockEntityAtPos);
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

    @OnlyIn(Dist.CLIENT)
    public int getCookProgressionScaled() {

        int cookTime = this.furnaceData.get(2);
        int cookTimeTotal = this.furnaceData.get(3);
        return cookTimeTotal != 0 && cookTime != 0 ? cookTime * 24 / cookTimeTotal : 0;
    }

    @OnlyIn(Dist.CLIENT)
    public int getBurnLeftScaled() {

        int burnTime = this.furnaceData.get(0);
        int recipesUsed = this.furnaceData.get(1);

        if(recipesUsed == 0) {
            recipesUsed = 200;
        }

        return burnTime * 23 / recipesUsed;
    }

    @OnlyIn(Dist.CLIENT)
    public boolean isBurning() {

        return this.furnaceData.get(0) > 0;
    }
}
