package com.lordskittles.nordicarcanum.common.block.decoration;

import com.lordskittles.arcanumapi.common.block.BlockMod;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicResourcesItemGroup;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.common.ToolType;

public class BlockCompactResource extends BlockMod {

    public BlockCompactResource() {

        super(Block.Properties.of(Material.METAL, MaterialColor.COLOR_RED)
                .strength(5.0F, 6.0F)
                .sound(SoundType.METAL)
                .harvestLevel(Tiers.WOOD.getLevel())
                .harvestTool(ToolType.PICKAXE));

        this.group = NordicResourcesItemGroup.INSTANCE;
    }
}
