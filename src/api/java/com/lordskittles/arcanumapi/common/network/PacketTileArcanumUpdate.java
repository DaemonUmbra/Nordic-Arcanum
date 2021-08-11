package com.lordskittles.arcanumapi.common.network;

import com.lordskittles.arcanumapi.common.utilities.ClientUtilities;
import com.lordskittles.arcanumapi.core.ArcanumAPI;
import com.lordskittles.arcanumapi.arcanum.IArcanumTile;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraftforge.fmllegacy.network.NetworkEvent.Context;

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
            BlockEntity tile = ClientUtilities.getTileEntity(BlockEntity.class, message.pos);
            if(tile == null) {
                ArcanumAPI.LOG.info("Update arcanum packet received for position: {} in level, but no valid tile was found.", message.pos);
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

    public static void encode(PacketTileArcanumUpdate packet, FriendlyByteBuf buffer) {

        buffer.writeBlockPos(packet.pos);
        buffer.writeFloat(packet.current);
        buffer.writeFloat(packet.maximum);
    }

    public static PacketTileArcanumUpdate decode(FriendlyByteBuf buffer) {

        return new PacketTileArcanumUpdate(buffer.readBlockPos(), buffer.readFloat(), buffer.readFloat());
    }
}
