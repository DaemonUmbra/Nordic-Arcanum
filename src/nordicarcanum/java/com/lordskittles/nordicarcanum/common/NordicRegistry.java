package com.lordskittles.nordicarcanum.common;

import com.lordskittles.nordicarcanum.core.NordicTags;
import net.minecraft.world.item.Item;

public class NordicRegistry {

    public static boolean isItemValid(Item item, RegistryType type) {

        boolean valid = false;

        switch(type) {
            case CORE:
                valid = NordicTags.IsItemInTag(item, NordicTags.STAFF_CORE);
                break;
            case BINDING:
                valid = NordicTags.IsItemInTag(item, NordicTags.STAFF_BINDING);
                break;
            case CRYSTAL:
                valid = NordicTags.IsItemInTag(item, NordicTags.STAFF_CRYSTAL);
                break;
            case ROD:
                valid = NordicTags.IsItemInTag(item, NordicTags.STAFF_ROD);
                break;
        }

        return valid;
    }
}
