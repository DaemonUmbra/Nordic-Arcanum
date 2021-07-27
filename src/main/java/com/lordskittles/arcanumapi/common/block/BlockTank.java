package com.lordskittles.arcanumapi.common.block;

import com.lordskittles.arcanumapi.common.network.PacketHandlerBase;
import com.lordskittles.arcanumapi.common.blockentity.BlockEntityFluidInventory;
import com.lordskittles.arcanumapi.common.utilities.ClientUtilities;
import com.lordskittles.arcanumapi.common.utilities.FluidUtilities;
import com.lordskittles.arcanumapi.common.utilities.NBTUtilities;
import com.lordskittles.arcanumapi.arcanum.ArcanumServerManager;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Containers;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tiers;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.ChatFormatting;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;
import java.util.List;

public abstract class BlockTank<T extends BlockEntityFluidInventory> extends BlockMod implements IItemBlockOverride {

    private final Class<T> blockClass;
    private final String modid;
    private final PacketHandlerBase packetHandler;

    public BlockTank(SoundType sound, CreativeModeTab group, Class<T> blockClass, String modid, PacketHandlerBase packetHandler) {

        super(Block.Properties.of(Material.STONE, MaterialColor.STONE)
                .strength(3.0F, 3.0F)
                .sound(sound)
                .harvestLevel(Tiers.STONE.getLevel())
                .harvestTool(ToolType.PICKAXE));

        this.blockClass = blockClass;
        this.modid = modid;
        this.packetHandler = packetHandler;
        this.group = group;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter world, List<Component> tooltip, TooltipFlag flag) {

        CompoundTag nbt = NBTUtilities.getPersistentData(modid, stack);
        FluidStack fluid = FluidStack.loadFluidStackFromNBT(nbt);

        if(! fluid.isEmpty()) {
            MutableComponent component = new TextComponent(ChatFormatting.GREEN + "");
            component.append(fluid.getDisplayName());
            component.append(new TextComponent(ChatFormatting.AQUA + " " + ((Integer) fluid.getAmount()).toString()));

            tooltip.add(component);
        }

        super.appendHoverText(stack, world, tooltip, flag);
    }

    @Override
    public void onRemove(BlockState oldState, Level world, BlockPos pos, BlockState newState, boolean isMoving) {

        if(oldState != newState) {
            BlockEntityFluidInventory tank = ClientUtilities.getTileEntity(blockClass, pos);
            if(tank != null) {
                Player player = world.getNearestPlayer(pos.getX(), pos.getY(), pos.getZ(), 15D, null);
                ItemStack stack = oldState.getBlock().getCloneItemStack(world, pos, oldState);
                if(tank.canRetainInventory(player)) {
                    CompoundTag nbt = NBTUtilities.getPersistentData(modid, stack);

                    FluidUtilities.saveFluid(nbt, stack, tank.getFluid());

                    Containers.dropItemStack(world, pos.getX(), pos.getY(), pos.getZ(), stack);

                    if(player instanceof ServerPlayer) {
                        try {
                            ArcanumServerManager.useArcanum((ServerPlayer) player, tank.getRetainCost(), packetHandler);
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
                }
            }
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {

        BlockEntity entity = world.getBlockEntity(pos);

        if(! world.isClientSide) {
            if(entity instanceof BlockEntityFluidInventory) {
                onTileActivated(world, player, (BlockEntityFluidInventory) entity, pos, hand);

                if(FluidUtilities.tryFluidInsert(entity, null, player, hand)) {
                    world.playSound(null, pos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0f, 1.0f);
                    return InteractionResult.SUCCESS;
                }
                else
                    if(FluidUtilities.tryFluidExtract(entity, null, player, hand)) {
                        world.playSound(null, pos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0f, 1.0f);
                        return InteractionResult.SUCCESS;
                    }
            }
        }

        return InteractionResult.SUCCESS;
    }

    protected void onTileActivated(Level world, Player player, BlockEntityFluidInventory tile, BlockPos pos, InteractionHand hand) {

    }

//    @Override
//    public boolean isNormalCube(BlockState state, IBlockReader world, BlockPos pos)
//    {
//        return false;
//    }

    @Override
    public int getLightEmission(BlockState state, BlockGetter world, BlockPos pos) {

        int ambientLight = 0;
        BlockEntityFluidInventory tile = (BlockEntityFluidInventory) world.getBlockEntity(pos);
        if(tile != null) {
            FluidStack fluid = tile.getFluid();
            if(! fluid.isEmpty()) {
                FluidAttributes fluidAttributes = fluid.getFluid().getAttributes();
                ambientLight = Math.max(ambientLight, world instanceof BlockAndTintGetter ? fluidAttributes.getLuminosity((BlockAndTintGetter) world, pos)
                                                                                           : fluidAttributes.getLuminosity(fluid));
            }
        }
        return ambientLight;
    }

    public abstract BlockItem getOverride();
}
