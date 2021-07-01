package com.lordskittles.arcanumapi.common.utilities;

import com.lordskittles.arcanumapi.core.ArcanumNames;
import com.lordskittles.arcanumapi.magic.schools.SchoolType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class NBTUtilities {

    private static SchoolType[] schools = new SchoolType[] { SchoolType.Ur, SchoolType.Kaun, SchoolType.Ar, SchoolType.Hagal, SchoolType.Yr, SchoolType.Fe };

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

    public static List<SchoolType> getUndiscoveredSchools(String modid, PlayerEntity player) {

        CompoundNBT nbt = getPersistentData(modid, player.getPersistentData());
        List<SchoolType> schoolsToDiscover = new ArrayList<>();

        for(SchoolType school : NBTUtilities.schools) {
            String key = school.name + ArcanumNames.DISCOVERED_SUFFIX;
            if(nbt.contains(key)) {
                boolean discovered = nbt.getBoolean(key);
                if(! discovered) {
                    schoolsToDiscover.add(school);
                }
            }
            else {
                nbt.putBoolean(key, false);
            }
        }

        return schoolsToDiscover;
    }

    public static void setSchoolDiscovered(String modid, PlayerEntity player, String key) {

        CompoundNBT nbt = getPersistentData(modid, player.getPersistentData());

        nbt.putBoolean(key, true);
    }
}
