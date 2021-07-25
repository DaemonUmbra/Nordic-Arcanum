package com.lordskittles.nordicarcanum.common.block.decoration;

import com.lordskittles.arcanumapi.common.block.BlockMod;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicDecorationItemGroup;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class BlockDecoration extends BlockMod {

    public BlockDecoration(Material material, MaterialColor color) {

        super(Block.Properties.of(material, color)
                .strength(1.5f, 6.0f)
                .sound(SoundType.STONE));

        this.group = NordicDecorationItemGroup.INSTANCE;
    }

    public BlockDecoration(float hardness) {

        super(Block.Properties.of(Material.STONE, MaterialColor.STONE)
                .strength(hardness, 6.0f)
                .sound(SoundType.STONE));

        this.group = NordicDecorationItemGroup.INSTANCE;
    }

    public BlockDecoration(int lightLevel) {

        super(Block.Properties.of(Material.GLASS, MaterialColor.GOLD)
                .strength(0.5f, 3.0f)
                .sound(SoundType.GLASS), lightLevel);

        this.group = NordicDecorationItemGroup.INSTANCE;
    }

    public BlockDecoration(Material material, MaterialColor color, int harvestLevel) {

        super(Block.Properties.of(material, color)
                .strength(0.5f, 3.0F)
                .sound(SoundType.GLASS)
                .harvestLevel(harvestLevel));

        this.group = NordicDecorationItemGroup.INSTANCE;
    }

    public BlockDecoration(Block.Properties properties, CreativeModeTab group) {

        super(properties);

        this.group = group;
    }
}
