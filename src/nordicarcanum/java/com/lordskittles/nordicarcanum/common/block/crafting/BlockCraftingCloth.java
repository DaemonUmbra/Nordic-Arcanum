package com.lordskittles.nordicarcanum.common.block.crafting;

import com.lordskittles.arcanumapi.common.block.BlockMod;
import com.lordskittles.nordicarcanum.common.registry.Items;
import com.lordskittles.nordicarcanum.common.registry.TileEntities;
import com.lordskittles.nordicarcanum.common.tileentity.crafting.TileEntityCraftingCloth;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class BlockCraftingCloth extends BlockMod
{
	public BlockCraftingCloth()
	{
		super(Block.Properties.create(Material.WOOL)
				.hardnessAndResistance(-1.0f, 0.8f)
				.sound(SoundType.CLOTH)
				.notSolid());
	}

	public void initColorHandler(BlockColors blockColors)
	{
		blockColors.register((state, world, pos, tintIndex) ->
		{
			if (world != null)
			{
				BlockState facadeBlock = getFacadeBlock(world, pos);
				try
				{
					return blockColors.getColor(facadeBlock, world, pos, tintIndex);
				}
				catch (Exception exception)
				{
					return - 1;
				}
			}
			return -1;
		}, this);
	}

	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
	{
		return new ItemStack(Items.crafting_cloth_item.get());
	}

	@Override
	public int getLightValue(BlockState state, IBlockReader world, BlockPos pos)
	{
		BlockState facadeState = getFacadeBlock(world, pos);
		if (!isFacadeNull(facadeState))
		{
			return facadeState.getLightValue();
		}

		return super.getLightValue(state, world, pos);
	}

	@Override
	public float getAmbientOcclusionLightValue(BlockState state, IBlockReader world, BlockPos pos)
	{
		BlockState facadeState = getFacadeBlock(world, pos);

		if (!isFacadeNull(facadeState))
		{
			return facadeState.getBlock().getAmbientOcclusionLightValue(facadeState, world, pos);
		}

		return super.getAmbientOcclusionLightValue(state, world, pos);
	}

	@Nullable
	private BlockState getFacadeBlock(IBlockReader blockAccess, BlockPos pos)
	{
		TileEntity te = blockAccess.getTileEntity(pos);
		if (te instanceof TileEntityCraftingCloth)
		{
			return ((TileEntityCraftingCloth)te).getFacadeState();
		}
		return null;
	}

	private boolean isFacadeNull(BlockState facadeState)
	{
		return (facadeState == null || facadeState == net.minecraft.block.Blocks.AIR.getDefaultState());
	}

//	@Override
//	public boolean isNormalCube(BlockState state, IBlockReader world, BlockPos pos)
//	{
//		BlockState facadeBlock = getFacadeBlock(world, pos);
//		if (facadeBlock == null)
//		{
//			return super.isNormalCube(state, world, pos);
//		}
//		try
//		{
//			return facadeBlock.getBlock().isNormalCube(facadeBlock, world, pos);
//		}
//		catch (Exception exception)
//		{
//			return super.isNormalCube(state, world, pos);
//		}
//	}
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit)
	{
		if (!world.isRemote)
		{
			TileEntity tile = world.getTileEntity(pos);

			if (tile instanceof TileEntityCraftingCloth)
			{
				if (player.isSneaking())
				{
					BlockState orginalState = ((TileEntityCraftingCloth)tile).getFacadeState();

					player.inventory.addItemStackToInventory(new ItemStack(Items.crafting_cloth_item.get()));
					world.setBlockState(pos, orginalState);
				}
				else
				{
					NetworkHooks.openGui((ServerPlayerEntity)player, (TileEntityCraftingCloth)tile, pos);
				}
			}

		}
		return ActionResultType.SUCCESS;
	}

	@Override
	public boolean hasTileEntity(BlockState state)
	{
		return true;
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos)
	{
		return !world.getBlockState(pos.down()).hasTileEntity();
	}

	@Nullable
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world)
	{
		return TileEntities.crafting_cloth.get().create();
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos)
	{
		BlockState facade = getFacadeBlock(reader, pos);
		return !isFacadeNull(facade) ? facade.propagatesSkylightDown(reader, pos) : super.propagatesSkylightDown(state, reader, pos);
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
	public boolean isSideInvisible(BlockState state, BlockState adjacentBlockState, Direction side)
	{
		if (state.getBlock().equals(adjacentBlockState.getBlock()))
		{
			return false;
		}

		return super.isSideInvisible(state, adjacentBlockState, side);
	}
}
