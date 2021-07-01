package com.lordskittles.nordicarcanum.common.tileentity.magic;

import com.lordskittles.arcanumapi.common.tileentity.TileEntityMagicTank;
import com.lordskittles.nordicarcanum.common.registry.TileEntities;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicFluidValues;
import net.minecraft.tileentity.TileEntityType;

public class TileEntityArcaneTank extends TileEntityMagicTank<TileEntityArcaneTank> {

    public TileEntityArcaneTank(TileEntityType<?> tileEntityTypeIn) {

        super(tileEntityTypeIn, NordicFluidValues.ARCANE_TANK, 0, NordicArcanum.PACKET_HANDLER);
    }

    public TileEntityArcaneTank() {

        this(TileEntities.arcane_tank.get());
    }
}
