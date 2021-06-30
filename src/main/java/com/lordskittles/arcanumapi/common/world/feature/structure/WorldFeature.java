package com.lordskittles.arcanumapi.common.world.feature.structure;

import com.mojang.serialization.Codec;
import net.minecraft.block.*;
import net.minecraft.state.DirectionProperty;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.function.Function;

public abstract class WorldFeature<T extends IFeatureConfig> extends Feature<T>
{
    protected enum TransformDirection
    {
        Forward,
        Right
    }

    protected IWorld world;
    protected Random random;
    protected T config;
    protected final MutableBoundingBox bounds;
    protected int rotation = 0;

    public WorldFeature(Codec<T> configFactory, MutableBoundingBox bounds)
    {
        super(configFactory);

        this.bounds = bounds;
    }

    @Override
    public boolean generate(ISeedReader world, ChunkGenerator generator, Random rand, BlockPos pos, T config)
    {
        this.world = world;
        this.random = rand;
        this.config = config;
        this.rotation = rand.nextInt(3);

        if (!validBounds(pos) || isFluid(pos.down()))
            return false;

        return place(pos, generator);
    }

    protected BlockState transformStairState(BlockState state, DirectionProperty property, Direction defaultDir)
    {
        switch (this.rotation)
        {
            case 0:
                if (defaultDir == Direction.NORTH)
                    return state.with(property, Direction.WEST);
                else if (defaultDir == Direction.EAST)
                    return state.with(property, Direction.SOUTH);
            case 1:
                if (defaultDir == Direction.NORTH)
                    return state.with(property, Direction.NORTH);
                else if (defaultDir == Direction.EAST)
                    return state.with(property, Direction.WEST);
            case 2:
                if (defaultDir == Direction.NORTH)
                    return state.with(property, Direction.EAST);
                else if (defaultDir == Direction.EAST)
                    return state.with(property, Direction.NORTH);
            case 3:
                if (defaultDir == Direction.NORTH)
                    return state.with(property, Direction.SOUTH);
                else if (defaultDir == Direction.EAST)
                    return state.with(property, Direction.EAST);
        }

        return state;
    }

    protected BlockPos transformPosition(BlockPos pos, TransformDirection direction)
    {
        switch (this.rotation)
        {
            case 0:
                if (direction == TransformDirection.Forward)
                    return pos.north();
                else if (direction == TransformDirection.Right)
                    return pos.east();
            case 1:
                if (direction == TransformDirection.Forward)
                    return pos.east();
                else if (direction == TransformDirection.Right)
                    return pos.south();
            case 2:
                if (direction == TransformDirection.Forward)
                    return pos.south();
                else if (direction == TransformDirection.Right)
                    return pos.west();
            case 3:
                if (direction == TransformDirection.Forward)
                    return pos.west();
                else if (direction == TransformDirection.Right)
                    return pos.north();
        }

        return pos;
    }

    protected abstract boolean place(BlockPos position, ChunkGenerator generator);

    protected boolean validBounds(BlockPos start)
    {
        for (int x = bounds.minX; x < bounds.maxX; x++)
        {
            for (int y = bounds.minY; y < bounds.maxY; y++)
            {
                for (int z = bounds.minZ; z < bounds.maxZ; z++)
                {
                    BlockPos pos = new BlockPos(start.getX() + x, start.getY() + y, start.getZ() + z);
                    if (!isReplaceable(pos))
                        return false;
                }
            }
        }

        BlockPos downStart = start.down();

        if (!isGrassOrDirt(downStart) && !isGravelOrSand(downStart) && !isStone(downStart))
            return false;

        if (!isGrassOrDirt(downStart.south()) && !isGravelOrSand(downStart.south()) && !isStone(downStart.south()))
            return false;

        if (!isGrassOrDirt(downStart.west()) && !isGravelOrSand(downStart.west()) && !isStone(downStart.west()))
            return false;

        if (!isGrassOrDirt(downStart.west().south()) && !isGravelOrSand(downStart.west().south()) && !isStone(downStart.west().south()))
            return false;

        return true;
    }

    protected boolean generateChest(BlockPos pos, ResourceLocation lootTable, @Nullable BlockState state)
    {
        if (isReplaceable(pos))
        {
            if (state == null)
            {
                state = Blocks.CHEST.getDefaultState().with(ChestBlock.FACING, Direction.NORTH);
            }

            this.world.setBlockState(pos, state, 2);
            TileEntity tile = this.world.getTileEntity(pos);
            if (tile instanceof ChestTileEntity)
            {
                ((ChestTileEntity)tile).setLootTable(lootTable, random.nextLong());
            }

            return true;
        }

        return false;
    }

    protected boolean isFluid(BlockPos pos)
    {
        return this.world.getBlockState(pos).getBlock() instanceof FlowingFluidBlock;
    }

    protected boolean isReplaceable(BlockPos pos)
    {
        BlockState state = this.world.getBlockState(pos);

        return state.getBlock() instanceof LeavesBlock || state == Blocks.AIR.getDefaultState() || state.getBlock() instanceof BushBlock ||
                isStone(pos) || isGrassOrDirt(pos) || isGravelOrSand(pos) || isSnow(pos);
    }

    protected boolean isLeavesOrAir(BlockPos pos)
    {
        BlockState state = this.world.getBlockState(pos);

        return state.getBlock() instanceof LeavesBlock || state == Blocks.AIR.getDefaultState();
    }

    protected boolean isTallPlant(BlockPos pos)
    {
        BlockState state = this.world.getBlockState(pos);

        return state.getBlock() instanceof BushBlock;
    }

    protected boolean isGrassOrDirt(BlockPos pos)
    {
        return this.world.getBlockState(pos) == Blocks.GRASS_BLOCK.getDefaultState() || this.world.getBlockState(pos) == Blocks.DIRT.getDefaultState() ||
                this.world.getBlockState(pos) == Blocks.COARSE_DIRT.getDefaultState();
    }

    protected boolean isStone(BlockPos pos)
    {
        return this.world.getBlockState(pos) == Blocks.STONE.getDefaultState() || this.world.getBlockState(pos) == Blocks.ANDESITE.getDefaultState() ||
                this.world.getBlockState(pos) == Blocks.DIORITE.getDefaultState() || this.world.getBlockState(pos) == Blocks.GRANITE.getDefaultState() ||
                this.world.getBlockState(pos) == Blocks.SANDSTONE.getDefaultState();
    }

    protected boolean isGravelOrSand(BlockPos pos)
    {
        return this.world.getBlockState(pos) == Blocks.GRAVEL.getDefaultState() || this.world.getBlockState(pos) == Blocks.SAND.getDefaultState();
    }

    protected boolean isSnow(BlockPos pos)
    {
        return this.world.getBlockState(pos) == Blocks.SNOW.getDefaultState() || this.world.getBlockState(pos) == Blocks.SNOW_BLOCK.getDefaultState();
    }
}
