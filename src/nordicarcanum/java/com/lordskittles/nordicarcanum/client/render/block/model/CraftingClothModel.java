package com.lordskittles.nordicarcanum.client.render.block.model;

import com.google.common.collect.Lists;
import com.lordskittles.arcanumapi.common.utilities.QuadUtilities;
import com.lordskittles.nordicarcanum.common.blockentity.crafting.BlockEntityCraftingCloth;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.IDynamicBakedModel;
import net.minecraftforge.client.model.data.IModelData;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class CraftingClothModel implements IDynamicBakedModel {

    private final BakedModel defaultModel;

    private BlockState mimicState;
    private BakedModel mimicModel;

    private final TextureAtlasSprite topSprite;
    private final TextureAtlasSprite sideSprite;

    public CraftingClothModel() {

        defaultModel = Minecraft.getInstance().getBlockRenderer().getBlockModel(Blocks.PURPLE_WOOL.defaultBlockState());

        TextureAtlas atlas = Minecraft.getInstance().getModelManager().getAtlas(TextureAtlas.LOCATION_BLOCKS);
        topSprite = atlas.getSprite(NordicArcanum.RL("blocks/crafting_cloth_top"));
        sideSprite = atlas.getSprite(NordicArcanum.RL("blocks/crafting_cloth_side"));
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random rand, @Nonnull IModelData tileData) {

        this.mimicState = tileData.getData(BlockEntityCraftingCloth.FACADE_STATE);
        this.mimicModel = Minecraft.getInstance().getBlockRenderer().getBlockModel(this.mimicState);

        List<BakedQuad> newQuads = Lists.newArrayList();
        List<BakedQuad> quads = this.mimicModel.getQuads(this.mimicState, side, rand);

        if(quads.size() == 0) {
            quads = this.defaultModel.getQuads(state, side, rand);
        }

        for(BakedQuad quad : quads) {
            newQuads.add(quad);
            TextureAtlasSprite sprite = getSpriteForDirection(quad.getDirection());
            if(sprite != null) {
                newQuads.add(QuadUtilities.remap(new BakedQuad(quad.getVertices().clone(), quad.getTintIndex(), quad.getDirection(), quad.sprite, true), sprite));
            }
        }
        return newQuads;
    }

    @Nullable
    private TextureAtlasSprite getSpriteForDirection(Direction direction) {

        switch(direction) {
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
    public boolean useAmbientOcclusion() {

        if(this.mimicState == null)
            return false;

        BakedModel model = Minecraft.getInstance().getBlockRenderer().getBlockModel(this.mimicState);
        return model.useAmbientOcclusion();
    }

    @Override
    public boolean isGui3d() {

        return false;
    }

    @Override
    public boolean usesBlockLight() {

        return false;
    }

    @Override
    public boolean isCustomRenderer() {

        return true;
    }

    @Override
    public TextureAtlasSprite getParticleIcon() {

        return topSprite;
    }

    @Override
    public ItemOverrides getOverrides() {

        return null;
    }

    @Nonnull
    @Override
    public IModelData getModelData(@Nonnull BlockAndTintGetter world, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nonnull IModelData tileData) {

        this.mimicState = tileData.getData(BlockEntityCraftingCloth.FACADE_STATE);
        this.mimicModel = Minecraft.getInstance().getBlockRenderer().getBlockModel(this.mimicState);
        return tileData;
    }
}