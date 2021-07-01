package com.lordskittles.nordicarcanum.common.network;

import com.lordskittles.arcanumapi.common.network.PacketHandlerBase;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class PacketHandler extends PacketHandlerBase {

    private static final SimpleChannel netHandler = createChannel(NordicArcanum.RL("main"));

    @Override
    public void initialize() {

        super.initialize();
        registerPacket(PacketMultiblockFormed.class, PacketMultiblockFormed::encode, PacketMultiblockFormed::decode, PacketMultiblockFormed::handle);
    }

    @Override
    protected SimpleChannel getChannel() {

        return netHandler;
    }
}
