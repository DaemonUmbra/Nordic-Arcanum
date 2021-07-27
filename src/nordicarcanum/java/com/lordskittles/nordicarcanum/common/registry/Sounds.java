package com.lordskittles.nordicarcanum.common.registry;

import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicNames;
import net.minecraft.world.entity.player.Player;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Sounds {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, NordicArcanum.MODID);

    public static final RegistryObject<SoundEvent> viking_saw = SOUNDS.register(NordicNames.ITEM_SOUND + NordicNames.VIKING_SAW, () -> new SoundEvent(NordicArcanum.RL(NordicNames.ITEM_SOUND + NordicNames.VIKING_SAW)));
    public static final RegistryObject<SoundEvent> infusion = SOUNDS.register(NordicNames.INFUSION_SOUND, () -> new SoundEvent(NordicArcanum.RL(NordicNames.INFUSION_SOUND)));

    public static void play(SoundEvent sound, Level world, BlockPos pos, float volume) {

        play(sound, world, SoundSource.BLOCKS, pos, null, volume, 0.1F, 0.9F);
    }

    public static void play(SoundEvent sound, Level world, SoundSource category, BlockPos pos, Player player, float volume, float pitchMultiplier, float pitchOffset) {

        double x = pos.getX() + 0.5D;
        double y = pos.getY() + 0.5D;
        double z = pos.getZ() + 0.5D;

        world.playSound(player, x, y, z, sound, category, volume, world.getRandom().nextFloat() * pitchMultiplier + pitchOffset);
    }
}
