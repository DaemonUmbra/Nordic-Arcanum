package com.lordskittles.nordicarcanum.common.tileentity.magic;

import com.lordskittles.nordicarcanum.common.registry.TileEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class TileEntityCrystalMatrix extends BlockEntity {

    public TileEntityCrystalMatrix(BlockPos pos, BlockState state) {

        super(TileEntities.crystal_matrix.get(), pos, state);
    }
}
