package com.lordskittles.nordicarcanum.common.block.decoration;

import com.lordskittles.nordicarcanum.client.itemgroups.NordicWorldItemGroup;
import com.lordskittles.arcanumapi.common.block.BlockMod;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.ItemTier;
import net.minecraftforge.common.ToolType;

public class BlockPlank extends BlockMod
{
	public BlockPlank()
	{
		super(Block.Properties.create(Material.WOOD, MaterialColor.WOOD)
				.hardnessAndResistance(2.0f, 3.0f)
				.sound(SoundType.WOOD)
				.harvestLevel(ItemTier.WOOD.getHarvestLevel())
				.harvestTool(ToolType.AXE));

		this.group = NordicWorldItemGroup.INSTANCE;
	}
}
