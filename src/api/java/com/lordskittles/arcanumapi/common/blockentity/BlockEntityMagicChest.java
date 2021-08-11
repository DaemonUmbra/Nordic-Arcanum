package com.lordskittles.arcanumapi.common.blockentity;

import com.lordskittles.arcanumapi.common.network.PacketHandlerBase;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.state.BlockState;

public abstract class BlockEntityMagicChest<T extends BlockEntityMagicChest<T>> extends BlockEntityInventoryArcanum<T> {

    protected float lidAngle;
    protected float prevLidAngle;

    private final float retainCost;

    public BlockEntityMagicChest(BlockEntityType<?> blockEntityTypeIn, BlockPos pos, BlockState state, int size, String containerId, float retainCost, String modid, PacketHandlerBase packetHandler) {

        super(blockEntityTypeIn, pos, state, size, containerId, modid, packetHandler);

        this.retainCost = retainCost;
    }

    public boolean canRetainInventory(Player player) {

        return getCurrentArcanum(player) > this.retainCost;
    }

    public float getRetainCost() {

        return this.retainCost;
    }

    public float getLidAngle(float partialTicks) {

        return Mth.lerp(partialTicks, this.prevLidAngle, this.lidAngle);
    }

    public abstract AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player player);

    public static void tick(Level level, BlockPos pos, BlockState state, BlockEntityMagicChest inventory) {

        BlockEntityMagicChest.tick(level, pos, state, inventory);
        inventory.prevLidAngle = inventory.lidAngle;
        if(inventory.numPlayersUsing > 0 && inventory.lidAngle == 0.0F) {
            level.playSound(null, pos, SoundEvents.CHEST_OPEN, SoundSource.BLOCKS, 1.0f, 1.0f);
        }

        if(inventory.numPlayersUsing == 0 && inventory.lidAngle > 0.0F || inventory.numPlayersUsing > 0 && inventory.lidAngle < 1.0F) {
            float f1 = inventory.lidAngle;
            if(inventory.numPlayersUsing > 0) {
                inventory.lidAngle += 0.1F;
            }
            else {
                inventory.lidAngle -= 0.1F;
            }

            if(inventory.lidAngle > 1.0F) {
                inventory.lidAngle = 1.0F;
            }

            if(inventory.lidAngle < 0.5F && f1 >= 0.5F) {
                level.playSound(null, pos, SoundEvents.CHEST_CLOSE, SoundSource.BLOCKS, 1.0f, 1.0f);
            }

            if(inventory.lidAngle < 0.0F) {
                inventory.lidAngle = 0.0F;
            }
        }
    }
}
