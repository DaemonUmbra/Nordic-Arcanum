package com.lordskittles.arcanumapi.client.render.item;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.block.model.ItemTransforms.TransformType;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import com.mojang.math.Quaternion;
import com.mojang.math.Transformation;
import com.mojang.math.Vector3f;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraftforge.client.model.data.IModelData;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ItemModelWrapper implements BakedModel {

    private final BakedModel internal;

    private TransformType transform = TransformType.NONE;

    public ItemModelWrapper(BakedModel internal) {

        this.internal = internal;
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, Random rand) {

        return this.internal.getQuads(state, side, rand);
    }

    @Nonnull
    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random rand, @Nonnull IModelData extraData) {

        return this.internal.getQuads(state, side, rand, extraData);
    }

    @Override
    public boolean useAmbientOcclusion() {

        return this.internal.useAmbientOcclusion();
    }

    @Override
    public boolean isGui3d() {

        return this.internal.isGui3d();
    }

    @Override
    public boolean usesBlockLight() {

        return this.internal.usesBlockLight();
    }

    @Override
    public boolean isCustomRenderer() {

        return true;
    }

    @Override
    public TextureAtlasSprite getParticleIcon() {

        return this.internal.getParticleIcon();
    }

    @Override
    public ItemOverrides getOverrides() {

        return ItemOverrides.EMPTY;
    }

    @Nonnull
    @Deprecated
    @Override
    public ItemTransforms getTransforms() {

        return internal.getTransforms();
    }

    @Nonnull
    @Override
    public IModelData getModelData(@Nonnull BlockAndTintGetter world, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nonnull IModelData tileData) {

        return internal.getModelData(world, pos, state, tileData);
    }

    @Override
    public BakedModel handlePerspective(TransformType type, PoseStack stack) {

        transform = type;

        Transformation matrix = transforms.get(type);
        if(! matrix.isIdentity()) {
            matrix.push(stack);
        }

        return this;
    }

    @Nonnull
    public TransformType getTransform() {

        return this.transform;
    }

    public static Map<TransformType, Transformation> transforms = ImmutableMap.<TransformType, Transformation>builder()
            .put(TransformType.GUI, get(0, 0, 0, 30, 225, 0, 0.625F))
            .put(TransformType.THIRD_PERSON_RIGHT_HAND, get(0, 2.5F, 0, 75, 45, 0, 0.375F))
            .put(TransformType.THIRD_PERSON_LEFT_HAND, get(0, 2.5F, 0, 75, 45, 0, 0.375F))
            .put(TransformType.FIRST_PERSON_RIGHT_HAND, get(0, 0, 0, 0, 45, 0, 0.4F))
            .put(TransformType.FIRST_PERSON_LEFT_HAND, get(0, 0, 0, 0, 225, 0, 0.4F))
            .put(TransformType.GROUND, get(0, 2, 0, 0, 0, 0, 0.25F))
            .put(TransformType.HEAD, get(0, 0, 0, 0, 0, 0, 1))
            .put(TransformType.FIXED, get(0, 0, 0, 0, 0, 0, 1))
            .put(TransformType.NONE, get(0, 0, 0, 0, 0, 0, 0))
            .build();

    private static Transformation get(float tx, float ty, float tz, float ax, float ay, float az, float s) {

        return new Transformation(new Vector3f(tx / 16, ty / 16, tz / 16),
                new Quaternion(ax, ay, az, true),
                new Vector3f(s, s, s), null);
    }
}
