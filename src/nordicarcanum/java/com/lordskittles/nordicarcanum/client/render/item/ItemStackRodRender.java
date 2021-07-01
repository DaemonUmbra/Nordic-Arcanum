package com.lordskittles.nordicarcanum.client.render.item;

import com.lordskittles.arcanumapi.client.render.item.ItemModelWrapper;
import com.lordskittles.arcanumapi.client.render.item.ItemStackTERenderBase;
import com.lordskittles.arcanumapi.common.utilities.NBTUtilities;
import com.lordskittles.arcanumapi.core.NBTConstants;
import com.lordskittles.nordicarcanum.client.render.item.model.ModelRod1;
import com.lordskittles.nordicarcanum.client.render.item.model.ModelRod2;
import com.lordskittles.nordicarcanum.client.render.item.model.ModelRod3;
import com.lordskittles.nordicarcanum.client.render.item.model.ModelRod4;
import com.lordskittles.nordicarcanum.common.item.crafting.RodMaterial;
import com.lordskittles.nordicarcanum.common.item.crafting.RodShape;
import com.lordskittles.nordicarcanum.common.item.magic.ItemRod;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicResourceLocations;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class ItemStackRodRender extends ItemStackTERenderBase {

    private final ModelRod1 straight = new ModelRod1();
    private final ModelRod2 twist = new ModelRod2();
    private final ModelRod3 bend = new ModelRod3();
    private final ModelRod4 pillar = new ModelRod4();

    public static ItemModelWrapper model;

    @Override
    protected void renderItemSpecific(ItemStack stack, MatrixStack matrix, IRenderTypeBuffer buffer, int light, int overlay, ItemCameraTransforms.TransformType transform) {

        Model model = null;

        ItemRod rod = (ItemRod) stack.getItem();

        CompoundNBT nbt = stack.getTag();

        if(nbt == null) {
            nbt = new CompoundNBT();
        }

        CompoundNBT localNBT = NBTUtilities.getPersistentData(NordicArcanum.MODID, nbt);
        RodShape shape = RodShape.get(localNBT.getInt(NBTConstants.ROD_SHAPE_KEY));

        RodMaterial material = rod.getMaterial();
        ResourceLocation textureLoc = NordicResourceLocations.getRodTexture(material, shape.getValue() + 1);

        switch(shape) {
            case None:
                textureLoc = NordicResourceLocations.getRodTexture(material, 1);
            case Straight:
                model = straight;
                break;
            case Twist:
                model = twist;
                break;
            case Bend:
                model = bend;
                break;
            case Pillar:
                model = pillar;
                break;
        }

        float scale = 2f;

        if(this.transform == ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND) {
            matrix.translate(1.1, 0.6, - 1.25);
            matrix.rotate(Vector3f.ZP.rotationDegrees(110));
            matrix.rotate(Vector3f.XP.rotationDegrees(45));
        }
        else
            if(this.transform == ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND) {
                matrix.translate(- 1.1, 0.6, - 1.25);
                matrix.rotate(Vector3f.ZN.rotationDegrees(110));
                matrix.rotate(Vector3f.XP.rotationDegrees(45));
            }
            else
                if(this.transform == ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND) {
                    matrix.translate(0, - 0.6, 0.5);
                    matrix.rotate(Vector3f.ZN.rotationDegrees(15));
                    matrix.rotate(Vector3f.XN.rotationDegrees(15));
                }
                else
                    if(this.transform == ItemCameraTransforms.TransformType.FIRST_PERSON_LEFT_HAND) {
                        matrix.translate(0, - 0.6, - 0.5);
                        matrix.rotate(Vector3f.ZN.rotationDegrees(15));
                        matrix.rotate(Vector3f.XP.rotationDegrees(15));
                    }
                    else
                        if(this.transform == ItemCameraTransforms.TransformType.GUI || this.transform == ItemCameraTransforms.TransformType.GROUND) {
                            matrix.translate(0, 1.4D, 0);
                            matrix.rotate(Vector3f.ZP.rotationDegrees(180));
                        }

        matrix.scale(0.75f, 0.75f, 0.75f);

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
