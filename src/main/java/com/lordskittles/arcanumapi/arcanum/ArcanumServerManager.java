package com.lordskittles.arcanumapi.arcanum;

import com.lordskittles.arcanumapi.common.network.PacketHandlerBase;
import com.lordskittles.arcanumapi.common.network.PacketPlayerArcanumUpdate;
import com.lordskittles.arcanumapi.common.network.PacketTileArcanumUpdate;
import com.lordskittles.arcanumapi.common.utilities.MagicUtilities;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.tileentity.TileEntity;

public class ArcanumServerManager {

    public static void useArcanum(ServerPlayerEntity player, float arcanum, TileEntity tile, PacketHandlerBase packetHandler) throws Exception {

        if(player.world.isRemote)
            throw new Exception("Cannot use arcanum on the client...");

        MagicUtilities.useArcanum(player, arcanum);
        float current = MagicUtilities.getCurrentArcanum(player);
        float maximum = MagicUtilities.getMaximumArcanum(player);

        packetHandler.sendToAllTracking(new PacketTileArcanumUpdate(tile.getPos(), current, maximum), tile);
    }

    public static void useArcanum(ServerPlayerEntity player, float arcanum, PacketHandlerBase packetHandler) throws Exception {

        if(player.world.isRemote)
            throw new Exception("Cannot use arcanum on the client...");

        MagicUtilities.useArcanum(player, arcanum);
        float current = MagicUtilities.getCurrentArcanum(player);
        float maximum = MagicUtilities.getMaximumArcanum(player);

        packetHandler.sendToAllTracking(new PacketPlayerArcanumUpdate(current, maximum), player);
    }

    public static void updateArcanum(ServerPlayerEntity player, TileEntity tile, PacketHandlerBase packetHandler) throws Exception {

        if(player.world.isRemote)
            throw new Exception("Cannot update arcanum on the client...");

        float current = MagicUtilities.getCurrentArcanum(player);
        float maximum = MagicUtilities.getMaximumArcanum(player);

        packetHandler.sendToAllTracking(new PacketTileArcanumUpdate(tile.getPos(), current, maximum), tile);
    }

    public static void replenishArcanum(ServerPlayerEntity player, float arcanum, PacketHandlerBase packetHandler) throws Exception {

        if(player.world.isRemote)
            throw new Exception("Cannot replenish arcanum on the client...");

        MagicUtilities.replenishArcanum(player, arcanum);

        float current = MagicUtilities.getCurrentArcanum(player);
        float maximum = MagicUtilities.getMaximumArcanum(player);

        packetHandler.sendToAllTracking(new PacketPlayerArcanumUpdate(current, maximum), player);
    }
}
