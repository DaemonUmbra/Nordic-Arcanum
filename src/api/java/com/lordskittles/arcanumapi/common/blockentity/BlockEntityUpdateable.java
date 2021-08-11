package com.lordskittles.arcanumapi.common.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;

public abstract class BlockEntityUpdateable<T extends BlockEntityUpdateable> extends BlockEntity {

    public BlockEntityUpdateable(BlockEntityType<?> blockEntityTypeIn, BlockPos pos, BlockState state) {

        super(blockEntityTypeIn, pos, state);
    }

    protected int ticksExisted = 0;

    @Nonnull
    public CompoundTag getReducedUpdateTag() {

        return super.getUpdateTag();
    }

    public static void tick(Level level, BlockPos pos, BlockState state, BlockEntityUpdateable entity) {

        entity.ticksExisted++;

        if(level.isClientSide) {
            entity.onClientUpdate();
        }
        else {
            entity.onServerUpdate();
        }
    }

    public int getTicksExisted() {

        return this.ticksExisted;
    }

    protected void onClientUpdate() {}

    protected void onServerUpdate() {}
}
