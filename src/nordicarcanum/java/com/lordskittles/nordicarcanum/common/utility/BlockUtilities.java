package com.lordskittles.nordicarcanum.common.utility;

import com.lordskittles.nordicarcanum.common.registry.Blocks;
import com.lordskittles.nordicarcanum.common.tileentity.magic.TileEntitySigilPodium;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class BlockUtilities {

    public static List<TileEntitySigilPodium> getPodiums(IWorld world, BlockPos start) {

        List<TileEntitySigilPodium> podiums = new ArrayList<>();

        BlockPos topPodium = locateTopPodium(world, start);

        if(topPodium == null)
            return podiums;

        podiums.add((TileEntitySigilPodium) (world.getTileEntity(topPodium)));

        Direction direction = getOppDirectionFromBlockPos(world, start);
        BlockPos botLeftPodium = locateBotLeftPodium(start, direction);
        BlockPos botRightPodium = locateBotRightPodium(start, direction);

        if(botLeftPodium == null)
            return podiums;

        podiums.add((TileEntitySigilPodium) (world.getTileEntity(botLeftPodium)));

        if(botRightPodium == null)
            return podiums;

        podiums.add((TileEntitySigilPodium) (world.getTileEntity(botRightPodium)));

        return podiums;
    }

    public static boolean isAltarMultiblockComplete(IWorld world, BlockPos start) {

        Block chiseledCalcite = Blocks.chiseled_feldspar_brick.get();
        Block calciteBrick = Blocks.feldspar_brick.get();

        BlockPos topPodium = locateTopPodium(world, start);

        if(topPodium == null)
            return false;

        Direction direction = getOppDirectionFromBlockPos(world, start);
        BlockPos botLeftPodium = locateBotLeftPodium(start, direction);
        BlockPos botRightPodium = locateBotRightPodium(start, direction);

        BlockState state = world.getBlockState(topPodium.down());
        if(state == net.minecraft.block.Blocks.AIR.getDefaultState())
            return false;

        if(world.getBlockState(topPodium.down()).getBlock() != chiseledCalcite)
            return false;

        state = world.getBlockState(botLeftPodium.down());
        if(state == net.minecraft.block.Blocks.AIR.getDefaultState())
            return false;

        if(world.getBlockState(botLeftPodium.down()).getBlock() != chiseledCalcite)
            return false;

        state = world.getBlockState(botRightPodium.down());
        if(state == net.minecraft.block.Blocks.AIR.getDefaultState())
            return false;

        if(world.getBlockState(botRightPodium.down()).getBlock() != chiseledCalcite)
            return false;

        if(! checkBase(world, topPodium.down(), calciteBrick))
            return false;

        if(! checkBase(world, botLeftPodium.down(), calciteBrick))
            return false;

        if(! checkBase(world, botRightPodium.down(), calciteBrick))
            return false;

        if(! checkBase(world, start.down(), calciteBrick))
            return false;

        return true;
    }

    /**
     * Gets the direction from a pair of block positions, if the direction returned is UP, then it failed
     */
    private static Direction getOppDirectionFromBlockPos(IWorld world, BlockPos start) {

        BlockPos north = new BlockPos(start.getX(), start.getY(), start.getZ() - 5);
        BlockPos south = new BlockPos(start.getX(), start.getY(), start.getZ() + 5);
        BlockPos west = new BlockPos(start.getX() - 5, start.getY(), start.getZ());
        BlockPos east = new BlockPos(start.getX() + 5, start.getY(), start.getZ());
        BlockState podium = Blocks.sigil_podium.get().getDefaultState();

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
    private static BlockPos locateTopPodium(IWorld world, BlockPos start) {

        BlockPos north = new BlockPos(start.getX(), start.getY(), start.getZ() - 5);
        BlockPos south = new BlockPos(start.getX(), start.getY(), start.getZ() + 5);
        BlockPos west = new BlockPos(start.getX() - 5, start.getY(), start.getZ());
        BlockPos east = new BlockPos(start.getX() + 5, start.getY(), start.getZ());
        BlockState podium = Blocks.sigil_podium.get().getDefaultState();

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

    private static boolean checkBase(IWorld world, BlockPos pos, Block block) {

        Block blockAt = world.getBlockState(pos).getBlock();

        if(world.getBlockState(pos.north()).getBlock() != block)
            return false;

        if(world.getBlockState(pos.south()).getBlock() != block)
            return false;

        BlockPos topStart = pos.north().west();
        for(int x = 0; x < 3; x++) {
            if(world.getBlockState(new BlockPos(topStart.getX(), topStart.getY(), topStart.getZ() + x)).getBlock() != block)
                return false;
        }

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
