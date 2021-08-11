package com.lordskittles.arcanumapi.common.blockentity;

import com.lordskittles.arcanumapi.common.network.PacketBase;
import com.lordskittles.arcanumapi.common.network.PacketHandlerBase;
import com.lordskittles.arcanumapi.common.utilities.MagicUtilities;
import com.lordskittles.arcanumapi.arcanum.ArcanumServerManager;
import com.lordskittles.arcanumapi.arcanum.IArcanumUser;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class BlockEntityMagicTank<T extends BlockEntityMagicTank> extends BlockEntityFluidInventory<BlockEntityMagicTank> implements IArcanumUser {

    private final int capacity;
    private final float retainCost;
    private final PacketHandlerBase packetHandler;

    public BlockEntityMagicTank(BlockEntityType<?> blockEntityTypeIn, BlockPos pos, BlockState state, int capacity, float retainCost, PacketHandlerBase packetHandler) {

        super(blockEntityTypeIn, pos, state);
        this.capacity = capacity;
        this.retainCost = retainCost;
        this.packetHandler = packetHandler;
    }

    @Override
    public float getRetainCost() {

        return this.retainCost;
    }

    @Override
    public boolean canRetainInventory(Player player) {

        return getCurrentArcanum(player) > this.retainCost;
    }

    public float getCurrentArcanum(Player player) {

        return MagicUtilities.getCurrentArcanum(player);
    }

    @Override
    public int getCapacity() {

        return this.capacity;
    }

    @Override
    public void useArcanum(Player player, float cost) {

        if(player instanceof ServerPlayer) {
            try {
                ArcanumServerManager.useArcanum((ServerPlayer) player, cost, this, packetHandler);
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void sendPacket(PacketBase packet, BlockEntity tracking) {

        packetHandler.sendToAllTracking(packet, tracking);
    }
}
