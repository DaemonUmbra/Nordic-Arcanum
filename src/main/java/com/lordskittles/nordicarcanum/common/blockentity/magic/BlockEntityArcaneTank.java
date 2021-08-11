package com.lordskittles.nordicarcanum.common.blockentity.magic;

import com.lordskittles.arcanumapi.common.blockentity.BlockEntityMagicTank;
import com.lordskittles.nordicarcanum.common.registry.BlockEntities;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicFluidValues;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityArcaneTank extends BlockEntityMagicTank<BlockEntityArcaneTank> {

    public BlockEntityArcaneTank(BlockPos pos, BlockState state) {

        super(BlockEntities.arcane_tank.get(), pos, state, NordicFluidValues.ARCANE_TANK, 0, NordicArcanum.PACKET_HANDLER);
    }
}
