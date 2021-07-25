package com.lordskittles.nordicarcanum.common.item.crafting;

import com.lordskittles.arcanumapi.common.item.ItemMod;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicItemGroup;
import com.lordskittles.nordicarcanum.common.registry.Blocks;
import com.lordskittles.nordicarcanum.common.tileentity.crafting.TileEntityCraftingCloth;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import net.minecraft.block.*;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import net.minecraft.world.item.Item.Properties;

public class ItemCraftingCloth extends ItemMod {

    public ItemCraftingCloth() {

        super(new Properties().group(NordicItemGroup.INSTANCE).maxStackSize(1));
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {

        tooltip.add(new TranslationTextComponent(NordicArcanum.MODID + ".crafting_cloth_instructions").mergeStyle(TextFormatting.BLUE));

        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {

        BlockPos pos = context.getPos();
        World world = context.getWorld();
        BlockState currentState = world.getBlockState(pos);
        PlayerEntity player = context.getPlayer();
        TileEntity tile = world.getTileEntity(pos);

        if(isValidBlock(currentState)) {
            if(tile == null) {
                world.setBlockState(pos, Blocks.crafting_cloth.get().getDefaultState());
                tile = world.getTileEntity(pos);
                if(tile instanceof TileEntityCraftingCloth) {
                    TileEntityCraftingCloth cloth = (TileEntityCraftingCloth) tile;
                    cloth.setFacadeState(currentState);
                }

                player.setHeldItem(context.getHand(), ItemStack.EMPTY);
            }
        }
        return ActionResultType.SUCCESS;
    }

    private boolean isValidBlock(BlockState currentState) {

        return ! (currentState.getBlock() instanceof BushBlock) &&
               ! (currentState.getBlock() instanceof IGrowable) && ! (currentState.getBlock() instanceof GrassBlock) &&
               ! (currentState.getBlock() instanceof FenceBlock) && ! (currentState.getBlock() instanceof DoorBlock);
    }
}
