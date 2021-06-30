package com.lordskittles.arcanumapi.client.render;

import com.lordskittles.arcanumapi.core.ArcanumAPI;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.crash.ReportedException;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

@Mod.EventBusSubscriber(modid = ArcanumAPI.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ArcaneRenderer
{
    public static final int FULL_LIGHT = 0xF000F0;

    public static void renderObject(@Nullable Model3D model, @Nonnull MatrixStack stack, IVertexBuilder buffer, int argb, int light)
    {
        if (model != null)
        {
            RenderResizableCuboid.INSTANCE.renderCube(model, stack, buffer, argb, light);
        }
    }

    public static <T extends ItemEntity> void renderItemEntity(T entity, DVector3 pos, DVector3 scale, float ticks, MatrixStack stack, IRenderTypeBuffer buffer, int light)
    {
        EntityRenderer<? super T> renderer = Minecraft.getInstance().getRenderManager().getRenderer(entity);

        try
        {
            Vector3d position = renderer.getRenderOffset(entity, ticks);
            double x = pos.x + position.getX();
            double y = pos.y + position.getY();
            double z = pos.z + position.getZ();

            stack.push();
            stack.translate(x, y, z);
            stack.scale((float)scale.x, (float)scale.y, (float)scale.z);
            renderItem(entity, ticks, stack, buffer, light);
            stack.pop();
        }
        catch (Throwable throwable)
        {
            CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Rendering entity in world");
            CrashReportCategory itemCategory = crashreport.makeCategory("Entity being rendered");
            entity.fillCrashReport(itemCategory);
            CrashReportCategory renderCategory = crashreport.makeCategory("Renderer details");
            renderCategory.addDetail("Assigned renderer", renderer);
            renderCategory.addDetail("Location", CrashReportCategory.getCoordinateInfo(pos.x, pos.y, pos.z));
            renderCategory.addDetail("Delta", ticks);
            throw new ReportedException(crashreport);
        }
    }

    public static void renderItem(ItemEntity entity, float ticks, MatrixStack stack, IRenderTypeBuffer buffer, int light)
    {
        World world = entity.world;
        Random random = world.rand;
        ItemRenderer renderer = Minecraft.getInstance().getItemRenderer();

        stack.push();
        ItemStack itemStack = entity.getItem();
        int i = itemStack.isEmpty() ? 187 : Item.getIdFromItem(itemStack.getItem()) + itemStack.getDamage();
        random.setSeed(i);
        IBakedModel model = renderer.getItemModelWithOverrides(itemStack, world, (LivingEntity)null);
        float bob = (MathHelper.sin((entity.age + ticks) / 10.0f + entity.hoverStart) * 0.1f + 0.1f) * 0.25f;

        stack.translate(0, bob + 0.25f, 0);

        float rotation = (((float)entity.getAge() + ticks) / 20.0F + entity.hoverStart) * 0.5f;
        stack.rotate(Vector3f.YP.rotation(rotation));

        renderer.renderItem(itemStack, ItemCameraTransforms.TransformType.GROUND, false, stack, buffer, light, OverlayTexture.NO_OVERLAY, model);
        stack.pop();
    }

    public static void resetColor()
    {
        RenderSystem.color4f(1, 1, 1, 1);
    }

    public static float getRed(int color)
    {
        return (color >> 16 & 0xFF) / 255.0F;
    }

    public static float getGreen(int color)
    {
        return (color >> 8 & 0xFF) / 255.0F;
    }

    public static float getBlue(int color)
    {
        return (color & 0xFF) / 255.0F;
    }

    public static float getAlpha(int color)
    {
        return (color >> 24 & 0xFF) / 255.0F;
    }

    public static int getColorARGB(@Nonnull FluidStack fluidStack)
    {
        return fluidStack.getFluid().getAttributes().getColor(fluidStack);
    }

    public static int getColorARGB(@Nonnull FluidStack fluidStack, float fluidScale)
    {
        if (fluidStack.isEmpty())
        {
            return -1;
        }
        int color = getColorARGB(fluidStack);
        if (fluidStack.getFluid().getAttributes().isGaseous(fluidStack))
        {
            return getColorARGB(getRed(color), getGreen(color), getBlue(color), Math.min(1, fluidScale + 0.2F));
        }
        return color;
    }

    public static int getColorARGB(float red, float green, float blue, float alpha)
    {
        return getColorARGB((int) (255 * red), (int) (255 * green), (int) (255 * blue), alpha);
    }

    public static int getColorARGB(int red, int green, int blue, float alpha)
    {
        if (alpha < 0)
        {
            alpha = 0;
        }
        else if (alpha > 1)
        {
            alpha = 1;
        }

        int argb = (int) (255 * alpha) << 24;
        argb |= red << 16;
        argb |= green << 8;
        argb |= blue;
        return argb;
    }

    public static int calculateGlowLight(int light, @Nonnull FluidStack fluid)
    {
        return fluid.isEmpty() ? light : calculateGlowLight(light, fluid.getFluid().getAttributes().getLuminosity(fluid));
    }

    public static int calculateGlowLight(int light, int glow)
    {
        if (glow >= 15)
        {
            return ArcaneRenderer.FULL_LIGHT;
        }
        int blockLight = LightTexture.getLightBlock(light);
        int skyLight = LightTexture.getLightSky(light);
        return LightTexture.packLight(Math.max(blockLight, glow), Math.max(skyLight, glow));
    }

    public static TextureAtlasSprite getFluidTexture(@Nonnull FluidStack fluidStack, @Nonnull FluidType type)
    {
        Fluid fluid = fluidStack.getFluid();
        ResourceLocation spriteLocation;
        if (type == FluidType.STILL)
        {
            spriteLocation = fluid.getAttributes().getStillTexture(fluidStack);
        }
        else
        {
            spriteLocation = fluid.getAttributes().getFlowingTexture(fluidStack);
        }
        return getSprite(spriteLocation);
    }

    public static TextureAtlasSprite getSprite(ResourceLocation spriteLocation)
    {
        return Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(spriteLocation);
    }

    public enum FluidType
    {
        STILL,
        FLOWING
    }

    public static class Model3D
    {
        public DVector3 min;
        public DVector3 max;
        public TextureAtlasSprite[] textures = new TextureAtlasSprite[6];
        public boolean[] renderSides = new boolean[] { true, true, true, true, true, true, false };

        public double sizeX()
        {
            return this.max.x - this.min.x;
        }

        public double sizeY()
        {
            return this.max.y - this.min.y;
        }

        public double sizeZ()
        {
            return this.max.z - this.min.z;
        }

        public void setSideRender(Direction side, boolean value)
        {
            this.renderSides[side.ordinal()] = value;
        }

        public boolean shouldSideRender(Direction side)
        {
            return this.renderSides[side.ordinal()];
        }

        public void setTexture(TextureAtlasSprite texture)
        {
            setTextures(texture, texture, texture, texture, texture, texture);
        }

        public void setTextures(TextureAtlasSprite down, TextureAtlasSprite up, TextureAtlasSprite north, TextureAtlasSprite south, TextureAtlasSprite west, TextureAtlasSprite east)
        {
            this.textures[0] = down;
            this.textures[1] = up;
            this.textures[2] = north;
            this.textures[3] = south;
            this.textures[4] = east;
            this.textures[5] = west;
        }
    }

    public static class DVector3
    {
        public double x;
        public double y;
        public double z;

        public DVector3(double x, double y, double z)
        {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public DVector3()
        {
            this.x = 0;
            this.y = 0;
            this.z = 0;
        }
    }
}
