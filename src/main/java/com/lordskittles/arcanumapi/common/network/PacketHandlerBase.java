package com.lordskittles.arcanumapi.common.network;

import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.fmllegacy.network.NetworkDirection;
import net.minecraftforge.fmllegacy.network.NetworkEvent;
import net.minecraftforge.fmllegacy.network.NetworkRegistry;
import net.minecraftforge.fmllegacy.network.PacketDistributor;
import net.minecraftforge.fmllegacy.network.simple.SimpleChannel;
import net.minecraftforge.fmllegacy.network.simple.SimpleChannel;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class PacketHandlerBase {

    protected static SimpleChannel createChannel(ResourceLocation name) {

        return NetworkRegistry.newSimpleChannel(name, () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
    }

    private static final String PROTOCOL_VERSION = "1";
    private static int index = 0;

    public void initialize() {

        registerPacket(PacketFluidUpdate.class, PacketFluidUpdate::encode, PacketFluidUpdate::decode, PacketFluidUpdate::handle);
        registerPacket(PacketUpdateTile.class, PacketUpdateTile::encode, PacketUpdateTile::decode, PacketUpdateTile::handle);
        registerPacket(PacketTileArcanumUpdate.class, PacketTileArcanumUpdate::encode, PacketTileArcanumUpdate::decode, PacketTileArcanumUpdate::handle);
        registerPacket(PacketPlayerArcanumUpdate.class, PacketPlayerArcanumUpdate::encode, PacketPlayerArcanumUpdate::decode, PacketPlayerArcanumUpdate::handle);
    }

    protected abstract SimpleChannel getChannel();

    protected <MSG> void registerPacket(Class<MSG> type, BiConsumer<MSG, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, MSG> decoder, BiConsumer<MSG, Supplier<NetworkEvent.Context>> consumer) {

        getChannel().registerMessage(index++, type, encoder, decoder, consumer);
    }

    public <MSG> void sendTo(MSG message, ServerPlayer player) {

        getChannel().sendTo(message, player.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
    }

    public <MSG> void sendToAll(MSG message) {

        getChannel().send(PacketDistributor.ALL.noArg(), message);
    }

    public <MSG> void sendToAllTracking(MSG message, Entity entity) {

        getChannel().send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), message);
    }

    public <MSG> void sendToAllTracking(MSG message, BlockEntity tile) {

        sendToAllTracking(message, tile.getLevel(), tile.getBlockPos());
    }

    public <MSG> void sendToAllTracking(MSG message, Level world, BlockPos pos) {

        if(world instanceof ServerLevel) {
            //If we have a ServerWorld just directly figure out the ChunkPos so as to not require looking up the chunk
            // This provides a decent performance boost over using the packet distributor
            ((ServerLevel) world).getChunkSource().chunkMap.getPlayers(new ChunkPos(pos), false).forEach(p -> sendTo(message, p));
        }
        else {
            //Otherwise fallback to entities tracking the chunk if some mod did something odd and our world is not a ServerWorld
            getChannel().send(PacketDistributor.TRACKING_CHUNK.with(() -> world.getChunk(pos.getX() >> 4, pos.getZ() >> 4)), message);
        }
    }
}
