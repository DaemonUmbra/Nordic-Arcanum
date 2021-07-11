package com.lordskittles.arcanumapi.common.utilities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fluids.FluidStack;

import java.util.function.Consumer;

public class NBTUtilities {

    public static void setFluidStackIfPresent(CompoundNBT nbt, String key, Consumer<FluidStack> setter) {
//        CompoundNBT base = getPersistentData(nbt);
        if(nbt.contains(key, Constants.NBT.TAG_COMPOUND)) {
            setter.accept(FluidStack.loadFluidStackFromNBT(nbt.getCompound(key)));
        }
    }

    public static CompoundNBT getPersistentData(String modid, ItemStack stack) {

        return getPersistentData(modid, getData(stack));
    }

    public static CompoundNBT getPersistentData(String modid, Entity entity) {

        return getPersistentData(modid, entity.getPersistentData());
    }

    public static CompoundNBT getPersistentData(String modid, CompoundNBT base) {

        CompoundNBT nbt;
        if(hasPersistentData(modid, base)) {
            nbt = base.getCompound(modid);
        }
        else {
            nbt = new CompoundNBT();
            base.put(modid, nbt);
        }

        return nbt;
    }

    public static CompoundNBT getData(ItemStack stack) {

        CompoundNBT nbt = stack.getTag();
        if(nbt == null) {
            nbt = new CompoundNBT();
            stack.setTag(nbt);
        }

        return nbt;
    }

    public static boolean hasPersistentData(String modid, CompoundNBT nbt) {

        return nbt.contains(modid) && nbt.get(modid) instanceof CompoundNBT;
    }



    public static void setSchoolDiscovered(String modid, PlayerEntity player, String key) {

        CompoundNBT nbt = getPersistentData(modid, player.getPersistentData());

        nbt.putBoolean(key, true);
    }
}
