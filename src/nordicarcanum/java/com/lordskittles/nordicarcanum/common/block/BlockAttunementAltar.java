package com.lordskittles.nordicarcanum.common.block;

import com.lordskittles.arcanumapi.common.block.BlockMod;
import com.lordskittles.arcanumapi.common.block.IItemBlockOverride;
import com.lordskittles.arcanumapi.common.item.block.ItemBlockBase;
import com.lordskittles.nordicarcanum.client.render.item.ItemStackAttunementAltarRender;
import com.lordskittles.nordicarcanum.common.block.voxelshapes.VoxelsAttunementAltar;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicItemGroup;
import com.lordskittles.nordicarcanum.common.tileentity.TileEntityAttunementAltar;
import com.lordskittles.nordicarcanum.common.registry.TileEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class BlockAttunementAltar extends BlockMod implements IItemBlockOverride
{
	public BlockAttunementAltar()
	{
		super(Block.Properties.create(Material.ROCK, MaterialColor.GRAY)
				.hardnessAndResistance(1.5f, 6.0f)
				.sound(SoundType.STONE)
				.harvestLevel(ItemTier.STONE.getHarvestLevel())
				.harvestTool(ToolType.PICKAXE), 4);
		
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
		return TileEntities.attunement_altar.get().create();
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context)
	{
		return VoxelsAttunementAltar.SHAPE.get();
	}
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit)
	{
		if (!world.isRemote)
		{
			TileEntity tile = world.getTileEntity(pos);
			if (tile instanceof TileEntityAttunementAltar)
			{
				TileEntityAttunementAltar altar = (TileEntityAttunementAltar) tile;
				altar.validateStructure();
				NetworkHooks.openGui((ServerPlayerEntity)player, altar, pos);
			}
		}
		return ActionResultType.SUCCESS;
	}

	@Override
	public BlockItem getOverride()
	{
		return new ItemBlockBase(this, new Item.Properties().group(group()).setISTER(() -> ItemStackAttunementAltarRender::new));
	}
}
