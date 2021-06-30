package com.lordskittles.nordicarcanum.common.block.decoration;

import com.lordskittles.nordicarcanum.client.itemgroups.NordicWorldItemGroup;
import com.lordskittles.arcanumapi.common.block.IItemGroupHolder;
import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemTier;
import net.minecraftforge.common.ToolType;

public class BlockSlab extends SlabBlock implements IItemGroupHolder
{
	public BlockSlab()
	{
		this(Material.ROCK, SoundType.STONE);
	}
	
	public BlockSlab(Material material, SoundType sound)
	{
		this(Block.Properties.create(material).sound(sound).harvestLevel(ItemTier.WOOD.getHarvestLevel()).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2.0F, 6.0f));
	}
	
	public BlockSlab(Block.Properties properties)
	{
		super(properties);
	}

	@Override
	public ItemGroup group()
	{
		return NordicWorldItemGroup.INSTANCE;
	}
}
