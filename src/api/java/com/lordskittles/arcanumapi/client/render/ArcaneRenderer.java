package com.lordskittles.arcanumapi.client.render;

import com.lordskittles.arcanumapi.core.ArcanumAPI;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.CrashReport;
import net.minecraft.CrashReportCategory;
import net.minecraft.ReportedException;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import com.mojang.math.Vector3f;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

@Mod.EventBusSubscriber(modid = ArcanumAPI.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ArcaneRenderer {

    public static final int FULL_LIGHT = 0xF000F0;

    public static float[] HSVtoRGB(float h, float s, float v) {
        // H is given on [0->6] or -1. S and V are given on [0->1].
        // RGB are each returned on [0->1].
        float m, n, f;
        int i;

        float[] hsv = new float[3];
        float[] rgb = new float[3];

        hsv[0] = h;
        hsv[1] = s;
        hsv[2] = v;

        if(hsv[0] == - 1) {
            rgb[0] = rgb[1] = rgb[2] = hsv[2];
            return rgb;
        }
        i = (int) (Math.floor(hsv[0]));
        f = hsv[0] - i;
        if(i % 2 == 0) {
            f = 1 - f; // if i is even
        }
        m = hsv[2] * (1 - hsv[1]);
        n = hsv[2] * (1 - hsv[1] * f);
        switch(i) {
            case 6:
            case 0:
                rgb[0] = hsv[2];
                rgb[1] = n;
                rgb[2] = m;
                break;
            case 1:
                rgb[0] = n;
                rgb[1] = hsv[2];
                rgb[2] = m;
                break;
            case 2:
                rgb[0] = m;
                rgb[1] = hsv[2];
                rgb[2] = n;
                break;
            case 3:
                rgb[0] = m;
                rgb[1] = n;
                rgb[2] = hsv[2];
                break;
            case 4:
                rgb[0] = n;
                rgb[1] = m;
                rgb[2] = hsv[2];
                break;
            case 5:
                rgb[0] = hsv[2];
                rgb[1] = m;
                rgb[2] = n;
                break;
        }

        return rgb;

    }

    public static void renderObject(@Nullable Model3D model, @Nonnull PoseStack stack, VertexConsumer buffer, int argb, int light) {

        if(model != null) {
            RenderResizableCuboid.INSTANCE.renderCube(model, stack, buffer, argb, light);
        }
    }

    public static <T extends ItemEntity> void renderItemEntity(T entity, DVector3 pos, DVector3 scale, float ticks, PoseStack stack, MultiBufferSource buffer, int light) {

        EntityRenderer<? super T> renderer = Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(entity);

        try {
            Vec3 position = renderer.getRenderOffset(entity, ticks);
            double x = pos.x + position.x();
            double y = pos.y + position.y();
            double z = pos.z + position.z();

            stack.pushPose();
            stack.translate(x, y, z);
            stack.scale((float) scale.x, (float) scale.y, (float) scale.z);
            renderItem(entity, ticks, stack, buffer, light);
            stack.popPose();
        }
        catch(Throwable throwable) {
            CrashReport crashreport = CrashReport.forThrowable(throwable, "Rendering entity in world");
            CrashReportCategory itemCategory = crashreport.addCategory("Entity being rendered");
            entity.fillCrashReportCategory(itemCategory);
            CrashReportCategory renderCategory = crashreport.addCategory("Renderer details");
            renderCategory.setDetail("Assigned renderer", renderer);
//            renderCategory.setDetail("Location", CrashReportCategory.formatLocation(pos.x, pos.y, pos.z));
            renderCategory.setDetail("Delta", ticks);
            throw new ReportedException(crashreport);
        }
    }

    public static void renderItem(ItemEntity entity, float ticks, PoseStack stack, MultiBufferSource buffer, int light) {

        Level world = entity.level;
        Random random = world.random;
        ItemRenderer renderer = Minecraft.getInstance().getItemRenderer();

        stack.pushPose();
        ItemStack itemStack = entity.getItem();
        int i = itemStack.isEmpty() ? 187 : Item.getId(itemStack.getItem()) + itemStack.getDamageValue();
        random.setSeed(i);
        BakedModel model = renderer.getModel(itemStack, world, (LivingEntity) null, 0);
        float bob = (Mth.sin((entity.age + ticks) / 10.0f + entity.bobOffs) * 0.1f + 0.1f) * 0.25f;

        stack.translate(0, bob + 0.25f, 0);

        float rotation = (((float) entity.getAge() + ticks) / 20.0F + entity.bobOffs) * 0.5f;
        stack.mulPose(Vector3f.YP.rotation(rotation));

        renderer.render(itemStack, ItemTransforms.TransformType.GROUND, false, stack, buffer, light, OverlayTexture.NO_OVERLAY, model);
        stack.popPose();
    }

    public static void resetColor() {

//        RenderSystem.color4f(1, 1, 1, 1);
    }

    public static float getRed(int color) {

        return (color >> 16 & 0xFF) / 255.0F;
    }

    public static float getGreen(int color) {

        return (color >> 8 & 0xFF) / 255.0F;
    }

    public static float getBlue(int color) {

        return (color & 0xFF) / 255.0F;
    }

    public static float getAlpha(int color) {

        return (color >> 24 & 0xFF) / 255.0F;
    }

    public static int getColorARGB(@Nonnull FluidStack fluidStack) {

        return fluidStack.getFluid().getAttributes().getColor(fluidStack);
    }

    public static int getColorARGB(@Nonnull FluidStack fluidStack, float fluidScale) {

        if(fluidStack.isEmpty()) {
            return - 1;
        }
        int color = getColorARGB(fluidStack);
        if(fluidStack.getFluid().getAttributes().isGaseous(fluidStack)) {
            return getColorARGB(getRed(color), getGreen(color), getBlue(color), Math.min(1, fluidScale + 0.2F));
        }
        return color;
    }

    public static int getColorARGB(float red, float green, float blue, float alpha) {

        return getColorARGB((int) (255 * red), (int) (255 * green), (int) (255 * blue), alpha);
    }

    public static int getColorARGB(int red, int green, int blue, float alpha) {

        if(alpha < 0) {
            alpha = 0;
        }
        else
            if(alpha > 1) {
                alpha = 1;
            }

        int argb = (int) (255 * alpha) << 24;
        argb |= red << 16;
        argb |= green << 8;
        argb |= blue;
        return argb;
    }

    public static int calculateGlowLight(int light, @Nonnull FluidStack fluid) {

        return fluid.isEmpty() ? light : calculateGlowLight(light, fluid.getFluid().getAttributes().getLuminosity(fluid));
    }

    public static int calculateGlowLight(int light, int glow) {

        if(glow >= 15) {
            return ArcaneRenderer.FULL_LIGHT;
        }
        int blockLight = LightTexture.block(light);
        int skyLight = LightTexture.sky(light);
        return LightTexture.pack(Math.max(blockLight, glow), Math.max(skyLight, glow));
    }

    public static TextureAtlasSprite getFluidTexture(@Nonnull FluidStack fluidStack, @Nonnull FluidType type) {

        Fluid fluid = fluidStack.getFluid();
        ResourceLocation spriteLocation;
        if(type == FluidType.STILL) {
            spriteLocation = fluid.getAttributes().getStillTexture(fluidStack);
        }
        else {
            spriteLocation = fluid.getAttributes().getFlowingTexture(fluidStack);
        }
        return getSprite(spriteLocation);
    }

    public static TextureAtlasSprite getSprite(ResourceLocation spriteLocation) {

        return Minecraft.getInstance().getTextureAtlas(TextureAtlas.LOCATION_BLOCKS).apply(spriteLocation);
    }

    public enum FluidType {
        STILL,
        FLOWING
    }

    public static class Model3D {

        public DVector3 min;
        public DVector3 max;
        public TextureAtlasSprite[] textures = new TextureAtlasSprite[6];
        public boolean[] renderSides = new boolean[] { true, true, true, true, true, true, false };

        public double sizeX() {

            return this.max.x - this.min.x;
        }

        public double sizeY() {

            return this.max.y - this.min.y;
        }

        public double sizeZ() {

            return this.max.z - this.min.z;
        }

        public void setSideRender(Direction side, boolean value) {

            this.renderSides[side.ordinal()] = value;
        }

        public boolean shouldSideRender(Direction side) {

            return this.renderSides[side.ordinal()];
        }

        public void setTexture(TextureAtlasSprite texture) {

            setTextures(texture, texture, texture, texture, texture, texture);
        }

        public void setTextures(TextureAtlasSprite down, TextureAtlasSprite up, TextureAtlasSprite north, TextureAtlasSprite south, TextureAtlasSprite west, TextureAtlasSprite east) {

            this.textures[0] = down;
            this.textures[1] = up;
            this.textures[2] = north;
            this.textures[3] = south;
            this.textures[4] = east;
            this.textures[5] = west;
        }
    }

    public static class DVector3 {

        public double x;
        public double y;
        public double z;

        public DVector3(double x, double y, double z) {

            this.x = x;
            this.y = y;
            this.z = z;
        }

        public DVector3() {

            this.x = 0;
            this.y = 0;
            this.z = 0;
        }
    }
}
