package com.lordskittles.nordicarcanum.client.render.item.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.RenderType;

public class ModelRod1 extends Model {

//    private final ModelPart rod1;

    public ModelRod1() {

        super(RenderType::entityCutout);
//        textureWidth = 32;
//        textureHeight = 32;
//
//        rod1 = new ModelRenderer(this);
//        rod1.setRotationPoint(8.0F, 24.0F, 0.0F);
//        rod1.setTextureOffset(0, 0).addBox(- 8.0F, - 17.0F, 0.0F, 1.0F, 17.0F, 1.0F, 0.0F, false);
    }

    @Override
    public void renderToBuffer(PoseStack stack, VertexConsumer consumer, int light, int destroyStage, float red, float green, float blue, float alpha) {

//        rod1.render(matrixStack, buffer, packedLight, packedOverlay);
    }

//    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
//
//        modelRenderer.rotateAngleX = x;
//        modelRenderer.rotateAngleY = y;
//        modelRenderer.rotateAngleZ = z;
//    }
}