package com.lordskittles.nordicarcanum.client.render.item.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;

public class ModelBindingBrazier extends Model {

    private final ModelRenderer binding2;

    public ModelBindingBrazier() {

        super(RenderType::getEntityCutout);
        textureWidth = 32;
        textureHeight = 32;

        binding2 = new ModelRenderer(this);
        binding2.setRotationPoint(3.0F, 1.0F, - 3.0F);
        binding2.setTextureOffset(0, 6).addBox(- 5.0F, 22.0F, 2.0F, 3.0F, 1.0F, 3.0F, 0.0F, false);
        binding2.setTextureOffset(0, 0).addBox(- 6.0F, 21.0F, 1.0F, 5.0F, 1.0F, 5.0F, 0.0F, false);
        binding2.setTextureOffset(0, 12).addBox(- 6.7F, 19.2F, 0.3F, 3.0F, 1.0F, 1.0F, 0.0F, false);
        binding2.setTextureOffset(5, 16).addBox(- 6.7F, 18.2F, 0.3F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        binding2.setTextureOffset(15, 2).addBox(- 6.0F, 20.0F, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        binding2.setTextureOffset(15, 0).addBox(- 2.0F, 20.0F, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        binding2.setTextureOffset(14, 8).addBox(- 3.3F, 19.2F, 0.3F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        binding2.setTextureOffset(9, 9).addBox(- 1.3F, 19.2F, 0.3F, 1.0F, 1.0F, 3.0F, 0.0F, false);
        binding2.setTextureOffset(0, 14).addBox(- 6.7F, 19.2F, 1.3F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        binding2.setTextureOffset(12, 13).addBox(- 6.7F, 19.2F, 3.7F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        binding2.setTextureOffset(14, 10).addBox(- 6.7F, 18.2F, 5.7F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        binding2.setTextureOffset(10, 13).addBox(- 6.0F, 20.0F, 5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        binding2.setTextureOffset(0, 10).addBox(- 6.7F, 19.2F, 5.7F, 3.0F, 1.0F, 1.0F, 0.0F, false);
        binding2.setTextureOffset(9, 6).addBox(- 3.3F, 19.2F, 5.7F, 3.0F, 1.0F, 1.0F, 0.0F, false);
        binding2.setTextureOffset(8, 10).addBox(- 2.0F, 20.0F, 5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        binding2.setTextureOffset(6, 13).addBox(- 1.3F, 19.2F, 3.7F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        binding2.setTextureOffset(0, 2).addBox(- 1.3F, 18.2F, 5.7F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        binding2.setTextureOffset(0, 0).addBox(- 1.3F, 18.2F, 0.3F, 1.0F, 1.0F, 1.0F, 0.0F, false);
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

        binding2.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {

        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}