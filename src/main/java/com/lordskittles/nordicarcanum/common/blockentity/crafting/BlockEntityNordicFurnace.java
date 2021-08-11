package com.lordskittles.nordicarcanum.common.blockentity.crafting;

import com.google.common.collect.Maps;
import com.lordskittles.arcanumapi.common.blockentity.BlockEntityInventorySyncable;
import com.lordskittles.arcanumapi.common.blockentity.BlockEntityTickable;
import com.lordskittles.nordicarcanum.common.block.crafting.BlockNordicFurnace;
import com.lordskittles.nordicarcanum.common.inventory.containers.ContainerNordicFurnace;
import com.lordskittles.nordicarcanum.common.inventory.crafting.NordicFurnaceRecipe;
import com.lordskittles.nordicarcanum.common.registry.BlockEntities;
import com.lordskittles.nordicarcanum.common.registry.RecipeType;
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
import net.minecraft.world.level.Level;
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

    private static NordicFurnaceRecipe getRecipeFor(ItemStack left, ItemStack right, Level level) {

        return RecipeType.nordic_furnace.findFirst(level, recipe -> recipe.matches(new ItemStack[] { left, right }));
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
                else if(! output.sameItem(result)) {
                    return false;
                }
                else if(output.getCount() + result.getCount() <= this.getMaxStackSize() && output.getCount() + result.getCount() <= output.getMaxStackSize()) {
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
            return ForgeHooks.getBurnTime(fuel, null);
        }
    }

    private int getCookTime(ItemStack left, ItemStack right) {

        NordicFurnaceRecipe recipe = getRecipeFor(left, right, level);

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
            else if(output.getItem() == result.getItem()) {
                output.grow(result.getCount());
            }

            if(! this.level.isClientSide) {
                this.setRecipeUsed(recipe);
            }

            left.shrink(1);
            right.shrink(1);
        }
    }

    public static void furnaceTick(Level level, BlockPos pos, BlockState state, BlockEntityNordicFurnace furnace) {

        BlockEntityTickable.tick(level, pos, state, furnace);
        boolean burning = furnace.isBurning();
        boolean isDirty = false;
        if(burning) {
            -- furnace.burnTime;
        }

        if(! furnace.level.isClientSide) {
            ItemStack fuel = furnace.items.getStackInSlot(FUEL_SLOT);
            if(furnace.isBurning() || ! fuel.isEmpty() && ! (furnace.items.getStackInSlot(0).isEmpty() && furnace.items.getStackInSlot(1).isEmpty())) {
                NordicFurnaceRecipe recipe = getRecipeFor(furnace.items.getStackInSlot(0), furnace.items.getStackInSlot(1), level);
                if(! furnace.isBurning() && furnace.canSmelt(recipe)) {
                    furnace.burnTime = furnace.getBurnTime(fuel);
                    furnace.recipesUsed = furnace.burnTime;
                    if(furnace.isBurning()) {
                        isDirty = true;
                        if(fuel.hasContainerItem()) {
                            furnace.items.setStackInSlot(FUEL_SLOT, fuel.getContainerItem());
                        }
                        else if(! fuel.isEmpty()) {
                            Item fuelItem = fuel.getItem();
                            fuel.shrink(1);
                            if(fuel.isEmpty()) {
                                furnace.items.setStackInSlot(FUEL_SLOT, fuel.getContainerItem());
                            }
                        }
                    }
                }

                if(furnace.isBurning() && furnace.canSmelt(recipe)) {
                    if(furnace.cookTimeTotal == 0) {
                        furnace.cookTimeTotal = furnace.getCookTime(furnace.items.getStackInSlot(0), furnace.items.getStackInSlot(1));
                    }

                    ++ furnace.cookTime;
                    if(furnace.cookTime >= furnace.cookTimeTotal) {
                        furnace.cookTime = 0;
                        furnace.cookTimeTotal = furnace.getCookTime(furnace.items.getStackInSlot(0), furnace.items.getStackInSlot(1));
                        furnace.smelt(recipe);
                        isDirty = true;
                    }
                }
                else {
                    furnace.cookTime = 0;
                }
            }
            else if(! furnace.isBurning() && furnace.cookTime > 0) {
                furnace.cookTime = Mth.clamp(furnace.cookTime - 2, 0, furnace.cookTimeTotal);
            }

            if(burning != furnace.isBurning()) {
                isDirty = true;
                furnace.level.setBlock(furnace.getBlockPos(), furnace.level.getBlockState(furnace.getBlockPos()).setValue(BlockNordicFurnace.SMELTING, Boolean.valueOf(furnace.isBurning())), 3);
            }
        }

        if(isDirty) {
            furnace.setChanged();
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
            this.recipes.compute(recipe.getId(), (p_214004_0_, p_214004_1_) -> 1 + (p_214004_1_ == null ? 0 : p_214004_1_));
        }
    }

    @Nullable
    @Override
    public Recipe<?> getRecipeUsed() {

        return null;
    }
}
