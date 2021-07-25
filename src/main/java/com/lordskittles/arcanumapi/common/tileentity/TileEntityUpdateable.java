package com.lordskittles.arcanumapi.common.tileentity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;

public abstract class TileEntityUpdateable<T extends TileEntityUpdateable> extends BlockEntity {

    public TileEntityUpdateable(BlockEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState state) {

        super(tileEntityTypeIn, pos, state);
    }

    protected int ticksExisted = 0;

    @Nonnull
    public CompoundTag getReducedUpdateTag() {

        return super.getUpdateTag();
    }

    public void tick() {

        ticksExisted++;

        if(this.level.isClientSide) {
            onClientUpdate();
        }
        else {
            onServerUpdate();
        }
    }

    public int getTicksExisted() {

        return this.ticksExisted;
    }

    protected void onClientUpdate() {}

    protected void onServerUpdate() {}
}
