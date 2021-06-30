package com.lordskittles.nordicarcanum.common.inventory.containers;

import com.lordskittles.arcanumapi.client.slot.SlotFuel;
import com.lordskittles.arcanumapi.common.inventory.containers.ContainerBase;
import com.lordskittles.nordicarcanum.common.registry.Containers;
import com.lordskittles.nordicarcanum.core.NordicInventorySlots;
import com.lordskittles.nordicarcanum.common.tileentity.TileEntityNordicFurnace;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Objects;

public class ContainerNordicFurnace extends ContainerBase<TileEntityNordicFurnace>
{
    private final IIntArray furnaceData;

    public ContainerNordicFurnace(final int windowId, final PlayerInventory playerInventory, final TileEntityNordicFurnace tile, final IIntArray furnaceData)
    {
        super(Containers.nordic_furnace.get(), NordicInventorySlots.NORDIC_FURNACE, windowId, playerInventory, tile);

        this.furnaceData = furnaceData;

        createInputSlot(Tile, 0,  25, 17);
        createInputSlot(Tile, 1,  49, 17);
        createFuelSlot(Tile, 2,  37, 52);
        createOutputSlot(Tile, 3, 135, 35);

        generateMainPlayerInventory(playerInventory, 8, 84);
        generatePlayerInventoryHotbar(playerInventory, 8, 142);

        this.trackIntArray(furnaceData);
    }

    public ContainerNordicFurnace(final int windowId, final PlayerInventory playerInventory, final PacketBuffer packetBuffer)
    {
        this(windowId, playerInventory, getTileEntity(playerInventory, packetBuffer), new IntArray(4));
    }

    private void createInputSlot(IInventory inventory, int index, int x, int y)
    {
        this.addSlot(new Slot(inventory, index, x, y));
    }

    private void createFuelSlot(IInventory inventory, int index, int x, int y)
    {
        this.addSlot(new SlotFuel(inventory, index, x, y));
    }

    private static TileEntityNordicFurnace getTileEntity(final PlayerInventory playerInventory, final PacketBuffer packetBuffer)
    {
        Objects.requireNonNull(playerInventory, "PlayerInventory cannot be null");
        Objects.requireNonNull(packetBuffer, "Data cannot be null");

        final TileEntity tileAtPos = playerInventory.player.world.getTileEntity(packetBuffer.readBlockPos());
        if (tileAtPos instanceof TileEntityNordicFurnace)
        {
            return (TileEntityNordicFurnace)tileAtPos;
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

    @OnlyIn(Dist.CLIENT)
    public int getCookProgressionScaled()
    {
        int cookTime = this.furnaceData.get(2);
        int cookTimeTotal = this.furnaceData.get(3);
        return cookTimeTotal != 0 && cookTime != 0 ? cookTime * 24 / cookTimeTotal : 0;
    }

    @OnlyIn(Dist.CLIENT)
    public int getBurnLeftScaled()
    {
        int burnTime = this.furnaceData.get(0);
        int recipesUsed = this.furnaceData.get(1);

        if (recipesUsed == 0)
        {
            recipesUsed = 200;
        }

        return burnTime * 13 / recipesUsed;
    }

    @OnlyIn(Dist.CLIENT)
    public boolean isBurning()
    {
        return this.furnaceData.get(0) > 0;
    }
}
