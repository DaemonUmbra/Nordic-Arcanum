package com.lordskittles.arcanumapi.common.inventory.crafting;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public abstract class ArcaneRecipeBase implements Recipe<ArcaneRecipeBase.DummyIInventory> {

    public final String group;
    protected final RecipeType<?> type;

    public ArcaneRecipeBase(RecipeType<?> type, String group) {

        this.type = type;
        this.group = group;
    }

    @Override
    public boolean matches(DummyIInventory inv, Level worldIn) {

        return true;
    }

    public static class DummyIInventory implements Container {

        private static final DummyIInventory INSTANCE = new DummyIInventory();

        public static DummyIInventory getInstance() {

            return INSTANCE;
        }

        @Override
        public int getContainerSize() {

            return 0;
        }

        @Override
        public boolean isEmpty() {

            return true;
        }

        @Override
        public ItemStack getItem(int index) {

            return ItemStack.EMPTY;
        }

        @Override
        public ItemStack removeItem(int index, int count) {

            return ItemStack.EMPTY;
        }

        @Override
        public ItemStack removeItemNoUpdate(int index) {

            return ItemStack.EMPTY;
        }

        @Override
        public void setItem(int index, ItemStack stack) {

        }

        @Override
        public void setChanged() {

        }

        @Override
        public boolean stillValid(Player player) {

            return false;
        }

        @Override
        public void clearContent() {

        }
    }
}
