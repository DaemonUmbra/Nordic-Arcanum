package com.lordskittles.nordicarcanum.client.render.item.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.RenderType;

public class ModelRod4 extends Model {

//    private final ModelPart rod4;

    public ModelRod4() {

        super(RenderType::entityCutout);
//        textureWidth = 32;
//        textureHeight = 32;
//
//        rod4 = new ModelRenderer(this);
//        rod4.setRotationPoint(18.0F, 20.0F, 0.0F);
//        rod4.setTextureOffset(0, 0).addBox(- 19.0F, - 9.0F, - 1.0F, 2.0F, 13.0F, 2.0F, 0.0F, false);
//        rod4.setTextureOffset(8, 0).addBox(- 19.5F, - 13.0F, - 1.5F, 3.0F, 1.0F, 3.0F, 0.0F, false);
//        rod4.setTextureOffset(8, 8).addBox(- 19.5F, - 10.0F, - 1.5F, 3.0F, 1.0F, 3.0F, 0.0F, false);
//        rod4.setTextureOffset(8, 4).addBox(- 19.0F, - 15.0F, - 1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
//        rod4.setTextureOffset(8, 12).addBox(- 18.5F, - 13.0F, - 0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);
    }

    @Override
    public void renderToBuffer(PoseStack stack, VertexConsumer consumer, int light, int destroyStage, float red, float green, float blue, float alpha) {

//        rod4.render(matrixStack, buffer, packedLight, packedOverlay);
    }

//    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
//
//        modelRenderer.rotateAngleX = x;
//        modelRenderer.rotateAngleY = y;
//        modelRenderer.rotateAngleZ = z;
//    }
}