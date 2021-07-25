package com.lordskittles.arcanumapi.common.utilities;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

public interface IMultiblock<T extends Enum> {

    T[] getValues();

    StructureTemplate getTemplate(StructureManager templateManager);

    boolean isValidBlock(BlockState type);

    boolean isFormed();

    void setFormed(boolean formed);

    void validateStructure();

    BlockPos getPos();

    CompoundTag getUpdateTag();

    ResourceLocation getRegistryName();
}
