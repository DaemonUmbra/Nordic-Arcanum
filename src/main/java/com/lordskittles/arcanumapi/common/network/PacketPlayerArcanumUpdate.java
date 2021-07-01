package com.lordskittles.arcanumapi.common.network;

import com.lordskittles.arcanumapi.common.utilities.ClientUtilities;
import com.lordskittles.arcanumapi.common.utilities.MagicUtilities;
import com.lordskittles.arcanumapi.core.ArcanumAPI;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent.Context;

import java.util.function.Supplier;

public class PacketPlayerArcanumUpdate extends PacketBase {

    private final float current;
    private final float maximum;

    public PacketPlayerArcanumUpdate(float current, float maximum) {

        this.current = current;
        this.maximum = maximum;
    }

    public static void handle(PacketPlayerArcanumUpdate message, Supplier<Context> context) {

        context.get().enqueueWork(() ->
        {
            PlayerEntity player = ClientUtilities.getPlayer(context);
            if(player == null) {
                ArcanumAPI.LOG.info("Update arcanum packet received for player in world, but no valid player was found... This should NEVER happen");
            }
            else {
                MagicUtilities.setArcanum(player, message.current, message.maximum);
            }
        });

        context.get().setPacketHandled(true);
    }

    public static void encode(PacketPlayerArcanumUpdate packet, PacketBuffer buffer) {

        buffer.writeFloat(packet.current);
        buffer.writeFloat(packet.maximum);
    }

    public static PacketPlayerArcanumUpdate decode(PacketBuffer buffer) {

        return new PacketPlayerArcanumUpdate(buffer.readFloat(), buffer.readFloat());
    }
}
