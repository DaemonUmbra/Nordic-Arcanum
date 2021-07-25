package com.lordskittles.nordicarcanum.client.render.item;

import com.lordskittles.arcanumapi.client.render.item.ItemModelWrapper;
import com.lordskittles.arcanumapi.client.render.item.ItemStackTERenderBase;
import com.lordskittles.arcanumapi.common.utilities.NBTUtilities;
import com.lordskittles.arcanumapi.core.NBTConstants;
import com.lordskittles.nordicarcanum.client.render.item.model.ModelBindingBrazier;
import com.lordskittles.nordicarcanum.client.render.item.model.ModelBindingCage;
import com.lordskittles.nordicarcanum.client.render.item.model.ModelBindingCane;
import com.lordskittles.nordicarcanum.client.render.item.model.ModelBindingPedestal;
import com.lordskittles.nordicarcanum.common.item.crafting.BindingMaterial;
import com.lordskittles.nordicarcanum.common.item.crafting.BindingShape;
import com.lordskittles.nordicarcanum.common.item.magic.ItemBinding;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicResourceLocations;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.model.Model;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class ItemStackBindingRender extends ItemStackTERenderBase {

    private final ModelBindingPedestal pedestal = new ModelBindingPedestal();
    private final ModelBindingBrazier brazier = new ModelBindingBrazier();
    private final ModelBindingCane cane = new ModelBindingCane();
    private final ModelBindingCage cage = new ModelBindingCage();

    public static ItemModelWrapper model;

    @Override
    protected void renderItemSpecific(ItemStack stack, PoseStack matrix, MultiBufferSource buffer, int light, int overlay, ItemTransforms.TransformType transform) {

        Model model = null;

        ItemBinding binding = (ItemBinding) stack.getItem();

        CompoundTag nbt = stack.getTag();

        if(nbt == null) {
            nbt = new CompoundTag();
        }

        CompoundTag localNBT = NBTUtilities.getPersistentData(NordicArcanum.MODID, nbt);
        BindingShape shape = BindingShape.get(localNBT.getInt(NBTConstants.BINDING_SHAPE_KEY));

        BindingMaterial material = binding.getMaterial();
        ResourceLocation textureLoc = NordicResourceLocations.getBindingTexture(material, shape.getValue() + 1);

        switch(shape) {
            case None:
                textureLoc = NordicResourceLocations.getBindingTexture(material, 1);
            case Pedestal:
                model = pedestal;
                break;
            case Brazier:
                model = brazier;
                break;
            case Cane:
                model = cane;
                break;
            case Cage:
                model = cage;
                break;
        }

        float scale = 2f;

        matrix.translate(- 0.15, 2.6D, 0);
        matrix.rotate(Vector3f.ZP.rotationDegrees(180));

        matrix.scale(scale, scale, scale);
        model.render(matrix, buffer.getBuffer(model.getRenderType(textureLoc)), light, overlay, 1, 1, 1, 1);
    }

    @Override
    protected void renderBlockSpecific(ItemStack stack, MatrixStack matrix, IRenderTypeBuffer buffer, int light, int overlay, ItemCameraTransforms.TransformType transform) {

    }

    @Nonnull
    @Override
    protected ItemCameraTransforms.TransformType getTransform(ItemStack stack) {

        return model.getTransform();
    }
}
