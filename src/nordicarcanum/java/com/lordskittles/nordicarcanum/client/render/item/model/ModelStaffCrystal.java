package com.lordskittles.nordicarcanum.client.render.item.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;

public class ModelStaffCrystal extends Model
{
	private final ModelRenderer crystal;
	private final ModelRenderer rotation;

	public ModelStaffCrystal()
	{
		super(RenderType::getEntityCutout);
		textureWidth = 16;
		textureHeight = 16;

		crystal = new ModelRenderer(this);
		crystal.setRotationPoint(0.5F, 46.0F, -0.5F);
		crystal.setTextureOffset(0, 4).addBox(-1.5F, -24.5F, -0.5F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		rotation = new ModelRenderer(this);
		rotation.setRotationPoint(-1.9F, -22.9F, -0.2F);
		crystal.addChild(rotation);
		setRotationAngle(rotation, 0.0F, 0.7854F, 0.0F);
		rotation.setTextureOffset(0, 0).addBox(-1.0F, -1.0F, 0.0F, 3.0F, 1.0F, 3.0F, 0.0F, false);
		rotation.setTextureOffset(7, 7).addBox(-0.0101F, -2.1F, 1.0101F, 1.0F, 3.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		crystal.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}