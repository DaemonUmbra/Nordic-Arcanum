package com.lordskittles.nordicarcanum.common.block.decoration;

import com.lordskittles.arcanumapi.common.block.IItemGroupHolder;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicDecorationItemGroup;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ToolType;

public class BlockSlab extends SlabBlock implements IItemGroupHolder {

    public BlockSlab() {

        this(Material.STONE, SoundType.STONE);
    }

    public BlockSlab(Material material, SoundType sound) {

        this(Block.Properties.of(material)
                .sound(sound)
                .harvestLevel(Tiers.WOOD.getLevel())
                .harvestTool(ToolType.PICKAXE)
                .strength(2.0F, 6.0f));
    }

    public BlockSlab(Block.Properties properties) {

        super(properties);
    }

    @Override
    public CreativeModeTab group() {

        return NordicDecorationItemGroup.INSTANCE;
    }
}
