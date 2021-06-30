package com.lordskittles.nordicarcanum.common.item;

import com.lordskittles.arcanumapi.common.item.ItemMod;
import com.lordskittles.arcanumapi.common.utilities.NBTUtilities;
import com.lordskittles.arcanumapi.core.NBTConstants;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicItemGroup;
import com.lordskittles.nordicarcanum.client.render.item.ItemStackBindingRender;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicNames;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class ItemBinding extends ItemMod
{
    private BindingShape shape = BindingShape.None;
    private final BindingMaterial material;

    public ItemBinding(BindingMaterial material)
    {
        super(new Item.Properties().group(NordicItemGroup.INSTANCE).setISTER(() -> ItemStackBindingRender::new));

        this.material = material;
    }

    public BindingShape getShape()
    {
        return this.shape;
    }

    public void setShape(BindingShape shape)
    {
        this.shape = shape;
    }

    public BindingMaterial getMaterial()
    {
        return this.material;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag)
    {
        BindingShape shape = getShapeFromNBT(stack);

        tooltip.add(new StringTextComponent(TextFormatting.BLUE + I18n.format(NordicArcanum.MODID + ".cast." + shape.title)));
    }

    @Override
    public boolean updateItemStackNBT(CompoundNBT nbt)
    {
        CompoundNBT base = NBTUtilities.getPersistentData(NordicArcanum.MODID, nbt);
        if (this.shape == BindingShape.None)
        {
            BindingShape shape = BindingShape.get(base.getInt(NBTConstants.BINDING_SHAPE_KEY));
            if (shape != BindingShape.None)
            {
                setShape(shape);
                return true;
            }
        }

        return false;
    }

    private BindingShape getShapeFromNBT(ItemStack stack)
    {
        CompoundNBT nbt = stack.getTag();

        if (nbt == null)
        {
            nbt = new CompoundNBT();
        }

        CompoundNBT localNBT = NBTUtilities.getPersistentData(NordicArcanum.MODID, nbt);
        return BindingShape.get(localNBT.getInt(NBTConstants.BINDING_SHAPE_KEY));
    }
}
