package com.lordskittles.nordicarcanum.common.inventory.containers;

import com.lordskittles.nordicarcanum.common.registry.Containers;
import com.lordskittles.nordicarcanum.common.blockentity.magic.BlockEntityAttunementAltar;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.Objects;

public class ContainerAttunementAltar extends AbstractContainerMenu {

    private final BlockEntityAttunementAltar entity;

    public ContainerAttunementAltar(final int windowId, final Inventory playerInventory, final BlockEntityAttunementAltar entity) {

        super(Containers.attunement_altar.get(), windowId);

        this.entity = entity;
    }

    public ContainerAttunementAltar(final int windowId, final Inventory playerInventory, final FriendlyByteBuf packetBuffer) {

        this(windowId, playerInventory, getBlockEntity(playerInventory, packetBuffer));
    }

    public BlockEntityAttunementAltar getEntity() {

        return this.entity;
    }

    @Override
    public boolean stillValid(Player player) {

        return entity.stillValid(player);
    }

    private static BlockEntityAttunementAltar getBlockEntity(final Inventory playerInventory, final FriendlyByteBuf packetBuffer) {

        Objects.requireNonNull(playerInventory, "PlayerInventory cannot be null");
        Objects.requireNonNull(packetBuffer, "Data cannot be null");

        final BlockEntity blockEntityAtPos = playerInventory.player.level.getBlockEntity(packetBuffer.readBlockPos());
        if(blockEntityAtPos instanceof BlockEntityAttunementAltar) {
            return (BlockEntityAttunementAltar) blockEntityAtPos;
        }

        throw new IllegalStateException("Tile Entity is not correct! " + blockEntityAtPos);
    }
}
