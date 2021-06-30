package com.lordskittles.arcanumapi.common.tileentity;

import com.lordskittles.arcanumapi.common.network.PacketHandlerBase;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;

public abstract class TileEntityMagicChest<T extends TileEntityMagicChest<T>> extends TileEntityInventoryArcanum<T>
{
    protected float lidAngle;
    protected float prevLidAngle;

    private final float retainCost;

    public TileEntityMagicChest(TileEntityType<?> tileEntityTypeIn, int size, String containerId, float retainCost, String modid, PacketHandlerBase packetHandler)
    {
        super(tileEntityTypeIn, size, containerId, modid, packetHandler);

        this.retainCost = retainCost;
    }

    public boolean canRetainInventory(PlayerEntity player)
    {
        return getCurrentArcanum(player) > this.retainCost;
    }

    public float getRetainCost()
    {
        return this.retainCost;
    }

    public float getLidAngle(float partialTicks)
    {
        return MathHelper.lerp(partialTicks, this.prevLidAngle, this.lidAngle);
    }

    public abstract Container createMenu(int id, PlayerInventory playerInventory, PlayerEntity player);

    @Override
    public void tick()
    {
        super.tick();
        this.prevLidAngle = this.lidAngle;
        if (this.numPlayersUsing > 0 && this.lidAngle == 0.0F)
        {
            world.playSound(null, pos, SoundEvents.BLOCK_CHEST_OPEN, SoundCategory.BLOCKS, 1.0f, 1.0f);
        }

        if (this.numPlayersUsing == 0 && this.lidAngle > 0.0F || this.numPlayersUsing > 0 && this.lidAngle < 1.0F)
        {
            float f1 = this.lidAngle;
            if (this.numPlayersUsing > 0)
            {
                this.lidAngle += 0.1F;
            }
            else
            {
                this.lidAngle -= 0.1F;
            }

            if (this.lidAngle > 1.0F)
            {
                this.lidAngle = 1.0F;
            }

            if (this.lidAngle < 0.5F && f1 >= 0.5F)
            {
                world.playSound(null, pos, SoundEvents.BLOCK_CHEST_CLOSE, SoundCategory.BLOCKS, 1.0f, 1.0f);
            }

            if (this.lidAngle < 0.0F)
            {
                this.lidAngle = 0.0F;
            }
        }
    }
}
