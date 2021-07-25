package com.lordskittles.nordicarcanum.common.tileentity.crafting;

import com.lordskittles.arcanumapi.common.network.PacketBase;
import com.lordskittles.arcanumapi.common.tileentity.SmartSyncTank;
import com.lordskittles.arcanumapi.common.tileentity.TileEntityFluidInventory;
import com.lordskittles.arcanumapi.common.utilities.InventoryUtilities;
import com.lordskittles.arcanumapi.common.utilities.ItemUtilities;
import com.lordskittles.arcanumapi.common.utilities.NBTUtilities;
import com.lordskittles.nordicarcanum.common.inventory.crafting.ArcaneInfuserRecipe;
import com.lordskittles.nordicarcanum.common.registry.RecipeType;
import com.lordskittles.nordicarcanum.common.registry.TileEntities;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicFluidValues;
import com.lordskittles.nordicarcanum.core.NordicTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandlerModifiable;

public class TileEntityArcaneInfuser extends TileEntityFluidInventory<TileEntityArcaneInfuser> implements Container {

    private ArcaneInfuserRecipe recipe = null;

    private int progress = 0;

    protected NonNullList<ItemStack> contents = NonNullList.withSize(1, ItemStack.EMPTY);
    protected IItemHandlerModifiable items = InventoryUtilities.createHandler(this);
    protected LazyOptional<IItemHandlerModifiable> itemHandler = LazyOptional.of(() -> items);

    public TileEntityArcaneInfuser(BlockPos pos, BlockState state) {

        super(TileEntities.arcane_infuser.get(), pos, state);
    }

    private ArcaneInfuserRecipe getRecipe(ItemStack input, FluidStack fluidInput) {

        return RecipeType.arcane_infuser.findFirst(level, recipe -> recipe.matches(input, fluidInput));
    }

    public boolean setHeldItem(ItemStack stack) {

        if(! this.getHeldItem().isEmpty())
            return false;

        setItem(0, ItemUtilities.singleDeepCopy(stack));
        sendUpdatePacket();

        return true;
    }

    public ItemStack getHeldItem() {

        return this.contents.get(0);
    }

    public ItemStack removeItems() {

        if(this.getHeldItem() == ItemStack.EMPTY)
            return ItemStack.EMPTY;

        ItemStack stack = ItemUtilities.deepCopy(getHeldItem());

        resetItem();

        return stack;
    }

    @Override
    public boolean doesRetainFluid() {

        return false;
    }

    @Override
    public void onServerUpdate() {

        super.onServerUpdate();
        if(this.recipe == null) {
            this.recipe = getRecipe(getHeldItem(), this.tank.getFluid());
        }

        if(canProgress()) {
            updateProgress();
        }
    }

    private boolean isAboveHeatSource() {

        BlockState below = this.level.getBlockState(getBlockPos().below());
        return NordicTags.IsBlockInTag(below, NordicTags.HEAT_SOURCE);
    }

    public boolean canProgress() {

        if(this.getHeldItem() == ItemStack.EMPTY)
            return false;

        if(this.tank.isEmpty())
            return false;

        if(this.recipe == null)
            return false;

        if(! isAboveHeatSource())
            return false;

        return this.tank.getFluidAmount() >= this.recipe.fluidIngredient.getAmount() && this.getHeldItem().getCount() >= this.recipe.ingredient.getItems()[0].getCount();
    }

    public void updateProgress() {

        if(! canProgress())
            return;

        this.progress++;
        ItemStack output = ItemStack.EMPTY;

        if(this.progress >= this.recipe.time) {
            output = this.recipe.getRecipeOutput();
        }

        if(output != ItemStack.EMPTY) {
            BlockPos pos = getBlockPos().above();
            this.level.addFreshEntity(new ItemEntity(this.level, pos.getX(), pos.getY(), pos.getZ(), output));
            resetItem();
            this.tank.drain(this.recipe.fluidIngredient.getAmount(), IFluidHandler.FluidAction.EXECUTE);
            this.recipe = null;
            this.setChanged();
        }
    }

    public void resetItem() {

        setItem(0, ItemStack.EMPTY);
        this.progress = 0;
    }

    @Override
    public int getCapacity() {

        return NordicFluidValues.ARCANE_INFUSER;
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket packet) {

        SmartSyncTank tank = this.tank;
        CompoundTag nbt = packet.getTag();
        super.onDataPacket(net, packet);
        load(nbt);

        if(this.level != null && this.level.isClientSide) {
            if(! this.tank.equals(tank)) {
                this.level.blockEntityChanged(getBlockPos());
            }
        }
    }

    @Override
    public CompoundTag save(CompoundTag compound) {

        super.save(compound);

        CompoundTag base = NBTUtilities.getPersistentData(NordicArcanum.MODID, compound);
        ContainerHelper.saveAllItems(base, this.contents);

        return compound;
    }

    @Override
    public void load(CompoundTag compound) {

        super.load(compound);

        CompoundTag base = NBTUtilities.getPersistentData(NordicArcanum.MODID, compound);
        ContainerHelper.loadAllItems(base, this.contents);
    }

    @Override
    protected void sendPacket(PacketBase packet, BlockEntity tracking) {

        NordicArcanum.PACKET_HANDLER.sendToAllTracking(packet, tracking);
    }

    public NonNullList<ItemStack> getItems() {

        return this.contents;
    }

    @Override
    public int getContainerSize() {

        return 1;
    }

    @Override
    public boolean isEmpty() {

        return this.getItems().stream().allMatch(ItemStack::isEmpty);
    }

    @Override
    public ItemStack getItem(int index) {

        return this.contents.get(index);
    }

    @Override
    public ItemStack removeItem(int index, int count) {

        ItemStack stack = ContainerHelper.removeItem(this.contents, index, count);
        if(! stack.isEmpty()) {
            this.setChanged();
        }

        return stack;
    }

    @Override
    public ItemStack removeItemNoUpdate(int index) {

        return ContainerHelper.takeItem(this.contents, index);
    }

    @Override
    public void setItem(int index, ItemStack stack) {

        this.contents.set(index, stack);
        sendUpdatePacket();
    }

    @Override
    public boolean stillValid(Player player) {

        if(this.level.getBlockEntity(this.getBlockPos()) != this) {
            return false;
        }

        return ! (player.distanceToSqr((double) this.getBlockPos().getX() + 0.5D, (double) this.getBlockPos().getY() + 0.5D, (double) this.getBlockPos().getZ() + 0.5D) > 64.0D);
    }

    @Override
    public void clearContent() {

        this.contents.clear();
    }
}
