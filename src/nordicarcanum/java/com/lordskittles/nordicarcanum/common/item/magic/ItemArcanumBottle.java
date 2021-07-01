package com.lordskittles.nordicarcanum.common.item.magic;

import com.lordskittles.arcanumapi.common.item.ItemMod;
import com.lordskittles.arcanumapi.magic.ArcanumServerManager;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicItemGroup;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class ItemArcanumBottle extends ItemMod {

    public ItemArcanumBottle() {

        super(new Item.Properties().group(NordicItemGroup.INSTANCE));
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World world, LivingEntity entity) {

        PlayerEntity player = entity instanceof PlayerEntity ? (PlayerEntity) entity : null;
        if(player instanceof ServerPlayerEntity) {
            CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayerEntity) player, stack);
        }

        if(! world.isRemote) {
            try {
                ArcanumServerManager.replenishArcanum((ServerPlayerEntity) player, 50, NordicArcanum.PACKET_HANDLER);
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }

        if(player != null) {
            player.addStat(Stats.ITEM_USED.get(this));
            if(! player.abilities.isCreativeMode) {
                stack.shrink(1);
            }
        }

        if(player == null || ! player.abilities.isCreativeMode) {
            if(stack.isEmpty()) {
                return new ItemStack(Items.GLASS_BOTTLE);
            }

            if(player != null) {
                player.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
            }
        }

        return stack;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {

        player.setActiveHand(hand);
        return ActionResult.resultSuccess(player.getHeldItem(hand));
    }

    @Override
    public int getUseDuration(ItemStack stack) {

        return 32;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {

        return UseAction.DRINK;
    }
}
