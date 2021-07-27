package com.lordskittles.nordicarcanum.common.inventory.containers;

import com.lordskittles.arcanumapi.common.inventory.containers.ContainerBase;
import com.lordskittles.nordicarcanum.common.inventory.ClothInventory;
import com.lordskittles.nordicarcanum.common.inventory.ClothResultInventory;
import com.lordskittles.nordicarcanum.common.inventory.crafting.CraftingClothRecipe;
import com.lordskittles.nordicarcanum.common.registry.Containers;
import com.lordskittles.nordicarcanum.common.blockentity.crafting.BlockEntityCraftingCloth;
import com.lordskittles.nordicarcanum.common.utility.RecipeUtilities;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.Objects;
import java.util.Optional;

public class ContainerCraftingCloth extends ContainerBase<BlockEntityCraftingCloth> {

    private final BlockEntityCraftingCloth Tile;
    private final ClothInventory craftMatrix = new ClothInventory(this);
    private final ClothResultInventory craftResult;
    private final ContainerLevelAccess canInteract;
    private final Player player;

    public ContainerCraftingCloth(int windowID, Inventory playerInventory, BlockEntityCraftingCloth tile) {

        super(Containers.crafting_cloth.get(), 9, windowID, playerInventory, tile);

        this.Tile = tile;
        this.canInteract = ContainerLevelAccess.create(Tile.getLevel(), Tile.getBlockPos());
        this.player = playerInventory.player;
        this.Tile.onOpened(this.player);
        this.craftResult = new ClothResultInventory(this.Tile);

        this.addSlot(new ClothResultSlot(this.player, this.craftMatrix, this.craftResult, 0, 129, 51));

        for(int y = 0; y < 3; ++ y) {
            for(int x = 0; x < 3; ++ x) {
                this.addSlot(new Slot(this.craftMatrix, x + y * 3, 20 + x * 20, 20 + y * 20));
            }
        }

        for(int y = 0; y < 3; ++ y) {
            for(int x = 0; x < 9; ++ x) {
                this.addSlot(new Slot(playerInventory, x + y * 9 + 9, 8 + x * 18, 108 + y * 18));
            }
        }

        for(int x = 0; x < 9; ++ x) {
            this.addSlot(new Slot(playerInventory, x, 8 + x * 18, 166));
        }
    }

    public ContainerCraftingCloth(final int windowId, final Inventory playerInventory, final FriendlyByteBuf packetBuffer) {

        this(windowId, playerInventory, getTileEntity(playerInventory, packetBuffer));
    }

    private static BlockEntityCraftingCloth getTileEntity(final Inventory playerInventory, final FriendlyByteBuf packetBuffer) {

        Objects.requireNonNull(playerInventory, "PlayerInventory cannot be null");
        Objects.requireNonNull(packetBuffer, "Data cannot be null");

        final BlockEntity tileAtPos = playerInventory.player.level.getBlockEntity(packetBuffer.readBlockPos());
        if(tileAtPos instanceof BlockEntityCraftingCloth) {
            return (BlockEntityCraftingCloth) tileAtPos;
        }

        throw new IllegalStateException("Tile Entity is not correct! " + tileAtPos);
    }

    protected void updateCraftingGrid(int windowId, Level world, Player player, CraftingContainer craftingInventory, ClothResultInventory craftingResult) {

        if(! world.isClientSide) {
            ServerPlayer serverplayerentity = (ServerPlayer) player;
            ItemStack itemstack = ItemStack.EMPTY;
            Optional<CraftingRecipe> optional = world.getServer().getRecipeManager().getRecipeFor(RecipeType.CRAFTING, craftingInventory, world);
            if(optional.isPresent()) {
                CraftingRecipe icraftingrecipe = optional.get();
                if(craftingResult.setRecipeUsed(world, serverplayerentity, icraftingrecipe)) {
                    itemstack = icraftingrecipe.getResultItem();
                }
            }
            else {
                CraftingClothRecipe recipe = RecipeUtilities.getClothRecipeFor(world, craftingInventory, player);
                if(recipe != null) {
                    if(craftingResult.setRecipeUsed(world, serverplayerentity, recipe)) {
                        itemstack = recipe.assemble(null);
                    }
                }
            }

            craftingResult.setItem(0, itemstack);

            serverplayerentity.connection.send(new ClientboundContainerSetSlotPacket(windowId, getStateId(), 0, itemstack));
        }
    }

    /**
     * Callback for when the crafting matrix is changed.
     */
    public void onCraftMatrixChanged(Inventory inventory) {

        this.canInteract.execute((world, pos) ->
        {
            updateCraftingGrid(this.containerId, world, this.player, this.craftMatrix, this.craftResult);
        });
    }

    public float getCurrentArcanum() {

        return this.Tile.getCurrentArcanum();
    }

    public float getMaximumArcanum() {

        return this.Tile.getMaximumArcanum();
    }

    public void removed(Player player) {

        super.removed(player);
        this.canInteract.execute((world, p_217068_3_) ->
        {
            this.clearContainer(player, this.craftMatrix);
        });
    }

    @Override
    public boolean stillValid(Player player) {

        return Tile.stillValid(player);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {

        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if(slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if(index == 0) {
                this.canInteract.execute((world, p_217067_3_) ->
                {
                    itemstack1.getItem().onCraftedBy(itemstack1, world, player);
                });
                if(! this.moveItemStackTo(itemstack1, 10, 46, true)) {
                    return ItemStack.EMPTY;
                }

                //slot.onSlotChange(itemstack1, itemstack);
            }
            else
                if(index >= 10 && index < 46) {
                    if(! this.moveItemStackTo(itemstack1, 1, 10, false)) {
                        if(index < 37) {
                            if(! this.moveItemStackTo(itemstack1, 37, 46, false)) {
                                return ItemStack.EMPTY;
                            }
                        }
                        else
                            if(! this.moveItemStackTo(itemstack1, 10, 37, false)) {
                                return ItemStack.EMPTY;
                            }
                    }
                }
                else
                    if(! this.moveItemStackTo(itemstack1, 10, 46, false)) {
                        return ItemStack.EMPTY;
                    }

            if(itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            }
            else {
                slot.setChanged();
            }

            if(itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemstack1);
            if(index == 0) {
                player.drop(itemstack1, false);
            }
        }

        return itemstack;
    }
}
