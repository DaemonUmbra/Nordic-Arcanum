package com.lordskittles.nordicarcanum.client.render.tileentity;

import com.lordskittles.arcanumapi.client.render.tileentity.TileEntityRendererBase;
import com.lordskittles.nordicarcanum.client.render.tileentity.model.ModelAttunementAltar;
import com.lordskittles.nordicarcanum.common.tileentity.magic.TileEntityAttunementAltar;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.ModelBlockRenderer;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import net.minecraftforge.client.model.data.EmptyModelData;

public class TileRendererAttunementAltar extends TileEntityRendererBase<TileEntityAttunementAltar> {

    private final ModelAttunementAltar model = new ModelAttunementAltar();

    private int currentRot;
    private final float multiplier = 0.0125f;

    private BakedModel bakedModel;
    private final ModelBlockRenderer renderer;
    private final ModelManager modelManager;

    public TileRendererAttunementAltar(BlockEntityRenderDispatcher dispatcher) {

        super(dispatcher);

        BlockRenderDispatcher blockDispatcher = Minecraft.getInstance().getBlockRendererDispatcher();
        renderer = blockDispatcher.getBlockModelRenderer();
        modelManager = blockDispatcher.getBlockModelShapes().getModelManager();
    }

    @Override
    public void render(TileEntityAttunementAltar tile, float ticks, MatrixStack stack, IRenderTypeBuffer buffer, int light, int overlay) {

        this.currentRot++;
        World world = renderDispatcher.world;

        if(bakedModel == null) {

            bakedModel = modelManager.getModel(NordicArcanum.RL("models/block/entities/attunement_altar_obj"));
        }

        IVertexBuilder builder = buffer.getBuffer(RenderType.getEntityCutout(new ResourceLocation("minecraft:textures/block/acacia_planks.png")));

        stack.push();
//        stack.rotate(Vector3f.XN.rotationDegrees(180));
        stack.translate(0.5D, 0D, 0.5D);
        stack.rotate(Vector3f.YP.rotationDegrees(- this.currentRot * 0.1f));
        stack.translate(-0.5D, 1D, -0.5D);
        renderer.renderModel(
                world,
                bakedModel,
                tile.getBlockState(),
                tile.getPos(),
                stack,
                builder,
                false,
                world.rand,
                world.rand.nextLong(),
                overlay,
                bakedModel.getModelData(
                        world,
                        tile.getPos(),
                        tile.getBlockState(),
                        EmptyModelData.INSTANCE));
        stack.pop();
    }
}
