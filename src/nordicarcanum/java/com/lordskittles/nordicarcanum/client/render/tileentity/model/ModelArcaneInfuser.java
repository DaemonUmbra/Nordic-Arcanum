package com.lordskittles.nordicarcanum.client.render.tileentity.model;

import com.lordskittles.nordicarcanum.core.NordicResourceLocations;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;

import javax.annotation.Nonnull;

public class ModelArcaneInfuser extends Model {

    private final ModelPart Entity;

    private RenderType RENDER_TYPE = getRenderType(NordicResourceLocations.ARCANE_INFUSER);

    public ModelArcaneInfuser() {

        super(RenderType::getEntityCutout);
        textureWidth = 80;
        textureHeight = 80;

        Entity = new ModelRenderer(this);
        Entity.setRotationPoint(0.0F, 24.0F, 1.0F);
        Entity.setTextureOffset(0, 0).addBox(- 1.0F, - 13.0F, - 9.0F, 2.0F, 9.0F, 2.0F, 0.0F, false);
        Entity.setTextureOffset(0, 0).addBox(- 8.0F, - 1.0F, - 9.0F, 16.0F, 1.0F, 16.0F, 0.0F, false);
        Entity.setTextureOffset(33, 40).addBox(4.0F, - 16.0F, - 3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Entity.setTextureOffset(6, 40).addBox(2.0F, - 16.0F, - 3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Entity.setTextureOffset(0, 40).addBox(- 5.0F, - 16.0F, - 3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Entity.setTextureOffset(36, 39).addBox(- 3.0F, - 16.0F, - 3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Entity.setTextureOffset(33, 38).addBox(- 3.0F, - 16.0F, - 5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Entity.setTextureOffset(0, 38).addBox(- 5.0F, - 16.0F, - 5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Entity.setTextureOffset(30, 37).addBox(2.0F, - 16.0F, - 5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Entity.setTextureOffset(5, 37).addBox(4.0F, - 16.0F, - 5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Entity.setTextureOffset(30, 39).addBox(- 3.0F, - 16.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Entity.setTextureOffset(5, 35).addBox(- 5.0F, - 16.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Entity.setTextureOffset(6, 33).addBox(2.0F, - 16.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Entity.setTextureOffset(6, 17).addBox(4.0F, - 16.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Entity.setTextureOffset(3, 39).addBox(- 3.0F, - 16.0F, 2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Entity.setTextureOffset(12, 13).addBox(- 5.0F, - 16.0F, 2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Entity.setTextureOffset(4, 11).addBox(2.0F, - 16.0F, 2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Entity.setTextureOffset(6, 0).addBox(4.0F, - 16.0F, 2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Entity.setTextureOffset(35, 36).addBox(- 1.0F, - 16.0F, - 3.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        Entity.setTextureOffset(30, 35).addBox(- 1.0F, - 16.0F, 0.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        Entity.setTextureOffset(10, 11).addBox(- 1.0F, - 16.0F, 2.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        Entity.setTextureOffset(0, 36).addBox(- 1.0F, - 16.0F, - 5.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        Entity.setTextureOffset(30, 33).addBox(- 6.0F, - 16.0F, - 7.0F, 12.0F, 1.0F, 1.0F, 0.0F, false);
        Entity.setTextureOffset(48, 14).addBox(- 6.0F, - 16.0F, 4.0F, 12.0F, 1.0F, 1.0F, 0.0F, false);
        Entity.setTextureOffset(34, 60).addBox(- 6.0F, - 16.0F, - 6.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
        Entity.setTextureOffset(58, 27).addBox(5.0F, - 16.0F, - 6.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
        Entity.setTextureOffset(0, 57).addBox(3.0F, - 16.0F, - 6.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
        Entity.setTextureOffset(56, 56).addBox(1.0F, - 16.0F, - 6.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
        Entity.setTextureOffset(44, 49).addBox(- 2.0F, - 16.0F, - 6.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
        Entity.setTextureOffset(46, 26).addBox(- 4.0F, - 16.0F, - 6.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
        Entity.setTextureOffset(0, 17).addBox(- 7.0F, - 3.0F, - 8.0F, 14.0F, 2.0F, 14.0F, 0.0F, false);
        Entity.setTextureOffset(22, 49).addBox(4.0F, - 15.0F, - 6.0F, 1.0F, 4.0F, 10.0F, 0.0F, false);
        Entity.setTextureOffset(48, 0).addBox(- 5.0F, - 15.0F, - 6.0F, 1.0F, 4.0F, 10.0F, 0.0F, false);
        Entity.setTextureOffset(34, 54).addBox(- 4.0F, - 15.0F, - 6.0F, 8.0F, 4.0F, 1.0F, 0.0F, false);
        Entity.setTextureOffset(34, 49).addBox(- 4.0F, - 15.0F, 3.0F, 8.0F, 4.0F, 1.0F, 0.0F, false);
        Entity.setTextureOffset(0, 33).addBox(- 5.0F, - 8.0F, - 6.0F, 10.0F, 5.0F, 10.0F, 0.0F, false);
        Entity.setTextureOffset(0, 48).addBox(- 4.0F, - 11.0F, - 5.0F, 8.0F, 1.0F, 8.0F, 0.0F, false);
        Entity.setTextureOffset(42, 17).addBox(- 4.0F, - 9.0F, - 5.0F, 8.0F, 1.0F, 8.0F, 0.0F, false);
        Entity.setTextureOffset(30, 38).addBox(- 5.0F, - 10.0F, - 6.0F, 10.0F, 1.0F, 10.0F, 0.0F, false);
        Entity.setTextureOffset(8, 21).addBox(5.0F, - 6.0F, - 2.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
        Entity.setTextureOffset(0, 33).addBox(- 1.0F, - 6.0F, 4.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        Entity.setTextureOffset(6, 28).addBox(- 1.0F, - 13.0F, 4.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        Entity.setTextureOffset(0, 28).addBox(- 1.0F, - 13.0F, - 7.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        Entity.setTextureOffset(8, 25).addBox(- 1.0F, - 6.0F, - 7.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        Entity.setTextureOffset(8, 17).addBox(- 6.0F, - 6.0F, - 2.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
        Entity.setTextureOffset(6, 11).addBox(- 6.0F, - 13.0F, - 2.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
        Entity.setTextureOffset(0, 11).addBox(5.0F, - 13.0F, - 2.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
        Entity.setTextureOffset(22, 63).addBox(6.0F, - 13.0F, - 2.0F, 2.0F, 9.0F, 2.0F, 0.0F, false);
        Entity.setTextureOffset(0, 17).addBox(- 8.0F, - 13.0F, - 2.0F, 2.0F, 9.0F, 2.0F, 0.0F, false);
        Entity.setTextureOffset(8, 0).addBox(- 1.0F, - 13.0F, 5.0F, 2.0F, 9.0F, 2.0F, 0.0F, false);
    }

    public void render(@Nonnull MatrixStack matrix, @Nonnull IRenderTypeBuffer renderer, int light, int overlay) {

        render(matrix, renderer.getBuffer(RENDER_TYPE), light, overlay, 1, 1, 1, 1);
    }

    public void render(MatrixStack stack, IVertexBuilder buffer, int light, int overlay) {

        Entity.render(stack, buffer, light, overlay);
    }

    @Override
    public void render(MatrixStack stack, IVertexBuilder buffer, int light, int destroyStage, float red, float green, float blue, float alpha) {

        render(stack, buffer, light, destroyStage);
    }
}
