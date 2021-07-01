package com.lordskittles.arcanumapi.client.render.item;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.TransformationMatrix;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.IBlockDisplayReader;
import net.minecraftforge.client.model.data.IModelData;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ItemModelWrapper implements IBakedModel {

    private final IBakedModel internal;

    private TransformType transform = TransformType.NONE;

    public ItemModelWrapper(IBakedModel internal) {

        this.internal = internal;
    }

    @Override
    public IBakedModel getBakedModel() {

        return this.internal;
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
    public boolean isAmbientOcclusion() {

        return this.internal.isAmbientOcclusion();
    }

    @Override
    public boolean isGui3d() {

        return this.internal.isGui3d();
    }

    @Override
    public boolean isSideLit() {

        return this.internal.isSideLit();
    }

    @Override
    public boolean isBuiltInRenderer() {

        return true;
    }

    @Override
    public TextureAtlasSprite getParticleTexture() {

        return this.internal.getParticleTexture();
    }

    @Override
    public ItemOverrideList getOverrides() {

        return ItemOverrideList.EMPTY;
    }

    @Nonnull
    @Deprecated
    @Override
    public ItemCameraTransforms getItemCameraTransforms() {

        return internal.getItemCameraTransforms();
    }

    @Nonnull
    @Override
    public IModelData getModelData(@Nonnull IBlockDisplayReader world, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nonnull IModelData tileData) {

        return internal.getModelData(world, pos, state, tileData);
    }

    @Override
    public IBakedModel handlePerspective(TransformType type, MatrixStack stack) {

        transform = type;

        TransformationMatrix matrix = transforms.get(type);
        if(! matrix.isIdentity()) {
            matrix.push(stack);
        }

        return this;
    }

    @Nonnull
    public TransformType getTransform() {

        return this.transform;
    }

    public static Map<TransformType, TransformationMatrix> transforms = ImmutableMap.<TransformType, TransformationMatrix>builder()
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

    private static TransformationMatrix get(float tx, float ty, float tz, float ax, float ay, float az, float s) {

        return new TransformationMatrix(new Vector3f(tx / 16, ty / 16, tz / 16),
                new Quaternion(ax, ay, az, true),
                new Vector3f(s, s, s), null);
    }
}
