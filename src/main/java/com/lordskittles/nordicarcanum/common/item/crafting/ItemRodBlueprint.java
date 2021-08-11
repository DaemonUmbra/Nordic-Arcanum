package com.lordskittles.nordicarcanum.common.item.crafting;

import com.lordskittles.arcanumapi.common.item.ItemMod;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicItemGroup;
import net.minecraft.world.item.Item;

public class ItemRodBlueprint extends ItemMod {

    private final RodShape shape;

    public ItemRodBlueprint(RodShape shape) {

        super(new Item.Properties().tab(NordicItemGroup.INSTANCE).stacksTo(1));

        this.shape = shape;
    }

    public RodShape getShape() {

        return this.shape;
    }
}
