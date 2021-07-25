package com.lordskittles.arcanumapi.common.block;

import com.lordskittles.arcanumapi.common.block.voxelshapes.VoxelsMagicChest;
import com.lordskittles.arcanumapi.common.network.PacketHandlerBase;
import com.lordskittles.arcanumapi.common.tileentity.TileEntityMagicChest;
import com.lordskittles.arcanumapi.common.utilities.ClientUtilities;
import com.lordskittles.arcanumapi.common.utilities.NBTUtilities;
import com.lordskittles.arcanumapi.arcanum.ArcanumServerManager;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Containers;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tiers;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.util.*;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.ChatFormatting;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fmllegacy.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.List;

import net.minecraft.core.NonNullList;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;

public abstract class BlockMagicChest<T extends TileEntityMagicChest> extends BlockMod {

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    private final int slotCount;

    private final Class<T> tileClass;
    private final String modid;

    public BlockMagicChest(Class<T> tileClass, CreativeModeTab group, String modid, int slotCount) {

        super(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_BLACK)
                .strength(2.5f, 2.5f)
                .sound(SoundType.WOOD)
                .harvestLevel(Tiers.STONE.getLevel())
                .harvestTool(ToolType.PICKAXE));

        this.slotCount = slotCount;
        this.tileClass = tileClass;
        this.modid = modid;
        this.group = group;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter world, List<Component> tooltip, TooltipFlag flag) {

        CompoundTag nbt = NBTUtilities.getPersistentData(modid, stack);
        NonNullList list = NonNullList.withSize(slotCount, ItemStack.EMPTY);
        ContainerHelper.loadAllItems(nbt, list);

        for(int i = 0; i < list.size(); i++) {
            ItemStack item = (ItemStack) list.get(i);

            if(! item.isEmpty()) {
                MutableComponent component = new TextComponent(ChatFormatting.GREEN + "");
                component.append(item.getItem().getDescription());
                component.append(new TextComponent(ChatFormatting.AQUA + " " + ((Integer) item.getCount()).toString()));

                tooltip.add(component);
            }
        }

        super.appendHoverText(stack, world, tooltip, flag);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {

        switch(state.getValue(FACING)) {
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
    public BlockState getStateForPlacement(BlockPlaceContext context) {

        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getClockWise().getOpposite());
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {

        if(! world.isClientSide) {
            BlockEntity tile = world.getBlockEntity(pos);
            if(tileClass.isInstance(tile)) {
                NetworkHooks.openGui((ServerPlayer) player, tileClass.cast(tile), pos);
            }
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public void setPlacedBy(Level world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {

        BlockEntity tile = world.getBlockEntity(pos);
        if(tileClass.isInstance(tile)) {
            T chest = tileClass.cast(tile);
            CompoundTag nbt = NBTUtilities.getPersistentData(modid, stack);

            NonNullList list = NonNullList.withSize(chest.getContainerSize(), ItemStack.EMPTY);
            ContainerHelper.loadAllItems(nbt, list);

            chest.setItems(list);
        }

        super.setPlacedBy(world, pos, state, placer, stack);
    }

    protected abstract PacketHandlerBase getPacketHandler();

    @Override
    public void onRemove(BlockState oldState, Level world, BlockPos pos, BlockState newState, boolean isMoving) {

        if(oldState != newState) {
            TileEntityMagicChest chest = ClientUtilities.getTileEntity(tileClass, pos);
            if(chest != null) {
                ItemStack stack = oldState.getBlock().getCloneItemStack(world, pos, oldState);
                Player player = world.getNearestPlayer(pos.getX(), pos.getY(), pos.getZ(), 15D, null);
                if(chest.canRetainInventory(player)) {
                    CompoundTag nbt = NBTUtilities.getPersistentData(modid, stack);

                    ContainerHelper.saveAllItems(nbt, chest.getItems());
                    stack.save(nbt);

                    Containers.dropItemStack(world, pos.getX(), pos.getY(), pos.getZ(), stack);

                    if(player instanceof ServerPlayer) {
                        try {
                            ArcanumServerManager.useArcanum((ServerPlayer) player, chest.getRetainCost(), getPacketHandler());
                        }
                        catch(Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                else {
                    if(! player.isCreative()) {
                        Containers.dropItemStack(world, pos.getX(), pos.getY(), pos.getZ(), stack);
                    }
                    Containers.dropContents(world, pos, tileClass.cast(chest).getItems());
                }
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {

        builder.add(FACING);
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {

        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {

        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    public abstract BlockEntity createBlockEntity(BlockState state, BlockGetter world);
}
