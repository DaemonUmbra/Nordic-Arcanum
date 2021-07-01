package com.lordskittles.nordicarcanum.client.render.tileentity.model;

import com.lordskittles.nordicarcanum.core.NordicResourceLocations;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;

import javax.annotation.Nonnull;

public class ModelCrystalMatrix extends Model {

    private final ModelRenderer crystal;
    private final ModelRenderer ring;

    private final RenderType RENDER_TYPE = getRenderType(NordicResourceLocations.ATTUNEMENT_ALTAR);
    private final float multiplier = 0.0125f;

    private int currentRot;

    private int partialTicks;
    private int entityTicks;

    public ModelCrystalMatrix() {

        super(RenderType::getEntityTranslucent);

        currentRot = 0;

        textureWidth = 80;
        textureHeight = 80;

        crystal = new ModelRenderer(this);
        crystal.setRotationPoint(0.0F, 16.0F, 0.0F);
        setRotationAngle(crystal, 0.0F, 0.7854F, - 0.7854F);
        crystal.setTextureOffset(42, 19).addBox(- 3.0F, - 3.0F, - 3.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);

        ring = new ModelRenderer(this);
        ring.setRotationPoint(0.0F, 15.8F, 0.0F);
        ring.setTextureOffset(42, 31).addBox(- 3.0F, - 0.5F, 5.95F, 6.0F, 1.0F, 1.0F, 0.0F, false);
        ring.setTextureOffset(36, 39).addBox(- 3.0F, - 0.5F, - 6.95F, 6.0F, 1.0F, 1.0F, 0.0F, false);
        ring.setTextureOffset(0, 26).addBox(- 6.95F, - 0.5F, - 3.0F, 1.0F, 1.0F, 6.0F, 0.0F, false);
        ring.setTextureOffset(0, 19).addBox(5.95F, - 0.5F, - 3.0F, 1.0F, 1.0F, 6.0F, 0.0F, false);
    }

    public void render(@Nonnull MatrixStack matrix, @Nonnull IRenderTypeBuffer renderer, int light, int overlayLight) {

        render(matrix, renderer.getBuffer(RENDER_TYPE), light, overlayLight, 1, 1, 1, 1);
    }

    public void render(MatrixStack stack, IVertexBuilder buffer, int light, int overlay) {

        float bob = (MathHelper.sin((this.entityTicks + this.partialTicks) / 10.0f) * 0.1f + 0.1f) * this.multiplier;
        stack.translate(0, bob, 0);

        float rot = (MathHelper.sin((this.entityTicks + this.partialTicks) / 10.0f) * 0.1f + 0.1f) * this.multiplier;
        stack.rotate(Vector3f.YP.rotationDegrees(rot));
        crystal.render(stack, buffer, light, overlay);
        stack.rotate(Vector3f.YP.rotationDegrees(- rot * 4));

        renderRing(stack, buffer, light, overlay, 0, 0, 0, - rot * 0.1f);
    }

    @Override
    public void render(MatrixStack stack, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {

        render(stack, buffer, light, overlay);
    }

    private void renderRing(MatrixStack stack, IVertexBuilder buffer, int light, int overlay, int offset, double offsetPositionX, double offsetPositionY, float rot) {

        stack.translate(offsetPositionX, offsetPositionY, 0);
        stack.rotate(Vector3f.ZP.rotationDegrees(offset));

        stack.rotate(Vector3f.YP.rotationDegrees(rot));
        ring.render(stack, buffer, light, overlay);
        stack.rotate(Vector3f.YP.rotationDegrees(45));
        ring.render(stack, buffer, light, overlay);
        stack.rotate(Vector3f.YP.rotationDegrees(- 45));

        stack.rotate(Vector3f.ZP.rotationDegrees(- offset));
        stack.translate(- offsetPositionX, - offsetPositionY, 0);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {

        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}