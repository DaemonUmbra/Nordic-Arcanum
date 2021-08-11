package com.lordskittles.nordicarcanum.common.block.world.trees;

import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

public abstract class NordicTree extends AbstractTreeGrower {

    public abstract TreeConfiguration getConfig();
}
