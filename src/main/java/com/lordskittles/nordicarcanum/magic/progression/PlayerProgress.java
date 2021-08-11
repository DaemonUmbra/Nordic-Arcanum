package com.lordskittles.nordicarcanum.magic.progression;

import com.lordskittles.arcanumapi.core.NBTConstants;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class PlayerProgress {

    private List<ResourceLocation> knownSchools = new ArrayList<>();

    public void load(CompoundTag nbt) {

        this.knownSchools.clear();
        if(nbt.contains(NBTConstants.DISCOVERED_SCHOOLS)) {
            ListTag list = nbt.getList(NBTConstants.DISCOVERED_SCHOOLS, 8);
            for(int i = 0; i < list.size(); i++) {
                ResourceLocation s = new ResourceLocation(list.getString(i));
                this.knownSchools.add(s);
            }
        }
    }

    public void store(CompoundTag nbt) {

        ListTag list = new ListTag();
        for(ResourceLocation location : knownSchools) {
            list.add(StringTag.valueOf(location.toString()));
        }

        nbt.put(NBTConstants.DISCOVERED_SCHOOLS, list);
    }

    public boolean isValid() {

        return true;
    }

    public List<ResourceLocation> getKnownSchools() {

        return this.knownSchools;
    }

    public void resetSchools() {

        this.knownSchools.clear();
    }

    public void learnSchool(ResourceLocation school) {

        if(! this.knownSchools.contains(school)) {
            this.knownSchools.add(school);
        }
    }
}
