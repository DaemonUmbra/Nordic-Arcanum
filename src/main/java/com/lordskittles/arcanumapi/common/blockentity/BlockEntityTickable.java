package com.lordskittles.arcanumapi.common.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class BlockEntityTickable<T extends BlockEntityTickable> extends BlockEntityUpdateable {

    protected final T block;

    public BlockEntityTickable(BlockEntityType<?> blockEntityTypeIn, BlockPos pos, BlockState state, T block) {

        super(blockEntityTypeIn, pos, state);

        this.block = block;
    }

    @SuppressWarnings("rawtypes")
    public static void tick(Level level, BlockPos pos, BlockState state, BlockEntityTickable tickable) {

        tickable.tick(level, (BlockEntityTickable) level.getBlockEntity(pos));
    }

    protected abstract void tick(Level world, T block);
}
