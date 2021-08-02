package com.lordskittles.nordicarcanum.common.particle;

import com.lordskittles.nordicarcanum.client.utils.Color;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TransformSparkleParticle extends TextureSheetParticle {

    protected TransformSparkleParticle(ClientLevel world, double x, double y, double z, double motionX, double motionY, double motionZ, Color color) {

        super(world, x, y, z);
        this.xd = motionX;
        this.yd = motionY;
        this.zd = motionZ;
        this.xo = x + motionX;
        this.yo = y + motionY;
        this.zo = z + motionZ;
        this.x = this.xo;
        this.y = this.yo;
        this.z = this.zo;
        this.rCol = color.r;
        this.gCol = color.g;
        this.bCol = color.b;
        this.alpha = color.a;
        this.hasPhysics = true;
        this.lifetime = (int) (Math.random() * 10.0D) + 30;
        this.quadSize = 0.05F * (this.random.nextFloat() * 0.5F + 0.5F) * 2.0F;
        this.gravity = 0.2F;
    }

    @Override
    public ParticleRenderType getRenderType() {

        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public int getLightColor(float partialTick) {

        int i = super.getLightColor(partialTick);
        float f = (float) this.age / (float) this.lifetime;
        f = f * f;
        f = f * f;
        int j = i & 255;
        int k = i >> 16 & 255;
        k = k + (int) (f * 15.0F * 16.0F);
        if(k > 240) {
            k = 240;
        }

        return j | k << 16;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<TransformSparkleParticleType> {

        private final SpriteSet spriteSet;

        public Provider(SpriteSet spriteSet) {

            this.spriteSet = spriteSet;
        }

        public Particle createParticle(TransformSparkleParticleType typeIn, ClientLevel level, double x, double y, double z, double motionX, double motionY, double motionZ) {

            TransformSparkleParticle particle = new TransformSparkleParticle(level, x, y, z, motionX, motionY, motionZ, Color.fromRandomHue(level.random, 2F, 6F, .6F, 1, .4F));
            particle.pickSprite(this.spriteSet);
            return particle;
        }
    }
}
