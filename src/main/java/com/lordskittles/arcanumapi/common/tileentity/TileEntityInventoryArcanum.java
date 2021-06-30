package com.lordskittles.arcanumapi.common.tileentity;

import com.lordskittles.arcanumapi.common.network.PacketHandlerBase;
import com.lordskittles.arcanumapi.common.utilities.MagicUtilities;
import com.lordskittles.arcanumapi.magic.ArcanumServerManager;
import com.lordskittles.arcanumapi.magic.IArcanumUser;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.tileentity.TileEntityType;

public abstract class TileEntityInventoryArcanum<T extends TileEntityInventoryArcanum<T>> extends TileEntityInventorySyncable<T> implements IArcanumUser
{
    private final PacketHandlerBase packetHandler;

    public TileEntityInventoryArcanum(TileEntityType<?> tileEntityTypeIn, int size, String containerId, String modid, PacketHandlerBase packetHandler)
    {
        super(tileEntityTypeIn, size, containerId, modid);
        this.packetHandler = packetHandler;
    }

    @Override
    public void useArcanum(PlayerEntity player, float cost)
    {
        if (player instanceof ServerPlayerEntity)
        {
            try
            {
                ArcanumServerManager.useArcanum((ServerPlayerEntity) player, cost, this, packetHandler);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public float getCurrentArcanum(PlayerEntity player)
    {
        return MagicUtilities.getCurrentArcanum(player);
    }
}
