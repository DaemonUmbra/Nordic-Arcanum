package com.lordskittles.nordicarcanum.client.render.tileentity.model;

import com.lordskittles.nordicarcanum.core.NordicResourceLocations;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;

import javax.annotation.Nonnull;

public class ModelNordicAnvil extends Model
{
	private RenderType RENDER_TYPE = getRenderType(NordicResourceLocations.NORDIC_ANVIL);
	private final ModelRenderer Entity;

	public ModelNordicAnvil()
	{
		super(RenderType::getEntityCutout);
		textureWidth = 64;
		textureHeight = 64;

		Entity = new ModelRenderer(this);
		Entity.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(Entity, 0.0F, 1.5708F, 0F);
		Entity.setTextureOffset(0, 0).addBox(-4.0F, -4.0F, -6.0F, 8.0F, 4.0F, 12.0F, 0.0F, false);
		Entity.setTextureOffset(18, 16).addBox(-3.0F, -5.0F, -4.0F, 6.0F, 1.0F, 8.0F, 0.0F, false);
		Entity.setTextureOffset(22, 26).addBox(-2.0F, -10.0F, -3.0F, 4.0F, 5.0F, 6.0F, 0.0F, false);
		Entity.setTextureOffset(0, 32).addBox(2.0F, -16.0F, 3.0F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		Entity.setTextureOffset(28, 0).addBox(-3.0F, -16.0F, 3.0F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		Entity.setTextureOffset(33, 33).addBox(-3.0F, -16.0F, -6.0F, 1.0F, 1.0F, 9.0F, 0.0F, false);
		Entity.setTextureOffset(11, 33).addBox(-3.0F, -11.0F, -6.0F, 1.0F, 1.0F, 9.0F, 0.0F, false);
		Entity.setTextureOffset(0, 32).addBox(2.0F, -11.0F, -6.0F, 1.0F, 1.0F, 9.0F, 0.0F, false);
		Entity.setTextureOffset(28, 0).addBox(2.0F, -16.0F, -6.0F, 1.0F, 1.0F, 9.0F, 0.0F, false);
		Entity.setTextureOffset(22, 16).addBox(-3.0F, -16.0F, -7.0F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		Entity.setTextureOffset(18, 16).addBox(2.0F, -16.0F, -7.0F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		Entity.setTextureOffset(0, 18).addBox(-2.0F, -16.0F, -7.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		Entity.setTextureOffset(0, 16).addBox(-2.0F, -11.0F, -7.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		Entity.setTextureOffset(0, 16).addBox(-2.0F, -16.0F, -6.0F, 4.0F, 6.0F, 10.0F, 0.0F, false);
		Entity.setTextureOffset(0, 6).addBox(-2.0F, -16.0F, 4.0F, 4.0F, 5.0F, 1.0F, 0.0F, false);
		Entity.setTextureOffset(0, 0).addBox(-1.5F, -15.75F, 5.0F, 3.0F, 4.0F, 2.0F, 0.0F, false);
		Entity.setTextureOffset(0, 20).addBox(-1.0F, -15.5F, 7.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
	}

	public void render(@Nonnull MatrixStack matrix, @Nonnull IRenderTypeBuffer renderer, int light, int overlay)
	{
		render(matrix, renderer.getBuffer(RENDER_TYPE), light, overlay, 1, 1, 1, 1);
	}

	public void render(MatrixStack stack, IVertexBuilder buffer, int light, int overlay)
	{
		Entity.render(stack, buffer, light, overlay);
	}

	@Override
	public void render(MatrixStack stack, IVertexBuilder buffer, int light, int destroyStage, float red, float green, float blue, float alpha)
	{
		render(stack, buffer, light, destroyStage);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
