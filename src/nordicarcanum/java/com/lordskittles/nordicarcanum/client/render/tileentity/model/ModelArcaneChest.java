package com.lordskittles.nordicarcanum.client.render.tileentity.model;

import com.lordskittles.arcanumapi.common.tileentity.TileEntityMagicChest;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class ModelArcaneChest extends Model {

    private final ModelRenderer lid;
    private final ModelRenderer lock;
    private final ModelRenderer bottom;

    private float lidAngle;

    private RenderType RENDER_TYPE;

    public ModelArcaneChest(ResourceLocation textureLocation) {

        super(RenderType::getEntityCutout);
        this.RENDER_TYPE = getRenderType(textureLocation);

        textureWidth = 64;
        textureHeight = 64;

        lid = new ModelRenderer(this);
        lid.setRotationPoint(0.0F, 13.0F, 4.0F);
        lid.setTextureOffset(0, 20).addBox(- 5.0F, - 4.0F, - 9.0F, 11.0F, 2.0F, 11.0F, 0.0F, false);

        lock = new ModelRenderer(this);
        lock.setRotationPoint(0.0F, 13.0F, 4.0F);
        lock.setTextureOffset(0, 0).addBox(- 0.5F, - 3.2F, - 10.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);

        bottom = new ModelRenderer(this);
        bottom.setRotationPoint(- 8.0F, 17.0F, 8.0F);
        bottom.setTextureOffset(0, 0).addBox(3.0F, - 6.0F, - 13.0F, 11.0F, 9.0F, 11.0F, 0.0F, false);
    }

    public void render(@Nonnull MatrixStack matrix, @Nonnull IRenderTypeBuffer renderer, int light, int overlayLight, TileEntityMagicChest tile, float partialTicks) {

        this.lidAngle = tile.getLidAngle(partialTicks);

        render(matrix, renderer.getBuffer(RENDER_TYPE), light, overlayLight, 1, 1, 1, 1);
    }

    public void render(MatrixStack stack, IVertexBuilder buffer, float angle, int light, int destroyStage) {

        lid.rotateAngleX = - (angle * ((float) Math.PI / 2F));
        lock.rotateAngleX = lid.rotateAngleX;
        lid.render(stack, buffer, light, destroyStage);
        lock.render(stack, buffer, light, destroyStage);
        bottom.render(stack, buffer, light, destroyStage);
    }

    @Override
    public void render(MatrixStack stack, IVertexBuilder buffer, int light, int destroyStage, float red, float green, float blue, float alpha) {

        render(stack, buffer, lidAngle, light, destroyStage);
    }

    public void render(MatrixStack stack, IRenderTypeBuffer buffer, int light, int overlay) {

        render(stack, buffer.getBuffer(RENDER_TYPE), 0, light, overlay);
    }
}
