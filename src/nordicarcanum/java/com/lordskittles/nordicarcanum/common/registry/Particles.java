package com.lordskittles.nordicarcanum.common.registry;

import com.lordskittles.arcanumapi.core.ArcanumNames;
import com.lordskittles.nordicarcanum.common.particle.AttunementAltarParticle;
import com.lordskittles.nordicarcanum.common.particle.AttunementAltarParticleType;
import com.lordskittles.nordicarcanum.common.particle.TransformSparkleParticle;
import com.lordskittles.nordicarcanum.common.particle.TransformSparkleParticleType;
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
public class Particles {

    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, NordicArcanum.MODID);

    public static final RegistryObject<AttunementAltarParticleType> ar_particle = PARTICLES.register(ArcanumNames.AR + NordicNames.PARTICLE, () -> new AttunementAltarParticleType(1, 1, 1, 1));
    public static final RegistryObject<AttunementAltarParticleType> fe_particle = PARTICLES.register(ArcanumNames.FE + NordicNames.PARTICLE, () -> new AttunementAltarParticleType(1, 1, 1, 1));
    public static final RegistryObject<AttunementAltarParticleType> hagal_particle = PARTICLES.register(ArcanumNames.HAGAL + NordicNames.PARTICLE, () -> new AttunementAltarParticleType(1, 1, 1, 1));
    public static final RegistryObject<AttunementAltarParticleType> kaun_particle = PARTICLES.register(ArcanumNames.KAUN + NordicNames.PARTICLE, () -> new AttunementAltarParticleType(1, 1, 1, 1));
    public static final RegistryObject<AttunementAltarParticleType> ur_particle = PARTICLES.register(ArcanumNames.UR + NordicNames.PARTICLE, () -> new AttunementAltarParticleType(1, 1, 1, 1));
    public static final RegistryObject<AttunementAltarParticleType> yr_particle = PARTICLES.register(ArcanumNames.YR + NordicNames.PARTICLE, () -> new AttunementAltarParticleType(1, 1, 1, 1));
    public static final RegistryObject<TransformSparkleParticleType> transform_sparkle_particle = PARTICLES.register(NordicNames.TRANSFORM_SPARKLE + NordicNames.PARTICLE, () -> new TransformSparkleParticleType(1, 1, 1, 1));

    @SubscribeEvent
    public static void registerParticleFactory(ParticleFactoryRegisterEvent event) {

        Minecraft.getInstance().particles.registerFactory(ar_particle.get(), AttunementAltarParticle.AttunementAltar::new);
        Minecraft.getInstance().particles.registerFactory(fe_particle.get(), AttunementAltarParticle.AttunementAltar::new);
        Minecraft.getInstance().particles.registerFactory(hagal_particle.get(), AttunementAltarParticle.AttunementAltar::new);
        Minecraft.getInstance().particles.registerFactory(kaun_particle.get(), AttunementAltarParticle.AttunementAltar::new);
        Minecraft.getInstance().particles.registerFactory(ur_particle.get(), AttunementAltarParticle.AttunementAltar::new);
        Minecraft.getInstance().particles.registerFactory(yr_particle.get(), AttunementAltarParticle.AttunementAltar::new);
        Minecraft.getInstance().particles.registerFactory(transform_sparkle_particle.get(), TransformSparkleParticle.TransformSparkle::new);
    }
}
