package com.lordskittles.nordicarcanum.common.particle;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Locale;

public class OpaqueColoredParticleData implements IParticleData {

    public static final IParticleData.IDeserializer<OpaqueColoredParticleData> DESERIALIZER = createSerializer();

    private final float red;
    private final float green;
    private final float blue;
    private final float alpha;

    public OpaqueColoredParticleData(float red, float green, float blue, float alpha) {

        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = MathHelper.clamp(alpha, 0.01f, 4.0f);
    }

    @OnlyIn(Dist.CLIENT)
    public final float getRed() {

        return this.red;
    }

    @OnlyIn(Dist.CLIENT)
    public final float getGreen() {

        return this.green;
    }

    @OnlyIn(Dist.CLIENT)
    public final float getBlue() {

        return this.blue;
    }

    @OnlyIn(Dist.CLIENT)
    public final float getAlpha() {

        return this.alpha;
    }

    @Override
    public ParticleType<?> getType() {

        return /*Particles.opaque_colored_particle.get()*/null;
    }

    @Override
    public void write(PacketBuffer buffer) {

        buffer.writeFloat(this.red);
        buffer.writeFloat(this.green);
        buffer.writeFloat(this.blue);
        buffer.writeFloat(this.alpha);
    }

    @Override
    public String getParameters() {

        return String.format(Locale.ROOT, "%s %.2f %.2f %.2f %.2f", Registry.PARTICLE_TYPE.getKey(this.getType()), this.red, this.green, this.blue, this.alpha);
    }

    private static final IParticleData.IDeserializer<OpaqueColoredParticleData> createSerializer() {

        return new IDeserializer<OpaqueColoredParticleData>() {

            @Override
            public OpaqueColoredParticleData deserialize(ParticleType<OpaqueColoredParticleData> type, StringReader reader) throws CommandSyntaxException {

                reader.expect(' ');
                float red = (float) reader.readDouble();
                float green = (float) reader.readDouble();
                float blue = (float) reader.readDouble();
                float alpha = (float) reader.readDouble();

                return new OpaqueColoredParticleData(red, green, blue, alpha);
            }

            @Override
            public OpaqueColoredParticleData read(ParticleType<OpaqueColoredParticleData> type, PacketBuffer buffer) {

                return new OpaqueColoredParticleData(buffer.readFloat(), buffer.readFloat(), buffer.readFloat(), buffer.readFloat());
            }
        };
    }
}
