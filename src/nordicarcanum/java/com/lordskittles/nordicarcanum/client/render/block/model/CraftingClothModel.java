package com.lordskittles.nordicarcanum.client.render.block.model;

import com.google.common.collect.Lists;
import com.lordskittles.arcanumapi.common.utilities.QuadUtilities;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.common.tileentity.TileEntityCraftingCloth;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.*;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockDisplayReader;
import net.minecraftforge.client.model.data.IDynamicBakedModel;
import net.minecraftforge.client.model.data.IModelData;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

public class CraftingClothModel implements IDynamicBakedModel
{
    private final IBakedModel defaultModel;

    private BlockState mimicState;
    private IBakedModel mimicModel;

    private final TextureAtlasSprite topSprite;
    private final TextureAtlasSprite sideSprite;

    public CraftingClothModel()
    {
        defaultModel = Minecraft.getInstance().getBlockRendererDispatcher().getBlockModelShapes().getModel(Blocks.PURPLE_WOOL.getDefaultState());

        AtlasTexture atlas = Minecraft.getInstance().getModelManager().getAtlasTexture(AtlasTexture.LOCATION_BLOCKS_TEXTURE);
        topSprite = atlas.getSprite(NordicArcanum.RL("blocks/crafting_cloth_top"));
        sideSprite = atlas.getSprite(NordicArcanum.RL("blocks/crafting_cloth_side"));
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random rand, @Nonnull IModelData tileData)
    {
        this.mimicState = tileData.getData(TileEntityCraftingCloth.FACADE_STATE);
        this.mimicModel = Minecraft.getInstance().getBlockRendererDispatcher().getBlockModelShapes().getModel(this.mimicState);

        List<BakedQuad> newQuads = Lists.newArrayList();
        List<BakedQuad> quads = this.mimicModel.getQuads(this.mimicState, side, rand);

        if (quads.size() == 0)
        {
            quads = this.defaultModel.getQuads(state, side, rand);
        }

        for (BakedQuad quad : quads)
        {
            newQuads.add(quad);
            TextureAtlasSprite sprite = getSpriteForDirection(quad.getFace());
            if (sprite != null)
            {
                newQuads.add(QuadUtilities.remap(new BakedQuad(quad.getVertexData().clone(), quad.getTintIndex(), quad.getFace(), quad.sprite, true), sprite));
            }
        }
        return newQuads;
    }

    @Nullable
    private TextureAtlasSprite getSpriteForDirection(Direction direction)
    {
        switch (direction)
        {
            case UP:
                return topSprite;
            case SOUTH:
            case NORTH:
            case WEST:
            case EAST:
                return sideSprite;
            default:
                return null;
        }
    }

    @Override
    public boolean isAmbientOcclusion()
    {
        if (this.mimicState == null)
            return false;

        IBakedModel model = Minecraft.getInstance().getBlockRendererDispatcher().getBlockModelShapes().getModel(this.mimicState);
        return model.isAmbientOcclusion();
    }

    @Override
    public boolean isGui3d()
    {
        return false;
    }

    @Override
    public boolean isSideLit()
    {
        return false;
    }

    @Override
    public boolean isBuiltInRenderer()
    {
        return false;
    }

    @Override
    public TextureAtlasSprite getParticleTexture()
    {
        return topSprite;
    }

    @Override
    public ItemOverrideList getOverrides()
    {
        return null;
    }

    @Nonnull
    @Override
    public IModelData getModelData(@Nonnull IBlockDisplayReader world, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nonnull IModelData tileData)
    {
        this.mimicState = tileData.getData(TileEntityCraftingCloth.FACADE_STATE);
        this.mimicModel = Minecraft.getInstance().getBlockRendererDispatcher().getBlockModelShapes().getModel(this.mimicState);
        return tileData;
    }
}