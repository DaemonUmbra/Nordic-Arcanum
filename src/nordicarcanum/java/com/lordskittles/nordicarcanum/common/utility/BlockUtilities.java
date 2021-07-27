package com.lordskittles.nordicarcanum.common.utility;

import com.lordskittles.nordicarcanum.common.registry.Blocks;
import com.lordskittles.nordicarcanum.common.blockentity.magic.BlockEntitySigilPodium;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class BlockUtilities {

    public static List<BlockEntitySigilPodium> getPodiums(LevelAccessor level, BlockPos start) {

        List<BlockEntitySigilPodium> podiums = new ArrayList<>();

        BlockPos topPodium = locateTopPodium(level, start);

        if(topPodium == null)
            return podiums;

        podiums.add((BlockEntitySigilPodium) (level.getBlockEntity(topPodium)));

        Direction direction = getOppDirectionFromBlockPos(level, start);
        BlockPos botLeftPodium = locateBotLeftPodium(start, direction);
        BlockPos botRightPodium = locateBotRightPodium(start, direction);

        if(botLeftPodium == null)
            return podiums;

        podiums.add((BlockEntitySigilPodium) (level.getBlockEntity(botLeftPodium)));

        if(botRightPodium == null)
            return podiums;

        podiums.add((BlockEntitySigilPodium) (level.getBlockEntity(botRightPodium)));

        return podiums;
    }

    public static boolean isAltarMultiblockComplete(LevelAccessor level, BlockPos start) {

        Block chiseledCalcite = Blocks.chiseled_feldspar_brick.get();
        Block calciteBrick = Blocks.feldspar_brick.get();

        BlockPos topPodium = locateTopPodium(level, start);

        if(topPodium == null)
            return false;

        Direction direction = getOppDirectionFromBlockPos(level, start);
        BlockPos botLeftPodium = locateBotLeftPodium(start, direction);
        BlockPos botRightPodium = locateBotRightPodium(start, direction);

        BlockState state = level.getBlockState(topPodium.below());
        if(state == net.minecraft.world.level.block.Blocks.AIR.defaultBlockState())
            return false;

        if(level.getBlockState(topPodium.below()).getBlock() != chiseledCalcite)
            return false;

        state = level.getBlockState(botLeftPodium.below());
        if(state == net.minecraft.world.level.block.Blocks.AIR.defaultBlockState())
            return false;

        if(level.getBlockState(botLeftPodium.below()).getBlock() != chiseledCalcite)
            return false;

        state = level.getBlockState(botRightPodium.below());
        if(state == net.minecraft.world.level.block.Blocks.AIR.defaultBlockState())
            return false;

        if(level.getBlockState(botRightPodium.below()).getBlock() != chiseledCalcite)
            return false;

        if(! checkBase(level, topPodium.below(), calciteBrick))
            return false;

        if(! checkBase(level, botLeftPodium.below(), calciteBrick))
            return false;

        if(! checkBase(level, botRightPodium.below(), calciteBrick))
            return false;

        if(! checkBase(level, start.below(), calciteBrick))
            return false;

        return true;
    }

    /**
     * Gets the direction from a pair of block positions, if the direction returned is UP, then it failed
     */
    private static Direction getOppDirectionFromBlockPos(LevelAccessor world, BlockPos start) {

        BlockPos north = new BlockPos(start.getX(), start.getY(), start.getZ() - 5);
        BlockPos south = new BlockPos(start.getX(), start.getY(), start.getZ() + 5);
        BlockPos west = new BlockPos(start.getX() - 5, start.getY(), start.getZ());
        BlockPos east = new BlockPos(start.getX() + 5, start.getY(), start.getZ());
        BlockState podium = Blocks.sigil_podium.get().defaultBlockState();

        // Check north
        BlockState state = world.getBlockState(north);
        if(world.getBlockState(north) == podium)
            return Direction.NORTH;

        state = world.getBlockState(south);
        if(world.getBlockState(south) == podium)
            return Direction.SOUTH;

        state = world.getBlockState(west);
        if(world.getBlockState(west) == podium)
            return Direction.WEST;

        state = world.getBlockState(east);
        if(world.getBlockState(east) == podium)
            return Direction.EAST;

        return Direction.NORTH;
    }

    @Nullable
    private static BlockPos locateTopPodium(LevelAccessor world, BlockPos start) {

        BlockPos north = new BlockPos(start.getX(), start.getY(), start.getZ() - 5);
        BlockPos south = new BlockPos(start.getX(), start.getY(), start.getZ() + 5);
        BlockPos west = new BlockPos(start.getX() - 5, start.getY(), start.getZ());
        BlockPos east = new BlockPos(start.getX() + 5, start.getY(), start.getZ());
        BlockState podium = Blocks.sigil_podium.get().defaultBlockState();

        // Check north
        if(world.getBlockState(north) == podium)
            return north;

        if(world.getBlockState(south) == podium)
            return south;

        if(world.getBlockState(west) == podium)
            return west;

        if(world.getBlockState(east) == podium)
            return east;

        return null;
    }

    @Nullable
    private static BlockPos locateBotRightPodium(BlockPos start, Direction direction) {

        BlockPos position = null;

        switch(direction) {
            case NORTH:
                position = new BlockPos(start.getX() + 4, start.getY(), start.getZ() + 4);
                break;
            case SOUTH:
                position = new BlockPos(start.getX() - 4, start.getY(), start.getZ() - 4);
                break;
            case EAST:
                position = new BlockPos(start.getX() - 4, start.getY(), start.getZ() + 4);
                break;
            case WEST:
                position = new BlockPos(start.getX() + 4, start.getY(), start.getZ() - 4);
                break;
        }

        return position;
    }

    @Nullable
    private static BlockPos locateBotLeftPodium(BlockPos start, Direction direction) {

        BlockPos position = null;

        switch(direction) {
            case NORTH:
                position = new BlockPos(start.getX() - 4, start.getY(), start.getZ() + 4);
                break;
            case SOUTH:
                position = new BlockPos(start.getX() + 4, start.getY(), start.getZ() - 4);
                break;
            case EAST:
                position = new BlockPos(start.getX() - 4, start.getY(), start.getZ() - 4);
                break;
            case WEST:
                position = new BlockPos(start.getX() + 4, start.getY(), start.getZ() + 4);
                break;
        }

        return position;
    }

    private static boolean checkBase(LevelAccessor world, BlockPos pos, Block block) {

        if(world.getBlockState(pos.north()).getBlock() != block)
            return false;

        if(world.getBlockState(pos.south()).getBlock() != block)
            return false;

        BlockPos topStart = pos.north().west();
        for(int x = 0; x < 3; x++) {
            if(world.getBlockState(new BlockPos(topStart.getX(), topStart.getY(), topStart.getZ() + x)).getBlock() != block)
                return false;
        }

        Block blockAt;
        BlockPos botStart = pos.north().east();
        for(int x = 0; x < 3; x++) {
            BlockPos newPos = new BlockPos(botStart.getX(), botStart.getY(), botStart.getZ() + x);
            blockAt = world.getBlockState(newPos).getBlock();
            if(blockAt != block)
                return false;
        }

        return true;
    }
}
