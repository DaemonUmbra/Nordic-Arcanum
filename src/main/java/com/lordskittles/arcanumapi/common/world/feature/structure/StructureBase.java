package com.lordskittles.arcanumapi.common.world.feature.structure;

import com.mojang.serialization.Codec;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;

public abstract class StructureBase<C extends IFeatureConfig> extends Structure<C> {

    public StructureBase(Codec<C> codec) {

        super(codec);
    }

    public abstract int getDistance();

    public abstract int getSeparation();

    public abstract int getSeedModifier();
}
