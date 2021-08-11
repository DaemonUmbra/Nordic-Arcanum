package com.lordskittles.nordicarcanum.client.render.tileentity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.RenderType;

public class ModelArcaneTank extends Model {

//    private final ModelPart Entity;
//
//    private RenderType RENDER_TYPE = getRenderType(NordicResourceLocations.ARCANE_TANK);

    public ModelArcaneTank() {

        super(RenderType::entityTranslucent);
//        textureWidth = 64;
//        textureHeight = 64;
//
//        Entity = new ModelRenderer(this);
//        Entity.setRotationPoint(0.0F, 16.3125F, 0.0F);
//        Entity.setTextureOffset(0, 11).addBox(- 5.0F, 4.6875F, - 5.0F, 10.0F, 1.0F, 10.0F, 0.0F, false);
//        Entity.setTextureOffset(0, 0).addBox(- 5.0F, - 4.3125F, - 5.0F, 10.0F, 1.0F, 10.0F, 0.0F, false);
//        Entity.setTextureOffset(0, 22).addBox(- 4.0F, - 5.3125F, - 4.0F, 8.0F, 1.0F, 8.0F, 0.0F, false);
//        Entity.setTextureOffset(4, 11).addBox(- 4.0F, - 3.3125F, 3.0F, 1.0F, 8.0F, 1.0F, 0.0F, false);
//        Entity.setTextureOffset(0, 11).addBox(3.0F, - 3.3125F, 3.0F, 1.0F, 8.0F, 1.0F, 0.0F, false);
//        Entity.setTextureOffset(4, 0).addBox(- 4.0F, - 3.3125F, - 4.0F, 1.0F, 8.0F, 1.0F, 0.0F, false);
//        Entity.setTextureOffset(0, 0).addBox(3.0F, - 3.3125F, - 4.0F, 1.0F, 8.0F, 1.0F, 0.0F, false);
//        Entity.setTextureOffset(26, 26).addBox(- 3.0F, - 3.3125F, - 3.0F, 6.0F, 8.0F, 6.0F, 0.0F, false);
    }

//    public void render(@Nonnull MatrixStack matrix, @Nonnull IRenderTypeBuffer renderer, int light, int overlay) {
//
//        render(matrix, renderer.getBuffer(RENDER_TYPE), light, overlay, 1, 1, 1, 1);
//    }
//
//    public void render(MatrixStack stack, IVertexBuilder buffer, int light, int overlay) {
//
//        Entity.render(stack, buffer, light, overlay);
//    }

    @Override
    public void renderToBuffer(PoseStack stack, VertexConsumer consumer, int light, int destroyStage, float red, float green, float blue, float alpha) {

//        render(stack, buffer, light, destroyStage);
    }
}
