package com.lordskittles.arcanumapi.common.block;

import com.lordskittles.arcanumapi.common.network.PacketHandlerBase;
import com.lordskittles.arcanumapi.common.tileentity.TileEntityFluidInventory;
import com.lordskittles.arcanumapi.common.utilities.ClientUtilities;
import com.lordskittles.arcanumapi.common.utilities.FluidUtilities;
import com.lordskittles.arcanumapi.common.utilities.NBTUtilities;
import com.lordskittles.arcanumapi.magic.ArcanumServerManager;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockDisplayReader;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;
import java.util.List;

public abstract class BlockTank<T extends TileEntityFluidInventory> extends BlockMod implements IItemBlockOverride
{
    private final Class<T> tileClass;
    private final String modid;
    private final PacketHandlerBase packetHandler;

    public BlockTank(SoundType sound, ItemGroup group, Class<T> tileClass, String modid, PacketHandlerBase packetHandler)
    {
        super(Block.Properties.create(Material.ROCK, MaterialColor.STONE)
                .hardnessAndResistance(3.0F, 3.0F)
                .sound(sound)
                .harvestLevel(ItemTier.STONE.getHarvestLevel())
                .harvestTool(ToolType.PICKAXE));

        this.tileClass = tileClass;
        this.modid = modid;
        this.packetHandler = packetHandler;
        this.group = group;
    }

    public abstract TileEntity createTileEntity(BlockState state, IBlockReader world);

    @Override
    public void addInformation(ItemStack stack, @Nullable IBlockReader world, List<ITextComponent> tooltip, ITooltipFlag flag)
    {
        CompoundNBT nbt = NBTUtilities.getPersistentData(modid, stack);
        FluidStack fluid = FluidStack.loadFluidStackFromNBT(nbt);

        if (!fluid.isEmpty())
        {
            IFormattableTextComponent component = new StringTextComponent(TextFormatting.GREEN + "");
            component.appendSibling(fluid.getDisplayName());
            component.appendSibling(new StringTextComponent(TextFormatting.AQUA + " " + ((Integer) fluid.getAmount()).toString()));

            tooltip.add(component);
        }

        super.addInformation(stack, world, tooltip, flag);
    }

    @Override
    public void onReplaced(BlockState oldState, World world, BlockPos pos, BlockState newState, boolean isMoving)
    {
        if (oldState != newState)
        {
            TileEntityFluidInventory tank = ClientUtilities.getTileEntity(tileClass, pos);
            if (tank != null)
            {
                PlayerEntity player = world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 15D, null);
                ItemStack stack = oldState.getBlock().getItem(world, pos, oldState);
                if (tank.canRetainInventory(player))
                {
                    CompoundNBT nbt = NBTUtilities.getPersistentData(modid, stack);

                    FluidUtilities.saveFluid(nbt, stack, tank.getFluid());

                    InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), stack);

                    if (player instanceof ServerPlayerEntity)
                    {
                        try
                        {
                            ArcanumServerManager.useArcanum((ServerPlayerEntity) player, tank.getRetainCost(), packetHandler);
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
                else
                {
                    if (!player.isCreative())
                    {
                        InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), stack);
                    }
                }
            }
        }
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit)
    {
        TileEntity tile = world.getTileEntity(pos);

        if (!world.isRemote)
        {
            if (tile instanceof TileEntityFluidInventory)
            {
                onTileActivated(world, player, (TileEntityFluidInventory)tile, pos, hand);

                if (FluidUtilities.tryFluidInsert(tile, null, player, hand))
                {
                    world.playSound(null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0f, 1.0f);
                    return ActionResultType.SUCCESS;
                }
                else if (FluidUtilities.tryFluidExtract(tile, null, player, hand))
                {
                    world.playSound(null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0f, 1.0f);
                    return ActionResultType.SUCCESS;
                }
            }
        }

        return ActionResultType.SUCCESS;
    }

    protected void onTileActivated(World world, PlayerEntity player, TileEntityFluidInventory tile, BlockPos pos, Hand hand)
    {

    }

//    @Override
//    public boolean isNormalCube(BlockState state, IBlockReader world, BlockPos pos)
//    {
//        return false;
//    }

    @Override
    public boolean hasTileEntity(BlockState state)
    {
        return true;
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos)
    {
        int ambientLight = 0;
        TileEntityFluidInventory tile = (TileEntityFluidInventory) world.getTileEntity(pos);
        if (tile != null)
        {
            FluidStack fluid = tile.getFluid();
            if (!fluid.isEmpty())
            {
                FluidAttributes fluidAttributes = fluid.getFluid().getAttributes();
                ambientLight = Math.max(ambientLight, world instanceof IBlockDisplayReader ? fluidAttributes.getLuminosity((IBlockDisplayReader) world, pos)
                        : fluidAttributes.getLuminosity(fluid));
            }
        }
        return ambientLight;
    }

    public abstract BlockItem getOverride();
}
