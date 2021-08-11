package com.lordskittles.nordicarcanum.client.render.item.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.RenderType;

public class ModelBindingCane extends Model {

//    private final ModelPart binding3;

    public ModelBindingCane() {

        super(RenderType::entityCutout);
//        textureWidth = 32;
//        textureHeight = 32;
//
//        binding3 = new ModelRenderer(this);
//        binding3.setRotationPoint(5.0F, 14.0F, 0.0F);
//        binding3.setTextureOffset(13, 9).addBox(- 6.5F, 9.0F, 0.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
//        binding3.setTextureOffset(5, 3).addBox(- 6.0F, 8.0F, - 0.5F, 3.0F, 1.0F, 2.0F, 0.0F, false);
//        binding3.setTextureOffset(12, 0).addBox(- 4.0F, 7.0F, - 0.5F, 2.0F, 1.0F, 2.0F, 0.0F, false);
//        binding3.setTextureOffset(0, 10).addBox(- 3.0F, 6.0F, - 1.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
//        binding3.setTextureOffset(0, 3).addBox(- 2.0F, 3.0F, - 1.0F, 1.0F, 4.0F, 3.0F, 0.0F, false);
//        binding3.setTextureOffset(5, 7).addBox(- 3.0F, 3.0F, - 1.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
//        binding3.setTextureOffset(8, 11).addBox(- 4.0F, 2.0F, - 0.5F, 2.0F, 1.0F, 2.0F, 0.0F, false);
//        binding3.setTextureOffset(0, 0).addBox(- 7.0F, 1.0F, - 0.5F, 4.0F, 1.0F, 2.0F, 0.0F, false);
//        binding3.setTextureOffset(10, 6).addBox(- 8.0F, 2.0F, - 0.5F, 2.0F, 1.0F, 2.0F, 0.0F, false);
//        binding3.setTextureOffset(13, 3).addBox(- 9.0F, 3.0F, 0.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
//        binding3.setTextureOffset(10, 0).addBox(- 9.0F, 4.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
    }

    @Override
    public void renderToBuffer(PoseStack stack, VertexConsumer consumer, int light, int destroyStage, float red, float green, float blue, float alpha) {

//        binding3.render(matrixStack, buffer, packedLight, packedOverlay);
    }

//    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
//
//        modelRenderer.rotateAngleX = x;
//        modelRenderer.rotateAngleY = y;
//        modelRenderer.rotateAngleZ = z;
//    }
}