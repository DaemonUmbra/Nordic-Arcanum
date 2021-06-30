package com.lordskittles.nordicarcanum.common.item.magic;

import com.lordskittles.arcanumapi.common.item.ItemMod;
import com.lordskittles.arcanumapi.common.utilities.NBTUtilities;
import com.lordskittles.arcanumapi.core.NBTConstants;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicItemGroup;
import com.lordskittles.nordicarcanum.client.render.item.ItemStackRodRender;
import com.lordskittles.nordicarcanum.common.item.crafting.RodMaterial;
import com.lordskittles.nordicarcanum.common.item.crafting.RodShape;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
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

public class ItemRod extends ItemMod
{
    private RodShape shape = RodShape.Straight;
    private final RodMaterial material;

    public ItemRod(RodMaterial material)
    {
        super(new Item.Properties().group(NordicItemGroup.INSTANCE).setISTER(() -> ItemStackRodRender::new));

        this.material = material;
    }

    public RodShape getShape()
    {
        return this.shape;
    }

    public void setShape(RodShape shape)
    {
        this.shape = shape;
    }

    public RodMaterial getMaterial()
    {
        return this.material;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag)
    {
        tooltip.add(new StringTextComponent(TextFormatting.BLUE + I18n.format(NordicArcanum.MODID + ".rod." + shape.title)));
    }

    @Override
    public boolean updateItemStackNBT(CompoundNBT nbt)
    {
        CompoundNBT base = NBTUtilities.getPersistentData(NordicArcanum.MODID, nbt);

        this.shape = RodShape.get(base.getInt(NBTConstants.ROD_SHAPE_KEY));

        return true;
    }
}
