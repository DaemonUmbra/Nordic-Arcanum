package com.lordskittles.nordicarcanum.common.registry;

import com.lordskittles.nordicarcanum.common.inventory.crafting.*;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicNames;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RecipeSerializers {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, NordicArcanum.MODID);

    public static final RegistryObject<RecipeSerializer<NordicFurnaceRecipe>> nordic_furnace = RECIPE_SERIALIZER.register(NordicNames.NORDIC_FURNACE, NordicFurnaceRecipe.Serializer::new);
    public static final RegistryObject<RecipeSerializer<CraftingClothRecipe>> crafting_cloth = RECIPE_SERIALIZER.register(NordicNames.CRAFTING_CLOTH, CraftingClothRecipe.Serializer::new);
    public static final RegistryObject<RecipeSerializer<NordicAnvilRecipe>> nordic_anvil = RECIPE_SERIALIZER.register(NordicNames.NORDIC_ANVIL, NordicAnvilRecipe.Serializer::new);
    public static final RegistryObject<RecipeSerializer<VikingSawRecipe>> viking_saw = RECIPE_SERIALIZER.register(NordicNames.VIKING_SAW, VikingSawRecipe.Serializer::new);
    public static final RegistryObject<RecipeSerializer<ArcaneInfuserRecipe>> arcane_infuser = RECIPE_SERIALIZER.register(NordicNames.ARCANE_INFUSER, ArcaneInfuserRecipe.Serializer::new);
}
