package com.lordskittles.nordicarcanum.common.item.crafting;

import com.lordskittles.arcanumapi.common.item.ItemMod;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicItemGroup;
import com.lordskittles.nordicarcanum.common.blockentity.crafting.BlockEntityNordicAnvil;
import com.lordskittles.nordicarcanum.common.registry.Sounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

public class ItemShapingHammer extends ItemMod {

    public ItemShapingHammer() {

        super(NordicItemGroup.INSTANCE);
    }

    @Override
    public InteractionResult onItemUseFirst(ItemStack stack, UseOnContext context) {

        Level world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockEntity block = world.getBlockEntity(pos);

        if(block instanceof BlockEntityNordicAnvil) {
            BlockEntityNordicAnvil anvil = (BlockEntityNordicAnvil) block;

            Sounds.play(SoundEvents.ANVIL_PLACE, world, pos, 0.5F);
            anvil.updateProgress(world);
        }

        return InteractionResult.SUCCESS;
    }
}
