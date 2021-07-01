package com.lordskittles.arcanumapi.common.network;

import com.lordskittles.arcanumapi.common.utilities.ClientUtilities;
import com.lordskittles.arcanumapi.core.ArcanumAPI;
import com.lordskittles.arcanumapi.magic.IArcanumTile;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent.Context;

import java.util.function.Supplier;

public class PacketTileArcanumUpdate extends PacketBase {

    private final BlockPos pos;
    private final float current;
    private final float maximum;

    public PacketTileArcanumUpdate(BlockPos pos, float current, float maximum) {

        this.pos = pos;
        this.current = current;
        this.maximum = maximum;
    }

    public static void handle(PacketTileArcanumUpdate message, Supplier<Context> context) {

        context.get().enqueueWork(() ->
        {
            TileEntity tile = ClientUtilities.getTileEntity(TileEntity.class, message.pos);
            if(tile == null) {
                ArcanumAPI.LOG.info("Update arcanum packet received for position: {} in world, but no valid tile was found.", message.pos);
            }
            else {
                if(tile instanceof IArcanumTile) {
                    IArcanumTile user = (IArcanumTile) tile;
                    user.updateArcanum(message.current, message.maximum);
                }
            }
        });

        context.get().setPacketHandled(true);
    }

    public static void encode(PacketTileArcanumUpdate packet, PacketBuffer buffer) {

        buffer.writeBlockPos(packet.pos);
        buffer.writeFloat(packet.current);
        buffer.writeFloat(packet.maximum);
    }

    public static PacketTileArcanumUpdate decode(PacketBuffer buffer) {

        return new PacketTileArcanumUpdate(buffer.readBlockPos(), buffer.readFloat(), buffer.readFloat());
    }
}
