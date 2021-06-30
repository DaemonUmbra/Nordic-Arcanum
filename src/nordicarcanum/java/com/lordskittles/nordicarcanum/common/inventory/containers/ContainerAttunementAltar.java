package com.lordskittles.nordicarcanum.common.inventory.containers;

import com.lordskittles.nordicarcanum.common.tileentity.magic.TileEntityAttunementAltar;
import com.lordskittles.nordicarcanum.common.registry.Blocks;
import com.lordskittles.nordicarcanum.common.registry.Containers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;

import java.util.Objects;

public class ContainerAttunementAltar extends Container
{
    private final TileEntityAttunementAltar Tile;
    private final IWorldPosCallable canInteract;

    public ContainerAttunementAltar(final int windowId, final PlayerInventory playerInventory, final TileEntityAttunementAltar tile)
    {
        super(Containers.attunement_altar.get(), windowId);

        this.Tile = tile;
        this.canInteract = IWorldPosCallable.of(Tile.getWorld(), Tile.getPos());
    }

    public ContainerAttunementAltar(final int windowId, final PlayerInventory playerInventory, final PacketBuffer packetBuffer)
    {
        this(windowId, playerInventory, getTileEntity(playerInventory, packetBuffer));
    }

    public TileEntityAttunementAltar getTile()
    {
        return this.Tile;
    }

    @Override
    public boolean canInteractWith(PlayerEntity player)
    {
        return isWithinUsableDistance(this.canInteract, player, Blocks.attunement_altar.get());
    }

    private static TileEntityAttunementAltar getTileEntity(final PlayerInventory playerInventory, final PacketBuffer packetBuffer)
    {
        Objects.requireNonNull(playerInventory, "PlayerInventory cannot be null");
        Objects.requireNonNull(packetBuffer, "Data cannot be null");

        final TileEntity tileAtPos = playerInventory.player.world.getTileEntity(packetBuffer.readBlockPos());
        if (tileAtPos instanceof TileEntityAttunementAltar)
        {
            return (TileEntityAttunementAltar)tileAtPos;
        }

        throw new IllegalStateException("Tile Entity is not correct! " + tileAtPos);
    }
}
