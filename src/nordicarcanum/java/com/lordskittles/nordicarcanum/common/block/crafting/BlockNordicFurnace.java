package com.lordskittles.nordicarcanum.common.block.crafting;

import com.lordskittles.arcanumapi.common.block.BlockMod;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicItemGroup;
import com.lordskittles.nordicarcanum.common.registry.TileEntities;
import com.lordskittles.nordicarcanum.common.tileentity.crafting.TileEntityNordicFurnace;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockNordicFurnace extends BlockMod {

    public static final BooleanProperty SMELTING = BooleanProperty.create("smelting");
    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

    public BlockNordicFurnace() {

        super(Block.Properties.create(Material.ROCK)
                .setRequiresTool()
                .hardnessAndResistance(3.5F)
                .setLightLevel((state) -> state.get(SMELTING) ? 13 : 0));
        this.setDefaultState(this.getStateContainer().getBaseState().with(SMELTING, false).with(FACING, Direction.NORTH));

        this.group = NordicItemGroup.INSTANCE;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {

        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {

        return TileEntities.nordic_furnace.get().create();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {

        if(! world.isRemote) {
            TileEntity tile = world.getTileEntity(pos);
            if(tile instanceof TileEntityNordicFurnace) {
                NetworkHooks.openGui((ServerPlayerEntity) player, (TileEntityNordicFurnace) tile, pos);
            }
        }

        return ActionResultType.SUCCESS;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {

        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {

        builder.add(SMELTING).add(FACING);
    }

    @Override
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {

        super.animateTick(stateIn, worldIn, pos, rand);

        if(stateIn.get(SMELTING)) {

            double d0 = (double)pos.getX() + 0.5D;
            double d1 = (double)pos.getY();
            double d2 = (double)pos.getZ() + 0.5D;
            if (rand.nextDouble() < 0.1D) {
                worldIn.playSound(d0, d1, d2, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
            }

            double offsetX = (rand.nextFloat() * 0.5D) + 0.25D;
            double offsetZ = (rand.nextFloat() * 0.5D) + 0.25D;
            worldIn.addParticle(ParticleTypes.SMOKE, pos.getX() + offsetX, pos.getY() + 1D, pos.getZ() + offsetZ, 0, rand.nextFloat() * 0.05D, 0);

            Direction direction = stateIn.get(FACING);
            Direction.Axis direction$axis = direction.getAxis();
            double d3 = 0.52D;
            double d4 = rand.nextDouble() * 0.6D - 0.3D;
            double d5 = direction$axis == Direction.Axis.X ? (double)direction.getXOffset() * 0.52D : d4;
            double d6 = rand.nextDouble() * 6.0D / 16.0D;
            double d7 = direction$axis == Direction.Axis.Z ? (double)direction.getZOffset() * 0.52D : d4;
            worldIn.addParticle(ParticleTypes.FLAME, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
        }
    }
}
