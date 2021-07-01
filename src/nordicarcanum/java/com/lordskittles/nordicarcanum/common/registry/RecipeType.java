package com.lordskittles.nordicarcanum.common.registry;

import com.lordskittles.arcanumapi.common.inventory.crafting.ArcaneRecipeBase;
import com.lordskittles.nordicarcanum.common.inventory.crafting.*;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicNames;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class RecipeType<T extends ArcaneRecipeBase> implements IRecipeType<T> {

    private static final List<RecipeType<? extends ArcaneRecipeBase>> TYPES = new ArrayList<>();

    public static final RecipeType<NordicFurnaceRecipe> nordic_furnace = registerType(NordicNames.NORDIC_FURNACE);
    public static final RecipeType<CraftingClothRecipe> crafting_cloth = registerType(NordicNames.CRAFTING_CLOTH);
    public static final RecipeType<NordicAnvilRecipe> nordic_anvil = registerType(NordicNames.NORDIC_ANVIL);
    public static final RecipeType<VikingSawRecipe> viking_saw = registerType(NordicNames.VIKING_SAW);
    public static final RecipeType<ArcaneInfuserRecipe> arcane_infuser = registerType(NordicNames.ARCANE_INFUSER);

    private final Map<ResourceLocation, T> cachedRecipes = new HashMap<>();
    private final ResourceLocation registryName;

    private static <T extends ArcaneRecipeBase> RecipeType<T> registerType(String name) {

        RecipeType<T> type = new RecipeType<>(name);
        TYPES.add(type);
        return type;
    }

    public static void registerRecipeTypes(IForgeRegistry<IRecipeSerializer<?>> registry) {

        TYPES.forEach(type -> Registry.register(Registry.RECIPE_TYPE, type.registryName, type));
    }

    private RecipeType(String name) {

        this.registryName = NordicArcanum.RL(name);
    }

    @Override
    public String toString() {

        return registryName.toString();
    }

    public static void clearCachedRecipes() {

        TYPES.forEach(type -> type.cachedRecipes.clear());
    }

    public Map<ResourceLocation, T> getRecipes(World world) {

        if(cachedRecipes.isEmpty()) {
            RecipeManager recipeManager = world.getRecipeManager();
            List<T> recipes = recipeManager.getRecipes(this, ArcaneRecipeBase.DummyIInventory.getInstance(), world);
            recipes.forEach(recipe -> cachedRecipes.put(recipe.getId(), recipe));
        }

        return cachedRecipes;
    }

    public Stream<T> stream(World world) {

        return getRecipes(world).values().stream();
    }

    public T findFirst(World world, Predicate<T> predicate) {

        return stream(world).filter(predicate).findFirst().orElse(null);
    }

    public T getRecipe(World world, ResourceLocation recipeId) {

        return getRecipes(world).get(recipeId);
    }
}
