package com.lordskittles.arcanumapi.common.multiblock;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.template.PlacementSettings;

public abstract class MultiblockDetector<M extends IMultiblock> {

    protected class StartInfo {

        public PlacementSettings placement;
        public BlockPos position;

        public StartInfo(PlacementSettings placement, BlockPos position) {

            this.placement = placement;
            this.position = position;
        }

        @Override
        public String toString() {

            return String.format("Position: u1=%s - Placement: u2=%s - u3=%s", position.toString(), placement.getMirror().toString(), placement.getRotation().toString());
        }
    }

    protected abstract IMultiblock getMultiblock();

    public boolean validate(IWorld world, BlockPos position) {

        return true;
    }
}
