package com.lordskittles.nordicarcanum.common.blockentity.crafting;

import com.google.common.collect.Maps;
import com.lordskittles.arcanumapi.common.blockentity.BlockEntityInventorySyncable;
import com.lordskittles.nordicarcanum.common.block.crafting.BlockNordicFurnace;
import com.lordskittles.nordicarcanum.common.inventory.containers.ContainerNordicFurnace;
import com.lordskittles.nordicarcanum.common.inventory.crafting.NordicFurnaceRecipe;
import com.lordskittles.nordicarcanum.common.registry.RecipeType;
import com.lordskittles.nordicarcanum.common.registry.BlockEntities;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicInventorySlots;
import com.lordskittles.nordicarcanum.core.NordicNames;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeHolder;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;

import javax.annotation.Nullable;
import java.util.Map;

public class BlockEntityNordicFurnace extends BlockEntityInventorySyncable<BlockEntityNordicFurnace> implements RecipeHolder, StackedContentsCompatible {

    protected int burnTime;
    protected int cookTime;
    protected int cookTimeTotal;
    protected int recipesUsed;

    private static final int FUEL_SLOT = 2;
    private static final int OUTPUT_SLOT = 3;

    private final Map<ResourceLocation, Integer> recipes = Maps.newHashMap();

    protected final ContainerData furnaceData = new ContainerData() {

        public int get(int index) {

            switch(index) {
                case 0:
                    return BlockEntityNordicFurnace.this.burnTime;
                case 1:
                    return BlockEntityNordicFurnace.this.recipesUsed;
                case 2:
                    return BlockEntityNordicFurnace.this.cookTime;
                case 3:
                    return BlockEntityNordicFurnace.this.cookTimeTotal;
                default:
                    return 0;
            }
        }

        public void set(int index, int value) {

            switch(index) {
                case 0:
                    BlockEntityNordicFurnace.this.burnTime = value;
                    break;
                case 1:
                    BlockEntityNordicFurnace.this.recipesUsed = value;
                    break;
                case 2:
                    BlockEntityNordicFurnace.this.cookTime = value;
                    break;
                case 3:
                    BlockEntityNordicFurnace.this.cookTimeTotal = value;
            }

        }

        public int getCount() {

            return 4;
        }
    };

    public BlockEntityNordicFurnace(BlockPos pos, BlockState state) {

        super(BlockEntities.nordic_furnace.get(), pos, state, NordicInventorySlots.NORDIC_FURNACE, NordicNames.NORDIC_FURNACE, NordicArcanum.MODID);
    }

    private NordicFurnaceRecipe getRecipeFor(ItemStack left, ItemStack right) {

        return RecipeType.nordic_furnace.findFirst(level, recipe ->
        {
            return recipe.matches(new ItemStack[] { left, right });
        });
    }

    @Override
    public CompoundTag save(CompoundTag compound) {

        super.save(compound);

        if(! this.checkLootAndWrite(compound)) {
            ContainerHelper.saveAllItems(compound, this.contents);
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
    public void load(CompoundTag compound) {

        super.load(compound);

        this.contents = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if(! this.checkLootAndRead(compound)) {
            ContainerHelper.loadAllItems(compound, this.contents);
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
    public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player player) {

        return new ContainerNordicFurnace(id, playerInventory, this, this.furnaceData);
    }

    public boolean isBurning() {

        return this.burnTime > 0;
    }

    private boolean canSmelt(@Nullable NordicFurnaceRecipe recipe) {

        if(recipe == null)
            return false;

        if(recipe.matches(new ItemStack[] { this.items.getStackInSlot(0), this.items.getStackInSlot(1) })) {
            ItemStack result = recipe.getResultItem();
            if(result.isEmpty()) {
                return false;
            }
            else {
                ItemStack output = this.items.getStackInSlot(OUTPUT_SLOT);
                if(output.isEmpty()) {
                    return true;
                }
                else
                    if(! output.sameItem(result)) {
                        return false;
                    }
                    else
                        if(output.getCount() + result.getCount() <= this.getMaxStackSize() && output.getCount() + result.getCount() <= output.getMaxStackSize()) {
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
            return ForgeHooks.getBurnTime(fuel, getRecipeUsed().getType());
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
            ItemStack result = recipe.getResultItem();
            ItemStack output = this.items.getStackInSlot(OUTPUT_SLOT);

            if(output.isEmpty()) {
                this.items.setStackInSlot(OUTPUT_SLOT, result.copy());
            }
            else
                if(output.getItem() == result.getItem()) {
                    output.grow(result.getCount());
                }

            if(! this.level.isClientSide) {
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

        if(! this.level.isClientSide) {
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
                    this.cookTime = Mth.clamp(this.cookTime - 2, 0, this.cookTimeTotal);
                }

            if(burning != this.isBurning()) {
                isDirty = true;
                this.level.setBlock(this.getBlockPos(), this.level.getBlockState(this.getBlockPos()).setValue(BlockNordicFurnace.SMELTING, Boolean.valueOf(this.isBurning())), 3);
            }
        }

        if(isDirty) {
            this.setChanged();
        }
    }

    @Override
    public void fillStackedContents(StackedContents helper) {

        for(ItemStack stack : this.contents) {
            helper.accountStack(stack);
        }
    }

    @Override
    public void setRecipeUsed(@Nullable Recipe<?> recipe) {

        if(recipe != null) {
            this.recipes.compute(recipe.getId(), (p_214004_0_, p_214004_1_) ->
            {
                return 1 + (p_214004_1_ == null ? 0 : p_214004_1_);
            });
        }
    }

    @Nullable
    @Override
    public Recipe<?> getRecipeUsed() {

        return null;
    }
}
