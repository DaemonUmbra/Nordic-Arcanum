package com.lordskittles.arcanumapi.common.world.feature.structure;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.StructureFeature;

public abstract class StructureBase<C extends FeatureConfiguration> extends StructureFeature<C> {

    public StructureBase(Codec<C> codec) {

        super(codec);
    }

    public abstract int getDistance();

    public abstract int getSeparation();

    public abstract int getSeedModifier();
}
