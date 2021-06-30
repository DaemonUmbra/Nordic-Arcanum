package com.lordskittles.nordicarcanum.common.events;

import com.lordskittles.arcanumapi.common.utilities.MagicUtilities;
import com.lordskittles.arcanumapi.magic.ArcanumServerManager;
import com.lordskittles.nordicarcanum.common.advancements.Advancements;
import com.lordskittles.nordicarcanum.common.registry.Items;
import com.lordskittles.nordicarcanum.common.registry.Sounds;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.common.registry.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = NordicArcanum.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EntityEvents
{
    @SubscribeEvent
    public static void onEntityTick(LivingUpdateEvent event)
    {
        if (event.getEntityLiving() instanceof PlayerEntity)
        {
            PlayerEntity player = (PlayerEntity)event.getEntityLiving();
            if (player.world.getBlockState(player.getPosition()) == Blocks.liquid_arcanum.get().getDefaultState())
            {
                if (MagicUtilities.getCurrentArcanum(player) < 50)
                {
                    try
                    {
                        ArcanumServerManager.replenishArcanum((ServerPlayerEntity) player, 50, NordicArcanum.PACKET_HANDLER);
                        player.world.setBlockState(player.getPosition(), net.minecraft.block.Blocks.AIR.getDefaultState());
                        Sounds.play(SoundEvents.ITEM_BUCKET_FILL, player.world, player.getPosition(), 0.5f);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onItemPickup(PlayerEvent.ItemPickupEvent event)
    {
        if (event.getPlayer() instanceof ServerPlayerEntity)
        {
            if (event.getStack().getItem() == Items.arcane_dust.get())
            {
                Advancements.arcane_dust_obtain.trigger((ServerPlayerEntity) event.getPlayer());
            }
        }
    }

    @SubscribeEvent
    public static void onItemCrafted(PlayerEvent.ItemCraftedEvent event)
    {
        if (event.getPlayer() instanceof ServerPlayerEntity)
        {
            if (event.getCrafting().getItem() == Items.crafting_cloth_item.get())
            {
                Advancements.crafting_cloth_crafted.trigger((ServerPlayerEntity)event.getPlayer());
            }
        }
    }
}
