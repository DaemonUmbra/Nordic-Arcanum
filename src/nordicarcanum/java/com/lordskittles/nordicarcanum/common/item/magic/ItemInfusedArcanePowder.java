package com.lordskittles.nordicarcanum.common.item.magic;

import com.lordskittles.arcanumapi.common.item.ItemMod;
import com.lordskittles.arcanumapi.common.utilities.BlockUtilities;
import com.lordskittles.arcanumapi.common.utilities.MathUtilities;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicItemGroup;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicResourcesItemGroup;
import com.lordskittles.nordicarcanum.common.block.IInfusable;
import com.lordskittles.nordicarcanum.common.registry.Blocks;
import com.lordskittles.nordicarcanum.common.registry.Particles;
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
import net.minecraft.util.math.*;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import java.util.Random;

public class ItemInfusedArcanePowder extends ItemMod {

    public ItemInfusedArcanePowder() {

        super(new Item.Properties().group(NordicResourcesItemGroup.INSTANCE).maxStackSize(4));
    }

    @Override
    public boolean hasEffect(ItemStack stack) {

        return true;
    }

    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {

        BlockPos pos = context.getPos();
        Direction direction = context.getPlacementHorizontalFacing().rotateY().getOpposite();
        BlockPos right = BlockUtilities.getRightPos(direction, pos);

        World world = context.getWorld();
        PlayerEntity player = context.getPlayer();
        BlockState state = world.getBlockState(pos);

        if(state.getBlock() instanceof IInfusable) {
            IInfusable infusable = (IInfusable) state.getBlock();
            if(infusable.isValid(world, pos, right, state)) {
                playInfusionEffect(world, player, infusable.getInfusedPositions(world, pos, right, state, direction), context);
                adjustItemStack(stack, player);

                infusable.infuse(world, pos, right, state, direction);
                return ActionResultType.SUCCESS;
            }
        }
        else if(state == net.minecraft.block.Blocks.GLASS.getDefaultState()) {
            playInfusionEffect(world, player, new BlockPos[] { pos }, context);
            adjustItemStack(stack, player);

            world.setBlockState(pos, Blocks.arcane_glass.get().getDefaultState());

            return ActionResultType.SUCCESS;
        }

        return ActionResultType.PASS;
    }

    private void playInfusionEffect(World world, PlayerEntity player, BlockPos[] pos, ItemUseContext context) {

        Vector3d playerPos = MathUtilities.posToHandPos(player, context.getHand());
        Random rand = world.rand;

        int count = 50;
        for(int i = 0; i < count / pos.length; i++) {

            boolean floaty = (i < count / 3);

            for(BlockPos x : pos) {

                Vector3d blockPos = new Vector3d(x.getX(), x.getY(), x.getZ());
                blockPos = blockPos.add(0.5D, 0.5D, 0.5D);
                blockPos = blockPos.subtract(playerPos);

                world.addParticle(
                        Particles.transform_sparkle_particle.get(),
                        playerPos.x,
                        playerPos.y,
                        playerPos.z,
                        blockPos.x / 11.0D + rand.nextGaussian() * 0.025D,
                        blockPos.y / 11.0D + rand.nextGaussian() * 0.025D + (floaty ? 0.05D : 0.15D),
                        blockPos.z / 11.0D + rand.nextGaussian() * 0.025D);
            }
        }
        Sounds.play(Sounds.infusion.get(), world, SoundCategory.PLAYERS, pos[0], player, 1F, 0.1F, 0.9F);
    }

    private void adjustItemStack(ItemStack stack, PlayerEntity player) {

        if(player.isCreative())
            return;

        ItemStack newStack = ItemStack.EMPTY;
        if(stack.getCount() > 1) {
            newStack = new ItemStack(stack.getItem(), stack.getCount() - 1);
        }

        player.setItemStackToSlot(EquipmentSlotType.MAINHAND, newStack);
    }
}
