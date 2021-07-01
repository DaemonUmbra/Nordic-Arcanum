package com.lordskittles.nordicarcanum.common.block.decoration;

import com.lordskittles.arcanumapi.common.block.IItemGroupHolder;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicWorldItemGroup;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoorBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class BlockDoor extends DoorBlock implements IItemGroupHolder {

    public BlockDoor() {

        super(Block.Properties.from(Blocks.OAK_DOOR));
    }

    @SuppressWarnings("deprecation")
    @Nonnull
    @Override
    public VoxelShape getShape(@NotNull BlockState state, @NotNull IBlockReader world, @NotNull BlockPos pos, @NotNull ISelectionContext context) {

        return Blocks.OAK_DOOR.getShape(state, world, pos, context);
    }

    @Override
    public ItemGroup group() {

        return NordicWorldItemGroup.INSTANCE;
    }
}
