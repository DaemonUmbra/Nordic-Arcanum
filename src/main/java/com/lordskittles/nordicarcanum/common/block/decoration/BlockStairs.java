package com.lordskittles.nordicarcanum.common.block.decoration;

import com.lordskittles.arcanumapi.common.block.IItemGroupHolder;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicDecorationItemGroup;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ToolType;

public class BlockStairs extends StairBlock implements IItemGroupHolder {

    public BlockStairs(Block aBlock) {

        this(aBlock, Material.STONE, SoundType.STONE);
    }

    public BlockStairs(Block block, Material material, SoundType sound) {

        this(block, material, sound, Tiers.WOOD.getLevel(), ToolType.PICKAXE, 6.0f);
    }

    public BlockStairs(Block block, Material material, SoundType sound, int harvestLevel, ToolType tool, float resistance) {

        super(() -> block.defaultBlockState(),
                Block.Properties.of(Material.STONE)
                        .sound(sound)
                        .harvestLevel(harvestLevel)
                        .harvestTool(tool)
                        .strength(2.0f, resistance));
    }

    @Override
    public CreativeModeTab group() {

        return NordicDecorationItemGroup.INSTANCE;
    }
}
