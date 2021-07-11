package com.lordskittles.arcanumapi.common.block;

import com.lordskittles.arcanumapi.common.block.voxelshapes.VoxelsMagicChest;
import com.lordskittles.arcanumapi.common.network.PacketHandlerBase;
import com.lordskittles.arcanumapi.common.tileentity.TileEntityMagicChest;
import com.lordskittles.arcanumapi.common.utilities.ClientUtilities;
import com.lordskittles.arcanumapi.common.utilities.NBTUtilities;
import com.lordskittles.arcanumapi.arcanum.ArcanumServerManager;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTier;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.List;

public abstract class BlockMagicChest<T extends TileEntityMagicChest> extends BlockMod {

    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
    private final int slotCount;

    private final Class<T> tileClass;
    private final String modid;

    public BlockMagicChest(Class<T> tileClass, ItemGroup group, String modid, int slotCount) {

        super(Block.Properties.create(Material.WOOD, MaterialColor.BLACK)
                .hardnessAndResistance(2.5f, 2.5f)
                .sound(SoundType.WOOD)
                .harvestLevel(ItemTier.STONE.getHarvestLevel())
                .harvestTool(ToolType.PICKAXE));

        this.slotCount = slotCount;
        this.tileClass = tileClass;
        this.modid = modid;
        this.group = group;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable IBlockReader world, List<ITextComponent> tooltip, ITooltipFlag flag) {

        CompoundNBT nbt = NBTUtilities.getPersistentData(modid, stack);
        NonNullList list = NonNullList.withSize(slotCount, ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(nbt, list);

        for(int i = 0; i < list.size(); i++) {
            ItemStack item = (ItemStack) list.get(i);

            if(! item.isEmpty()) {
                IFormattableTextComponent component = new StringTextComponent(TextFormatting.GREEN + "");
                component.appendSibling(item.getItem().getName());
                component.appendSibling(new StringTextComponent(TextFormatting.AQUA + " " + ((Integer) item.getCount()).toString()));

                tooltip.add(component);
            }
        }

        super.addInformation(stack, world, tooltip, flag);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {

        switch(state.get(FACING)) {
            case NORTH:
                return VoxelsMagicChest.NORTH.get();
            case EAST:
                return VoxelsMagicChest.EAST.get();
            case SOUTH:
                return VoxelsMagicChest.SOUTH.get();
            case WEST:
                return VoxelsMagicChest.WEST.get();

            default:
                return super.getShape(state, worldIn, pos, context);
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {

        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().rotateY().getOpposite());
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {

        if(! world.isRemote) {
            TileEntity tile = world.getTileEntity(pos);
            if(tileClass.isInstance(tile)) {
                NetworkHooks.openGui((ServerPlayerEntity) player, tileClass.cast(tile), pos);
            }
        }

        return ActionResultType.SUCCESS;
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {

        TileEntity tile = world.getTileEntity(pos);
        if(tileClass.isInstance(tile)) {
            T chest = tileClass.cast(tile);
            CompoundNBT nbt = NBTUtilities.getPersistentData(modid, stack);

            NonNullList list = NonNullList.withSize(chest.getSizeInventory(), ItemStack.EMPTY);
            ItemStackHelper.loadAllItems(nbt, list);

            chest.setItems(list);
        }

        super.onBlockPlacedBy(world, pos, state, placer, stack);
    }

    protected abstract PacketHandlerBase getPacketHandler();

    @Override
    public void onReplaced(BlockState oldState, World world, BlockPos pos, BlockState newState, boolean isMoving) {

        if(oldState != newState) {
            TileEntityMagicChest chest = ClientUtilities.getTileEntity(tileClass, pos);
            if(chest != null) {
                ItemStack stack = oldState.getBlock().getItem(world, pos, oldState);
                PlayerEntity player = world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 15D, null);
                if(chest.canRetainInventory(player)) {
                    CompoundNBT nbt = NBTUtilities.getPersistentData(modid, stack);

                    ItemStackHelper.saveAllItems(nbt, chest.getItems());
                    stack.write(nbt);

                    InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), stack);

                    if(player instanceof ServerPlayerEntity) {
                        try {
                            ArcanumServerManager.useArcanum((ServerPlayerEntity) player, chest.getRetainCost(), getPacketHandler());
                        }
                        catch(Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                else {
                    if(! player.isCreative()) {
                        InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), stack);
                    }
                    InventoryHelper.dropItems(world, pos, tileClass.cast(chest).getItems());
                }
            }
        }
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {

        builder.add(FACING);
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {

        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {

        return state.rotate(mirror.toRotation(state.get(FACING)));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {

        return true;
    }

    public abstract TileEntity createTileEntity(BlockState state, IBlockReader world);
}
