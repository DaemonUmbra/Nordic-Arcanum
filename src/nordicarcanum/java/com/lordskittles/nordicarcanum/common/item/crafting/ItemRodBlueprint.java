package com.lordskittles.nordicarcanum.common.item.crafting;

import com.lordskittles.arcanumapi.common.item.ItemMod;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicItemGroup;
import net.minecraft.item.Item;

public class ItemRodBlueprint extends ItemMod {

    private final RodShape shape;

    public ItemRodBlueprint(RodShape shape) {

        super(new Item.Properties().group(NordicItemGroup.INSTANCE).maxStackSize(1));

        this.shape = shape;
    }

    public RodShape getShape() {

        return this.shape;
    }
}
