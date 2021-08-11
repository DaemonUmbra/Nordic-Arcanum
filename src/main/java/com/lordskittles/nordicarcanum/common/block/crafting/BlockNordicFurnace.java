package com.lordskittles.nordicarcanum.common.block.crafting;

import com.lordskittles.arcanumapi.common.block.BlockMod;
import com.lordskittles.arcanumapi.common.utilities.BlockEntityHelper;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicItemGroup;
import com.lordskittles.nordicarcanum.common.blockentity.crafting.BlockEntityNordicFurnace;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.fmllegacy.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class BlockNordicFurnace extends BlockMod implements EntityBlock {

    public static final BooleanProperty SMELTING = BooleanProperty.create("smelting");
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public BlockNordicFurnace() {

        super(Block.Properties.of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.5F)
                .lightLevel((state) -> state.getValue(SMELTING) ? 13 : 0));
        this.registerDefaultState(this.getStateDefinition().any().setValue(SMELTING, false).setValue(FACING, Direction.NORTH));

        this.group = NordicItemGroup.INSTANCE;
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {

        if(! world.isClientSide) {
            BlockEntity tile = world.getBlockEntity(pos);
            if(tile instanceof BlockEntityNordicFurnace) {
                NetworkHooks.openGui((ServerPlayer) player, (BlockEntityNordicFurnace) tile, pos);
            }
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {

        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {

        builder.add(SMELTING).add(FACING);
    }

    @Override
    public void animateTick(BlockState stateIn, Level level, BlockPos pos, Random rand) {

        super.animateTick(stateIn, level, pos, rand);

        if(stateIn.getValue(SMELTING)) {

            double d0 = (double)pos.getX() + 0.5D;
            double d1 = (double)pos.getY();
            double d2 = (double)pos.getZ() + 0.5D;
            if (rand.nextDouble() < 0.1D) {
                level.playLocalSound(d0, d1, d2, SoundEvents.FURNACE_FIRE_CRACKLE, SoundSource.BLOCKS, 1.0F, 1.0F, false);
            }

            double offsetX = (rand.nextFloat() * 0.5D) + 0.25D;
            double offsetZ = (rand.nextFloat() * 0.5D) + 0.25D;
            level.addParticle(ParticleTypes.SMOKE, pos.getX() + offsetX, pos.getY() + 1D, pos.getZ() + offsetZ, 0, rand.nextFloat() * 0.05D, 0);

            Direction direction = stateIn.getValue(FACING);
            Direction.Axis direction$axis = direction.getAxis();
            double d3 = 0.52D;
            double d4 = rand.nextDouble() * 0.6D - 0.3D;
            double d5 = direction$axis == Direction.Axis.X ? (double)direction.getStepX() * 0.52D : d4;
            double d6 = rand.nextDouble() * 6.0D / 16.0D;
            double d7 = direction$axis == Direction.Axis.Z ? (double)direction.getStepZ() * 0.52D : d4;
            level.addParticle(ParticleTypes.FLAME, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {

        return new BlockEntityNordicFurnace(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> entityType) {

        return level.isClientSide ? null : BlockEntityHelper.createTickerHelper(entityType, BlockEntityNordicFurnace::furnaceTick);
    }
}
