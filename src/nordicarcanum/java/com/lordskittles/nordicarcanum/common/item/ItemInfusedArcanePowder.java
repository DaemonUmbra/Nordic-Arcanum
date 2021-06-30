package com.lordskittles.nordicarcanum.common.item;

import com.lordskittles.arcanumapi.common.item.ItemMod;
import com.lordskittles.arcanumapi.common.utilities.BlockUtilities;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicItemGroup;
import com.lordskittles.nordicarcanum.common.block.BlockSigilPodium;
import com.lordskittles.nordicarcanum.common.block.IInfusable;
import com.lordskittles.nordicarcanum.common.registry.Sounds;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemInfusedArcanePowder extends ItemMod
{
    public ItemInfusedArcanePowder()
    {
        super(new Item.Properties().group(NordicItemGroup.INSTANCE).maxStackSize(4));
    }

    @Override
    public boolean hasEffect(ItemStack stack)
    {
        return true;
    }

    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context)
    {
        BlockPos pos = context.getPos();
        Direction direction = context.getPlacementHorizontalFacing().rotateY().getOpposite();
        BlockPos right = BlockUtilities.getRightPos(direction, pos);

        World world = context.getWorld();
        PlayerEntity player = context.getPlayer();
        BlockState state = world.getBlockState(pos);

        if(state.getBlock() instanceof IInfusable)
        {
            IInfusable infusable = (IInfusable) state.getBlock();
            if(infusable.isValid(world, pos, right, state))
            {
                playerInfusionSound(world, player, pos);
                adjustItemStack(stack, player);

                infusable.infuse(world, pos, right, state, direction);
                return ActionResultType.SUCCESS;
            }
        }
        else if(state == net.minecraft.block.Blocks.GLASS.getDefaultState())
        {
            playerInfusionSound(world, player, pos);
            adjustItemStack(stack, player);

            world.setBlockState(pos, state);

            return ActionResultType.SUCCESS;
        }
        // TODO: this is a temporary fix due to the schematic issue. this will be removed when the issue is fixed
        else if(state.getBlock() instanceof BlockSigilPodium && !state.get(BlockSigilPodium.ISCORE))
        {
            playerInfusionSound(world, player, pos);
            adjustItemStack(stack, player);

            world.setBlockState(pos, state.with(BlockSigilPodium.ISCORE, true));

            return ActionResultType.SUCCESS;
        }

        return ActionResultType.PASS;
    }

    private void playerInfusionSound(World world, PlayerEntity player, BlockPos pos)
    {
        Sounds.play(Sounds.infusion.get(), world, SoundCategory.PLAYERS, pos, player, 1F, 0.1F, 0.9F);
    }

    private void adjustItemStack(ItemStack stack, PlayerEntity player)
    {
        if (player.isCreative())
            return;

        ItemStack newStack = ItemStack.EMPTY;
        if (stack.getCount() > 1)
        {
            newStack = new ItemStack(stack.getItem(), stack.getCount() - 1);
        }

        player.setItemStackToSlot(EquipmentSlotType.MAINHAND, newStack);
    }
}
