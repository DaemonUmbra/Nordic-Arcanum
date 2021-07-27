package com.lordskittles.nordicarcanum.common.registry;

import com.lordskittles.nordicarcanum.common.particle.AttunementAltarParticle;
import com.lordskittles.nordicarcanum.common.particle.AttunementAltarParticleType;
import com.lordskittles.nordicarcanum.common.particle.TransformSparkleParticle;
import com.lordskittles.nordicarcanum.common.particle.TransformSparkleParticleType;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicNames;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = NordicArcanum.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class Particles {

    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, NordicArcanum.MODID);

    public static final RegistryObject<AttunementAltarParticleType> ar_particle = PARTICLES.register(NordicNames.AR + NordicNames.PARTICLE, () -> new AttunementAltarParticleType(1, 1, 1, 1));
    public static final RegistryObject<AttunementAltarParticleType> fe_particle = PARTICLES.register(NordicNames.FE + NordicNames.PARTICLE, () -> new AttunementAltarParticleType(1, 1, 1, 1));
    public static final RegistryObject<AttunementAltarParticleType> hagal_particle = PARTICLES.register(NordicNames.HAGAL + NordicNames.PARTICLE, () -> new AttunementAltarParticleType(1, 1, 1, 1));
    public static final RegistryObject<AttunementAltarParticleType> kaun_particle = PARTICLES.register(NordicNames.KAUN + NordicNames.PARTICLE, () -> new AttunementAltarParticleType(1, 1, 1, 1));
    public static final RegistryObject<AttunementAltarParticleType> ur_particle = PARTICLES.register(NordicNames.UR + NordicNames.PARTICLE, () -> new AttunementAltarParticleType(1, 1, 1, 1));
    public static final RegistryObject<AttunementAltarParticleType> yr_particle = PARTICLES.register(NordicNames.YR + NordicNames.PARTICLE, () -> new AttunementAltarParticleType(1, 1, 1, 1));
    public static final RegistryObject<TransformSparkleParticleType> transform_sparkle_particle = PARTICLES.register(NordicNames.TRANSFORM_SPARKLE + NordicNames.PARTICLE, () -> new TransformSparkleParticleType(1, 1, 1, 1));

    @SubscribeEvent
    public static void registerParticleFactory(ParticleFactoryRegisterEvent event) {

        Minecraft.getInstance().particleEngine.register(ar_particle.get(), AttunementAltarParticle.AttunementAltar::new);
        Minecraft.getInstance().particleEngine.register(fe_particle.get(), AttunementAltarParticle.AttunementAltar::new);
        Minecraft.getInstance().particleEngine.register(hagal_particle.get(), AttunementAltarParticle.AttunementAltar::new);
        Minecraft.getInstance().particleEngine.register(kaun_particle.get(), AttunementAltarParticle.AttunementAltar::new);
        Minecraft.getInstance().particleEngine.register(ur_particle.get(), AttunementAltarParticle.AttunementAltar::new);
        Minecraft.getInstance().particleEngine.register(yr_particle.get(), AttunementAltarParticle.AttunementAltar::new);
        Minecraft.getInstance().particleEngine.register(transform_sparkle_particle.get(), TransformSparkleParticle.TransformSparkle::new);
    }
}
