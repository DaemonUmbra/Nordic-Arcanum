package com.lordskittles.arcanumapi.common.inventory.crafting;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.world.World;

public abstract class ArcaneRecipeBase implements IRecipe<ArcaneRecipeBase.DummyIInventory>
{
    public final String group;
    protected final IRecipeType<?> type;

    public ArcaneRecipeBase(IRecipeType<?> type, String group)
    {
        this.type = type;
        this.group = group;
    }

    @Override
    public boolean matches(DummyIInventory inv, World worldIn)
    {
        return true;
    }

    public static class DummyIInventory implements IInventory
    {
        private static final DummyIInventory INSTANCE = new DummyIInventory();

        public static DummyIInventory getInstance()
        {
            return INSTANCE;
        }

        @Override
        public int getSizeInventory()
        {
            return 0;
        }

        @Override
        public boolean isEmpty()
        {
            return true;
        }

        @Override
        public ItemStack getStackInSlot(int index)
        {
            return ItemStack.EMPTY;
        }

        @Override
        public ItemStack decrStackSize(int index, int count)
        {
            return ItemStack.EMPTY;
        }

        @Override
        public ItemStack removeStackFromSlot(int index)
        {
            return ItemStack.EMPTY;
        }

        @Override
        public void setInventorySlotContents(int index, ItemStack stack)
        {

        }

        @Override
        public void markDirty()
        {

        }

        @Override
        public boolean isUsableByPlayer(PlayerEntity player)
        {
            return false;
        }

        @Override
        public void clear()
        {

        }
    }
}
