package com.lordskittles.nordicarcanum.client.render.item.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.RenderType;

public class ModelRod2 extends Model {

//    private final ModelPart rod2;

    public ModelRod2() {

        super(RenderType::entityCutout);
//        textureWidth = 16;
//        textureHeight = 16;
//
//        rod2 = new ModelRenderer(this);
//        rod2.setRotationPoint(- 5.0F, 21.0F, 0.0F);
//        rod2.setTextureOffset(0, 0).addBox(4.0F, - 10.0F, 0.0F, 1.0F, 13.0F, 1.0F, 0.0F, false);
//        rod2.setTextureOffset(7, 8).addBox(4.0F, - 16.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
//        rod2.setTextureOffset(7, 2).addBox(5.0F, - 15.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
//        rod2.setTextureOffset(4, 0).addBox(5.0F, - 11.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
//        rod2.setTextureOffset(4, 4).addBox(6.0F, - 14.0F, 0.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
    }

    @Override
    public void renderToBuffer(PoseStack stack, VertexConsumer consumer, int light, int destroyStage, float red, float green, float blue, float alpha) {

//        rod2.render(matrixStack, buffer, packedLight, packedOverlay);
    }

//    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
//
//        modelRenderer.rotateAngleX = x;
//        modelRenderer.rotateAngleY = y;
//        modelRenderer.rotateAngleZ = z;
//    }
}