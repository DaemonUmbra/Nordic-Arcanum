package com.lordskittles.nordicarcanum.common.tileentity.crafting;

import com.google.common.collect.Maps;
import com.lordskittles.arcanumapi.common.tileentity.TileEntityInventorySyncable;
import com.lordskittles.nordicarcanum.common.block.crafting.BlockNordicFurnace;
import com.lordskittles.nordicarcanum.common.inventory.containers.ContainerNordicFurnace;
import com.lordskittles.nordicarcanum.common.inventory.crafting.NordicFurnaceRecipe;
import com.lordskittles.nordicarcanum.common.registry.RecipeType;
import com.lordskittles.nordicarcanum.common.registry.TileEntities;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicInventorySlots;
import com.lordskittles.nordicarcanum.core.NordicNames;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IRecipeHelperPopulator;
import net.minecraft.inventory.IRecipeHolder;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.IIntArray;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.ForgeHooks;

import javax.annotation.Nullable;
import java.util.Map;

public class TileEntityNordicFurnace extends TileEntityInventorySyncable<TileEntityNordicFurnace> implements IRecipeHolder, IRecipeHelperPopulator {

    protected int burnTime;
    protected int cookTime;
    protected int cookTimeTotal;
    protected int recipesUsed;

    private static final int FUEL_SLOT = 2;
    private static final int OUTPUT_SLOT = 3;

    private final Map<ResourceLocation, Integer> recipes = Maps.newHashMap();

    protected final IIntArray furnaceData = new IIntArray() {

        public int get(int index) {

            switch(index) {
                case 0:
                    return TileEntityNordicFurnace.this.burnTime;
                case 1:
                    return TileEntityNordicFurnace.this.recipesUsed;
                case 2:
                    return TileEntityNordicFurnace.this.cookTime;
                case 3:
                    return TileEntityNordicFurnace.this.cookTimeTotal;
                default:
                    return 0;
            }
        }

        public void set(int index, int value) {

            switch(index) {
                case 0:
                    TileEntityNordicFurnace.this.burnTime = value;
                    break;
                case 1:
                    TileEntityNordicFurnace.this.recipesUsed = value;
                    break;
                case 2:
                    TileEntityNordicFurnace.this.cookTime = value;
                    break;
                case 3:
                    TileEntityNordicFurnace.this.cookTimeTotal = value;
            }

        }

        public int size() {

            return 4;
        }
    };

    public TileEntityNordicFurnace(TileEntityType<?> type) {

        super(type, NordicInventorySlots.NORDIC_FURNACE, NordicNames.NORDIC_FURNACE, NordicArcanum.MODID);
    }

    public TileEntityNordicFurnace() {

        this(TileEntities.nordic_furnace.get());
    }

    private NordicFurnaceRecipe getRecipeFor(ItemStack left, ItemStack right) {

        return RecipeType.nordic_furnace.findFirst(world, recipe ->
        {
            return recipe.matches(new ItemStack[] { left, right });
        });
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {

        super.write(compound);

        if(! this.checkLootAndWrite(compound)) {
            ItemStackHelper.saveAllItems(compound, this.contents);
        }
        compound.putInt("BurnTime", this.burnTime);
        compound.putInt("CookTime", this.cookTime);
        compound.putInt("CookTimeTotal", this.cookTimeTotal);
        compound.putShort("RecipesUsedSize", (short) this.recipes.size());
        int i = 0;

        for(Map.Entry<ResourceLocation, Integer> entry : this.recipes.entrySet()) {
            compound.putString("RecipeLocation" + i, entry.getKey().toString());
            compound.putInt("RecipeAmount" + i, entry.getValue());
            ++ i;
        }

        return compound;
    }

    @Override
    public void read(BlockState state, CompoundNBT compound) {

        super.read(state, compound);

        this.contents = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        if(! this.checkLootAndRead(compound)) {
            ItemStackHelper.loadAllItems(compound, this.contents);
        }

        this.burnTime = compound.getInt("burn_time");
        this.cookTime = compound.getInt("CookTime");
        this.cookTimeTotal = compound.getInt("CookTimeTotal");
        this.recipesUsed = this.getBurnTime(this.items.getStackInSlot(1));
        int i = compound.getShort("RecipesUsedSize");

        for(int j = 0; j < i; ++ j) {
            ResourceLocation resourcelocation = new ResourceLocation(compound.getString("RecipeLocation" + j));
            int k = compound.getInt("RecipeAmount" + j);
            this.recipes.put(resourcelocation, k);
        }
    }

    @Override
    public Container createMenu(int id, PlayerInventory playerInventory, PlayerEntity player) {

        return new ContainerNordicFurnace(id, playerInventory, this, this.furnaceData);
    }

    public boolean isBurning() {

        return this.burnTime > 0;
    }

    private boolean canSmelt(@Nullable NordicFurnaceRecipe recipe) {

        if(recipe == null)
            return false;

        if(recipe.matches(new ItemStack[] { this.items.getStackInSlot(0), this.items.getStackInSlot(1) })) {
            ItemStack result = recipe.getRecipeOutput();
            if(result.isEmpty()) {
                return false;
            }
            else {
                ItemStack output = this.items.getStackInSlot(OUTPUT_SLOT);
                if(output.isEmpty()) {
                    return true;
                }
                else
                    if(! output.isItemEqual(result)) {
                        return false;
                    }
                    else
                        if(output.getCount() + result.getCount() <= this.getInventoryStackLimit() && output.getCount() + result.getCount() <= output.getMaxStackSize()) {
                            return true;
                        }
                        else {
                            return output.getCount() + result.getCount() <= result.getMaxStackSize();
                        }
            }
        }
        else {
            return false;
        }
    }

    private int getBurnTime(ItemStack fuel) {

        if(fuel.isEmpty()) {
            return 0;
        }
        else {
            return ForgeHooks.getBurnTime(fuel);
        }
    }

    private int getCookTime(ItemStack left, ItemStack right) {

        NordicFurnaceRecipe recipe = getRecipeFor(left, right);

        return recipe != null ? recipe.getCookTime() : 200;
    }

    private void smelt(@Nullable NordicFurnaceRecipe recipe) {

        if(recipe != null && this.canSmelt(recipe)) {
            ItemStack left = this.items.getStackInSlot(0);
            ItemStack right = this.items.getStackInSlot(1);
            ItemStack result = recipe.getRecipeOutput();
            ItemStack output = this.items.getStackInSlot(OUTPUT_SLOT);

            if(output.isEmpty()) {
                this.items.setStackInSlot(OUTPUT_SLOT, result.copy());
            }
            else
                if(output.getItem() == result.getItem()) {
                    output.grow(result.getCount());
                }

            if(! this.world.isRemote) {
                this.setRecipeUsed(recipe);
            }

            left.shrink(1);
            right.shrink(1);
        }
    }

    @Override
    public void tick() {

        super.tick();
        boolean burning = this.isBurning();
        boolean isDirty = false;
        if(burning) {
            -- this.burnTime;
        }

        if(! this.world.isRemote) {
            ItemStack fuel = this.items.getStackInSlot(FUEL_SLOT);
            if(this.isBurning() || ! fuel.isEmpty() && ! (this.items.getStackInSlot(0).isEmpty() && this.items.getStackInSlot(1).isEmpty())) {
                NordicFurnaceRecipe recipe = getRecipeFor(this.items.getStackInSlot(0), this.items.getStackInSlot(1));
                if(! this.isBurning() && this.canSmelt(recipe)) {
                    this.burnTime = this.getBurnTime(fuel);
                    this.recipesUsed = this.burnTime;
                    if(this.isBurning()) {
                        isDirty = true;
                        if(fuel.hasContainerItem()) {
                            this.items.setStackInSlot(FUEL_SLOT, fuel.getContainerItem());
                        }
                        else
                            if(! fuel.isEmpty()) {
                                Item fuelItem = fuel.getItem();
                                fuel.shrink(1);
                                if(fuel.isEmpty()) {
                                    this.items.setStackInSlot(FUEL_SLOT, fuel.getContainerItem());
                                }
                            }
                    }
                }

                if(this.isBurning() && this.canSmelt(recipe)) {
                    if(this.cookTimeTotal == 0) {
                        this.cookTimeTotal = this.getCookTime(this.items.getStackInSlot(0), this.items.getStackInSlot(1));
                    }

                    ++ this.cookTime;
                    if(this.cookTime >= this.cookTimeTotal) {
                        this.cookTime = 0;
                        this.cookTimeTotal = this.getCookTime(this.items.getStackInSlot(0), this.items.getStackInSlot(1));
                        this.smelt(recipe);
                        isDirty = true;
                    }
                }
                else {
                    this.cookTime = 0;
                }
            }
            else
                if(! this.isBurning() && this.cookTime > 0) {
                    this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.cookTimeTotal);
                }

            if(burning != this.isBurning()) {
                isDirty = true;
                this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).with(BlockNordicFurnace.SMELTING, Boolean.valueOf(this.isBurning())), 3);
            }
        }

        if(isDirty) {
            this.markDirty();
        }
    }

    @Override
    public void fillStackedContents(RecipeItemHelper helper) {

        for(ItemStack stack : this.contents) {
            helper.accountStack(stack);
        }
    }

    @Override
    public void setRecipeUsed(@Nullable IRecipe<?> recipe) {

        if(recipe != null) {
            this.recipes.compute(recipe.getId(), (p_214004_0_, p_214004_1_) ->
            {
                return 1 + (p_214004_1_ == null ? 0 : p_214004_1_);
            });
        }
    }

    @Nullable
    @Override
    public IRecipe<?> getRecipeUsed() {

        return null;
    }
}
