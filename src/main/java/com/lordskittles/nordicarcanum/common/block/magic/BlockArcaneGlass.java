package com.lordskittles.nordicarcanum.common.block.magic;

import com.lordskittles.arcanumapi.common.block.BlockMod;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicDecorationItemGroup;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BlockArcaneGlass extends BlockMod {

    public BlockArcaneGlass() {

        super(Block.Properties.of(Material.GLASS)
                .noOcclusion()
                .strength(1.0f)
                .sound(SoundType.GLASS).isValidSpawn((state, blockGetter, pos, entity) -> { return false; }));

        this.group = NordicDecorationItemGroup.INSTANCE;
    }

    public VoxelShape getVisualShape(BlockState blockState, BlockGetter blockGetter, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }

    public float getShadeBrightness(BlockState p_48731_, BlockGetter p_48732_, BlockPos p_48733_) {
        return 1.0F;
    }

    public boolean propagatesSkylightDown(BlockState p_48740_, BlockGetter p_48741_, BlockPos p_48742_) {
        return true;
    }

    @OnlyIn(Dist.CLIENT)
    public boolean skipRendering(BlockState state1, BlockState state2, Direction direction) {
        return state2.is(this) || super.skipRendering(state1, state2, direction);
    }
}
