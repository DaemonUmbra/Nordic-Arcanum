package com.lordskittles.arcanumapi.common.blockentity;

import com.lordskittles.arcanumapi.common.inventory.NordicItemStackHandler;
import com.lordskittles.arcanumapi.common.network.PacketBase;
import com.lordskittles.arcanumapi.common.network.PacketFluidUpdate;
import com.lordskittles.arcanumapi.common.utilities.MathUtilities;
import com.lordskittles.arcanumapi.core.ArcanumAPI;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class BlockEntityFluidInventory<T extends BlockEntityFluidInventory> extends BlockEntityUpdateable<T> {

    public float prevScale;
    protected final SmartSyncTank tank = new SmartSyncTank(this, getCapacity());

    private NordicItemStackHandler itemHandler = new NordicItemStackHandler(1);
    private final LazyOptional<IItemHandler> itemCap = LazyOptional.of(() -> itemHandler);
    private final LazyOptional<IFluidHandler> fluidCap = LazyOptional.of(() -> this.tank);

    private boolean needsPacket = false;

    public BlockEntityFluidInventory(BlockEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState state) {

        super(tileEntityTypeIn, pos, state);
    }

    public FluidStack getFluid() {

        return this.tank.getFluid();
    }

    public boolean doesRetainFluid() {

        return true;
    }

    public float getRetainCost() {

        return 0;
    }

    public boolean canRetainInventory(Player player) {

        return false;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {

        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.orEmpty(cap, itemCap);
        }
        else
            if(cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
                return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.orEmpty(cap, fluidCap);
            }

        return super.getCapability(cap, side);
    }

    private void shrinkHand(Player player) {

        ItemStack heldItem = player.getInventory().items.get(player.getInventory().selected);
        heldItem.shrink(1);

        player.setItemSlot(EquipmentSlot.MAINHAND, heldItem);
    }

    public abstract int getCapacity();

    @Override
    public void tick() {

        super.tick();
        this.tank.tick();
    }

    @Override
    protected void onServerUpdate() {

        super.onServerUpdate();

        float scale = MathUtilities.getScale(this.prevScale, this.tank);
        if(scale != this.prevScale) {
            this.prevScale = scale;
            this.needsPacket = true;
        }

        if(this.needsPacket) {
            sendUpdatePacket();
            this.needsPacket = false;
        }
    }

    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {

        CompoundTag nbt = new CompoundTag();
        save(nbt);
        return new ClientboundBlockEntityDataPacket(getBlockPos(), 1, nbt);
    }

    @Override
    public CompoundTag getUpdateTag() {

        CompoundTag nbt = super.getUpdateTag();
        save(nbt);
        return nbt;
    }

    @Override
    public CompoundTag save(CompoundTag compound) {

        super.save(compound);

        this.tank.writeToNBT(compound);

        return compound;
    }

    @Override
    public void load(CompoundTag compound) {

        super.load(compound);

        this.tank.readFromNBT(compound);
    }

    public void handleScaleUpdate(CompoundTag nbt, float scale) {

        this.prevScale = scale;
        handleUpdateTag(nbt);
    }

    public void sendUpdatePacket() {

        sendUpdatePacket(this);
    }

    public void sendUpdatePacket(BlockEntity tracking) {

        if(this.level.isClientSide) {
            ArcanumAPI.LOG.warn("Update packet call requested from client side", new Exception());
        }
        else
            if(isRemoved()) {
                ArcanumAPI.LOG.warn("Update packet call requested for removed tile", new Exception());
            }
            else {
                sendPacket(new PacketFluidUpdate(this), tracking);
            }
    }

    protected abstract void sendPacket(PacketBase packet, BlockEntity tracking);
}
