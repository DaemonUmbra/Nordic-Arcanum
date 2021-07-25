package com.lordskittles.nordicarcanum.client.render.tileentity.model;

import com.lordskittles.nordicarcanum.core.NordicResourceLocations;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;

import javax.annotation.Nonnull;

public class ModelSigilPodium extends Model {

    private final ModelPart PodiumModel;

    private RenderType RENDER_TYPE = getRenderType(NordicResourceLocations.SIGIL_PODIUM);

    public ModelSigilPodium() {

        super(RenderType::getEntityCutout);
        textureWidth = 64;
        textureHeight = 64;

        PodiumModel = new ModelRenderer(this);
        PodiumModel.setRotationPoint(0.0F, 24.0F, 0.0F);
        PodiumModel.setTextureOffset(0, 0).addBox(- 8.0F, - 3.0F, - 8.0F, 16.0F, 3.0F, 16.0F, 0.0F, false);
        PodiumModel.setTextureOffset(0, 35).addBox(- 6.0F, - 9.0F, - 6.0F, 12.0F, 6.0F, 12.0F, 0.0F, false);
        PodiumModel.setTextureOffset(0, 19).addBox(- 7.0F, - 11.0F, - 7.0F, 14.0F, 2.0F, 14.0F, 0.0F, false);
        PodiumModel.setTextureOffset(42, 19).addBox(- 7.0F, - 14.0F, 3.0F, 4.0F, 3.0F, 4.0F, 0.0F, false);
        PodiumModel.setTextureOffset(36, 36).addBox(- 7.0F, - 14.0F, - 7.0F, 4.0F, 3.0F, 4.0F, 0.0F, false);
        PodiumModel.setTextureOffset(0, 7).addBox(3.0F, - 14.0F, - 7.0F, 4.0F, 3.0F, 4.0F, 0.0F, false);
        PodiumModel.setTextureOffset(0, 0).addBox(3.0F, - 14.0F, 3.0F, 4.0F, 3.0F, 4.0F, 0.0F, false);
        PodiumModel.setTextureOffset(0, 26).addBox(6.0F, - 12.0F, - 3.0F, 1.0F, 1.0F, 6.0F, 0.0F, false);
        PodiumModel.setTextureOffset(0, 19).addBox(- 7.0F, - 12.0F, - 3.0F, 1.0F, 1.0F, 6.0F, 0.0F, false);
        PodiumModel.setTextureOffset(42, 26).addBox(- 3.0F, - 12.0F, - 7.0F, 6.0F, 1.0F, 1.0F, 0.0F, false);
        PodiumModel.setTextureOffset(0, 14).addBox(- 3.0F, - 12.0F, 6.0F, 6.0F, 1.0F, 1.0F, 0.0F, false);
        PodiumModel.setTextureOffset(0, 43).addBox(- 7.0F, - 16.0F, - 7.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        PodiumModel.setTextureOffset(42, 28).addBox(5.0F, - 16.0F, - 7.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        PodiumModel.setTextureOffset(0, 39).addBox(5.0F, - 16.0F, 5.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        PodiumModel.setTextureOffset(0, 35).addBox(- 7.0F, - 16.0F, 5.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        PodiumModel.setTextureOffset(48, 7).addBox(6.0F, - 9.0F, - 7.0F, 1.0F, 6.0F, 1.0F, 0.0F, false);
        PodiumModel.setTextureOffset(48, 0).addBox(6.0F, - 9.0F, 6.0F, 1.0F, 6.0F, 1.0F, 0.0F, false);
        PodiumModel.setTextureOffset(48, 48).addBox(- 7.0F, - 9.0F, 6.0F, 1.0F, 6.0F, 1.0F, 0.0F, false);
        PodiumModel.setTextureOffset(8, 35).addBox(- 7.0F, - 9.0F, - 7.0F, 1.0F, 6.0F, 1.0F, 0.0F, false);
    }

    public void render(@Nonnull MatrixStack matrix, @Nonnull IRenderTypeBuffer renderer, int light, int overlay) {

        render(matrix, renderer.getBuffer(RENDER_TYPE), light, overlay, 1, 1, 1, 1);
    }

    public void render(MatrixStack stack, IVertexBuilder buffer, int light, int overlay) {

        PodiumModel.render(stack, buffer, light, overlay);
    }

    @Override
    public void render(MatrixStack stack, IVertexBuilder buffer, int light, int destroyStage, float red, float green, float blue, float alpha) {

        render(stack, buffer, light, destroyStage);
    }
}
