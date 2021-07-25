package com.lordskittles.arcanumapi.common.world.feature.structure;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.BoundingBox;

import javax.annotation.Nullable;
import java.util.Random;

public abstract class WorldFeature<T extends FeatureConfiguration> extends Feature<T> {

    protected enum TransformDirection {
        Forward,
        Right
    }

    protected LevelAccessor world;
    protected Random random;
    protected T config;
    protected final BoundingBox bounds;
    protected int rotation = 0;

    public WorldFeature(Codec<T> configFactory, BoundingBox bounds) {

        super(configFactory);

        this.bounds = bounds;
    }

    @Override
    public boolean place(FeaturePlaceContext<T> context) {

        this.world = world;
        this.random = context.random();
        this.config = config;
        this.rotation = random.nextInt(3);

        if(! validBounds(context.origin()) || isFluid(context.origin().below()))
            return false;

        return place(context.origin(), context.chunkGenerator());
    }

    protected BlockState transformStairState(BlockState state, DirectionProperty property, Direction defaultDir) {

        switch(this.rotation) {
            case 0:
                if(defaultDir == Direction.NORTH)
                    return state.setValue(property, Direction.WEST);
                else
                    if(defaultDir == Direction.EAST)
                        return state.setValue(property, Direction.SOUTH);
            case 1:
                if(defaultDir == Direction.NORTH)
                    return state.setValue(property, Direction.NORTH);
                else
                    if(defaultDir == Direction.EAST)
                        return state.setValue(property, Direction.WEST);
            case 2:
                if(defaultDir == Direction.NORTH)
                    return state.setValue(property, Direction.EAST);
                else
                    if(defaultDir == Direction.EAST)
                        return state.setValue(property, Direction.NORTH);
            case 3:
                if(defaultDir == Direction.NORTH)
                    return state.setValue(property, Direction.SOUTH);
                else
                    if(defaultDir == Direction.EAST)
                        return state.setValue(property, Direction.EAST);
        }

        return state;
    }

    protected BlockPos transformPosition(BlockPos pos, TransformDirection direction) {

        switch(this.rotation) {
            case 0:
                if(direction == TransformDirection.Forward)
                    return pos.north();
                else
                    if(direction == TransformDirection.Right)
                        return pos.east();
            case 1:
                if(direction == TransformDirection.Forward)
                    return pos.east();
                else
                    if(direction == TransformDirection.Right)
                        return pos.south();
            case 2:
                if(direction == TransformDirection.Forward)
                    return pos.south();
                else
                    if(direction == TransformDirection.Right)
                        return pos.west();
            case 3:
                if(direction == TransformDirection.Forward)
                    return pos.west();
                else
                    if(direction == TransformDirection.Right)
                        return pos.north();
        }

        return pos;
    }

    protected abstract boolean place(BlockPos position, ChunkGenerator generator);

    protected boolean validBounds(BlockPos start) {

        for(int x = bounds.minX(); x < bounds.maxX(); x++) {
            for(int y = bounds.minZ(); y < bounds.maxY(); y++) {
                for(int z = bounds.minZ(); z < bounds.maxZ(); z++) {
                    BlockPos pos = new BlockPos(start.getX() + x, start.getY() + y, start.getZ() + z);
                    if(! isReplaceable(pos))
                        return false;
                }
            }
        }

        BlockPos downStart = start.below();

        if(! isGrassOrDirt(downStart) && ! isGravelOrSand(downStart) && ! isStone(downStart))
            return false;

        if(! isGrassOrDirt(downStart.south()) && ! isGravelOrSand(downStart.south()) && ! isStone(downStart.south()))
            return false;

        if(! isGrassOrDirt(downStart.west()) && ! isGravelOrSand(downStart.west()) && ! isStone(downStart.west()))
            return false;

        if(! isGrassOrDirt(downStart.west().south()) && ! isGravelOrSand(downStart.west().south()) && ! isStone(downStart.west().south()))
            return false;

        return true;
    }

    protected boolean generateChest(BlockPos pos, ResourceLocation lootTable, @Nullable BlockState state) {

        if(isReplaceable(pos)) {
            if(state == null) {
                state = Blocks.CHEST.defaultBlockState().setValue(ChestBlock.FACING, Direction.NORTH);
            }

            this.world.setBlock(pos, state, 2);
            BlockEntity tile = this.world.getBlockEntity(pos);
            if(tile instanceof ChestBlockEntity) {
                ((ChestBlockEntity) tile).setLootTable(lootTable, random.nextLong());
            }

            return true;
        }

        return false;
    }

    protected boolean isFluid(BlockPos pos) {

        return this.world.getBlockState(pos).getBlock() instanceof LiquidBlock;
    }

    protected boolean isReplaceable(BlockPos pos) {

        BlockState state = this.world.getBlockState(pos);

        return state.getBlock() instanceof LeavesBlock || state == Blocks.AIR.defaultBlockState() || state.getBlock() instanceof BushBlock ||
               isStone(pos) || isGrassOrDirt(pos) || isGravelOrSand(pos) || isSnow(pos);
    }

    protected boolean isLeavesOrAir(BlockPos pos) {

        BlockState state = this.world.getBlockState(pos);

        return state.getBlock() instanceof LeavesBlock || state == Blocks.AIR.defaultBlockState();
    }

    protected boolean isTallPlant(BlockPos pos) {

        BlockState state = this.world.getBlockState(pos);

        return state.getBlock() instanceof BushBlock;
    }

    protected boolean isGrassOrDirt(BlockPos pos) {

        return this.world.getBlockState(pos) == Blocks.GRASS_BLOCK.defaultBlockState() || this.world.getBlockState(pos) == Blocks.DIRT.defaultBlockState() ||
               this.world.getBlockState(pos) == Blocks.COARSE_DIRT.defaultBlockState();
    }

    protected boolean isStone(BlockPos pos) {

        return this.world.getBlockState(pos) == Blocks.STONE.defaultBlockState() || this.world.getBlockState(pos) == Blocks.ANDESITE.defaultBlockState() ||
               this.world.getBlockState(pos) == Blocks.DIORITE.defaultBlockState() || this.world.getBlockState(pos) == Blocks.GRANITE.defaultBlockState() ||
               this.world.getBlockState(pos) == Blocks.SANDSTONE.defaultBlockState();
    }

    protected boolean isGravelOrSand(BlockPos pos) {

        return this.world.getBlockState(pos) == Blocks.GRAVEL.defaultBlockState() || this.world.getBlockState(pos) == Blocks.SAND.defaultBlockState();
    }

    protected boolean isSnow(BlockPos pos) {

        return this.world.getBlockState(pos) == Blocks.SNOW.defaultBlockState() || this.world.getBlockState(pos) == Blocks.SNOW_BLOCK.defaultBlockState();
    }
}
