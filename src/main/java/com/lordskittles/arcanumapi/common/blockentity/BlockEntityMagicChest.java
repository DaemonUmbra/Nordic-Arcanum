package com.lordskittles.arcanumapi.common.blockentity;

import com.lordskittles.arcanumapi.common.network.PacketHandlerBase;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
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

    @Override
    public void tick() {

        super.tick();
        this.prevLidAngle = this.lidAngle;
        if(this.numPlayersUsing > 0 && this.lidAngle == 0.0F) {
            level.playSound(null, worldPosition, SoundEvents.CHEST_OPEN, SoundSource.BLOCKS, 1.0f, 1.0f);
        }

        if(this.numPlayersUsing == 0 && this.lidAngle > 0.0F || this.numPlayersUsing > 0 && this.lidAngle < 1.0F) {
            float f1 = this.lidAngle;
            if(this.numPlayersUsing > 0) {
                this.lidAngle += 0.1F;
            }
            else {
                this.lidAngle -= 0.1F;
            }

            if(this.lidAngle > 1.0F) {
                this.lidAngle = 1.0F;
            }

            if(this.lidAngle < 0.5F && f1 >= 0.5F) {
                level.playSound(null, worldPosition, SoundEvents.CHEST_CLOSE, SoundSource.BLOCKS, 1.0f, 1.0f);
            }

            if(this.lidAngle < 0.0F) {
                this.lidAngle = 0.0F;
            }
        }
    }
}
