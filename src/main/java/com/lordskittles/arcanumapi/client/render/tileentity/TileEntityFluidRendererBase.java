package com.lordskittles.arcanumapi.client.render.tileentity;

import com.lordskittles.arcanumapi.client.render.ArcaneRenderType;
import com.lordskittles.arcanumapi.client.render.ArcaneRenderer;
import com.lordskittles.arcanumapi.client.render.ArcaneRenderer.DVector3;
import com.lordskittles.arcanumapi.client.render.ArcaneRenderer.Model3D;
import com.lordskittles.arcanumapi.client.render.FluidRenderMap;
import com.lordskittles.arcanumapi.common.tileentity.TileEntityFluidInventory;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;

public abstract class TileEntityFluidRendererBase<T extends TileEntityFluidInventory> extends TileEntityRendererBase<T> {

    protected static final FluidRenderMap<Int2ObjectMap<Model3D>> cachedFluids = new FluidRenderMap<>();
    protected static final int stages = 1400;

    public TileEntityFluidRendererBase(TileEntityRendererDispatcher dispatcher) {

        super(dispatcher);
    }

    public static void resetCachedModels() {

        cachedFluids.clear();
    }

    public void renderFluid(T tile, MatrixStack stack, IRenderTypeBuffer buffer, int light) {

        FluidStack fluid = tile.getFluid();
        float fluidScale = tile.prevScale;

        if(! fluid.isEmpty() && fluidScale > 0) {
            IVertexBuilder builder = buffer.getBuffer(ArcaneRenderType.resizableCuboid());
            int modelNumber = Math.min(stages - 1, (int) (fluidScale * ((float) stages - 1)));

            ArcaneRenderer.renderObject(getFluidModel(fluid, modelNumber), stack, builder, ArcaneRenderer.getColorARGB(fluid, fluidScale),
                    ArcaneRenderer.calculateGlowLight(light, fluid));
        }
    }

    protected Model3D getFluidModel(@Nonnull FluidStack fluid, int stage) {

        if(cachedFluids.containsKey(fluid) && cachedFluids.get(fluid).containsKey(stage)) {
            return cachedFluids.get(fluid).get(stage);
        }
        Model3D model = new Model3D();
        model.setTexture(ArcaneRenderer.getFluidTexture(fluid, ArcaneRenderer.FluidType.STILL));
        if(fluid.getFluid().getAttributes().getStillTexture(fluid) != null) {
            model.min = getMin(stage);
            model.max = getMax(stage);
        }

        if(cachedFluids.containsKey(fluid)) {
            cachedFluids.get(fluid).put(stage, model);
        }
        else {
            Int2ObjectMap<Model3D> map = new Int2ObjectOpenHashMap<>();
            map.put(stage, model);
            cachedFluids.put(fluid, map);
        }


        return model;
    }

    protected DVector3 getMin(int stage) {

        return new DVector3(.125 + .01, .0625 + .01, .125 + .01);
    }

    protected DVector3 getMax(int stage) {

        return new DVector3(.875 - .01, .0625 + ((float) stage / (float) stages) * .875 - .01, 0.875 - .01);
    }
}
