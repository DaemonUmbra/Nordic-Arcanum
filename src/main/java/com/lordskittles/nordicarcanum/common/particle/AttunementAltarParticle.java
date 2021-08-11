package com.lordskittles.nordicarcanum.common.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AttunementAltarParticle extends TextureSheetParticle {

    private final double coordX;
    private final double coordY;
    private final double coordZ;

    protected AttunementAltarParticle(ClientLevel world, double x, double y, double z, double motionX, double motionY, double motionZ) {

        super(world, x, y, z);
        this.xd = motionX;
        this.yd = motionY;
        this.zd = motionZ;
        this.coordX = x;
        this.coordY = y;
        this.coordZ = z;
        this.xo = x + motionX;
        this.yo = y + motionY;
        this.zo = z + motionZ;
        this.x = this.xo;
        this.y = this.yo;
        this.z = this.zo;
        float f = this.random.nextFloat() * 0.6F + 0.4F;
        this.rCol = 0.9F * f;
        this.gCol = 0.9F * f;
        this.bCol = f;
        this.hasPhysics = false;
        this.lifetime = (int) (Math.random() * 10.0D) + 30;
        this.quadSize = 0.11F * (this.random.nextFloat() * 0.5F + 0.5F) * 2.0F;
    }

    @Override
    public ParticleRenderType getRenderType() {

        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public void move(double x, double y, double z) {

        this.setBoundingBox(this.getBoundingBox().move(x, y, z));
        this.setLocationFromBoundingbox();
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

    public void tick() {

        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if(this.age++ >= this.lifetime) {
            this.remove();
        }
        else {
            float f = (float) this.age / (float) this.lifetime;
            f = 1.0F - f;
            float f1 = 1.0F - f;
            f1 = f1 * f1;
            f1 = f1 * f1;
            this.x = this.coordX + this.xd * (double) f;
            this.y = this.coordY + this.yd * (double) f - (double) (f1 * 1.2F);
            this.z = this.coordZ + this.zd * (double) f;
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<AttunementAltarParticleType> {

        private final SpriteSet spriteSet;

        public Provider(SpriteSet spriteSet) {

            this.spriteSet = spriteSet;
        }

        public Particle createParticle(AttunementAltarParticleType typeIn, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {

            AttunementAltarParticle particle = new AttunementAltarParticle(level, x, y, z, xSpeed, ySpeed, zSpeed);
            particle.pickSprite(this.spriteSet);
            return particle;
        }
    }
}
