package com.lordskittles.arcanumapi.common.multiblock;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

public interface IMultiblock {

    MultiblockReference[] getReferences();

    Template getTemplate(TemplateManager templateManager);

    boolean isValidBlock(BlockState type);

    boolean isFormed();

    void setFormed(boolean formed);

    void validateStructure();

    BlockPos getPos();

    CompoundNBT getUpdateTag();

    ResourceLocation getRegistryName();
}
