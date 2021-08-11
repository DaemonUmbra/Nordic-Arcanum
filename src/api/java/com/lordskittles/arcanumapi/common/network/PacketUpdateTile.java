package com.lordskittles.arcanumapi.common.network;

import com.lordskittles.arcanumapi.common.blockentity.BlockEntityUpdateable;
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

    public PacketUpdateTile(BlockEntityUpdateable block) {

        this(block.getBlockPos(), block.getReducedUpdateTag());
    }

    private PacketUpdateTile(BlockPos pos, CompoundTag updateTag) {

        this.pos = pos;
        this.updateTag = updateTag;
    }

    public static void handle(PacketUpdateTile message, Supplier<Context> context) {

        context.get().enqueueWork(() ->
        {
            BlockEntityUpdateable block = ClientUtilities.getTileEntity(BlockEntityUpdateable.class, message.pos);
            if(block == null) {
                ArcanumAPI.LOG.info("Update block packet received for position: {} in level, but no valid block was found.", message.pos);
            }
            else {
                block.handleUpdateTag(message.updateTag);
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
