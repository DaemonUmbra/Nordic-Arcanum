package com.lordskittles.nordicarcanum.common.inventory.containers;

import com.lordskittles.arcanumapi.common.inventory.containers.ContainerBase;
import com.lordskittles.nordicarcanum.common.inventory.ClothInventory;
import com.lordskittles.nordicarcanum.common.inventory.ClothResultInventory;
import com.lordskittles.nordicarcanum.common.registry.Blocks;
import com.lordskittles.nordicarcanum.common.registry.Containers;
import com.lordskittles.nordicarcanum.common.inventory.crafting.CraftingClothRecipe;
import com.lordskittles.nordicarcanum.common.tileentity.TileEntityCraftingCloth;
import com.lordskittles.nordicarcanum.common.utility.RecipeUtilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ICraftingRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SSetSlotPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.world.World;

import java.util.Objects;
import java.util.Optional;

public class ContainerCraftingCloth extends ContainerBase<TileEntityCraftingCloth>
{
    private final TileEntityCraftingCloth Tile;
    private final ClothInventory craftMatrix = new ClothInventory(this);
    private final ClothResultInventory craftResult;
    private final IWorldPosCallable canInteract;
    private final PlayerEntity player;

    public ContainerCraftingCloth(int windowID, PlayerInventory playerInventory, TileEntityCraftingCloth tile)
    {
        super(Containers.crafting_cloth.get(), 9, windowID, playerInventory, tile);

        this.Tile = tile;
        this.canInteract = IWorldPosCallable.of(Tile.getWorld(), Tile.getPos());
        this.player = playerInventory.player;
        this.Tile.onOpened(this.player);
        this.craftResult = new ClothResultInventory(this.Tile);

        this.addSlot(new ClothResultSlot(this.player, this.craftMatrix, this.craftResult, 0, 129, 51));

        for(int y = 0; y < 3; ++y)
        {
            for(int x = 0; x < 3; ++x)
            {
                this.addSlot(new Slot(this.craftMatrix, x + y * 3, 20 + x * 20, 20 + y * 20));
            }
        }

        for(int y = 0; y < 3; ++y)
        {
            for(int x = 0; x < 9; ++x)
            {
                this.addSlot(new Slot(playerInventory, x + y * 9 + 9, 8 + x * 18, 108 + y * 18));
            }
        }

        for(int x = 0; x < 9; ++x)
        {
            this.addSlot(new Slot(playerInventory, x, 8 + x * 18, 166));
        }
    }

    public ContainerCraftingCloth(final int windowId, final PlayerInventory playerInventory, final PacketBuffer packetBuffer)
    {
        this(windowId, playerInventory, getTileEntity(playerInventory, packetBuffer));
    }

    private static TileEntityCraftingCloth getTileEntity(final PlayerInventory playerInventory, final PacketBuffer packetBuffer)
    {
        Objects.requireNonNull(playerInventory, "PlayerInventory cannot be null");
        Objects.requireNonNull(packetBuffer, "Data cannot be null");

        final TileEntity tileAtPos = playerInventory.player.world.getTileEntity(packetBuffer.readBlockPos());
        if (tileAtPos instanceof TileEntityCraftingCloth)
        {
            return (TileEntityCraftingCloth)tileAtPos;
        }

        throw new IllegalStateException("Tile Entity is not correct! " + tileAtPos);
    }

    protected void updateCraftingGrid(int windowId, World world, PlayerEntity player, CraftingInventory craftingInventory, ClothResultInventory craftingResult)
    {
        if (!world.isRemote)
        {
            ServerPlayerEntity serverplayerentity = (ServerPlayerEntity)player;
            ItemStack itemstack = ItemStack.EMPTY;
            Optional<ICraftingRecipe> optional = world.getServer().getRecipeManager().getRecipe(IRecipeType.CRAFTING, craftingInventory, world);
            if (optional.isPresent())
            {
                ICraftingRecipe icraftingrecipe = optional.get();
                if (craftingResult.canUseRecipe(world, serverplayerentity, icraftingrecipe))
                {
                    itemstack = icraftingrecipe.getCraftingResult(craftingInventory);
                }
            }
            else
            {
                CraftingClothRecipe recipe = RecipeUtilities.getClothRecipeFor(world, craftingInventory, player);
                if (recipe != null)
                {
                    if (craftingResult.canUseRecipe(world, serverplayerentity, recipe))
                    {
                        itemstack = recipe.getCraftingResult(null);
                    }
                }
            }

            craftingResult.setInventorySlotContents(0, itemstack);

            serverplayerentity.connection.sendPacket(new SSetSlotPacket(windowId, 0, itemstack));
        }
    }

    /**
     * Callback for when the crafting matrix is changed.
     */
    public void onCraftMatrixChanged(IInventory inventory)
    {
        this.canInteract.consume((world, pos) ->
        {
            updateCraftingGrid(this.windowId, world, this.player, this.craftMatrix, this.craftResult);
        });
    }

    public float getCurrentArcanum()
    {
        return this.Tile.getCurrentArcanum();
    }

    public float getMaximumArcanum()
    {
        return this.Tile.getMaximumArcanum();
    }

    public void onContainerClosed(PlayerEntity player)
    {
        super.onContainerClosed(player);
        this.canInteract.consume((world, p_217068_3_) ->
        {
            this.clearContainer(player, world, this.craftMatrix);
        });
    }

    @Override
    public boolean canInteractWith(PlayerEntity player)
    {
        return isWithinUsableDistance(this.canInteract, player, Blocks.crafting_cloth.get());
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity player, int index)
    {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index == 0)
            {
                this.canInteract.consume((world, p_217067_3_) ->
                {
                    itemstack1.getItem().onCreated(itemstack1, world, player);
                });
                if (!this.mergeItemStack(itemstack1, 10, 46, true))
                {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (index >= 10 && index < 46)
            {
                if (!this.mergeItemStack(itemstack1, 1, 10, false))
                {
                    if (index < 37)
                    {
                        if (!this.mergeItemStack(itemstack1, 37, 46, false))
                        {
                            return ItemStack.EMPTY;
                        }
                    }
                    else if (!this.mergeItemStack(itemstack1, 10, 37, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
            }
            else if (!this.mergeItemStack(itemstack1, 10, 46, false))
            {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount())
            {
                return ItemStack.EMPTY;
            }

            ItemStack itemstack2 = slot.onTake(player, itemstack1);
            if (index == 0)
            {
                player.dropItem(itemstack2, false);
            }
        }

        this.onCraftMatrixChanged(this.craftMatrix);
        return itemstack;
    }
}
