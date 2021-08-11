package com.lordskittles.nordicarcanum.common.registry;

import com.lordskittles.arcanumapi.common.inventory.crafting.ArcaneRecipeBase;
import com.lordskittles.nordicarcanum.common.inventory.crafting.*;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicNames;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class RecipeType<T extends ArcaneRecipeBase> implements net.minecraft.world.item.crafting.RecipeType<T> {

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

    public static void registerRecipeTypes(IForgeRegistry<RecipeSerializer<?>> registry) {

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

    public Map<ResourceLocation, T> getRecipes(Level world) {

        if(cachedRecipes.isEmpty()) {
            RecipeManager recipeManager = world.getRecipeManager();
            List<T> recipes = recipeManager.getRecipesFor(this, ArcaneRecipeBase.DummyIInventory.getInstance(), world);
            recipes.forEach(recipe -> cachedRecipes.put(recipe.getId(), recipe));
        }

        return cachedRecipes;
    }

    public Stream<T> stream(Level world) {

        return getRecipes(world).values().stream();
    }

    public T findFirst(Level world, Predicate<T> predicate) {

        return stream(world).filter(predicate).findFirst().orElse(null);
    }

    public T getRecipe(Level world, ResourceLocation recipeId) {

        return getRecipes(world).get(recipeId);
    }
}
