package com.lordskittles.arcanumapi.common.utilities;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

public interface IMultiblock<T extends Enum> {

    T[] getValues();

    Template getTemplate(TemplateManager templateManager);

    boolean isValidBlock(BlockState type);

    boolean isFormed();

    void setFormed(boolean formed);

    void validateStructure();

    BlockPos getPos();

    CompoundNBT getUpdateTag();

    ResourceLocation getRegistryName();
}
