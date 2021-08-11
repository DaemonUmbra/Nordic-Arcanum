package com.lordskittles.nordicarcanum.common.block.world;

import com.lordskittles.arcanumapi.common.block.BlockMod;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicResourcesItemGroup;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class BlockOre extends BlockMod {

    public BlockOre(int harvestLevel, int lightLevel) {

        this(harvestLevel, lightLevel, 3.0F);
    }

    public BlockOre(int harvestLevel, int lightLevel, float hardness) {

        super(Block.Properties.of(Material.STONE, MaterialColor.COLOR_RED)
                .strength(hardness, 3.0F)
                .sound(SoundType.STONE)
                .harvestLevel(harvestLevel)
                .requiresCorrectToolForDrops(), lightLevel);

        this.group = NordicResourcesItemGroup.INSTANCE;
    }
}
