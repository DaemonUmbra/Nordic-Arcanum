package com.lordskittles.nordicarcanum.common.block;

import com.lordskittles.nordicarcanum.client.itemgroups.NordicItemGroup;
import com.lordskittles.arcanumapi.common.block.BlockMod;
import com.lordskittles.arcanumapi.common.block.IItemBlockOverride;
import com.lordskittles.arcanumapi.common.item.block.ItemBlockBase;
import com.lordskittles.nordicarcanum.client.render.item.ItemStackCrystalMatrixRender;
import com.lordskittles.nordicarcanum.common.registry.TileEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class BlockCrystalMatrix extends BlockMod implements IItemBlockOverride
{
    public BlockCrystalMatrix()
    {
        super(Block.Properties.create(Material.GLASS).notSolid().hardnessAndResistance(1.0f).sound(SoundType.GLASS));

        this.group = NordicItemGroup.INSTANCE;
    }

    @Override
    public boolean hasTileEntity(BlockState state)
    {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world)
    {
        return TileEntities.crystal_matrix.get().create();
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context)
    {
        return Block.makeCuboidShape(5, 5, 5, 11, 11, 11);
    }

    @Override
    public BlockItem getOverride()
    {
        return new ItemBlockBase(this, new Item.Properties().group(group()).setISTER(() -> ItemStackCrystalMatrixRender::new));
    }
}
