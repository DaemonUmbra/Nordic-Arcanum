package com.lordskittles.nordicarcanum.common.block.decoration;

import com.lordskittles.arcanumapi.common.block.BlockMod;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicResourcesItemGroup;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.ItemTier;
import net.minecraftforge.common.ToolType;

public class BlockCompactResource extends BlockMod {

    public BlockCompactResource() {

        super(Block.Properties.create(Material.IRON, MaterialColor.RED)
                .hardnessAndResistance(5.0F, 6.0F)
                .sound(SoundType.METAL)
                .harvestLevel(ItemTier.WOOD.getHarvestLevel())
                .harvestTool(ToolType.PICKAXE));

        this.group = NordicResourcesItemGroup.INSTANCE;
    }
}
