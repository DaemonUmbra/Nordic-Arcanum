package com.lordskittles.arcanumapi.common.blockentity;

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

public class BlockEntityInventorySyncable<T extends BlockEntityInventorySyncable<T>> extends BlockEntityInventory<T> {

    private int ticksSinceSync;

    public BlockEntityInventorySyncable(BlockEntityType<?> blockEntityTypeIn, BlockPos pos, BlockState state, int size, String containerId, String modid) {

        super(blockEntityTypeIn, pos, state, size, containerId, modid);
    }

    private static int calculatePlayersUsingSync(Level world, BlockEntityInventorySyncable block, int ticksSinceSync, int x, int y, int z, int numPlayersUsing) {

        if(numPlayersUsing != 0 && (ticksSinceSync + x + y + z) % 200 == 0) {
            numPlayersUsing = calculatePlayersUsing(world, block, x, y, z);
        }

        return numPlayersUsing;
    }

    private static int calculatePlayersUsing(Level world, BlockEntityInventorySyncable entity, int x, int y, int z) {

        int i = 0;

        for(Player player : world.getEntitiesOfClass(Player.class, new AABB((double) ((float) x - 5.0F), (double) ((float) y - 5.0F), (double) ((float) z - 5.0F), (double) ((float) (x + 1) + 5.0F), (double) ((float) (y + 1) + 5.0F), (double) ((float) (z + 1) + 5.0F)))) {
            if(player.containerMenu instanceof ContainerBase) {
                Container inventory = ((ContainerBase) player.containerMenu).getTileInventory();
                if(inventory == entity) {
                    ++ i;
                }
            }
            else
                if(player.containerMenu instanceof ContainerStorageBase) {
                    Container inventory = ((ContainerStorageBase) player.containerMenu).getTileInventory();
                    if(inventory == entity) {
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