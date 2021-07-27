package com.lordskittles.nordicarcanum.common.item.magic;

import com.lordskittles.arcanumapi.common.item.ItemMod;
import com.lordskittles.arcanumapi.common.utilities.NBTUtilities;
import com.lordskittles.arcanumapi.core.NBTConstants;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicItemGroup;
import com.lordskittles.nordicarcanum.client.render.item.ItemStackRodRender;
import com.lordskittles.nordicarcanum.common.item.crafting.RodMaterial;
import com.lordskittles.nordicarcanum.common.item.crafting.RodShape;
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

public class ItemRod extends ItemMod {

    private RodShape shape = RodShape.Straight;
    private final RodMaterial material;

    public ItemRod(RodMaterial material) {

        super(new Item.Properties().tab(NordicItemGroup.INSTANCE));//.setISTER(() -> ItemStackRodRender::new));

        this.material = material;
    }

    public RodShape getShape() {

        return this.shape;
    }

    public void setShape(RodShape shape) {

        this.shape = shape;
    }

    public RodMaterial getMaterial() {

        return this.material;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {

        tooltip.add(new TranslatableComponent(NordicArcanum.MODID + ".rod." + shape.title).setStyle(Style.EMPTY.applyFormat(ChatFormatting.BLUE)));
    }

    @Override
    public void verifyTagAfterLoad(CompoundTag nbt) {

        CompoundTag base = NBTUtilities.getPersistentData(NordicArcanum.MODID, nbt);

        this.shape = RodShape.get(base.getInt(NBTConstants.ROD_SHAPE_KEY));
    }
}
