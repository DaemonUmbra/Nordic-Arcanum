package com.lordskittles.arcanumapi.magic.progression;

import com.lordskittles.arcanumapi.core.NBTConstants;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class PlayerProgress {

    private List<ResourceLocation> knownSchools = new ArrayList<>();

    public void load(CompoundNBT nbt) {

        this.knownSchools.clear();
        if(nbt.contains(NBTConstants.DISCOVERED_SCHOOLS)) {
            ListNBT list = nbt.getList(NBTConstants.DISCOVERED_SCHOOLS, 8);
            for(int i = 0; i < list.size(); i++) {
                ResourceLocation s = new ResourceLocation(list.getString(i));
                this.knownSchools.add(s);
            }
        }
    }

    public void store(CompoundNBT nbt) {

        ListNBT list = new ListNBT();
        for(ResourceLocation location : knownSchools) {
            list.add(StringNBT.valueOf(location.toString()));
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
