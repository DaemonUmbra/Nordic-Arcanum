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

public class ModelAttunementAltar extends Model
{
	private final ModelRenderer podium;
	private final ModelRenderer pillar;
	private final ModelRenderer bowlsides;
	private final ModelRenderer crystal;
	private final ModelRenderer ring;

	private final RenderType RENDER_TYPE = getRenderType(NordicResourceLocations.ATTUNEMENT_ALTAR);
	private final float multiplier = 0.0125f;

	private int currentRot;
	private int bobTick;

	public ModelAttunementAltar() {
		super(RenderType::getEntityTranslucent);

		currentRot = 0;
		bobTick = 0;

		textureWidth = 80;
		textureHeight = 80;

		podium = new ModelRenderer(this);
		podium.setRotationPoint(0.0F, 12.8333F, 0.0F);
		podium.setTextureOffset(0, 0).addBox(-8.0F, 8.1667F, -8.0F, 16.0F, 3.0F, 16.0F, 0.0F, false);
		podium.setTextureOffset(0, 35).addBox(-6.0F, -4.8333F, -6.0F, 12.0F, 2.0F, 12.0F, 0.0F, false);
		podium.setTextureOffset(0, 19).addBox(-7.0F, -6.8333F, -7.0F, 14.0F, 2.0F, 14.0F, 0.0F, false);

		pillar = new ModelRenderer(this);
		pillar.setRotationPoint(0.0F, 15.5F, 0.0F);
		pillar.setTextureOffset(40, 41).addBox(-4.0F, -5.5F, -4.0F, 8.0F, 11.0F, 8.0F, 0.0F, false);

		bowlsides = new ModelRenderer(this);
		bowlsides.setRotationPoint(0.0F, 5.0F, 0.0F);
		bowlsides.setTextureOffset(0, 8).addBox(6.0F, -1.0F, -3.0F, 1.0F, 2.0F, 6.0F, 0.0F, false);
		bowlsides.setTextureOffset(0, 0).addBox(-7.0F, -1.0F, -3.0F, 1.0F, 2.0F, 6.0F, 0.0F, false);
		bowlsides.setTextureOffset(48, 0).addBox(-3.0F, -1.0F, -7.0F, 6.0F, 2.0F, 1.0F, 0.0F, false);
		bowlsides.setTextureOffset(36, 36).addBox(-3.0F, -1.0F, 6.0F, 6.0F, 2.0F, 1.0F, 0.0F, false);

		crystal = new ModelRenderer(this);
		crystal.setRotationPoint(0.0F, -2.0F, 0.0F);
		setRotationAngle(crystal, 0.0F, 0.7854F, -0.7854F);
		crystal.setTextureOffset(42, 19).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);

		ring = new ModelRenderer(this);
		ring.setRotationPoint(0.0F, -2.2F, 0.0F);
		ring.setTextureOffset(42, 31).addBox(-3.0F, -0.5F, 6.2F, 6.0F, 1.0F, 1.0F, 0.0F, false);
		ring.setTextureOffset(36, 39).addBox(-3.0F, -0.5F, -7.2F, 6.0F, 1.0F, 1.0F, 0.0F, false);
		ring.setTextureOffset(0, 26).addBox(-7.2F, -0.5F, -3.0F, 1.0F, 1.0F, 6.0F, 0.0F, false);
		ring.setTextureOffset(0, 19).addBox(6.2F, -0.5F, -3.0F, 1.0F, 1.0F, 6.0F, 0.0F, false);
	}

	public void render(@Nonnull MatrixStack matrix, @Nonnull IRenderTypeBuffer renderer, int light, int overlayLight)
	{
		render(matrix, renderer.getBuffer(RENDER_TYPE), light, overlayLight, 1, 1, 1, 1);
	}

	public void render(MatrixStack stack, IVertexBuilder buffer, int light, int overlay)
	{
		this.currentRot++;
		this.bobTick++;

		podium.render(stack, buffer, light, overlay);
		pillar.render(stack, buffer, light, overlay);

		stack.rotate(Vector3f.YP.rotationDegrees(45));
		bowlsides.render(stack, buffer, light, overlay);
		stack.rotate(Vector3f.YP.rotationDegrees(-45));

		float delta = this.bobTick * 0.0125f;
		float bob = MathHelper.sin(delta) * this.multiplier;
		stack.translate(0, bob, 0);

		float rot = this.currentRot * 0.025f;
		stack.rotate(Vector3f.YP.rotationDegrees(rot));
		crystal.render(stack, buffer, light, overlay);
		stack.rotate(Vector3f.YP.rotationDegrees(-rot * 4));

		renderRing(stack, buffer, light, overlay, 0, 0, 0);
		renderRing(stack, buffer, light, overlay, 45, -0.1D, -0.05D);
		renderRing(stack, buffer, light, overlay, 90, -0.14D, -0.1D);
	}

	@Override
	public void render(MatrixStack stack, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha)
	{
		render(stack, buffer, light, overlay);
	}

	private void renderRing(MatrixStack stack, IVertexBuilder buffer, int light, int overlay, int offset, double offsetPositionX, double offsetPositionY)
	{
		stack.translate(offsetPositionX, offsetPositionY, 0);
		stack.rotate(Vector3f.ZP.rotationDegrees(offset));

		stack.rotate(Vector3f.YP.rotationDegrees(-this.currentRot * 0.1f));
		ring.render(stack, buffer, light, overlay);
		stack.rotate(Vector3f.YP.rotationDegrees(45));
		ring.render(stack, buffer, light, overlay);
		stack.rotate(Vector3f.YP.rotationDegrees(-45));

		stack.rotate(Vector3f.ZP.rotationDegrees(-offset));
		stack.translate(-offsetPositionX, -offsetPositionY, 0);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}