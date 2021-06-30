package com.lordskittles.arcanumapi.common.tileentity;

import com.lordskittles.arcanumapi.common.network.DescSynced;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import javax.annotation.Nonnull;

public class SmartSyncTank extends FluidTank
{
    @DescSynced
    private FluidStack syncedFluidStackDesc = FluidStack.EMPTY;
    @DescSynced
    private FluidStack syncedFluidStackGui = FluidStack.EMPTY;

    private boolean pending = false;

    private int syncTimer = -1;
    private final TileEntityFluidInventory owner;
    private final int threshold;

    public SmartSyncTank(TileEntityFluidInventory owner, int capacity)
    {
        super(capacity);

        this.owner = owner;
        this.threshold = Math.min(1000, capacity / 100);
    }

    public void tick()
    {
        if (this.owner.getWorld().isRemote)
        {
            super.setFluid(this.syncedFluidStackGui);
        }
        else
        {
            if (this.syncTimer > 0)
            {
                this.syncTimer--;
            }
            else if (this.syncTimer == 0)
            {
                if (this.pending)
                {
                    this.syncedFluidStackDesc = getFluid().copy();
                    this.pending = false;
                    this.syncTimer = 20;
                }
                else
                {
                    this.syncTimer = -1;
                }
            }
        }
    }

    private void deferredSync(int ticks)
    {
        if (this.syncTimer == -1)
        {
            this.syncedFluidStackDesc = getFluid().copy();
            this.syncTimer = ticks;
        }
        else
        {
            this.pending = true;
        }
    }

    private void onFluidChange(FluidStack newFluid)
    {
        this.syncedFluidStackGui = newFluid.copy();

        int delta = Math.abs(this.syncedFluidStackDesc.getAmount() - newFluid.getAmount());
        if (delta >= this.threshold || this.syncedFluidStackDesc.getFluid() != newFluid.getFluid())
        {
            deferredSync(20);
        }
    }

    @Override
    public int fill(FluidStack resource, FluidAction action)
    {
        int filled = super.fill(resource, action);
        if (filled != 0 && action.execute())
        {
            onFluidChange(getFluid());
        }
        return filled;
    }

    @Nonnull
    @Override
    public FluidStack drain(FluidStack resource, FluidAction action)
    {
        FluidStack drained = super.drain(resource, action);
        if (!drained.isEmpty() && action.execute())
        {
            onFluidChange(getFluid());
        }
        return drained;
    }

    @Nonnull
    @Override
    public FluidStack drain(int maxDrain, FluidAction action)
    {
        FluidStack drained = super.drain(maxDrain, action);
        if (!drained.isEmpty() && action.execute())
        {
            onFluidChange(getFluid());
        }
        return drained;
    }

    @Override
    protected void onContentsChanged()
    {
        super.onContentsChanged();

        // We don't use onContentsChanged() for sync purposes, because its gets called even for simulated changes,
        // and we have no way of knowing whether or not this is a simulation.

        this.owner.markDirty();
    }

    @Override
    public void setFluid(FluidStack stack)
    {
        onFluidChange(stack);

        super.setFluid(stack);
    }

    @Override
    public FluidTank readFromNBT(CompoundNBT nbt)
    {
        FluidTank tank = super.readFromNBT(nbt);
        syncedFluidStackDesc = tank.getFluid().copy();
        return tank;
    }
}
