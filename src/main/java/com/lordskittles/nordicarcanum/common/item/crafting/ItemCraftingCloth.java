package com.lordskittles.nordicarcanum.common.item.crafting;

import com.lordskittles.arcanumapi.common.item.ItemMod;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicItemGroup;
import com.lordskittles.nordicarcanum.common.registry.Blocks;
import com.lordskittles.nordicarcanum.common.blockentity.crafting.BlockEntityCraftingCloth;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.List;

public class ItemCraftingCloth extends ItemMod {

    public ItemCraftingCloth() {

        super(new Properties().tab(NordicItemGroup.INSTANCE).stacksTo(1));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {

        tooltip.add(new TranslatableComponent(NordicArcanum.MODID + ".crafting_cloth_instructions").setStyle(Style.EMPTY.applyFormat(ChatFormatting.BLUE)));

        super.appendHoverText(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public InteractionResult onItemUseFirst(ItemStack stack, UseOnContext context) {

        BlockPos pos = context.getClickedPos();
        Level world = context.getLevel();
        BlockState currentState = world.getBlockState(pos);
        Player player = context.getPlayer();
        BlockEntity block = world.getBlockEntity(pos);

        if(isValidBlock(currentState)) {
            if(block == null) {
                world.setBlock(pos, Blocks.crafting_cloth.get().defaultBlockState(), 19);
                block = world.getBlockEntity(pos);
                if(block instanceof BlockEntityCraftingCloth) {
                    BlockEntityCraftingCloth cloth = (BlockEntityCraftingCloth) block;
                    cloth.setFacadeState(currentState);
                }

                player.setItemInHand(context.getHand(), ItemStack.EMPTY);
            }
        }
        return InteractionResult.SUCCESS;
    }

    private boolean isValidBlock(BlockState currentState) {

        return ! (currentState.getBlock() instanceof BushBlock) &&
               ! (currentState.getBlock() instanceof BonemealableBlock) && ! (currentState.getBlock() instanceof GrassBlock) &&
               ! (currentState.getBlock() instanceof FenceBlock) && ! (currentState.getBlock() instanceof DoorBlock);
    }
}
