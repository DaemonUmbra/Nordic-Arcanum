package com.lordskittles.nordicarcanum.common.registry;

import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicNames;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Sounds
{
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, NordicArcanum.MODID);

    public static final RegistryObject<SoundEvent> viking_saw = SOUNDS.register(NordicNames.ITEM_SOUND + NordicNames.VIKING_SAW, () -> new SoundEvent(NordicArcanum.RL(NordicNames.ITEM_SOUND + NordicNames.VIKING_SAW)));
    public static final RegistryObject<SoundEvent> infusion = SOUNDS.register(NordicNames.INFUSION_SOUND, () -> new SoundEvent(NordicArcanum.RL(NordicNames.INFUSION_SOUND)));

    public static void play(SoundEvent sound, World world, BlockPos pos, float volume)
    {
        play(sound, world, SoundCategory.BLOCKS, pos, null, volume, 0.1F, 0.9F);
    }

    public static void play(SoundEvent sound, World world, SoundCategory category, BlockPos pos, PlayerEntity player, float volume, float pitchMultiplier, float pitchOffset)
    {
        double x = pos.getX() + 0.5D;
        double y = pos.getY() + 0.5D;
        double z = pos.getZ() + 0.5D;

        world.playSound(player, x, y, z, sound, category, volume, world.rand.nextFloat() * pitchMultiplier + pitchOffset);
    }
}
