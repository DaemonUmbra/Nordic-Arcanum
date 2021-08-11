package com.lordskittles.nordicarcanum.common.block.decoration;

import com.lordskittles.arcanumapi.common.block.BlockMod;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicDecorationItemGroup;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.common.ToolType;

public class BlockPlank extends BlockMod {

    public BlockPlank() {

        super(Block.Properties.of(Material.WOOD, MaterialColor.WOOD)
                .strength(2.0f, 3.0f)
                .sound(SoundType.WOOD)
                .harvestLevel(Tiers.WOOD.getLevel())
                .harvestTool(ToolType.AXE));

        this.group = NordicDecorationItemGroup.INSTANCE;
    }
}
