package com.lordskittles.nordicarcanum.common.particle;

import com.lordskittles.arcanumapi.client.render.ArcaneRenderer;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class OpaqueColoredParticle extends SpriteTexturedParticle
{
    private ArcaneRenderer.DVector3 pos;

    public OpaqueColoredParticle(ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, OpaqueColoredParticleData data, IAnimatedSprite sprite)
    {
        super(world, x, y, z, xSpeed, ySpeed, zSpeed);

        this.motionX = xSpeed;
        this.motionY = ySpeed;
        this.motionZ = zSpeed;

        this.pos = new ArcaneRenderer.DVector3(x, y, z);

        this.particleScale = 0.1f * (this.rand.nextFloat() * 0.2f + 0.5f);

        float offset = (float)Math.random() * 0.4f + 0.6f;
        this.particleRed = ((float) (Math.random() * (double)0.2f) + 0.8f) * data.getRed() * offset;
        this.particleGreen = ((float) (Math.random() * (double)0.2f) + 0.8f) * data.getGreen() * offset;
        this.particleBlue = ((float) (Math.random() * (double)0.2f) + 0.8f) * data.getBlue() * offset;

        this.maxAge = (int)(Math.random() * 10d) + 40;
    }

    @Override
    public void tick()
    {
        this.prevPosX = pos.x;
        this.prevPosY = pos.y;
        this.prevPosZ = pos.z;

        if (this.age++ >= this.maxAge)
        {
            this.setExpired();
        }
        else
        {
            float scalar = (float)this.age / (float)this.maxAge;
            float offset = -scalar + scalar * scalar * 2.0f;
            float inverseOffset = 1.0f - offset;

            this.pos = new ArcaneRenderer.DVector3(
                    this.pos.x + this.motionX * (double)inverseOffset,
                    this.pos.y + this.motionY * (double)inverseOffset,
                    this.pos.z + this.motionZ * (double)inverseOffset
            );
        }
    }

    @Override
    public IParticleRenderType getRenderType()
    {
        return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<OpaqueColoredParticleData>
    {
        private final IAnimatedSprite spriteSet;

        public Factory(IAnimatedSprite sprite)
        {
            this.spriteSet = sprite;
        }

        @Nullable
        @Override
        public Particle makeParticle(OpaqueColoredParticleData type, ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed)
        {
            OpaqueColoredParticle particle = new OpaqueColoredParticle(world, x, y, z, xSpeed, ySpeed, zSpeed, type, this.spriteSet);

            particle.selectSpriteRandomly(this.spriteSet);

            return particle;
        }
    }
}
