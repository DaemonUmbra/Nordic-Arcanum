package com.lordskittles.arcanumapi.arcanum;

import com.lordskittles.arcanumapi.common.network.PacketHandlerBase;
import com.lordskittles.arcanumapi.common.network.PacketPlayerArcanumUpdate;
import com.lordskittles.arcanumapi.common.network.PacketTileArcanumUpdate;
import com.lordskittles.arcanumapi.common.utilities.MagicUtilities;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;

public class ArcanumServerManager {

    public static void useArcanum(ServerPlayer player, float arcanum, BlockEntity block, PacketHandlerBase packetHandler) throws Exception {

        if(player.level.isClientSide)
            throw new Exception("Cannot use arcanum on the client...");

        MagicUtilities.useArcanum(player, arcanum);
        float current = MagicUtilities.getCurrentArcanum(player);
        float maximum = MagicUtilities.getMaximumArcanum(player);

        packetHandler.sendToAllTracking(new PacketTileArcanumUpdate(block.getBlockPos(), current, maximum), block);
    }

    public static void useArcanum(ServerPlayer player, float arcanum, PacketHandlerBase packetHandler) throws Exception {

        if(player.level.isClientSide)
            throw new Exception("Cannot use arcanum on the client...");

        MagicUtilities.useArcanum(player, arcanum);
        float current = MagicUtilities.getCurrentArcanum(player);
        float maximum = MagicUtilities.getMaximumArcanum(player);

        packetHandler.sendToAllTracking(new PacketPlayerArcanumUpdate(current, maximum), player);
    }

    public static void updateArcanum(ServerPlayer player, BlockEntity block, PacketHandlerBase packetHandler) throws Exception {

        if(player.level.isClientSide)
            throw new Exception("Cannot update arcanum on the client...");

        float current = MagicUtilities.getCurrentArcanum(player);
        float maximum = MagicUtilities.getMaximumArcanum(player);

        packetHandler.sendToAllTracking(new PacketTileArcanumUpdate(block.getBlockPos(), current, maximum), block);
    }

    public static void replenishArcanum(ServerPlayer player, float arcanum, PacketHandlerBase packetHandler) throws Exception {

        if(player.level.isClientSide)
            throw new Exception("Cannot replenish arcanum on the client...");

        MagicUtilities.replenishArcanum(player, arcanum);

        float current = MagicUtilities.getCurrentArcanum(player);
        float maximum = MagicUtilities.getMaximumArcanum(player);

        packetHandler.sendToAllTracking(new PacketPlayerArcanumUpdate(current, maximum), player);
    }
}
