package com.lordskittles.nordicarcanum.client.render.tileentity;

import com.lordskittles.arcanumapi.client.render.tileentity.TileEntityRendererBase;
import com.lordskittles.nordicarcanum.client.render.tileentity.model.ModelNordicAnvil;
import com.lordskittles.nordicarcanum.common.tileentity.TileEntityNordicAnvil;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;

public class TileRendererNordicAnvil extends TileEntityRendererBase<TileEntityNordicAnvil>
{
    private static ModelNordicAnvil anvil = new ModelNordicAnvil();

    public TileRendererNordicAnvil(TileEntityRendererDispatcher rendererDispatcherIn)
    {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(TileEntityNordicAnvil tile, float ticks, MatrixStack stack, IRenderTypeBuffer buffer, int light, int overlay)
    {
        stack.push();

        Quaternion rotation = Vector3f.ZN.rotationDegrees(180);
        rotation.multiply(Vector3f.YN.rotationDegrees(180));
        stack.rotate(rotation);
        stack.translate(0.53, 0, -0.47);

        anvil.render(stack, buffer, light, overlay);
        stack.pop();

        ItemStack heldItem = tile.getHeldItem();
        if (!heldItem.isEmpty())
        {
            stack.push();
            stack.translate(0.5, 1, 0.5D);
            stack.rotate(Vector3f.XN.rotationDegrees(90));
            stack.scale(0.375f, 0.375f, 0.375f);

            Minecraft.getInstance().getItemRenderer().renderItem(heldItem, ItemCameraTransforms.TransformType.FIXED, light, overlay, stack, buffer);

            stack.pop();
        }

        ItemStack heldCast = tile.getHeldCast();
        if (!heldCast.isEmpty())
        {
            stack.push();
            stack.translate(0.3, 1, 0.5D);
            stack.rotate(Vector3f.XN.rotationDegrees(90));
            stack.rotate(Vector3f.ZP.rotationDegrees(15));
            stack.scale(0.25f, 0.25f, 0.25f);

            Minecraft.getInstance().getItemRenderer().renderItem(heldCast, ItemCameraTransforms.TransformType.FIXED, light, overlay, stack, buffer);

            stack.pop();
        }
    }
}
