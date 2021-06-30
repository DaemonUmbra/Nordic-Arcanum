package com.lordskittles.arcanumapi.common.registry;

import com.lordskittles.arcanumapi.common.item.ItemTinyFuel;
import com.lordskittles.arcanumapi.core.ArcanumAPI;
import com.lordskittles.arcanumapi.core.ArcanumNames;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Items
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ArcanumAPI.MODID);

    public static final RegistryObject<Item> tiny_coal = ITEMS.register(ArcanumNames.TINY_COAL, ItemTinyFuel::new);
    public static final RegistryObject<Item> tiny_charcoal = ITEMS.register(ArcanumNames.TINY_CHARCOAL, ItemTinyFuel::new);
}
