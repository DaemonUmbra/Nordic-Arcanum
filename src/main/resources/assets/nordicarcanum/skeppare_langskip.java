// Made with Blockbench 3.9.2
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


public class custom_model extends EntityModel<Entity> {
	private final ModelRenderer skeppare;
	private final ModelRenderer sail;
	private final ModelRenderer shield1;
	private final ModelRenderer cube_r1;
	private final ModelRenderer shield2;
	private final ModelRenderer cube_r2;
	private final ModelRenderer shield3;
	private final ModelRenderer cube_r3;
	private final ModelRenderer shield4;
	private final ModelRenderer cube_r4;
	private final ModelRenderer shield5;
	private final ModelRenderer cube_r5;
	private final ModelRenderer shield6;
	private final ModelRenderer cube_r6;
	private final ModelRenderer shield7;
	private final ModelRenderer cube_r7;
	private final ModelRenderer shield8;
	private final ModelRenderer cube_r8;
	private final ModelRenderer boat;

	public custom_model() {
		textureWidth = 128;
		textureHeight = 128;

		skeppare = new ModelRenderer(this);
		skeppare.setRotationPoint(0.0F, 24.0F, 0.0F);
		skeppare.setTextureOffset(10, 45).addBox(-2.0F, -19.0F, -23.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		skeppare.setTextureOffset(60, 5).addBox(-2.0F, -16.0F, -22.0F, 4.0F, 2.0F, 3.0F, 0.0F, false);
		skeppare.setTextureOffset(46, 38).addBox(-2.0F, -19.0F, 35.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		skeppare.setTextureOffset(60, 0).addBox(-2.0F, -16.0F, 35.0F, 4.0F, 2.0F, 3.0F, 0.0F, false);
		skeppare.setTextureOffset(60, 10).addBox(-2.0F, -19.0F, -19.0F, 4.0F, 5.0F, 2.0F, 0.0F, false);
		skeppare.setTextureOffset(60, 17).addBox(-2.0F, -19.0F, 33.0F, 4.0F, 5.0F, 2.0F, 0.0F, false);

		sail = new ModelRenderer(this);
		sail.setRotationPoint(0.0F, 0.0F, 0.0F);
		skeppare.addChild(sail);
		sail.setTextureOffset(0, 70).addBox(-1.0F, -38.0F, 7.0F, 2.0F, 36.0F, 2.0F, 0.0F, false);
		sail.setTextureOffset(72, 66).addBox(-1.0F, -42.0F, 7.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		sail.setTextureOffset(36, 34).addBox(-8.0F, -40.0F, 7.0F, 16.0F, 2.0F, 2.0F, 0.0F, false);
		sail.setTextureOffset(72, 57).addBox(-8.0F, -39.0F, 5.0F, 16.0F, 1.0F, 2.0F, 0.0F, false);
		sail.setTextureOffset(72, 54).addBox(-8.0F, -38.0F, 3.0F, 16.0F, 1.0F, 2.0F, 0.0F, false);
		sail.setTextureOffset(72, 63).addBox(-8.0F, -37.0F, 2.0F, 16.0F, 2.0F, 1.0F, 0.0F, false);
		sail.setTextureOffset(72, 60).addBox(-8.0F, -21.0F, 2.0F, 16.0F, 2.0F, 1.0F, 0.0F, false);
		sail.setTextureOffset(72, 46).addBox(-8.0F, -25.0F, 1.0F, 16.0F, 4.0F, 1.0F, 0.0F, false);
		sail.setTextureOffset(72, 41).addBox(-8.0F, -35.0F, 1.0F, 16.0F, 4.0F, 1.0F, 0.0F, false);
		sail.setTextureOffset(72, 34).addBox(-8.0F, -31.0F, 0.0F, 16.0F, 6.0F, 1.0F, 0.0F, false);
		sail.setTextureOffset(60, 28).addBox(-8.0F, -18.0F, 5.0F, 16.0F, 1.0F, 2.0F, 0.0F, false);
		sail.setTextureOffset(72, 51).addBox(-8.0F, -19.0F, 3.0F, 16.0F, 1.0F, 2.0F, 0.0F, false);

		shield1 = new ModelRenderer(this);
		shield1.setRotationPoint(0.0F, 0.0F, 0.0F);
		skeppare.addChild(shield1);
		

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		shield1.addChild(cube_r1);
		setRotationAngle(cube_r1, -0.7854F, 0.0F, 0.0F);
		cube_r1.setTextureOffset(28, 54).addBox(8.0F, -3.0F, -7.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		cube_r1.setTextureOffset(71, 7).addBox(8.0F, 0.0F, -6.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		cube_r1.setTextureOffset(29, 71).addBox(8.0F, -4.0F, -6.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		cube_r1.setTextureOffset(60, 41).addBox(8.0F, -3.0F, -6.0F, 1.0F, 3.0F, 3.0F, 0.0F, false);
		cube_r1.setTextureOffset(16, 51).addBox(8.0F, -3.0F, -3.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		shield2 = new ModelRenderer(this);
		shield2.setRotationPoint(0.0F, 0.0F, 21.0F);
		skeppare.addChild(shield2);
		

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(0.0F, 0.0F, 0.0F);
		shield2.addChild(cube_r2);
		setRotationAngle(cube_r2, -0.7854F, 0.0F, 0.0F);
		cube_r2.setTextureOffset(46, 38).addBox(8.0F, -3.0F, -7.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		cube_r2.setTextureOffset(24, 70).addBox(8.0F, 0.0F, -6.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		cube_r2.setTextureOffset(71, 2).addBox(8.0F, -4.0F, -6.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		cube_r2.setTextureOffset(20, 60).addBox(8.0F, -3.0F, -6.0F, 1.0F, 3.0F, 3.0F, 0.0F, false);
		cube_r2.setTextureOffset(36, 49).addBox(8.0F, -3.0F, -3.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		shield3 = new ModelRenderer(this);
		shield3.setRotationPoint(0.0F, 0.0F, 14.0F);
		skeppare.addChild(shield3);
		

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(0.0F, 0.0F, 0.0F);
		shield3.addChild(cube_r3);
		setRotationAngle(cube_r3, -0.7854F, 0.0F, 0.0F);
		cube_r3.setTextureOffset(10, 45).addBox(8.0F, -3.0F, -7.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		cube_r3.setTextureOffset(70, 14).addBox(8.0F, 0.0F, -6.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		cube_r3.setTextureOffset(16, 70).addBox(8.0F, -4.0F, -6.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		cube_r3.setTextureOffset(12, 60).addBox(8.0F, -3.0F, -6.0F, 1.0F, 3.0F, 3.0F, 0.0F, false);
		cube_r3.setTextureOffset(22, 45).addBox(8.0F, -3.0F, -3.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		shield4 = new ModelRenderer(this);
		shield4.setRotationPoint(0.0F, 0.0F, 7.0F);
		skeppare.addChild(shield4);
		

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(0.0F, 0.0F, 0.0F);
		shield4.addChild(cube_r4);
		setRotationAngle(cube_r4, -0.7854F, 0.0F, 0.0F);
		cube_r4.setTextureOffset(36, 38).addBox(8.0F, -3.0F, -7.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		cube_r4.setTextureOffset(69, 21).addBox(8.0F, 0.0F, -6.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		cube_r4.setTextureOffset(8, 70).addBox(8.0F, -4.0F, -6.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		cube_r4.setTextureOffset(57, 57).addBox(8.0F, -3.0F, -6.0F, 1.0F, 3.0F, 3.0F, 0.0F, false);
		cube_r4.setTextureOffset(0, 45).addBox(8.0F, -3.0F, -3.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		shield5 = new ModelRenderer(this);
		shield5.setRotationPoint(-17.0F, 0.0F, 0.0F);
		skeppare.addChild(shield5);
		

		cube_r5 = new ModelRenderer(this);
		cube_r5.setRotationPoint(0.0F, 0.0F, 0.0F);
		shield5.addChild(cube_r5);
		setRotationAngle(cube_r5, -0.7854F, 0.0F, 0.0F);
		cube_r5.setTextureOffset(18, 34).addBox(8.0F, -3.0F, -7.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		cube_r5.setTextureOffset(56, 51).addBox(8.0F, 0.0F, -6.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		cube_r5.setTextureOffset(48, 60).addBox(8.0F, -4.0F, -6.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		cube_r5.setTextureOffset(24, 48).addBox(8.0F, -3.0F, -6.0F, 1.0F, 3.0F, 3.0F, 0.0F, false);
		cube_r5.setTextureOffset(26, 34).addBox(8.0F, -3.0F, -3.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		shield6 = new ModelRenderer(this);
		shield6.setRotationPoint(-17.0F, 0.0F, 7.0F);
		skeppare.addChild(shield6);
		

		cube_r6 = new ModelRenderer(this);
		cube_r6.setRotationPoint(0.0F, 0.0F, 0.0F);
		shield6.addChild(cube_r6);
		setRotationAngle(cube_r6, -0.7854F, 0.0F, 0.0F);
		cube_r6.setTextureOffset(28, 14).addBox(8.0F, -3.0F, -7.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		cube_r6.setTextureOffset(24, 28).addBox(8.0F, 0.0F, -6.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		cube_r6.setTextureOffset(8, 56).addBox(8.0F, -4.0F, -6.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		cube_r6.setTextureOffset(10, 34).addBox(8.0F, -3.0F, -6.0F, 1.0F, 3.0F, 3.0F, 0.0F, false);
		cube_r6.setTextureOffset(0, 34).addBox(8.0F, -3.0F, -3.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		shield7 = new ModelRenderer(this);
		shield7.setRotationPoint(-17.0F, 0.0F, 14.0F);
		skeppare.addChild(shield7);
		

		cube_r7 = new ModelRenderer(this);
		cube_r7.setRotationPoint(0.0F, 0.0F, 0.0F);
		shield7.addChild(cube_r7);
		setRotationAngle(cube_r7, -0.7854F, 0.0F, 0.0F);
		cube_r7.setTextureOffset(0, 16).addBox(8.0F, -3.0F, -7.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		cube_r7.setTextureOffset(8, 28).addBox(8.0F, 0.0F, -6.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		cube_r7.setTextureOffset(16, 28).addBox(8.0F, -4.0F, -6.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		cube_r7.setTextureOffset(22, 8).addBox(8.0F, -3.0F, -6.0F, 1.0F, 3.0F, 3.0F, 0.0F, false);
		cube_r7.setTextureOffset(27, 7).addBox(8.0F, -3.0F, -3.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		shield8 = new ModelRenderer(this);
		shield8.setRotationPoint(-17.0F, 0.0F, 21.0F);
		skeppare.addChild(shield8);
		

		cube_r8 = new ModelRenderer(this);
		cube_r8.setRotationPoint(0.0F, 0.0F, 0.0F);
		shield8.addChild(cube_r8);
		setRotationAngle(cube_r8, -0.7854F, 0.0F, 0.0F);
		cube_r8.setTextureOffset(0, 0).addBox(8.0F, -3.0F, -7.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		cube_r8.setTextureOffset(12, 16).addBox(8.0F, 0.0F, -6.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		cube_r8.setTextureOffset(0, 28).addBox(8.0F, -4.0F, -6.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		cube_r8.setTextureOffset(22, 0).addBox(8.0F, -3.0F, -6.0F, 1.0F, 3.0F, 3.0F, 0.0F, false);
		cube_r8.setTextureOffset(0, 8).addBox(8.0F, -3.0F, -3.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		boat = new ModelRenderer(this);
		boat.setRotationPoint(0.0F, 0.0F, 0.0F);
		skeppare.addChild(boat);
		boat.setTextureOffset(0, 34).addBox(-8.0F, -6.0F, -8.0F, 2.0F, 4.0F, 32.0F, 0.0F, false);
		boat.setTextureOffset(0, 0).addBox(-7.0F, -2.0F, -8.0F, 14.0F, 2.0F, 32.0F, 0.0F, false);
		boat.setTextureOffset(60, 0).addBox(-4.0F, 0.0F, -5.0F, 8.0F, 2.0F, 26.0F, 0.0F, false);
		boat.setTextureOffset(36, 38).addBox(6.0F, -6.0F, -8.0F, 2.0F, 4.0F, 32.0F, 0.0F, false);
		boat.setTextureOffset(0, 45).addBox(4.0F, -7.0F, 22.0F, 2.0F, 5.0F, 6.0F, 0.0F, false);
		boat.setTextureOffset(0, 8).addBox(-4.0F, -4.0F, 22.0F, 8.0F, 2.0F, 6.0F, 0.0F, false);
		boat.setTextureOffset(36, 49).addBox(-2.0F, -6.0F, 28.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		boat.setTextureOffset(48, 45).addBox(-2.0F, -6.0F, -16.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		boat.setTextureOffset(0, 16).addBox(-2.0F, -14.0F, -19.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);
		boat.setTextureOffset(16, 16).addBox(-2.0F, -14.0F, 31.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);
		boat.setTextureOffset(0, 0).addBox(-4.0F, -4.0F, -12.0F, 8.0F, 2.0F, 6.0F, 0.0F, false);
		boat.setTextureOffset(36, 38).addBox(-6.0F, -7.0F, 22.0F, 2.0F, 5.0F, 6.0F, 0.0F, false);
		boat.setTextureOffset(16, 34).addBox(-6.0F, -7.0F, -12.0F, 2.0F, 5.0F, 6.0F, 0.0F, false);
		boat.setTextureOffset(0, 34).addBox(4.0F, -7.0F, -12.0F, 2.0F, 5.0F, 6.0F, 0.0F, false);
		boat.setTextureOffset(0, 56).addBox(2.0F, -9.0F, -16.0F, 2.0F, 5.0F, 4.0F, 0.0F, false);
		boat.setTextureOffset(36, 55).addBox(-4.0F, -9.0F, -16.0F, 2.0F, 5.0F, 4.0F, 0.0F, false);
		boat.setTextureOffset(16, 51).addBox(2.0F, -9.0F, 28.0F, 2.0F, 5.0F, 4.0F, 0.0F, false);
		boat.setTextureOffset(48, 51).addBox(-4.0F, -9.0F, 28.0F, 2.0F, 5.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		skeppare.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}