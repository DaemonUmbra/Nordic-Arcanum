package com.lordskittles.arcanumapi.common.tileentity;

import com.lordskittles.arcanumapi.common.network.PacketHandlerBase;
import com.lordskittles.arcanumapi.common.utilities.MagicUtilities;
import com.lordskittles.arcanumapi.arcanum.ArcanumServerManager;
import com.lordskittles.arcanumapi.arcanum.IArcanumUser;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class TileEntityInventoryArcanum<T extends TileEntityInventoryArcanum<T>> extends TileEntityInventorySyncable<T> implements IArcanumUser {

    private final PacketHandlerBase packetHandler;

    public TileEntityInventoryArcanum(BlockEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState state, int size, String containerId, String modid, PacketHandlerBase packetHandler) {

        super(tileEntityTypeIn, pos, state, size, containerId, modid);
        this.packetHandler = packetHandler;
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

    public float getCurrentArcanum(Player player) {

        return MagicUtilities.getCurrentArcanum(player);
    }
}
