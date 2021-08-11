package com.lordskittles.nordicarcanum.common.block.magic;

import com.lordskittles.arcanumapi.common.block.BlockMod;
import com.lordskittles.arcanumapi.common.block.IItemBlockOverride;
import com.lordskittles.arcanumapi.common.item.block.ItemBlockBase;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicItemGroup;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BlockCrystalMatrix extends BlockMod implements IItemBlockOverride {

    public BlockCrystalMatrix() {

        super(Block.Properties.of(Material.GLASS).noOcclusion().strength(1.0f).sound(SoundType.GLASS));

        this.group = NordicItemGroup.INSTANCE;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {

        return Block.box(5, 5, 5, 11, 11, 11);
    }

    @Override
    public BlockItem getOverride() {

        return new ItemBlockBase(this, new Item.Properties().tab(group()));//.setISTER(() -> ItemStackCrystalMatrixRender::new));
    }
}
