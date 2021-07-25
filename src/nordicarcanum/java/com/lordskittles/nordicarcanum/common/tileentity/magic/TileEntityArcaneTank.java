package com.lordskittles.nordicarcanum.common.tileentity.magic;

import com.lordskittles.arcanumapi.common.tileentity.TileEntityMagicTank;
import com.lordskittles.nordicarcanum.common.registry.TileEntities;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicFluidValues;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class TileEntityArcaneTank extends TileEntityMagicTank<TileEntityArcaneTank> {

    public TileEntityArcaneTank(BlockPos pos, BlockState state) {

        super(TileEntities.arcane_tank.get(), pos, state, NordicFluidValues.ARCANE_TANK, 0, NordicArcanum.PACKET_HANDLER);
    }
}
