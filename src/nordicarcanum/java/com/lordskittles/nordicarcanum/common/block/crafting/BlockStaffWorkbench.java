package com.lordskittles.nordicarcanum.common.block.crafting;

import com.lordskittles.arcanumapi.common.block.BlockMod;
import com.lordskittles.arcanumapi.common.block.MultiBlockPiece;
import com.lordskittles.arcanumapi.common.utilities.BlockUtilities;
import com.lordskittles.nordicarcanum.common.block.voxelshapes.VoxelsStaffWorkbench;
import com.lordskittles.nordicarcanum.common.registry.Blocks;
import com.lordskittles.nordicarcanum.common.registry.TileEntities;
import com.lordskittles.nordicarcanum.common.tileentity.crafting.TileEntityStaffWorkbench;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTier;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class BlockStaffWorkbench extends BlockMod {

    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
    public static final EnumProperty<MultiBlockPiece> PIECE = EnumProperty.create("piece", MultiBlockPiece.class);

    public BlockStaffWorkbench() {

        super(Block.Properties.create(Material.WOOD, MaterialColor.WOOD)
                .hardnessAndResistance(1.5F, 6.0F)
                .sound(SoundType.WOOD)
                .harvestLevel(ItemTier.WOOD.getHarvestLevel())
                .harvestTool(ToolType.AXE), 4);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(PIECE, MultiBlockPiece.BOTTOM_LEFT));
    }

    @Override
    public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player) {

        MultiBlockPiece piece = state.get(PIECE);
        Block block = null;

        switch(piece) {
            case BOTTOM_LEFT:
            case BOTTOM_RIGHT:
                block = Blocks.feldspar_brick.get();
                break;
            case TOP_LEFT:
            case TOP_RIGHT:
                block = Blocks.pine_stairs.get();
                break;
        }

        return new ItemStack(block);
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos) {

        BlockPos right = BlockUtilities.getRightPos(state.get(FACING), pos);
        if(! world.isAirBlock(right))
            return false;

        if(! world.isAirBlock(right.up()))
            return false;

        if(! world.isAirBlock(pos.up()))
            return false;

        return true;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {

        return true;
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity entity, ItemStack stack) {

        if(! (entity instanceof PlayerEntity))
            return;

        Direction direction = state.get(FACING);

        BlockPos rightPos = BlockUtilities.getRightPos(direction, pos);
        world.setBlockState(rightPos, Blocks.staff_workbench.get().getDefaultState().with(BlockStaffWorkbench.FACING, direction).with(PIECE, MultiBlockPiece.BOTTOM_RIGHT));
        world.setBlockState(pos.up(), Blocks.staff_workbench.get().getDefaultState().with(BlockStaffWorkbench.FACING, direction).with(PIECE, MultiBlockPiece.TOP_LEFT));
        world.setBlockState(rightPos.up(), Blocks.staff_workbench.get().getDefaultState().with(BlockStaffWorkbench.FACING, direction).with(PIECE, MultiBlockPiece.TOP_RIGHT));
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {

        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().rotateY().getOpposite());
    }

    @Override
    public void onBlockHarvested(World world, BlockPos pos, BlockState state, PlayerEntity player) {

        Direction direction = state.get(FACING);

        BlockPos horizontalPos = BlockUtilities.getRightPos(direction, pos);
        MultiBlockPiece piece = state.get(PIECE);
        if(piece == MultiBlockPiece.BOTTOM_RIGHT || piece == MultiBlockPiece.TOP_RIGHT) {
            horizontalPos = BlockUtilities.getLeftPos(direction, pos);
        }

        world.setBlockState(horizontalPos, net.minecraft.block.Blocks.AIR.getDefaultState());

        if(piece == MultiBlockPiece.BOTTOM_RIGHT || piece == MultiBlockPiece.BOTTOM_LEFT) {
            world.setBlockState(pos.up(), net.minecraft.block.Blocks.AIR.getDefaultState());
            world.setBlockState(horizontalPos.up(), net.minecraft.block.Blocks.AIR.getDefaultState());
        }
        else {
            world.setBlockState(pos.down(), net.minecraft.block.Blocks.AIR.getDefaultState());
            world.setBlockState(horizontalPos.down(), net.minecraft.block.Blocks.AIR.getDefaultState());
        }

        if(! player.isCreative()) {
            world.addEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Blocks.pine_stairs.get(), 2)));
            world.addEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Blocks.feldspar_brick.get(), 2)));
        }

        world.playEvent(player, 2001, pos, getStateId(state));
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {

        return TileEntities.staff_workbench.get().create();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {

        BlockPos actPos = pos;
        BlockPos leftPos = BlockUtilities.getLeftPos(state.get(FACING), pos);

        switch(state.get(PIECE)) {
            case TOP_LEFT:
                actPos = pos.down();
                break;
            case TOP_RIGHT:
                actPos = leftPos.down();
                break;
            case BOTTOM_LEFT:
                actPos = pos;
                break;
            case BOTTOM_RIGHT:
                actPos = leftPos;
                break;
        }

        if(! world.isRemote) {
            TileEntity tile = world.getTileEntity(actPos);
            ItemStack mainHandItem = player.getHeldItem(Hand.MAIN_HAND);
            ItemStack offHandItem = player.getHeldItem(Hand.OFF_HAND);

            if(mainHandItem != ItemStack.EMPTY) {

            }
            else {
                if(tile instanceof TileEntityStaffWorkbench) {
                    NetworkHooks.openGui((ServerPlayerEntity) player, (TileEntityStaffWorkbench) tile, actPos);
                }
            }
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {

        return VoxelsStaffWorkbench.getShape(state.get(FACING), state.get(PIECE));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {

        builder.add(FACING).add(PIECE);
    }
}
