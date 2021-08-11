package com.lordskittles.nordicarcanum.common.block.magic;

import com.lordskittles.arcanumapi.common.block.BlockTank;
import com.lordskittles.arcanumapi.common.item.block.ItemBlockBase;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicItemGroup;
import com.lordskittles.nordicarcanum.common.block.voxelshapes.VoxelsArcaneTank;
import com.lordskittles.nordicarcanum.common.blockentity.magic.BlockEntityArcaneTank;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BlockArcaneTank extends BlockTank<BlockEntityArcaneTank> {

    public BlockArcaneTank() {

        super(SoundType.GLASS, NordicItemGroup.INSTANCE, BlockEntityArcaneTank.class, NordicArcanum.MODID, NordicArcanum.PACKET_HANDLER);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {

        return VoxelsArcaneTank.SHAPE.get();
    }

    @Override
    public BlockItem getOverride() {

        return new ItemBlockBase(this, new Item.Properties().tab(group()));//.setISTER(() -> ItemStackArcaneTankRender::new));
    }
}
