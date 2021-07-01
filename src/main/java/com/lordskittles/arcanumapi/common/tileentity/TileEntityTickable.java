package com.lordskittles.arcanumapi.common.tileentity;

import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.world.World;

public abstract class TileEntityTickable<T extends TileEntityTickable> extends TileEntityUpdateable implements ITickableTileEntity {

    protected final T tile;

    public TileEntityTickable(TileEntityType<?> tileEntityTypeIn, T tile) {

        super(tileEntityTypeIn);

        this.tile = tile;
    }

    @Override
    public void tick() {

        tick(this.world, this.tile);
    }

    protected abstract void tick(World world, T tile);
}
