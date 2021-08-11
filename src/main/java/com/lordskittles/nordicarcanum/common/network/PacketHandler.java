package com.lordskittles.nordicarcanum.common.network;

import com.lordskittles.arcanumapi.common.network.PacketHandlerBase;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import net.minecraftforge.fmllegacy.network.simple.SimpleChannel;

public class PacketHandler extends PacketHandlerBase {

    private static SimpleChannel netHandler;

    @Override
    public void initialize() {

        netHandler = createChannel(NordicArcanum.RL("main"));

        super.initialize();
        registerPacket(PacketMultiblockFormed.class, PacketMultiblockFormed::encode, PacketMultiblockFormed::decode, PacketMultiblockFormed::handle);
    }

    @Override
    protected SimpleChannel getChannel() {

        return netHandler;
    }
}
