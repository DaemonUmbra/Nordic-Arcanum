package com.lordskittles.nordicarcanum.common.block.crafting;

import com.lordskittles.arcanumapi.common.block.BlockMod;
import com.lordskittles.arcanumapi.common.block.MultiBlockPiece;
import com.lordskittles.arcanumapi.common.utilities.BlockUtilities;
import com.lordskittles.nordicarcanum.common.block.voxelshapes.VoxelsAlchemyTable;
import com.lordskittles.nordicarcanum.common.registry.Blocks;
import com.lordskittles.nordicarcanum.common.blockentity.crafting.BlockEntityAlchemyTable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fmllegacy.network.NetworkHooks;

import javax.annotation.Nullable;

public class BlockAlchemyTable extends BlockMod {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final EnumProperty<MultiBlockPiece> PIECE = EnumProperty.create("piece", MultiBlockPiece.class);

    public BlockAlchemyTable() {

        super(Block.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
                .strength(3.0F, 3.0F)
                .sound(SoundType.STONE)
                .harvestLevel(Tiers.STONE.getLevel())
                .harvestTool(ToolType.PICKAXE));

        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH).setValue(PIECE, MultiBlockPiece.BOTTOM_LEFT));
    }

    @Override
    public ItemStack getPickBlock(BlockState state, HitResult target, BlockGetter world, BlockPos pos, Player player) {

        MultiBlockPiece piece = state.getValue(PIECE);
        Block block = null;

        switch(piece) {
            case BOTTOM_LEFT:
//                block = Blocks.ancient_norse_brick.get();
                break;
            case BOTTOM_RIGHT:
                block = Blocks.yew_plank.get();
                break;
            case TOP_LEFT:
                block = Blocks.yew_stairs.get();
                break;
            case TOP_RIGHT:
                block = net.minecraft.world.level.block.Blocks.CAULDRON;
                break;
        }

        return new ItemStack(block);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {

        BlockPos right = BlockUtilities.getRightPos(state.getValue(FACING), pos);
        if(! world.isEmptyBlock(right))
            return false;

        if(! world.isEmptyBlock(right.above()))
            return false;

        if(! world.isEmptyBlock(pos.above()))
            return false;

        return true;
    }

    @Override
    public void setPlacedBy(Level world, BlockPos pos, BlockState state, @Nullable LivingEntity entity, ItemStack stack) {

        if(! (entity instanceof Player))
            return;

        Direction direction = state.getValue(FACING);

        BlockPos rightPos = BlockUtilities.getRightPos(direction, pos);
        world.setBlock(rightPos, Blocks.alchemy_table.get().defaultBlockState().setValue(BlockStaffWorkbench.FACING, direction).setValue(PIECE, MultiBlockPiece.BOTTOM_RIGHT), 19);
        world.setBlock(pos.above(), Blocks.alchemy_table.get().defaultBlockState().setValue(BlockStaffWorkbench.FACING, direction).setValue(PIECE, MultiBlockPiece.TOP_LEFT), 19);
        world.setBlock(rightPos.above(), Blocks.alchemy_table.get().defaultBlockState().setValue(BlockStaffWorkbench.FACING, direction).setValue(PIECE, MultiBlockPiece.TOP_RIGHT), 19);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {

        return this.defaultBlockState().setValue(FACING, context.getNearestLookingDirection().getClockWise().getOpposite());
    }

    @Override
    public void playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player) {

        Direction direction = state.getValue(FACING);

        BlockPos horizontalPos = BlockUtilities.getRightPos(direction, pos);
        MultiBlockPiece piece = state.getValue(PIECE);
        if(piece == MultiBlockPiece.BOTTOM_RIGHT || piece == MultiBlockPiece.TOP_RIGHT) {
            horizontalPos = BlockUtilities.getLeftPos(direction, pos);
        }

        world.setBlock(horizontalPos, net.minecraft.world.level.block.Blocks.AIR.defaultBlockState(), 19);

        if(piece == MultiBlockPiece.BOTTOM_RIGHT || piece == MultiBlockPiece.BOTTOM_LEFT) {
            world.setBlock(pos.above(), net.minecraft.world.level.block.Blocks.AIR.defaultBlockState(), 19);
            world.setBlock(horizontalPos.above(), net.minecraft.world.level.block.Blocks.AIR.defaultBlockState(), 19);
        }
        else {
            world.setBlock(pos.below(), net.minecraft.world.level.block.Blocks.AIR.defaultBlockState(), 19);
            world.setBlock(horizontalPos.below(), net.minecraft.world.level.block.Blocks.AIR.defaultBlockState(), 19);
        }

        if(! player.isCreative()) {
            world.addFreshEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Blocks.yew_stairs.get(), 1)));
            world.addFreshEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Blocks.feldspar_brick.get(), 1)));
            world.addFreshEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(net.minecraft.world.level.block.Blocks.CAULDRON, 1)));
            world.addFreshEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Blocks.yew_plank.get(), 1)));
        }

        world.levelEvent(player, 2001, pos, getId(state));
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {

        BlockPos actPos = pos;
        BlockPos leftPos = BlockUtilities.getLeftPos(state.getValue(FACING), pos);

        switch(state.getValue(PIECE)) {
            case TOP_LEFT:
                actPos = pos.below();
                break;
            case TOP_RIGHT:
                actPos = leftPos.below();
                break;
            case BOTTOM_LEFT:
                actPos = pos;
                break;
            case BOTTOM_RIGHT:
                actPos = leftPos;
                break;
        }

        if(! world.isClientSide) {
            BlockEntity block = world.getBlockEntity(actPos);

            if(block instanceof BlockEntityAlchemyTable) {
                NetworkHooks.openGui((ServerPlayer) player, (BlockEntityAlchemyTable) block, actPos);
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {

        return VoxelsAlchemyTable.getShape(state.getValue(FACING), state.getValue(PIECE));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {

        builder.add(FACING).add(PIECE);
    }
}
