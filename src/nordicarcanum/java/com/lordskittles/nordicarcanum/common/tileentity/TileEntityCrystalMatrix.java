package com.lordskittles.nordicarcanum.common.tileentity;

import com.lordskittles.nordicarcanum.common.registry.TileEntities;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class TileEntityCrystalMatrix extends TileEntity
{
    public TileEntityCrystalMatrix(TileEntityType<?> tileEntityTypeIn)
    {
        super(tileEntityTypeIn);
    }

    public TileEntityCrystalMatrix()
    {
        this(TileEntities.crystal_matrix.get());
    }
}
