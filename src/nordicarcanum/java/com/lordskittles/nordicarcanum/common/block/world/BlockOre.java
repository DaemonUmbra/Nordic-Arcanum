package com.lordskittles.nordicarcanum.common.block.world;

import com.lordskittles.arcanumapi.common.block.BlockMod;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicResourcesItemGroup;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class BlockOre extends BlockMod {

    public BlockOre(int harvestLevel, int lightLevel) {

        this(harvestLevel, lightLevel, 3.0F);
    }

    public BlockOre(int harvestLevel, int lightLevel, float hardness) {

        super(Block.Properties.create(Material.ROCK, MaterialColor.RED)
                .hardnessAndResistance(hardness, 3.0F)
                .sound(SoundType.STONE)
                .harvestLevel(harvestLevel), lightLevel);

        this.group = NordicResourcesItemGroup.INSTANCE;
    }
}
