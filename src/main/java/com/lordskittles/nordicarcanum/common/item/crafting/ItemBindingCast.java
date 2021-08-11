package com.lordskittles.nordicarcanum.common.item.crafting;

import com.lordskittles.arcanumapi.common.item.ItemMod;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicItemGroup;
import net.minecraft.world.item.Item;

public class ItemBindingCast extends ItemMod {

    private final BindingShape shape;

    public ItemBindingCast(BindingShape shape) {

        super(new Item.Properties().tab(NordicItemGroup.INSTANCE).stacksTo(1));

        this.shape = shape;
    }

    public BindingShape getShape() {

        return this.shape;
    }
}
