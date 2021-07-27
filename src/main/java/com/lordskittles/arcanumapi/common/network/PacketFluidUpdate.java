package com.lordskittles.arcanumapi.common.network;

import com.lordskittles.arcanumapi.common.blockentity.BlockEntityFluidInventory;
import com.lordskittles.arcanumapi.common.utilities.ClientUtilities;
import com.lordskittles.arcanumapi.core.ArcanumAPI;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketFluidUpdate extends PacketBase {

    private final BlockPos pos;
    private final CompoundTag updateTag;
    private final float scale;

    public PacketFluidUpdate(BlockEntityFluidInventory block) {

        this(block.getBlockPos(), block.getUpdateTag(), block.prevScale);
    }

    private PacketFluidUpdate(BlockPos pos, CompoundTag updateTag, float scale) {

        this.pos = pos;
        this.updateTag = updateTag;
        this.scale = scale;
    }

    public static void handle(PacketFluidUpdate message, Supplier<NetworkEvent.Context> context) {

        context.get().enqueueWork(() ->
        {
            BlockEntityFluidInventory block = ClientUtilities.getTileEntity(BlockEntityFluidInventory.class, message.pos);
            if(block == null) {
                ArcanumAPI.LOG.info("Update block packet received for position: {} in level, but no valid block was found.", message.pos);
            }
            else {
                block.handleScaleUpdate(message.updateTag, message.scale);
            }
        });

        context.get().setPacketHandled(true);
    }

    public static void encode(PacketFluidUpdate packet, FriendlyByteBuf buffer) {

        buffer.writeBlockPos(packet.pos);
        buffer.writeNbt(packet.updateTag);
        buffer.writeFloat(packet.scale);
    }

    public static PacketFluidUpdate decode(FriendlyByteBuf buffer) {

        return new PacketFluidUpdate(buffer.readBlockPos(), buffer.readNbt(), buffer.readFloat());
    }
}
