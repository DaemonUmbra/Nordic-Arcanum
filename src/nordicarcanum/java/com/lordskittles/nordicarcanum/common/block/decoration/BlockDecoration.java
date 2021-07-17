package com.lordskittles.nordicarcanum.common.block.decoration;

import com.lordskittles.arcanumapi.common.block.BlockMod;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicDecorationItemGroup;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.ItemGroup;

public class BlockDecoration extends BlockMod {

    public BlockDecoration(Material material, MaterialColor color) {

        super(Block.Properties.create(material, color)
                .hardnessAndResistance(1.5f, 6.0f)
                .sound(SoundType.STONE));

        this.group = NordicDecorationItemGroup.INSTANCE;
    }

    public BlockDecoration(float hardness) {

        super(Block.Properties.create(Material.ROCK, MaterialColor.STONE)
                .hardnessAndResistance(hardness, 6.0f)
                .sound(SoundType.STONE));

        this.group = NordicDecorationItemGroup.INSTANCE;
    }

    public BlockDecoration(int lightLevel) {

        super(Block.Properties.create(Material.GLASS, MaterialColor.GOLD)
                .hardnessAndResistance(0.5f, 3.0f)
                .sound(SoundType.GLASS), lightLevel);

        this.group = NordicDecorationItemGroup.INSTANCE;
    }

    public BlockDecoration(Material material, MaterialColor color, int harvestLevel) {

        super(Block.Properties.create(material, color)
                .hardnessAndResistance(0.5f, 3.0F)
                .sound(SoundType.GLASS)
                .harvestLevel(harvestLevel));

        this.group = NordicDecorationItemGroup.INSTANCE;
    }

    public BlockDecoration(Block.Properties properties, ItemGroup group) {

        super(properties);

        this.group = group;
    }
}
