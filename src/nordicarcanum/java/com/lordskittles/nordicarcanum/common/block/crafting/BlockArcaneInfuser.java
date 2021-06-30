package com.lordskittles.nordicarcanum.common.block.crafting;

import com.lordskittles.arcanumapi.common.block.BlockTank;
import com.lordskittles.arcanumapi.common.item.block.ItemBlockBase;
import com.lordskittles.arcanumapi.common.tileentity.TileEntityFluidInventory;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicItemGroup;
import com.lordskittles.nordicarcanum.client.render.item.ItemStackArcaneInfuserRender;
import com.lordskittles.nordicarcanum.common.block.voxelshapes.VoxelsArcaneInfuser;
import com.lordskittles.nordicarcanum.common.registry.TileEntities;
import com.lordskittles.nordicarcanum.common.tileentity.crafting.TileEntityArcaneInfuser;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockArcaneInfuser extends BlockTank<TileEntityArcaneInfuser>
{
    public BlockArcaneInfuser()
    {
        super(SoundType.STONE, NordicItemGroup.INSTANCE, TileEntityArcaneInfuser.class, NordicArcanum.MODID, NordicArcanum.PACKET_HANDLER);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world)
    {
        return TileEntities.arcane_infuser.get().create();
    }

    @Override
    protected void onTileActivated(World world, PlayerEntity player, TileEntityFluidInventory tile, BlockPos pos, Hand hand)
    {
        TileEntityArcaneInfuser infuser = (TileEntityArcaneInfuser)tile;
        ItemStack held = player.getHeldItem(hand);
        if (held.getItem() instanceof BucketItem)
        {
            return;
        }

        if (player.isSneaking())
        {
            if (player.getHeldItem(hand).isEmpty())
            {
                ItemStack result = infuser.removeItems();
                if (!result.isEmpty())
                {
                    player.setHeldItem(hand, result);
                    world.playSound(null, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1.0f, 1.0f);
                }
            }
        }
        else
        {
            if (infuser.setHeldItem(held))
            {
                player.getHeldItem(hand).shrink(1);
                world.playSound(null, pos, SoundEvents.ENTITY_ITEM_FRAME_ADD_ITEM, SoundCategory.BLOCKS, 1.0f, 1.0f);
            }
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context)
    {
        return VoxelsArcaneInfuser.SHAPE.get();
    }

    private void shrinkHand(PlayerEntity player)
    {
        ItemStack heldItem = player.inventory.mainInventory.get(player.inventory.currentItem);
        heldItem.shrink(1);

        player.setItemStackToSlot(EquipmentSlotType.MAINHAND, heldItem);
    }

    @Override
    public BlockItem getOverride()
    {
        return new ItemBlockBase(this, new Item.Properties().group(group()).setISTER(() -> ItemStackArcaneInfuserRender::new));
    }
}
