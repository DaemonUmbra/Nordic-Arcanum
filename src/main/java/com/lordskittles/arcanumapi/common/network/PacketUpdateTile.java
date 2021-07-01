package com.lordskittles.arcanumapi.common.network;

import com.lordskittles.arcanumapi.common.tileentity.TileEntityUpdateable;
import com.lordskittles.arcanumapi.common.utilities.ClientUtilities;
import com.lordskittles.arcanumapi.core.ArcanumAPI;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent.Context;

import java.util.function.Supplier;

public class PacketUpdateTile extends PacketBase {

    private final CompoundNBT updateTag;
    private final BlockPos pos;

    public PacketUpdateTile(TileEntityUpdateable tile) {

        this(tile.getPos(), tile.getReducedUpdateTag());
    }

    private PacketUpdateTile(BlockPos pos, CompoundNBT updateTag) {

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
                tile.handleUpdateTag(tile.getBlockState(), message.updateTag);
            }
        });

        context.get().setPacketHandled(true);
    }

    public static void encode(PacketUpdateTile packet, PacketBuffer buffer) {

        buffer.writeBlockPos(packet.pos);
        buffer.writeCompoundTag(packet.updateTag);
    }

    public static PacketUpdateTile decode(PacketBuffer buffer) {

        return new PacketUpdateTile(buffer.readBlockPos(), buffer.readCompoundTag());
    }
}
