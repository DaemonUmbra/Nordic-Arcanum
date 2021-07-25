package com.lordskittles.arcanumapi.common.utilities;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.NonNullList;
import net.minecraftforge.fluids.FluidActionResult;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.wrapper.PlayerInvWrapper;

public class FluidUtilities {

    public static void saveFluid(CompoundTag nbt, ItemStack stack, FluidStack fluid) {

        stack.save(fluid.writeToNBT(nbt));
    }

    public static boolean tryFluidInsert(BlockEntity tile, Direction face, Player player, InteractionHand hand) {

        return doFluidInteraction(tile, face, player, hand, true);
    }

    public static boolean tryFluidExtract(BlockEntity tile, Direction face, Player player, InteractionHand hand) {

        return doFluidInteraction(tile, face, player, hand, false);
    }

    public static boolean tryFluidInsert(IFluidHandler handler, ItemStack source, NonNullList<ItemStack> returnedItems) {

        FluidActionResult result = FluidUtil.tryEmptyContainer(source, handler, 1000, null, true);
        if(result.isSuccess()) {
            returnedItems.add(result.getResult());
            source.shrink(1);
            return true;
        }

        return false;
    }

    private static boolean doFluidInteraction(BlockEntity tile, Direction face, Player player, InteractionHand hand, boolean isInserting) {

        ItemStack stack = player.getItemInHand(hand);

        return FluidUtil.getFluidHandler(stack).map(stackHandler ->
        {
            if(tile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, face).isPresent()) {
                if(stackHandler.getTanks() == 0)
                    return false;

                int capacity = stackHandler.getTankCapacity(0);
                return tile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, face).map(handler ->
                {
                    PlayerInvWrapper invWrapper = new PlayerInvWrapper(player.getInventory());
                    FluidActionResult result = isInserting ?
                                               FluidUtil.tryEmptyContainerAndStow(player.getItemInHand(hand), handler, invWrapper, capacity, player, true) :
                                               FluidUtil.tryFillContainerAndStow(player.getItemInHand(hand), handler, invWrapper, capacity, player, true);

                    if(result.isSuccess()) {
                        player.setItemInHand(hand, result.getResult());
                        return true;
                    }

                    return false;
                }).orElse(false);
            }
            return false;
        }).orElse(false);
    }
}
