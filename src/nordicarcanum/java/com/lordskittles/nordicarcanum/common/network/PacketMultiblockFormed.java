package com.lordskittles.nordicarcanum.common.network;

import com.lordskittles.arcanumapi.common.network.PacketBase;
import com.lordskittles.arcanumapi.common.utilities.ClientUtilities;
import com.lordskittles.arcanumapi.common.utilities.IMultiblock;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketMultiblockFormed extends PacketBase {

    private final BlockPos pos;
    private final CompoundTag updateTag;
    private final boolean isFormed;

    public PacketMultiblockFormed(IMultiblock multiblock) {

        this.isFormed = multiblock.isFormed();
        this.pos = multiblock.getPos();
        this.updateTag = multiblock.getUpdateTag();
    }

    private PacketMultiblockFormed(BlockPos pos, CompoundTag nbt, boolean isFormed) {

        this.pos = pos;
        this.updateTag = nbt;
        this.isFormed = isFormed;
    }

    public static void handle(PacketMultiblockFormed message, Supplier<NetworkEvent.Context> context) {

        context.get().enqueueWork(() ->
        {
            BlockEntity tile = ClientUtilities.getTileEntity(BlockEntity.class, message.pos);
            if(tile == null) {
                NordicArcanum.LOG.info("Update tile packet received for position: {} in world, but no valid tile was found.", message.pos);
            }
            else {
                if(tile instanceof IMultiblock) {
                    ((IMultiblock) tile).setFormed(message.isFormed);
                    tile.handleUpdateTag(tile.getBlockState(), message.updateTag);
                }
                else {
                    NordicArcanum.LOG.info("Update tile packet received for position: {} in world, tile valid, but not a multiblock.", message.pos);
                }
            }
        });

        context.get().setPacketHandled(true);
    }

    public static void encode(PacketMultiblockFormed packet, FriendlyByteBuf buffer) {

        buffer.writeBlockPos(packet.pos);
        buffer.writeCompoundTag(packet.updateTag);
        buffer.writeBoolean(packet.isFormed);
    }

    public static PacketMultiblockFormed decode(PacketBuffer buffer) {

        return new PacketMultiblockFormed(buffer.readBlockPos(), buffer.readCompoundTag(), buffer.readBoolean());
    }
}
