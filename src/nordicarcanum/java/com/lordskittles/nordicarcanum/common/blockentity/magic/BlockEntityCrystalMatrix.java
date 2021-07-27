package com.lordskittles.nordicarcanum.common.blockentity.magic;

import com.lordskittles.nordicarcanum.common.registry.BlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityCrystalMatrix extends BlockEntity {

    public BlockEntityCrystalMatrix(BlockPos pos, BlockState state) {

        super(BlockEntities.crystal_matrix.get(), pos, state);
    }
}
