package com.lordskittles.arcanumapi.common.tileentity;

import com.lordskittles.arcanumapi.common.network.PacketBase;
import com.lordskittles.arcanumapi.common.network.PacketHandlerBase;
import com.lordskittles.arcanumapi.common.utilities.MagicUtilities;
import com.lordskittles.arcanumapi.arcanum.ArcanumServerManager;
import com.lordskittles.arcanumapi.arcanum.IArcanumUser;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public abstract class TileEntityMagicTank<T extends TileEntityMagicTank> extends TileEntityFluidInventory<TileEntityMagicTank> implements IArcanumUser {

    private final int capacity;
    private final float retainCost;
    private final PacketHandlerBase packetHandler;

    public TileEntityMagicTank(TileEntityType<?> tileEntityTypeIn, int capacity, float retainCost, PacketHandlerBase packetHandler) {

        super(tileEntityTypeIn);
        this.capacity = capacity;
        this.retainCost = retainCost;
        this.packetHandler = packetHandler;
    }

    @Override
    public float getRetainCost() {

        return this.retainCost;
    }

    @Override
    public boolean canRetainInventory(PlayerEntity player) {

        return getCurrentArcanum(player) > this.retainCost;
    }

    public float getCurrentArcanum(PlayerEntity player) {

        return MagicUtilities.getCurrentArcanum(player);
    }

    @Override
    public int getCapacity() {

        return this.capacity;
    }

    @Override
    public void useArcanum(PlayerEntity player, float cost) {

        if(player instanceof ServerPlayerEntity) {
            try {
                ArcanumServerManager.useArcanum((ServerPlayerEntity) player, cost, this, packetHandler);
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void sendPacket(PacketBase packet, TileEntity tracking) {

        packetHandler.sendToAllTracking(packet, tracking);
    }
}
