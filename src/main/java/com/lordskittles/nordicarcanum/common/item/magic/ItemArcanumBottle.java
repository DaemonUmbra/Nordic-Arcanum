package com.lordskittles.nordicarcanum.common.item.magic;

import com.lordskittles.arcanumapi.arcanum.ArcanumServerManager;
import com.lordskittles.arcanumapi.common.item.ItemMod;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicItemGroup;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class ItemArcanumBottle extends ItemMod {

    public ItemArcanumBottle() {

        super(new Item.Properties().tab(NordicItemGroup.INSTANCE));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {

        Player player = entity instanceof Player ? (Player) entity : null;
        if(player instanceof ServerPlayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayer) player, stack);
        }

        if(! world.isClientSide) {
            try {
                ArcanumServerManager.replenishArcanum((ServerPlayer) player, 50, NordicArcanum.PACKET_HANDLER);
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }

        if(player != null) {
            player.awardStat(Stats.ITEM_USED.get(this));
            if(! player.isCreative()) {
                stack.shrink(1);
            }
        }

        if(player == null || ! player.isCreative()) {
            if(stack.isEmpty()) {
                return new ItemStack(Items.GLASS_BOTTLE);
            }

            if(player != null) {
                player.getInventory().add(new ItemStack(Items.GLASS_BOTTLE));
            }
        }

        return stack;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {

        player.startUsingItem(hand);
        return InteractionResultHolder.success(player.getItemInHand(hand));
    }

    @Override
    public int getUseDuration(ItemStack stack) {

        return 32;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {

        return UseAnim.DRINK;
    }
}
