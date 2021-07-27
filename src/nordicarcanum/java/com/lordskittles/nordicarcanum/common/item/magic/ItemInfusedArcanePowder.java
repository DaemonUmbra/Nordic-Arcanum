package com.lordskittles.nordicarcanum.common.item.magic;

import com.lordskittles.arcanumapi.common.item.ItemMod;
import com.lordskittles.arcanumapi.common.utilities.BlockUtilities;
import com.lordskittles.arcanumapi.common.utilities.MathUtilities;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicResourcesItemGroup;
import com.lordskittles.nordicarcanum.common.block.IInfusable;
import com.lordskittles.nordicarcanum.common.registry.Blocks;
import com.lordskittles.nordicarcanum.common.registry.Particles;
import com.lordskittles.nordicarcanum.common.registry.Sounds;
import com.mojang.math.Vector3d;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class ItemInfusedArcanePowder extends ItemMod {

    public ItemInfusedArcanePowder() {

        super(new Item.Properties().tab(NordicResourcesItemGroup.INSTANCE).stacksTo(4));
    }

    @Override
    public boolean isFoil(ItemStack stack) {

        return true;
    }

    @Override
    public InteractionResult onItemUseFirst(ItemStack stack, UseOnContext context) {

        BlockPos pos = context.getClickedPos();
        Direction direction = context.getHorizontalDirection().getClockWise().getOpposite();
        BlockPos right = BlockUtilities.getRightPos(direction, pos);

        Level world = context.getLevel();
        Player player = context.getPlayer();
        BlockState state = world.getBlockState(pos);

        if(state.getBlock() instanceof IInfusable) {
            IInfusable infusable = (IInfusable) state.getBlock();
            if(infusable.isValid(world, pos, right, state)) {
                playInfusionEffect(world, player, infusable.getInfusedPositions(world, pos, right, state, direction), context);
                adjustItemStack(stack, player);

                infusable.infuse(world, pos, right, state, direction);
                return InteractionResult.SUCCESS;
            }
        }
        else if(state == net.minecraft.world.level.block.Blocks.GLASS.defaultBlockState()) {
            playInfusionEffect(world, player, new BlockPos[] { pos }, context);
            adjustItemStack(stack, player);

            world.setBlock(pos, Blocks.arcane_glass.get().defaultBlockState(), 19);

            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    private void playInfusionEffect(Level level, Player player, BlockPos[] pos, UseOnContext context) {

        Vec3 playerPos = MathUtilities.posToHandPos(player, context.getHand());
        Random rand = level.getRandom();

        int count = 50;
        for(int i = 0; i < count / pos.length; i++) {

            boolean floaty = (i < count / 3);

            for(BlockPos x : pos) {

                Vec3 blockPos = new Vec3(x.getX(), x.getY(), x.getZ());
                blockPos = blockPos.add(0.5D, 0.5D, 0.5D);
                blockPos = blockPos.subtract(playerPos);

                level.addParticle(
                        Particles.transform_sparkle_particle.get(),
                        playerPos.x,
                        playerPos.y,
                        playerPos.z,
                        blockPos.x / 11.0D + rand.nextGaussian() * 0.025D,
                        blockPos.y / 11.0D + rand.nextGaussian() * 0.025D + (floaty ? 0.05D : 0.15D),
                        blockPos.z / 11.0D + rand.nextGaussian() * 0.025D);
            }
        }
        Sounds.play(Sounds.infusion.get(), level, SoundSource.PLAYERS, pos[0], player, 1F, 0.1F, 0.9F);
    }

    private void adjustItemStack(ItemStack stack, Player player) {

        if(player.isCreative())
            return;

        ItemStack newStack = ItemStack.EMPTY;
        if(stack.getCount() > 1) {
            newStack = new ItemStack(stack.getItem(), stack.getCount() - 1);
        }

        player.setItemSlot(EquipmentSlot.MAINHAND, newStack);
    }
}
