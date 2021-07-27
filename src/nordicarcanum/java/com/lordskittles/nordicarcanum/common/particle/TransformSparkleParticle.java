package com.lordskittles.nordicarcanum.common.particle;

import com.lordskittles.arcanumapi.client.render.ArcaneRenderer;
import com.lordskittles.arcanumapi.common.utilities.MathUtilities;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TransformSparkleParticle extends TextureSheetParticle {

    private final double coordX;
    private final double coordY;
    private final double coordZ;

    protected TransformSparkleParticle(ClientLevel world, double x, double y, double z, double motionX, double motionY, double motionZ, float red, float green, float blue, float alpha) {

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
        this.rCol = red;
        this.gCol = green;
        this.bCol = blue;
        this.alpha = alpha;
        this.hasPhysics = true;
        this.lifetime = (int) (Math.random() * 10.0D) + 30;
        this.quadSize = 0.02F * (this.random.nextFloat() * 0.5F + 0.5F) * 2.0F;
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

    public void tick() {

        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if(this.age++ >= this.lifetime) {
            this.remove();
        }
        else {
            this.yo -= 0.015D;
            this.move(this.xo, this.yo, this.zo);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class TransformSparkle implements ParticleProvider<TransformSparkleParticleType> {

        private final SpriteSet spriteSet;

        public TransformSparkle(SpriteSet spriteSet) {

            this.spriteSet = spriteSet;
        }

        public Particle createParticle(TransformSparkleParticleType typeIn, ClientLevel level, double x, double y, double z, double motionX, double motionY, double motionZ) {

            float hue = MathUtilities.map(level.getRandom().nextFloat() * 6, 0, 6, 2F, 6F);
            float[] randomColor = ArcaneRenderer.HSVtoRGB(hue, .6F, 1);
            float r = randomColor[0];
            float g = randomColor[1];
            float b = randomColor[2];

            TransformSparkleParticle particle = new TransformSparkleParticle(level, x, y, z, motionX, motionY, motionZ, r, g, b, .4f);
            particle.pickSprite(this.spriteSet);
            return particle;
        }
    }
}
