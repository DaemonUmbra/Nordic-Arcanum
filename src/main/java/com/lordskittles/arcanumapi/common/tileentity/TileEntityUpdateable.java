package com.lordskittles.arcanumapi.common.tileentity;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

import javax.annotation.Nonnull;

public abstract class TileEntityUpdateable<T extends TileEntityUpdateable> extends TileEntity implements ITickableTileEntity {

    public TileEntityUpdateable(TileEntityType<?> tileEntityTypeIn) {

        super(tileEntityTypeIn);
    }

    protected int ticksExisted = 0;

    @Nonnull
    public CompoundNBT getReducedUpdateTag() {

        return super.getUpdateTag();
    }

    @Override
    public void tick() {

        ticksExisted++;

        if(this.world.isRemote) {
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
