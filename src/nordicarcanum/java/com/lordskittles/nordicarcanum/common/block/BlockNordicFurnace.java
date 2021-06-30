package com.lordskittles.nordicarcanum.common.block;

import com.lordskittles.arcanumapi.common.block.BlockMod;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicItemGroup;
import com.lordskittles.nordicarcanum.common.registry.TileEntities;
import com.lordskittles.nordicarcanum.common.tileentity.TileEntityNordicFurnace;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class BlockNordicFurnace extends BlockMod
{
    public static final BooleanProperty SMELTING = BooleanProperty.create("smelting");
    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

    public BlockNordicFurnace()
    {
        super(Block.Properties.create(Material.ROCK)
                .setRequiresTool()
                .hardnessAndResistance(3.5F)
                .setLightLevel((state)-> state.get(SMELTING) ? 13 : 0));
        this.setDefaultState(this.getStateContainer().getBaseState().with(SMELTING, false).with(FACING, Direction.NORTH));

        this.group = NordicItemGroup.INSTANCE;
    }

    @Override
    public boolean hasTileEntity(BlockState state)
    {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world)
    {
        return TileEntities.nordic_furnace.get().create();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit)
    {
        if (!world.isRemote)
        {
            TileEntity tile = world.getTileEntity(pos);
            if (tile instanceof TileEntityNordicFurnace)
            {
                NetworkHooks.openGui((ServerPlayerEntity)player, (TileEntityNordicFurnace)tile, pos);
            }
        }

        return ActionResultType.SUCCESS;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(SMELTING).add(FACING);
    }
}
