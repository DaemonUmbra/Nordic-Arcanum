package com.lordskittles.arcanumapi.common.utilities;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class ClientUtilities
{
    public static <T extends TileEntity> T getTileEntity(@Nonnull Class<T> clazz, @Nonnull BlockPos pos)
    {
        World world = Minecraft.getInstance().world;
        TileEntity tile = world.getTileEntity(pos);
        if (tile == null)
        {
            return null;
        }

        if (clazz.isInstance(tile))
        {
            return clazz.cast(tile);
        }

        return null;
    }

    public static PlayerEntity getPlayer(Supplier<NetworkEvent.Context> context)
    {
        PlayerEntity player = context.get().getSender();
        if (player == null)
        {
            player = Minecraft.getInstance().player;
        }

        return player;
    }
}
