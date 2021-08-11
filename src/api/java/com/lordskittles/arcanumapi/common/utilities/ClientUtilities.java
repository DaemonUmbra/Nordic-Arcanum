package com.lordskittles.arcanumapi.common.utilities;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class ClientUtilities {

    public static <T extends BlockEntity> T getTileEntity(@Nonnull Class<T> clazz, @Nonnull BlockPos pos) {

        Level world = Minecraft.getInstance().level;
        BlockEntity tile = world.getBlockEntity(pos);
        if(tile == null) {
            return null;
        }

        if(clazz.isInstance(tile)) {
            return clazz.cast(tile);
        }

        return null;
    }

    public static Player getPlayer(Supplier<NetworkEvent.Context> context) {

        Player player = context.get().getSender();
        if(player == null) {
            player = Minecraft.getInstance().player;
        }

        return player;
    }
}
