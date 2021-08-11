package com.lordskittles.nordicarcanum.common.block.crafting;

import com.lordskittles.arcanumapi.common.block.BlockTank;
import com.lordskittles.arcanumapi.common.item.block.ItemBlockBase;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicItemGroup;
import com.lordskittles.nordicarcanum.client.render.item.ItemStackArcaneInfuserRender;
import com.lordskittles.nordicarcanum.common.block.voxelshapes.VoxelsArcaneInfuser;
import com.lordskittles.nordicarcanum.common.blockentity.crafting.BlockEntityArcaneInfuser;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BlockArcaneInfuser extends BlockTank<BlockEntityArcaneInfuser> {

    public BlockArcaneInfuser() {

        super(SoundType.STONE, NordicItemGroup.INSTANCE, BlockEntityArcaneInfuser.class, NordicArcanum.MODID, NordicArcanum.PACKET_HANDLER);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {

        BlockEntityArcaneInfuser infuser = (BlockEntityArcaneInfuser)level.getBlockEntity(pos);
        ItemStack held = player.getItemInHand(hand);
        if(held.getItem() instanceof BucketItem) {
            return InteractionResult.PASS;
        }

        if(player.isShiftKeyDown()) {
            if(player.getItemInHand(hand).isEmpty()) {
                ItemStack result = infuser.removeItems();
                if(! result.isEmpty()) {
                    player.setItemInHand(hand, result);
                    level.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0f, 1.0f);
                }
            }
        }
        else {
            if(infuser.setHeldItem(held)) {
                player.getItemInHand(hand).shrink(1);
                level.playSound(null, pos, SoundEvents.ITEM_FRAME_ADD_ITEM, SoundSource.BLOCKS, 1.0f, 1.0f);
            }
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {

        return VoxelsArcaneInfuser.SHAPE.get();
    }

    @Override
    public BlockItem getOverride() {

        return new ItemBlockBase(this, new Item.Properties().tab(group()));//.setISTER(() -> ItemStackArcaneInfuserRender::new));
    }
}
