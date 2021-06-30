package com.lordskittles.arcanumapi.common.tileentity;

import com.lordskittles.arcanumapi.core.NBTConstants;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public interface INordicFluidTank extends IFluidTank, INBTSerializable<CompoundNBT>
{
    void setStack(FluidStack stack);

    @Override
    default int fill(FluidStack stack, FluidAction action)
    {
        return stack.getAmount() - insert(stack).getAmount();
    }

    @Override
    default FluidStack drain(FluidStack stack, FluidAction action)
    {
        if (!isEmpty() && getFluid().isFluidEqual(stack))
        {
            return extract(stack.getAmount());
        }

        return FluidStack.EMPTY;
    }

    @Override
    default FluidStack drain(int amount, FluidAction action)
    {
        return extract(amount);
    }

    default FluidStack insert(FluidStack stack)
    {
        if (stack.isEmpty() || !isFluidValid(stack))
            return stack;

        int needed = getNeeded();
        if (needed <= 0)
        {
            return stack;
        }

        boolean sameType = false;
        if (isEmpty() || (sameType = stack.isFluidEqual(getFluid())))
        {
            int toAdd = Math.min(stack.getAmount(), needed);
            if (sameType)
            {
                growStack(toAdd);
            }
            else
            {
                setStack(new FluidStack(stack, toAdd));
            }

            return new FluidStack(stack, stack.getAmount() - toAdd);
        }

        return stack;
    }

    default FluidStack extract(int amount)
    {
        if (isEmpty() || amount < 1)
        {
            return FluidStack.EMPTY;
        }

        FluidStack ret = new FluidStack(getFluid(), Math.min(getFluidAmount(), amount));
        if (!ret.isEmpty())
        {
            shrinkStack(ret.getAmount());
        }

        return ret;
    }

    default int growStack(int amount)
    {
        int current = getFluidAmount();
        if (amount > 0)
        {
            amount = Math.min(amount, getNeeded());
        }

        int newSize = setStackSize(current + amount);
        return  newSize - current;
    }

    default int shrinkStack(int amount)
    {
        return -growStack(amount);
    }

    default boolean isEmpty()
    {
        return getFluid().isEmpty();
    }

    default void setEmpty()
    {
        setStack(FluidStack.EMPTY);
    }

    default boolean isFluidEqual(FluidStack other)
    {
        return getFluid().isFluidEqual(other);
    }

    default int setStackSize(int amount)
    {
        if (isEmpty())
        {
            return 0;
        }
        else if (amount <= 0)
        {
            setEmpty();
            return 0;
        }

        int maxStackSize = getCapacity();
        if (amount > maxStackSize)
        {
            amount = maxStackSize;
        }

        if (getFluidAmount() == amount)
        {
            return amount;
        }

        setStack(new FluidStack(getFluid(), amount));
        return amount;
    }

    default int getNeeded()
    {
        return Math.max(0, getCapacity() - getFluidAmount());
    }

    @Override
    default CompoundNBT serializeNBT()
    {
        CompoundNBT nbt = new CompoundNBT();
        if (!isEmpty())
        {
            nbt.put(NBTConstants.STORED, getFluid().writeToNBT(new CompoundNBT()));
        }

        return nbt;
    }
}
