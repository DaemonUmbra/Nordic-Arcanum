package com.lordskittles.arcanumapi.common.utilities;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fluids.FluidStack;

import java.util.function.Consumer;

public class NBTUtilities {

    public static void setFluidStackIfPresent(CompoundTag nbt, String key, Consumer<FluidStack> setter) {
//        CompoundNBT base = getPersistentData(nbt);
        if(nbt.contains(key, Constants.NBT.TAG_COMPOUND)) {
            setter.accept(FluidStack.loadFluidStackFromNBT(nbt.getCompound(key)));
        }
    }

    public static CompoundTag getPersistentData(String modid, ItemStack stack) {

        return getPersistentData(modid, getData(stack));
    }

    public static CompoundTag getPersistentData(String modid, Entity entity) {

        return getPersistentData(modid, entity.getPersistentData());
    }

    public static CompoundTag getPersistentData(String modid, CompoundTag base) {

        CompoundTag nbt;
        if(hasPersistentData(modid, base)) {
            nbt = base.getCompound(modid);
        }
        else {
            nbt = new CompoundTag();
            base.put(modid, nbt);
        }

        return nbt;
    }

    public static CompoundTag getData(ItemStack stack) {

        CompoundTag nbt = stack.getTag();
        if(nbt == null) {
            nbt = new CompoundTag();
            stack.setTag(nbt);
        }

        return nbt;
    }

    public static boolean hasPersistentData(String modid, CompoundTag nbt) {

        return nbt.contains(modid) && nbt.get(modid) instanceof CompoundTag;
    }



    public static void setSchoolDiscovered(String modid, Player player, String key) {

        CompoundTag nbt = getPersistentData(modid, player.getPersistentData());

        nbt.putBoolean(key, true);
    }
}
