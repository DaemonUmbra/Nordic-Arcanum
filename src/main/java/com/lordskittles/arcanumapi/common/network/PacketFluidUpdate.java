package com.lordskittles.arcanumapi.common.network;

import com.lordskittles.arcanumapi.common.tileentity.TileEntityFluidInventory;
import com.lordskittles.arcanumapi.common.utilities.ClientUtilities;
import com.lordskittles.arcanumapi.core.ArcanumAPI;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketFluidUpdate extends PacketBase
{
    private final BlockPos pos;
    private final CompoundNBT updateTag;
    private final float scale;

    public PacketFluidUpdate(TileEntityFluidInventory tile)
    {
        this(tile.getPos(), tile.getUpdateTag(), tile.prevScale);
    }

    private PacketFluidUpdate(BlockPos pos, CompoundNBT updateTag, float scale)
    {
        this.pos = pos;
        this.updateTag = updateTag;
        this.scale = scale;
    }

    public static void handle(PacketFluidUpdate message, Supplier<NetworkEvent.Context> context)
    {
        context.get().enqueueWork(() ->
        {
            TileEntityFluidInventory tile = ClientUtilities.getTileEntity(TileEntityFluidInventory.class, message.pos);
            if (tile == null)
            {
                ArcanumAPI.LOG.info("Update tile packet received for position: {} in world, but no valid tile was found.", message.pos);
            }
            else
            {
                tile.handleScaleUpdate(message.updateTag, message.scale);
            }
        });

        context.get().setPacketHandled(true);
    }

    public static void encode(PacketFluidUpdate packet, PacketBuffer buffer)
    {
        buffer.writeBlockPos(packet.pos);
        buffer.writeCompoundTag(packet.updateTag);
        buffer.writeFloat(packet.scale);
    }

    public static PacketFluidUpdate decode(PacketBuffer buffer)
    {
        return new PacketFluidUpdate(buffer.readBlockPos(), buffer.readCompoundTag(), buffer.readFloat());
    }
}
