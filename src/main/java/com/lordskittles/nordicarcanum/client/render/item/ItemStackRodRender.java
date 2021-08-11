package com.lordskittles.nordicarcanum.client.render.item;

import com.lordskittles.arcanumapi.client.render.item.ItemModelWrapper;
import com.lordskittles.arcanumapi.client.render.item.ItemStackTERenderBase;
import com.lordskittles.nordicarcanum.client.render.item.model.ModelRod1;
import com.lordskittles.nordicarcanum.client.render.item.model.ModelRod2;
import com.lordskittles.nordicarcanum.client.render.item.model.ModelRod3;
import com.lordskittles.nordicarcanum.client.render.item.model.ModelRod4;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.world.item.ItemStack;
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

    public ItemStackRodRender(BlockEntityRenderDispatcher dispatcher, EntityModelSet modelSet) {

        super(dispatcher, modelSet);
    }

    @Override
    protected void renderItemSpecific(ItemStack stack, PoseStack matrix, MultiBufferSource buffer, int light, int overlay, ItemTransforms.TransformType transform) {

//        Model model = null;
//
//        ItemRod rod = (ItemRod) stack.getItem();
//
//        CompoundTag nbt = stack.getTag();
//
//        if(nbt == null) {
//            nbt = new CompoundTag();
//        }
//
//        CompoundTag localNBT = NBTUtilities.getPersistentData(NordicArcanum.MODID, nbt);
//        RodShape shape = RodShape.get(localNBT.getInt(NBTConstants.ROD_SHAPE_KEY));
//
//        RodMaterial material = rod.getMaterial();
//        ResourceLocation textureLoc = NordicResourceLocations.getRodTexture(material, shape.getValue() + 1);
//
//        switch(shape) {
//            case None:
//                textureLoc = NordicResourceLocations.getRodTexture(material, 1);
//            case Straight:
//                model = straight;
//                break;
//            case Twist:
//                model = twist;
//                break;
//            case Bend:
//                model = bend;
//                break;
//            case Pillar:
//                model = pillar;
//                break;
//        }
//
//        float scale = 2f;
//
//        if(this.transform == ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND) {
//            matrix.translate(1.1, 0.6, - 1.25);
//            matrix.rotate(Vector3f.ZP.rotationDegrees(110));
//            matrix.rotate(Vector3f.XP.rotationDegrees(45));
//        }
//        else if(this.transform == ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND) {
//            matrix.translate(- 1.1, 0.6, - 1.25);
//            matrix.rotate(Vector3f.ZN.rotationDegrees(110));
//            matrix.rotate(Vector3f.XP.rotationDegrees(45));
//        }
//        else if(this.transform == ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND) {
//            matrix.translate(0, - 0.6, 0.5);
//            matrix.rotate(Vector3f.ZN.rotationDegrees(15));
//            matrix.rotate(Vector3f.XN.rotationDegrees(15));
//        }
//        else if(this.transform == ItemCameraTransforms.TransformType.FIRST_PERSON_LEFT_HAND) {
//            matrix.translate(0, - 0.6, - 0.5);
//            matrix.rotate(Vector3f.ZN.rotationDegrees(15));
//            matrix.rotate(Vector3f.XP.rotationDegrees(15));
//        }
//        else if(this.transform == ItemCameraTransforms.TransformType.GUI || this.transform == ItemCameraTransforms.TransformType.GROUND) {
//            matrix.translate(0, 1.4D, 0);
//            matrix.rotate(Vector3f.ZP.rotationDegrees(180));
//        }
//
//        matrix.scale(0.75f, 0.75f, 0.75f);
//
//        matrix.scale(scale, scale, scale);
//        model.render(matrix, buffer.getBuffer(model.getRenderType(textureLoc)), light, overlay, 1, 1, 1, 1);
    }

    @Override
    protected void renderBlockSpecific(ItemStack stack, PoseStack matrix, MultiBufferSource buffer, int light, int overlay, ItemTransforms.TransformType transform) {

    }

    @Nonnull
    @Override
    protected ItemTransforms.TransformType getTransform(ItemStack stack) {

        return model.getTransform();
    }
}
