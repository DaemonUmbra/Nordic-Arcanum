package com.lordskittles.nordicarcanum.common.block.decoration;

import com.lordskittles.nordicarcanum.client.itemgroups.NordicWorldItemGroup;
import com.lordskittles.arcanumapi.common.block.IItemGroupHolder;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemTier;
import net.minecraftforge.common.ToolType;

public class BlockStairs extends StairsBlock implements IItemGroupHolder
{
	public BlockStairs(Block aBlock)
	{
		this(aBlock, Material.ROCK, SoundType.STONE);
	}
	
	public BlockStairs(Block block, Material material, SoundType sound)
	{
		this(block, material, sound, ItemTier.WOOD.getHarvestLevel(), ToolType.PICKAXE, 6.0f);
	}
	
	public BlockStairs(Block block, Material material, SoundType sound, int harvestLevel, ToolType tool, float resistance)
	{
		super(() -> block.getDefaultState(), Block.Properties.create(Material.ROCK).sound(sound).harvestLevel(harvestLevel).harvestTool(tool).hardnessAndResistance(2.0f, resistance));
	}

	@Override
	public ItemGroup group()
	{
		return NordicWorldItemGroup.INSTANCE;
	}
}
