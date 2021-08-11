package com.lordskittles.nordicarcanum.common.block.world;

import com.lordskittles.arcanumapi.common.block.IItemGroupHolder;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicResourcesItemGroup;
import com.lordskittles.nordicarcanum.common.registry.Blocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class BlockLog extends RotatedPillarBlock implements IItemGroupHolder {

    public BlockLog() {

        super(Block.Properties.copy(net.minecraft.world.level.block.Blocks.OAK_LOG));
    }

    @Override
    public CreativeModeTab group() {

        return NordicResourcesItemGroup.INSTANCE;
    }

    @Nullable
    @Override
    public BlockState getToolModifiedState(BlockState state, Level world, BlockPos pos, Player player, ItemStack stack, ToolType toolType) {

        if(toolType == ToolType.AXE && state.getBlock() instanceof BlockLog) {
            BlockLog log = (BlockLog) (state.getBlock());
            Direction.Axis axis = state.getValue(AXIS);
            if(log == Blocks.yew_log.get()) {
                return Blocks.yew_log_stripped.get().defaultBlockState().setValue(AXIS, axis);
            }
            else if(log == Blocks.pine_log.get()) {
                return Blocks.pine_log_stripped.get().defaultBlockState().setValue(AXIS, axis);
            }
            else if(log == Blocks.juniper_log.get()) {
                return Blocks.juniper_log_stripped.get().defaultBlockState().setValue(AXIS, axis);
            }
        }

        return super.getToolModifiedState(state, world, pos, player, stack, toolType);
    }
}
