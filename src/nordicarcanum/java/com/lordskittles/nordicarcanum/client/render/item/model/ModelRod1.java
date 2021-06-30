package com.lordskittles.nordicarcanum.client.render.item.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;

public class ModelRod1 extends Model
{
	private final ModelRenderer rod1;

	public ModelRod1()
	{
		super(RenderType::getEntityCutout);
		textureWidth = 32;
		textureHeight = 32;

		rod1 = new ModelRenderer(this);
		rod1.setRotationPoint(8.0F, 24.0F, 0.0F);
		rod1.setTextureOffset(0, 0).addBox(-8.0F, -17.0F, 0.0F, 1.0F, 17.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		rod1.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}