package com.lordskittles.nordicarcanum.client.render.tileentity;

import com.lordskittles.arcanumapi.client.render.tileentity.TileEntityRendererBase;
import com.lordskittles.nordicarcanum.client.render.tileentity.model.ModelAttunementAltar;
import com.lordskittles.nordicarcanum.common.blockentity.magic.BlockEntityAttunementAltar;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.block.ModelBlockRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.world.level.Level;

public class TileRendererAttunementAltar extends TileEntityRendererBase<BlockEntityAttunementAltar> {

    private final ModelAttunementAltar model = new ModelAttunementAltar();

    private int currentRot;
    private final float multiplier = 0.0125f;

    private BakedModel bakedModel;
    private final ModelBlockRenderer renderer;
    private final ModelManager modelManager;

    public TileRendererAttunementAltar(BlockEntityRenderDispatcher dispatcher) {

        super(dispatcher);

        Minecraft minecraft = Minecraft.getInstance();
        BlockRenderDispatcher blockDispatcher = minecraft.getBlockRenderer();
        renderer = blockDispatcher.getModelRenderer();
        modelManager = minecraft.getModelManager();
    }

    @Override
    public void render(BlockEntityAttunementAltar tile, float ticks, PoseStack stack, MultiBufferSource buffer, int light, int overlay) {

        this.currentRot++;
        Level level = renderDispatcher.level;

        if(bakedModel == null) {

            bakedModel = modelManager.getModel(NordicArcanum.RL("models/block/entities/attunement_altar_obj"));
        }

//        IVertexBuilder builder = buffer.getBuffer(RenderType.getEntityCutout(new ResourceLocation("minecraft:textures/block/acacia_planks.png")));
//
//        stack.push();
////        stack.rotate(Vector3f.XN.rotationDegrees(180));
//        stack.translate(0.5D, 0D, 0.5D);
//        stack.rotate(Vector3f.YP.rotationDegrees(- this.currentRot * 0.1f));
//        stack.translate(-0.5D, 1D, -0.5D);
//        renderer.renderModel(
//                world,
//                bakedModel,
//                tile.getBlockState(),
//                tile.getPos(),
//                stack,
//                builder,
//                false,
//                world.rand,
//                world.rand.nextLong(),
//                overlay,
//                bakedModel.getModelData(
//                        world,
//                        tile.getPos(),
//                        tile.getBlockState(),
//                        EmptyModelData.INSTANCE));
//        stack.pop();
    }
}
