package com.lordskittles.arcanumapi.common.network;

import com.lordskittles.arcanumapi.common.tileentity.TileEntityUpdateable;
import com.lordskittles.arcanumapi.common.utilities.ClientUtilities;
import com.lordskittles.arcanumapi.core.ArcanumAPI;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraftforge.fmllegacy.network.NetworkEvent.Context;

import java.util.function.Supplier;

public class PacketUpdateTile extends PacketBase {

    private final CompoundTag updateTag;
    private final BlockPos pos;

    public PacketUpdateTile(TileEntityUpdateable tile) {

        this(tile.getBlockPos(), tile.getReducedUpdateTag());
    }

    private PacketUpdateTile(BlockPos pos, CompoundTag updateTag) {

        this.pos = pos;
        this.updateTag = updateTag;
    }

    public static void handle(PacketUpdateTile message, Supplier<Context> context) {

        context.get().enqueueWork(() ->
        {
            TileEntityUpdateable tile = ClientUtilities.getTileEntity(TileEntityUpdateable.class, message.pos);
            if(tile == null) {
                ArcanumAPI.LOG.info("Update tile packet received for position: {} in world, but no valid tile was found.", message.pos);
            }
            else {
                tile.handleUpdateTag(message.updateTag);
            }
        });

        context.get().setPacketHandled(true);
    }

    public static void encode(PacketUpdateTile packet, FriendlyByteBuf buffer) {

        buffer.writeBlockPos(packet.pos);
        buffer.writeNbt(packet.updateTag);
    }

    public static PacketUpdateTile decode(FriendlyByteBuf buffer) {

        return new PacketUpdateTile(buffer.readBlockPos(), buffer.readNbt());
    }
}
