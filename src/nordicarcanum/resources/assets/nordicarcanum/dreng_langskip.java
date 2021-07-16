// Made with Blockbench 3.9.2
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


public class custom_model extends EntityModel<Entity> {
	private final ModelRenderer dreng;
	private final ModelRenderer boat2;
	private final ModelRenderer sail2;

	public custom_model() {
		textureWidth = 128;
		textureHeight = 128;

		dreng = new ModelRenderer(this);
		dreng.setRotationPoint(0.0F, 24.0F, 0.0F);
		dreng.setTextureOffset(0, 34).addBox(-2.0F, -17.0F, -23.0F, 4.0F, 3.0F, 6.0F, 0.0F, false);
		dreng.setTextureOffset(0, 16).addBox(-2.0F, -17.0F, 33.0F, 4.0F, 3.0F, 6.0F, 0.0F, false);

		boat2 = new ModelRenderer(this);
		boat2.setRotationPoint(0.0F, 0.0F, 0.0F);
		dreng.addChild(boat2);
		boat2.setTextureOffset(36, 38).addBox(-8.0F, -6.0F, -8.0F, 2.0F, 4.0F, 32.0F, 0.0F, false);
		boat2.setTextureOffset(0, 0).addBox(-7.0F, -2.0F, -8.0F, 14.0F, 2.0F, 32.0F, 0.0F, false);
		boat2.setTextureOffset(60, 0).addBox(-4.0F, 0.0F, -5.0F, 8.0F, 2.0F, 26.0F, 0.0F, false);
		boat2.setTextureOffset(0, 34).addBox(6.0F, -6.0F, -8.0F, 2.0F, 4.0F, 32.0F, 0.0F, false);
		boat2.setTextureOffset(36, 50).addBox(4.0F, -7.0F, 22.0F, 2.0F, 5.0F, 6.0F, 0.0F, false);
		boat2.setTextureOffset(0, 8).addBox(-4.0F, -4.0F, 22.0F, 8.0F, 2.0F, 6.0F, 0.0F, false);
		boat2.setTextureOffset(12, 51).addBox(-2.0F, -6.0F, 28.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		boat2.setTextureOffset(0, 26).addBox(-2.0F, -6.0F, -16.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		boat2.setTextureOffset(16, 39).addBox(-2.0F, -14.0F, -19.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);
		boat2.setTextureOffset(36, 38).addBox(-2.0F, -14.0F, 31.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);
		boat2.setTextureOffset(0, 0).addBox(-4.0F, -4.0F, -12.0F, 8.0F, 2.0F, 6.0F, 0.0F, false);
		boat2.setTextureOffset(46, 44).addBox(-6.0F, -7.0F, 22.0F, 2.0F, 5.0F, 6.0F, 0.0F, false);
		boat2.setTextureOffset(0, 43).addBox(-6.0F, -7.0F, -12.0F, 2.0F, 5.0F, 6.0F, 0.0F, false);
		boat2.setTextureOffset(14, 19).addBox(4.0F, -7.0F, -12.0F, 2.0F, 5.0F, 6.0F, 0.0F, false);
		boat2.setTextureOffset(12, 57).addBox(2.0F, -9.0F, -16.0F, 2.0F, 5.0F, 4.0F, 0.0F, false);
		boat2.setTextureOffset(56, 38).addBox(-4.0F, -9.0F, -16.0F, 2.0F, 5.0F, 4.0F, 0.0F, false);
		boat2.setTextureOffset(52, 55).addBox(2.0F, -9.0F, 28.0F, 2.0F, 5.0F, 4.0F, 0.0F, false);
		boat2.setTextureOffset(0, 54).addBox(-4.0F, -9.0F, 28.0F, 2.0F, 5.0F, 4.0F, 0.0F, false);

		sail2 = new ModelRenderer(this);
		sail2.setRotationPoint(0.0F, 0.0F, 0.0F);
		dreng.addChild(sail2);
		sail2.setTextureOffset(0, 70).addBox(-1.0F, -38.0F, 7.0F, 2.0F, 36.0F, 2.0F, 0.0F, false);
		sail2.setTextureOffset(22, 0).addBox(-1.0F, -42.0F, 7.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		sail2.setTextureOffset(36, 34).addBox(-8.0F, -40.0F, 7.0F, 16.0F, 2.0F, 2.0F, 0.0F, false);
		sail2.setTextureOffset(72, 57).addBox(-8.0F, -39.0F, 5.0F, 16.0F, 1.0F, 2.0F, 0.0F, false);
		sail2.setTextureOffset(72, 54).addBox(-8.0F, -38.0F, 3.0F, 16.0F, 1.0F, 2.0F, 0.0F, false);
		sail2.setTextureOffset(72, 63).addBox(-8.0F, -37.0F, 2.0F, 16.0F, 2.0F, 1.0F, 0.0F, false);
		sail2.setTextureOffset(72, 60).addBox(-8.0F, -21.0F, 2.0F, 16.0F, 2.0F, 1.0F, 0.0F, false);
		sail2.setTextureOffset(72, 49).addBox(-8.0F, -25.0F, 1.0F, 16.0F, 4.0F, 1.0F, 0.0F, false);
		sail2.setTextureOffset(72, 44).addBox(-8.0F, -35.0F, 1.0F, 16.0F, 4.0F, 1.0F, 0.0F, false);
		sail2.setTextureOffset(72, 34).addBox(-8.0F, -31.0F, 0.0F, 16.0F, 6.0F, 1.0F, 0.0F, false);
		sail2.setTextureOffset(72, 41).addBox(-8.0F, -18.0F, 5.0F, 16.0F, 1.0F, 2.0F, 0.0F, false);
		sail2.setTextureOffset(60, 28).addBox(-8.0F, -19.0F, 3.0F, 16.0F, 1.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		dreng.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}