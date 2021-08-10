package com.lordskittles.arcanumapi.common.utilities;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class BlockEntityHelper {

    public static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> createTickerHelper(BlockEntityType<A> p_152133_, BlockEntityTicker<? super E> p_152135_) {
        return (BlockEntityTicker<A>)p_152135_;
    }
}
