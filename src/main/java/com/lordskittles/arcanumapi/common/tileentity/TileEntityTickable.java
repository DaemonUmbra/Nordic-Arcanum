package com.lordskittles.arcanumapi.common.tileentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class TileEntityTickable<T extends TileEntityTickable> extends TileEntityUpdateable {

    protected final T tile;

    public TileEntityTickable(BlockEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState state, T tile) {

        super(tileEntityTypeIn, pos, state);

        this.tile = tile;
    }

    @Override
    public void tick() {

        tick(this.level, this.tile);
    }

    protected abstract void tick(Level world, T tile);
}
