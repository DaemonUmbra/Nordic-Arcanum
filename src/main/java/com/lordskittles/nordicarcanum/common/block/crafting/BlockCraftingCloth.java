package com.lordskittles.nordicarcanum.common.block.crafting;

import com.lordskittles.arcanumapi.common.block.BlockMod;
import com.lordskittles.nordicarcanum.common.registry.Items;
import com.lordskittles.nordicarcanum.common.blockentity.crafting.BlockEntityCraftingCloth;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fmllegacy.network.NetworkHooks;

import javax.annotation.Nullable;

public class BlockCraftingCloth extends BlockMod {

    public BlockCraftingCloth() {

        super(Block.Properties.of(Material.WOOL)
                .strength(- 1.0f, 0.8f)
                .sound(SoundType.WOOL)
                .noOcclusion());
    }

    public void initColorHandler(BlockColors blockColors) {

        blockColors.register((state, world, pos, tintIndex) ->
        {
            if(world != null) {
                BlockState facadeBlock = getFacadeBlock(world, pos);
                try {
                    return blockColors.getColor(facadeBlock, world, pos, tintIndex);
                }
                catch(Exception exception) {
                    return - 1;
                }
            }
            return - 1;
        }, this);
    }

    @Override
    public ItemStack getPickBlock(BlockState state, HitResult target, BlockGetter world, BlockPos pos, Player player) {

        return new ItemStack(Items.crafting_cloth_item.get());
    }

    @Override
    public int getLightEmission(BlockState state, BlockGetter world, BlockPos pos) {

        BlockState facadeState = getFacadeBlock(world, pos);
        if(! isFacadeNull(facadeState)) {
            return facadeState.getLightEmission();
        }

        return super.getLightEmission(state, world, pos);
    }

    @Override
    public float getShadeBrightness(BlockState state, BlockGetter world, BlockPos pos) {

        BlockState facadeState = getFacadeBlock(world, pos);

        if(! isFacadeNull(facadeState)) {
            return facadeState.getBlock().getShadeBrightness(facadeState, world, pos);
        }

        return super.getShadeBrightness(state, world, pos);
    }

    @Nullable
    private BlockState getFacadeBlock(BlockGetter blockAccess, BlockPos pos) {

        BlockEntity te = blockAccess.getBlockEntity(pos);
        if(te instanceof BlockEntityCraftingCloth) {
            return ((BlockEntityCraftingCloth) te).getFacadeState();
        }
        return null;
    }

    private boolean isFacadeNull(BlockState facadeState) {

        return (facadeState == null || facadeState == net.minecraft.world.level.block.Blocks.AIR.defaultBlockState());
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {

        if(! world.isClientSide) {
            BlockEntity tile = world.getBlockEntity(pos);

            if(tile instanceof BlockEntityCraftingCloth) {
                if(player.isShiftKeyDown()) {
                    BlockState orginalState = ((BlockEntityCraftingCloth) tile).getFacadeState();

                    player.getInventory().add(new ItemStack(Items.crafting_cloth_item.get()));
                    world.setBlock(pos, orginalState, 19);
                }
                else {
                    NetworkHooks.openGui((ServerPlayer) player, (BlockEntityCraftingCloth) tile, pos);
                }
            }

        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {

        BlockState facade = getFacadeBlock(reader, pos);
        return ! isFacadeNull(facade) ? facade.propagatesSkylightDown(reader, pos) : super.propagatesSkylightDown(state, reader, pos);
    }

//	@Override
//	public void updateNeighbors(BlockState stateIn, IWorld worldIn, BlockPos pos, int flags)
//	{
//		BlockState mimic = getFacadeBlock(worldIn, pos);
//		if (!isFacadeNull(mimic))
//		{
//			mimic.updateNeighbors(worldIn, pos, flags);
//		}
//		else
//		{
//			super.updateNeighbors(stateIn, worldIn, pos, flags);
//		}
//	}

    @OnlyIn(Dist.CLIENT)
    public boolean skipRendering(BlockState state, BlockState adjacentBlockState, Direction side) {

        if(state.getBlock().equals(adjacentBlockState.getBlock())) {
            return false;
        }

        return super.skipRendering(state, adjacentBlockState, side);
    }
}
