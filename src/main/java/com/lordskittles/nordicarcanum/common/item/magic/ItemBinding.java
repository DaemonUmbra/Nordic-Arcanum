package com.lordskittles.nordicarcanum.common.item.magic;

import com.lordskittles.arcanumapi.common.item.ItemMod;
import com.lordskittles.arcanumapi.common.utilities.NBTUtilities;
import com.lordskittles.arcanumapi.core.NBTConstants;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicItemGroup;
import com.lordskittles.nordicarcanum.common.item.crafting.BindingMaterial;
import com.lordskittles.nordicarcanum.common.item.crafting.BindingShape;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class ItemBinding extends ItemMod {

    private BindingShape shape = BindingShape.None;
    private final BindingMaterial material;

    public ItemBinding(BindingMaterial material) {

        super(new Item.Properties().tab(NordicItemGroup.INSTANCE));//.setISTER(() -> ItemStackBindingRender::new));

        this.material = material;
    }

    public BindingShape getShape() {

        return this.shape;
    }

    public void setShape(BindingShape shape) {

        this.shape = shape;
    }

    public BindingMaterial getMaterial() {

        return this.material;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {

        BindingShape shape = getShapeFromNBT(stack);

        tooltip.add(new TranslatableComponent(NordicArcanum.MODID + ".cast." + shape.title).setStyle(Style.EMPTY.applyFormat(ChatFormatting.BLUE)));
    }

    @Override
    public void verifyTagAfterLoad(CompoundTag nbt) {

        CompoundTag base = NBTUtilities.getPersistentData(NordicArcanum.MODID, nbt);
        if(this.shape == BindingShape.None) {
            BindingShape shape = BindingShape.get(base.getInt(NBTConstants.BINDING_SHAPE_KEY));
            if(shape != BindingShape.None) {
                setShape(shape);
            }
        }
    }

    private BindingShape getShapeFromNBT(ItemStack stack) {

        CompoundTag nbt = stack.getTag();

        if(nbt == null) {
            nbt = new CompoundTag();
        }

        CompoundTag localNBT = NBTUtilities.getPersistentData(NordicArcanum.MODID, nbt);
        return BindingShape.get(localNBT.getInt(NBTConstants.BINDING_SHAPE_KEY));
    }
}
