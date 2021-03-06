package com.lordskittles.nordicarcanum.common.registry;

import com.lordskittles.nordicarcanum.common.inventory.crafting.*;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicNames;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RecipeSerializers {

    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, NordicArcanum.MODID);

    public static final RegistryObject<IRecipeSerializer<NordicFurnaceRecipe>> nordic_furnace = RECIPE_SERIALIZER.register(NordicNames.NORDIC_FURNACE, NordicFurnaceRecipe.Serializer::new);
    public static final RegistryObject<IRecipeSerializer<CraftingClothRecipe>> crafting_cloth = RECIPE_SERIALIZER.register(NordicNames.CRAFTING_CLOTH, CraftingClothRecipe.Serializer::new);
    public static final RegistryObject<IRecipeSerializer<NordicAnvilRecipe>> nordic_anvil = RECIPE_SERIALIZER.register(NordicNames.NORDIC_ANVIL, NordicAnvilRecipe.Serializer::new);
    public static final RegistryObject<IRecipeSerializer<VikingSawRecipe>> viking_saw = RECIPE_SERIALIZER.register(NordicNames.VIKING_SAW, VikingSawRecipe.Serializer::new);
    public static final RegistryObject<IRecipeSerializer<ArcaneInfuserRecipe>> arcane_infuser = RECIPE_SERIALIZER.register(NordicNames.ARCANE_INFUSER, ArcaneInfuserRecipe.Serializer::new);
}
