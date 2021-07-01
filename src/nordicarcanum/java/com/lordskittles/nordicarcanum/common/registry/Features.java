package com.lordskittles.nordicarcanum.common.registry;

import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicNames;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class Features {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, NordicArcanum.MODID);

    public static final RegistryObject<TreeFeature> yew_tree = FEATURES.register(NordicNames.YEW + NordicNames.TREE_SUFFIX, () -> new TreeFeature(BaseTreeFeatureConfig.CODEC));
    public static final RegistryObject<TreeFeature> pine_tree = FEATURES.register(NordicNames.PINE + NordicNames.TREE_SUFFIX, () -> new TreeFeature(BaseTreeFeatureConfig.CODEC));
    public static final RegistryObject<TreeFeature> juniper_tree = FEATURES.register(NordicNames.JUNIPER + NordicNames.TREE_SUFFIX, () -> new TreeFeature(BaseTreeFeatureConfig.CODEC));
}
