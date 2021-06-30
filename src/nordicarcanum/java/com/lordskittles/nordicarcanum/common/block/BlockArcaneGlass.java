package com.lordskittles.nordicarcanum.common.block;

import com.lordskittles.nordicarcanum.client.itemgroups.NordicItemGroup;
import com.lordskittles.arcanumapi.common.block.BlockMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BlockArcaneGlass extends BlockMod
{
    public BlockArcaneGlass() 
    {
        super(Block.Properties.create(Material.GLASS)
                .notSolid()
                .hardnessAndResistance(1.0f)
                .sound(SoundType.GLASS));

        this.group = NordicItemGroup.INSTANCE;
    }

    @OnlyIn(Dist.CLIENT)
    public float getAmbientOcclusionLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return 1.0F;
    }

    public boolean propagatesSkylightDown(BlockState state, IBlockReader world, BlockPos pos) {
        return true;
    }

    public boolean causesSuffocation(BlockState state, IBlockReader world, BlockPos pos) {
        return false;
    }

    public boolean isNormalCube(BlockState state, IBlockReader world, BlockPos pos) {
        return false;
    }

    public boolean canEntitySpawn(BlockState state, IBlockReader world, BlockPos pos, EntityType<?> type) {
        return false;
    }

    @OnlyIn(Dist.CLIENT)
    public boolean isSideInvisible(BlockState state, BlockState adjacentBlockState, Direction side)
    {
        return adjacentBlockState.getBlock() == this ? true : super.isSideInvisible(state, adjacentBlockState, side);
    }
}
