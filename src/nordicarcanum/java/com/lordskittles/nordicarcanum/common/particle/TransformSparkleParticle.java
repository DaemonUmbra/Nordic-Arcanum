package com.lordskittles.nordicarcanum.common.particle;

import com.lordskittles.arcanumapi.client.render.ArcaneRenderer;
import com.lordskittles.arcanumapi.common.utilities.MathUtilities;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TransformSparkleParticle extends SpriteTexturedParticle {

    private final double coordX;
    private final double coordY;
    private final double coordZ;

    protected TransformSparkleParticle(ClientWorld world, double x, double y, double z, double motionX, double motionY, double motionZ, float red, float green, float blue, float alpha) {

        super(world, x, y, z);
        this.motionX = motionX;
        this.motionY = motionY;
        this.motionZ = motionZ;
        this.coordX = x;
        this.coordY = y;
        this.coordZ = z;
        this.prevPosX = x + motionX;
        this.prevPosY = y + motionY;
        this.prevPosZ = z + motionZ;
        this.posX = this.prevPosX;
        this.posY = this.prevPosY;
        this.posZ = this.prevPosZ;
        this.particleScale = 0.08F * (this.rand.nextFloat() * 0.25F + 0.2F);
        float f = this.rand.nextFloat() * 0.6F + 0.4F;
        this.particleRed = red;
        this.particleGreen = green;
        this.particleBlue = blue;
        this.particleAlpha = alpha;
        this.canCollide = false;
        this.maxAge = (int) (Math.random() * 10.0D) + 30;
        this.particleScale = 0.11F * (this.rand.nextFloat() * 0.5F + 0.5F) * 2.0F;
    }

    @Override
    public IParticleRenderType getRenderType() {

        return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public void move(double x, double y, double z) {

        this.setBoundingBox(this.getBoundingBox().offset(x, y, z)); this.resetPositionToBB();
    }

    public int getBrightnessForRender(float partialTick) {

        int i = super.getBrightnessForRender(partialTick);
        float f = (float) this.age / (float) this.maxAge; f = f * f;
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

        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        if(this.age++ >= this.maxAge) {
            this.setExpired();
        }
        else {
            float f = (float) this.age / (float) this.maxAge;
            f = 1.0F - f;
            float f1 = 1.0F - f; f1 = f1 * f1;
            f1 = f1 * f1;
            this.posX = this.coordX + this.motionX * (double) f;
            this.posY = this.coordY + this.motionY * (double) f - (double) (f1 * 1.2F);
            this.posZ = this.coordZ + this.motionZ * (double) f;
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class TransformSparkle implements IParticleFactory<TransformSparkleParticleType> {

        private final IAnimatedSprite spriteSet;

        public TransformSparkle(IAnimatedSprite spriteSet) {

            this.spriteSet = spriteSet;
        }

        public Particle makeParticle(TransformSparkleParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {

            float hue = MathUtilities.map(worldIn.rand.nextFloat() * 6, 0, 6, 2F, 6F);
            float[] randomColor = ArcaneRenderer.HSVtoRGB(hue, .6F, 1);

            TransformSparkleParticle particle = new TransformSparkleParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, randomColor[0], randomColor[1], randomColor[2], .4f);
            particle.selectSpriteRandomly(this.spriteSet);
            return particle;
        }
    }
}
