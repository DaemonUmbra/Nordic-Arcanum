package com.lordskittles.arcanumapi.common.blockentity;

import com.lordskittles.arcanumapi.common.utilities.InventoryUtilities;
import com.lordskittles.arcanumapi.common.utilities.NBTUtilities;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Nameable;

public abstract class BlockEntityInventory<T extends BlockEntityInventory<T>> extends BlockEntityUpdateable<T> implements Container, MenuProvider, Nameable {

    protected int numPlayersUsing;

    @Nullable
    protected ResourceLocation lootTable;
    protected long lootTableSeed;

    protected NonNullList<ItemStack> contents;
    protected IItemHandlerModifiable items = InventoryUtilities.createHandler(this);
    protected LazyOptional<IItemHandlerModifiable> itemHandler = LazyOptional.of(() -> items);

    private final int size;
    private final Component title;
    private final String modid;

    public BlockEntityInventory(BlockEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState state, int size, String containerId, String modid) {

        super(tileEntityTypeIn, pos, state);

        this.size = size;
        this.title = new TranslatableComponent("container." + containerId);
        this.contents = NonNullList.withSize(this.size, ItemStack.EMPTY);
        this.modid = modid;
    }

    public static void swapContents(BlockEntityInventory tile, BlockEntityInventory other) {

        NonNullList<ItemStack> list = tile.getItems();
        tile.setItems(other.getItems());
        other.setItems(list);
    }

    public static int getPlayersUsing(BlockGetter reader, BlockPos pos) {

        BlockState state = reader.getBlockState(pos);
        if(state.hasBlockEntity()) {
            BlockEntity tile = reader.getBlockEntity(pos);
            if(tile instanceof BlockEntityInventory) {
                return ((BlockEntityInventory) tile).numPlayersUsing;
            }
        }

        return 0;
    }

    public void setItems(NonNullList<ItemStack> items) {

        this.contents = items;
    }

    public NonNullList<ItemStack> getItems() {

        return this.contents;
    }

    @Override
    public int getContainerSize() {

        return size;
    }

    @Override
    public boolean isEmpty() {

        return this.getItems().stream().allMatch(ItemStack::isEmpty);
    }

    protected void onSlotChanged() {}

    @Override
    public ItemStack getItem(int index) {

        return this.contents.get(index);
    }

    @Override
    public void setItem(int index, ItemStack stack) {

        this.contents.set(index, stack);

        onSlotChanged();
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
    public boolean stillValid(Player player) {

        if(this.level.getBlockEntity(this.worldPosition) != this) {
            return false;
        }

        return ! (player.distanceToSqr((double) this.worldPosition.getX() + 0.5D, (double) this.worldPosition.getY() + 0.5D, (double) this.worldPosition.getZ() + 0.5D) > 64.0D);
    }

    @Override
    public void clearContent() {

        this.contents.clear();
    }

    @Override
    public Component getName() {

        return this.title;
    }

    @Override
    public Component getDisplayName() {

        return getName();
    }

    @Nullable
    @Override
    public abstract AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player player);

    @Override
    public void setRemoved() {

        super.setRemoved();

        if(itemHandler != null) {
            itemHandler.invalidate();
        }
    }

    @Override
    public void load(CompoundTag compound) {

        super.load(compound);

        CompoundTag base = NBTUtilities.getPersistentData(modid, compound);

        this.contents = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);

        if(! this.checkLootAndRead(compound)) {
            ContainerHelper.loadAllItems(base, this.contents);
        }
    }

    @Override
    public CompoundTag save(CompoundTag compound) {

        super.save(compound);

        CompoundTag base = NBTUtilities.getPersistentData(modid, compound);

        if(! this.checkLootAndWrite(compound)) {
            ContainerHelper.loadAllItems(base, this.contents);
        }

        return compound;
    }

    @Override
    public boolean triggerEvent(int id, int type) {

        if(id == 1) {
            this.numPlayersUsing = type;
            return true;
        }

        return super.triggerEvent(id, type);
    }

//    @Override
//    public void clearCache() {
//
//        super.clearCache();
//
//        if(this.itemHandler != null) {
//            this.itemHandler.invalidate();
//            this.itemHandler = null;
//        }
//    }

    @Override
    public <T> LazyOptional getCapability(@Nonnull Capability<T> capability, @Nonnull Direction side) {

        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return itemHandler.cast();
        }

        return super.getCapability(capability, side);
    }

    @Override
    public void startOpen(Player player) {

        if(! player.isSpectator()) {
            if(this.numPlayersUsing < 0) {
                this.numPlayersUsing = 0;
            }

            ++ this.numPlayersUsing;
            this.onOpenOrClose();
        }
    }

    @Override
    public void stopOpen(Player player) {

        if(! player.isSpectator()) {
            -- this.numPlayersUsing;
            this.onOpenOrClose();
        }
    }

    public void fillWithLoot(@Nullable Player player) {

        if(this.lootTable != null && this.level.getServer() != null) {
            LootTable loottable = this.level.getServer().getLootTables().get(this.lootTable);
            this.lootTable = null;
            LootContext.Builder lootcontext$builder = (new LootContext.Builder((ServerLevel) this.level)).withParameter(LootContextParams.ORIGIN, new Vec3(this.worldPosition.getX(), this.worldPosition.getY(), this.worldPosition.getZ())).withOptionalRandomSeed(this.lootTableSeed);
            if(player != null) {
                lootcontext$builder.withLuck(player.getLuck()).withParameter(LootContextParams.THIS_ENTITY, player);
            }

            loottable.fill(this, lootcontext$builder.create(LootContextParamSets.CHEST));
        }
    }

    public void setLootTable(ResourceLocation lootTableIn, long seedIn) {

        this.lootTable = lootTableIn;
        this.lootTableSeed = seedIn;
    }

    protected boolean checkLootAndRead(CompoundTag compound) {

        if(compound.contains("LootTable", 8)) {
            this.lootTable = new ResourceLocation(compound.getString("LootTable"));
            this.lootTableSeed = compound.getLong("LootTableSeed");
            return true;
        }
        else {
            return false;
        }
    }

    protected boolean checkLootAndWrite(CompoundTag compound) {

        if(this.lootTable == null) {
            return false;
        }
        else {
            compound.putString("LootTable", this.lootTable.toString());
            if(this.lootTableSeed != 0L) {
                compound.putLong("LootTableSeed", this.lootTableSeed);
            }

            return true;
        }
    }

    protected void onOpenOrClose() {

    }

    protected void playSound(SoundEvent sound) {

        double dx = (double) this.worldPosition.getX() + 0.5D;
        double dy = (double) this.worldPosition.getY() + 0.5D;
        double dz = (double) this.worldPosition.getZ() + 0.5D;

        this.level.playSound((Player) null, dx, dy, dz, sound, SoundSource.BLOCKS, 0.5f, this.level.random.nextFloat() * 0.1f + 0.9f);
    }
}
