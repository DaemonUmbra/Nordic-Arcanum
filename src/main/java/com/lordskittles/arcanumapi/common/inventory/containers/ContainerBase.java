package com.lordskittles.arcanumapi.common.inventory.containers;

import com.lordskittles.arcanumapi.client.slot.SlotOutput;
import com.lordskittles.arcanumapi.common.tileentity.TileEntityInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IWorldPosCallable;

public abstract class ContainerBase<T extends TileEntityInventory> extends Container implements INordicContainer
{
    public final T Tile;
    protected final IWorldPosCallable canInteract;

    protected int nonPlayerSlotCount = 0;

    public ContainerBase(ContainerType<?> container, final int nonPlayerSlotCount, final int windowId, final PlayerInventory inventory, final T tile)
    {
        super(container, windowId);

        this.Tile = tile;
        this.canInteract = IWorldPosCallable.of(Tile.getWorld(), Tile.getPos());
        this.nonPlayerSlotCount = nonPlayerSlotCount;
    }

    public IInventory getTileInventory()
    {
        return Tile;
    }

    @Override
    public boolean canInteractWith(PlayerEntity player)
    {
        return true;
    }

    protected void createSlot(IInventory inventory, int index, int xPos, int yPos)
    {
        this.addSlot(new Slot(inventory, index, xPos, yPos));
    }

    protected void createOutputSlot(IInventory inventory, int index, int xPos, int yPos)
    {
        this.addSlot(new SlotOutput(inventory, index, xPos, yPos));
    }

    protected int generateMainPlayerInventory(IInventory inventory, int startX, int startY)
    {
        int startIndex = 9;
        int index = startIndex;
        int rows = 3;
        int cols = 9;
        int interval = 18;

        for (int row = 0; row < rows; row++)
        {
            for (int col = 0; col < cols; col++)
            {
                index = startIndex + ((row * cols) + col);
                createSlot(inventory, index, startX + (col * interval), startY + (row * interval));
            }
        }

        return index;
    }

    protected int generatePlayerInventoryHotbar(IInventory inventory, int startX, int startY)
    {
        int index = 0;
        int cols = 9;
        int interval = 18;

        for (int col = 0; col < cols; col++)
        {
            index = col;
            createSlot(inventory, index, startX + (col * interval), startY);
        }

        return index;
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
