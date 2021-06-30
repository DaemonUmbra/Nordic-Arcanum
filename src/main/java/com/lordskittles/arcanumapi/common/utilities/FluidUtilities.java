package com.lordskittles.arcanumapi.common.utilities;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fluids.FluidActionResult;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.wrapper.PlayerInvWrapper;

public class FluidUtilities
{
    public static void saveFluid(CompoundNBT nbt, ItemStack stack, FluidStack fluid)
    {
        stack.write(fluid.writeToNBT(nbt));
    }

    public static boolean tryFluidInsert(TileEntity tile, Direction face, PlayerEntity player, Hand hand)
    {
        return doFluidInteraction(tile, face, player, hand, true);
    }

    public static boolean tryFluidExtract(TileEntity tile, Direction face, PlayerEntity player, Hand hand)
    {
        return doFluidInteraction(tile, face, player, hand, false);
    }

    public static boolean tryFluidInsert(IFluidHandler handler, ItemStack source, NonNullList<ItemStack> returnedItems)
    {
        FluidActionResult result = FluidUtil.tryEmptyContainer(source, handler, 1000, null, true);
        if (result.isSuccess())
        {
            returnedItems.add(result.getResult());
            source.shrink(1);
            return true;
        }

        return false;
    }

    private static boolean doFluidInteraction(TileEntity tile, Direction face, PlayerEntity player, Hand hand, boolean isInserting)
    {
        ItemStack stack = player.getHeldItem(hand);

        return FluidUtil.getFluidHandler(stack).map(stackHandler ->
        {
            if (tile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, face).isPresent())
            {
                if (stackHandler.getTanks() == 0)
                    return false;

                int capacity = stackHandler.getTankCapacity(0);
                return tile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, face).map(handler ->
                {
                    PlayerInvWrapper invWrapper = new PlayerInvWrapper(player.inventory);
                    FluidActionResult result = isInserting ?
                            FluidUtil.tryEmptyContainerAndStow(player.getHeldItem(hand), handler, invWrapper, capacity, player, true) :
                            FluidUtil.tryFillContainerAndStow(player.getHeldItem(hand), handler, invWrapper, capacity, player, true);

                    if (result.isSuccess())
                    {
                        player.setHeldItem(hand, result.getResult());
                        return true;
                    }

                    return false;
                }).orElse(false);
            }
            return false;
        }).orElse(false);
    }
}
