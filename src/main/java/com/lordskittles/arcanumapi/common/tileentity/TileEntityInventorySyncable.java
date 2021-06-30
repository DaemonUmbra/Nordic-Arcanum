package com.lordskittles.arcanumapi.common.tileentity;

import com.lordskittles.arcanumapi.common.inventory.containers.ContainerBase;
import com.lordskittles.arcanumapi.common.inventory.containers.ContainerStorageBase;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class TileEntityInventorySyncable<T extends TileEntityInventorySyncable<T>> extends TileEntityInventory<T> implements ITickableTileEntity
{
    private int ticksSinceSync;

    public TileEntityInventorySyncable(TileEntityType<?> tileEntityTypeIn, int size, String containerId, String modid)
    {
        super(tileEntityTypeIn, size, containerId, modid);
    }

    private static int calculatePlayersUsingSync(World world, TileEntityInventorySyncable tile, int ticksSinceSync, int x, int y, int z, int numPlayersUsing)
    {
        if (numPlayersUsing != 0 && (ticksSinceSync + x + y + z) % 200 == 0)
        {
            numPlayersUsing = calculatePlayersUsing(world, tile, x, y, z);
        }

        return numPlayersUsing;
    }

    private static int calculatePlayersUsing(World world, TileEntityInventorySyncable tile, int x, int y, int z)
    {
        int i = 0;

        for(PlayerEntity player : world.getEntitiesWithinAABB(PlayerEntity.class, new AxisAlignedBB((double)((float)x - 5.0F), (double)((float)y - 5.0F), (double)((float)z - 5.0F), (double)((float)(x + 1) + 5.0F), (double)((float)(y + 1) + 5.0F), (double)((float)(z + 1) + 5.0F))))
        {
            if (player.openContainer instanceof ContainerBase)
            {
                IInventory inventory = ((ContainerBase)player.openContainer).getTileInventory();
                if (inventory == tile)
                {
                    ++i;
                }
            }
            else if (player.openContainer instanceof ContainerStorageBase)
            {
                IInventory inventory = ((ContainerStorageBase)player.openContainer).getTileInventory();
                if (inventory == tile)
                {
                    ++i;
                }
            }
        }

        return i;
    }

    @Nullable
    @Override
    public Container createMenu(int id, PlayerInventory playerInventory, PlayerEntity player)
    {
        return null;
    }

    @Override
    public void onClientUpdate()
    {
        ++this.ticksSinceSync;
        this.numPlayersUsing = calculatePlayersUsingSync(this.world, this, this.ticksSinceSync, this.pos.getX(), this.pos.getY(), this.pos.getZ(), this.numPlayersUsing);
    }
}
