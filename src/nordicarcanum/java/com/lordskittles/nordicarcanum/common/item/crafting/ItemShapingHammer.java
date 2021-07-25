package com.lordskittles.nordicarcanum.common.item.crafting;

import com.lordskittles.arcanumapi.common.item.ItemMod;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicItemGroup;
import com.lordskittles.nordicarcanum.common.registry.Sounds;
import com.lordskittles.nordicarcanum.common.tileentity.crafting.TileEntityNordicAnvil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.InteractionResult;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.level.Level;

public class ItemShapingHammer extends ItemMod {

    public ItemShapingHammer() {

        super(NordicItemGroup.INSTANCE);
    }

    @Override
    public InteractionResult onItemUseFirst(ItemStack stack, UseOnContext context) {

        Level world = context.getWorld();
        BlockPos pos = context.getPos();
        TileEntity tile = world.getTileEntity(pos);

        if(tile instanceof TileEntityNordicAnvil) {
            TileEntityNordicAnvil anvil = (TileEntityNordicAnvil) tile;

            Sounds.play(SoundEvents.BLOCK_ANVIL_PLACE, world, pos, 0.5F);
            anvil.updateProgress(world);
        }

        return ActionResultType.SUCCESS;
    }
}
