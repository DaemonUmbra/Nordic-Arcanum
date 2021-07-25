package com.lordskittles.arcanumapi.common.tileentity;

import com.lordskittles.arcanumapi.common.inventory.containers.ContainerBase;
import com.lordskittles.arcanumapi.common.inventory.containers.ContainerStorageBase;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import javax.annotation.Nullable;

public class TileEntityInventorySyncable<T extends TileEntityInventorySyncable<T>> extends TileEntityInventory<T> {

    private int ticksSinceSync;

    public TileEntityInventorySyncable(BlockEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState state, int size, String containerId, String modid) {

        super(tileEntityTypeIn, pos, state, size, containerId, modid);
    }

    private static int calculatePlayersUsingSync(Level world, TileEntityInventorySyncable tile, int ticksSinceSync, int x, int y, int z, int numPlayersUsing) {

        if(numPlayersUsing != 0 && (ticksSinceSync + x + y + z) % 200 == 0) {
            numPlayersUsing = calculatePlayersUsing(world, tile, x, y, z);
        }

        return numPlayersUsing;
    }

    private static int calculatePlayersUsing(Level world, TileEntityInventorySyncable tile, int x, int y, int z) {

        int i = 0;

        for(Player player : world.getEntitiesOfClass(Player.class, new AABB((double) ((float) x - 5.0F), (double) ((float) y - 5.0F), (double) ((float) z - 5.0F), (double) ((float) (x + 1) + 5.0F), (double) ((float) (y + 1) + 5.0F), (double) ((float) (z + 1) + 5.0F)))) {
            if(player.containerMenu instanceof ContainerBase) {
                Container inventory = ((ContainerBase) player.containerMenu).getTileInventory();
                if(inventory == tile) {
                    ++ i;
                }
            }
            else
                if(player.containerMenu instanceof ContainerStorageBase) {
                    Container inventory = ((ContainerStorageBase) player.containerMenu).getTileInventory();
                    if(inventory == tile) {
                        ++ i;
                    }
                }
        }

        return i;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player player) {

        return null;
    }

    @Override
    public void onClientUpdate() {

        ++ this.ticksSinceSync;
        this.numPlayersUsing = calculatePlayersUsingSync(this.level, this, this.ticksSinceSync, this.worldPosition.getX(), this.worldPosition.getY(), this.worldPosition.getZ(), this.numPlayersUsing);
    }
}
