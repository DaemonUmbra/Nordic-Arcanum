package com.lordskittles.nordicarcanum.common.block;

import com.lordskittles.arcanumapi.common.block.BlockTank;
import com.lordskittles.arcanumapi.common.item.block.ItemBlockBase;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicItemGroup;
import com.lordskittles.nordicarcanum.client.render.item.ItemStackArcaneTankRender;
import com.lordskittles.nordicarcanum.common.block.voxelshapes.VoxelsArcaneTank;
import com.lordskittles.nordicarcanum.common.registry.TileEntities;
import com.lordskittles.nordicarcanum.common.tileentity.TileEntityArcaneTank;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.item.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class BlockArcaneTank extends BlockTank<TileEntityArcaneTank>
{
    public BlockArcaneTank()
    {
        super(SoundType.GLASS, NordicItemGroup.INSTANCE, TileEntityArcaneTank.class, NordicArcanum.MODID, NordicArcanum.PACKET_HANDLER);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context)
    {
        return VoxelsArcaneTank.SHAPE.get();
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world)
    {
        return TileEntities.arcane_tank.get().create();
    }

    @Override
    public BlockItem getOverride()
    {
        return new ItemBlockBase(this, new Item.Properties().group(group()).setISTER(() -> ItemStackArcaneTankRender::new));
    }
}
