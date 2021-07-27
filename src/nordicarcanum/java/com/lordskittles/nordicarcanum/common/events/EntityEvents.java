package com.lordskittles.nordicarcanum.common.events;

import com.lordskittles.arcanumapi.common.utilities.MagicUtilities;
import com.lordskittles.arcanumapi.arcanum.ArcanumServerManager;
import com.lordskittles.nordicarcanum.common.advancements.Advancements;
import com.lordskittles.nordicarcanum.common.registry.Blocks;
import com.lordskittles.nordicarcanum.common.registry.Items;
import com.lordskittles.nordicarcanum.common.registry.Sounds;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = NordicArcanum.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EntityEvents {

    @SubscribeEvent
    public static void onEntityTick(LivingUpdateEvent event) {

        if(event.getEntityLiving() instanceof Player) {
            Player player = (Player) event.getEntityLiving();
            if(player.level.getBlockState(player.blockPosition()) == Blocks.liquid_arcanum.get().defaultBlockState()) {
                if(MagicUtilities.getCurrentArcanum(player) < 50) {
                    try {
                        ArcanumServerManager.replenishArcanum((ServerPlayer) player, 50, NordicArcanum.PACKET_HANDLER);
                        player.level.setBlock(player.blockPosition(), net.minecraft.world.level.block.Blocks.AIR.defaultBlockState(), 19);
                        Sounds.play(SoundEvents.BUCKET_FILL, player.level, player.blockPosition(), 0.5f);
                    }
                    catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onItemPickup(PlayerEvent.ItemPickupEvent event) {

        if(event.getPlayer() instanceof ServerPlayer) {
            if(event.getStack().getItem() == Items.arcane_powder.get()) {
                Advancements.arcane_powder_obtain.trigger((ServerPlayer) event.getPlayer());
            }
        }
    }

    @SubscribeEvent
    public static void onItemCrafted(PlayerEvent.ItemCraftedEvent event) {

        if(event.getPlayer() instanceof ServerPlayer) {
            if(event.getCrafting().getItem() == Items.crafting_cloth_item.get()) {
                Advancements.crafting_cloth_crafted.trigger((ServerPlayer) event.getPlayer());
            }
        }
    }
}
