package com.lordskittles.arcanumapi.common.tileentity;

import com.lordskittles.arcanumapi.common.inventory.NordicItemStackHandler;
import com.lordskittles.arcanumapi.common.network.PacketBase;
import com.lordskittles.arcanumapi.common.network.PacketFluidUpdate;
import com.lordskittles.arcanumapi.common.utilities.MathUtilities;
import com.lordskittles.arcanumapi.core.ArcanumAPI;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class TileEntityFluidInventory<T extends TileEntityFluidInventory> extends TileEntityUpdateable<T> {

    public float prevScale;
    protected final SmartSyncTank tank = new SmartSyncTank(this, getCapacity());

    private NordicItemStackHandler itemHandler = new NordicItemStackHandler(1);
    private final LazyOptional<IItemHandler> itemCap = LazyOptional.of(() -> itemHandler);
    private final LazyOptional<IFluidHandler> fluidCap = LazyOptional.of(() -> this.tank);

    private boolean needsPacket = false;

    public TileEntityFluidInventory(TileEntityType<?> tileEntityTypeIn) {

        super(tileEntityTypeIn);
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

    public boolean canRetainInventory(PlayerEntity player) {

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

    private void shrinkHand(PlayerEntity player) {

        ItemStack heldItem = player.inventory.mainInventory.get(player.inventory.currentItem);
        heldItem.shrink(1);

        player.setItemStackToSlot(EquipmentSlotType.MAINHAND, heldItem);
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
    public SUpdateTileEntityPacket getUpdatePacket() {

        CompoundNBT nbt = new CompoundNBT();
        write(nbt);
        return new SUpdateTileEntityPacket(getPos(), 1, nbt);
    }

    @Override
    public CompoundNBT getUpdateTag() {

        CompoundNBT nbt = super.getUpdateTag();
        write(nbt);
        return nbt;
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {

        super.write(compound);

        this.tank.writeToNBT(compound);

        return compound;
    }

    @Override
    public void read(BlockState state, CompoundNBT compound) {

        super.read(state, compound);

        this.tank.readFromNBT(compound);
    }

    public void handleScaleUpdate(CompoundNBT nbt, float scale) {

        this.prevScale = scale;
        handleUpdateTag(getBlockState(), nbt);
    }

    public void sendUpdatePacket() {

        sendUpdatePacket(this);
    }

    public void sendUpdatePacket(TileEntity tracking) {

        if(this.world.isRemote) {
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

    protected abstract void sendPacket(PacketBase packet, TileEntity tracking);
}
