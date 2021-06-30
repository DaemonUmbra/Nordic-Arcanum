package com.lordskittles.nordicarcanum.client.render.item.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;

public class ModelRod3 extends Model
{
	private final ModelRenderer rod3;

	public ModelRod3()
	{
		super(RenderType::getEntityCutout);
		textureWidth = 32;
		textureHeight = 32;

		rod3 = new ModelRenderer(this);
		rod3.setRotationPoint(0.0F, 26.0F, 0.0F);
		rod3.setTextureOffset(0, 11).addBox(-1.0F, -21.0F, 0.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		rod3.setTextureOffset(5, 1).addBox(-2.0F, -18.0F, 0.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		rod3.setTextureOffset(8, 9).addBox(-2.0F, -16.0F, 2.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);
		rod3.setTextureOffset(13, 3).addBox(0.0F, -15.0F, 2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		rod3.setTextureOffset(5, 5).addBox(0.0F, -14.0F, 0.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		rod3.setTextureOffset(8, 13).addBox(-2.0F, -17.0F, 2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		rod3.setTextureOffset(4, 13).addBox(0.0F, -13.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		rod3.setTextureOffset(0, 9).addBox(-2.0F, -12.0F, 0.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);
		rod3.setTextureOffset(11, 12).addBox(-2.0F, -11.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		rod3.setTextureOffset(0, 4).addBox(-2.0F, -10.0F, 0.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		rod3.setTextureOffset(8, 11).addBox(-2.0F, -9.0F, 2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		rod3.setTextureOffset(10, 5).addBox(-2.0F, -8.0F, 2.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		rod3.setTextureOffset(4, 11).addBox(-1.0F, -7.0F, 2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		rod3.setTextureOffset(0, 0).addBox(-1.0F, -6.0F, 0.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		rod3.setTextureOffset(10, 0).addBox(-1.0F, -5.0F, 0.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		rod3.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}