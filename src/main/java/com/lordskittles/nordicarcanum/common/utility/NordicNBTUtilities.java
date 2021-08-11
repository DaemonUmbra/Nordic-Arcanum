package com.lordskittles.nordicarcanum.common.utility;

import com.lordskittles.nordicarcanum.core.NordicNames;
import com.lordskittles.nordicarcanum.magic.schools.SchoolType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.List;

import static com.lordskittles.arcanumapi.common.utilities.NBTUtilities.getPersistentData;

public class NordicNBTUtilities {

    private static SchoolType[] schools = new SchoolType[] { SchoolType.Ur, SchoolType.Kaun, SchoolType.Ar, SchoolType.Hagal, SchoolType.Yr, SchoolType.Fe };

    public static List<SchoolType> getUndiscoveredSchools(String modid, Player player) {

        CompoundTag nbt = getPersistentData(modid, player.getPersistentData());
        List<SchoolType> schoolsToDiscover = new ArrayList<>();

        for(SchoolType school : NordicNBTUtilities.schools) {
            String key = school.name + NordicNames.DISCOVERED_SUFFIX;
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
}
