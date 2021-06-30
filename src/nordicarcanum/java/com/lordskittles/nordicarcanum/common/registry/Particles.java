package com.lordskittles.nordicarcanum.common.registry;

import com.lordskittles.nordicarcanum.common.particle.OpaqueColoredParticle;
import com.lordskittles.nordicarcanum.common.particle.OpaqueColoredParticleData;
import com.lordskittles.nordicarcanum.common.particle.TranslucentColoredParticle;
import com.lordskittles.nordicarcanum.common.particle.TranslucentColoredParticleData;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicNames;
import net.minecraft.client.Minecraft;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = NordicArcanum.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class Particles
{
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, NordicArcanum.MODID);

//    public static final RegistryObject<ParticleType<OpaqueColoredParticleData>> opaque_colored_particle = PARTICLES.register(NordicNames.OPAQUE + NordicNames.COLORED + NordicNames.PARTICLE, () -> new ParticleType<OpaqueColoredParticleData>(false, OpaqueColoredParticleData.DESERIALIZER));
//    public static final RegistryObject<ParticleType<TranslucentColoredParticleData>> translucent_colored_particle = PARTICLES.register(NordicNames.TRANSLUCENT + NordicNames.COLORED + NordicNames.PARTICLE, () -> new ParticleType<TranslucentColoredParticleData>(false, TranslucentColoredParticleData.DESERIALIZER));

    @SubscribeEvent
    public static void registerParticleFactory(ParticleFactoryRegisterEvent event)
    {
//        Minecraft.getInstance().particles.registerFactory(opaque_colored_particle.get(), OpaqueColoredParticle.Factory::new);
//        Minecraft.getInstance().particles.registerFactory(translucent_colored_particle.get(), TranslucentColoredParticle.Factory::new);
    }
}
