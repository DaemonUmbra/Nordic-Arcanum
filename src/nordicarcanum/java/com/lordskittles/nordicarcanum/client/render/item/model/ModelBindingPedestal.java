package com.lordskittles.nordicarcanum.client.render.item.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.RenderType;

public class ModelBindingPedestal extends Model {

//    private final ModelPart binding1;

    public ModelBindingPedestal() {

        super(RenderType::entityCutout);
//        textureWidth = 32;
//        textureHeight = 32;
//
//        binding1 = new ModelRenderer(this);
//        binding1.setRotationPoint(- 7.0F, 7.3F, - 4.0F);
//        binding1.setTextureOffset(9, 17).addBox(8.0F, 14.0F, 3.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
//        binding1.setTextureOffset(0, 9).addBox(3.3F, 12.9F, 6.7F, 1.0F, 2.0F, 1.0F, 0.0F, false);
//        binding1.setTextureOffset(8, 8).addBox(3.3F, 12.9F, 1.3F, 1.0F, 2.0F, 1.0F, 0.0F, false);
//        binding1.setTextureOffset(0, 6).addBox(8.7F, 12.9F, 1.3F, 1.0F, 2.0F, 1.0F, 0.0F, false);
//        binding1.setTextureOffset(0, 0).addBox(8.7F, 12.9F, 6.7F, 1.0F, 2.0F, 1.0F, 0.0F, false);
//        binding1.setTextureOffset(0, 15).addBox(5.0F, 15.7F, 3.0F, 3.0F, 1.0F, 3.0F, 0.0F, false);
//        binding1.setTextureOffset(0, 0).addBox(4.0F, 15.0F, 2.0F, 5.0F, 1.0F, 5.0F, 0.0F, false);
//        binding1.setTextureOffset(9, 15).addBox(4.0F, 14.0F, 6.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
//        binding1.setTextureOffset(16, 10).addBox(4.5F, 14.5F, 6.5F, 4.0F, 1.0F, 1.0F, 0.0F, false);
//        binding1.setTextureOffset(16, 8).addBox(4.5F, 14.5F, 1.5F, 4.0F, 1.0F, 1.0F, 0.0F, false);
//        binding1.setTextureOffset(8, 8).addBox(3.5F, 14.5F, 1.5F, 1.0F, 1.0F, 6.0F, 0.0F, false);
//        binding1.setTextureOffset(0, 6).addBox(8.5F, 14.5F, 1.5F, 1.0F, 1.0F, 6.0F, 0.0F, false);
//        binding1.setTextureOffset(8, 6).addBox(4.0F, 14.0F, 2.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
//        binding1.setTextureOffset(15, 0).addBox(4.0F, 14.0F, 3.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
    }

    @Override
    public void renderToBuffer(PoseStack stack, VertexConsumer consumer, int light, int destroyStage, float red, float green, float blue, float alpha) {

//        binding1.render(matrixStack, buffer, packedLight, packedOverlay);
    }

//    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
//
//        modelRenderer.rotateAngleX = x;
//        modelRenderer.rotateAngleY = y;
//        modelRenderer.rotateAngleZ = z;
//    }
}