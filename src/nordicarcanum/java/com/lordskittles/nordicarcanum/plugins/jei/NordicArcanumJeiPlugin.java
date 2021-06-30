package com.lordskittles.nordicarcanum.plugins.jei;

import com.lordskittles.nordicarcanum.common.registry.RecipeType;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

import static com.lordskittles.nordicarcanum.core.NordicArcanum.RL;

@JeiPlugin
@SuppressWarnings("unused")
public class NordicArcanumJeiPlugin implements IModPlugin
{
    @Nullable
    public static IJeiHelpers jeiHelpers;

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry)
    {
        jeiHelpers = registry.getJeiHelpers();

        registry.addRecipeCategories(new JeiCraftingClothCategory());
        registry.addRecipeCategories(new JeiNordicFurnaceCategory());
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration)
    {
        addRecipeType(registration, RecipeType.crafting_cloth, NordicCategoryID.CRAFTING_CLOTH);
        addRecipeType(registration, RecipeType.nordic_furnace, NordicCategoryID.NORDIC_FURNACE);
    }

    private void addRecipeType(IRecipeRegistration registration, RecipeType<?> type, ResourceLocation id)
    {
        registration.addRecipes(type.getRecipes(Minecraft.getInstance().world).values(), id);
    }

    @Override
    public ResourceLocation getPluginUid()
    {
        return RL("default");
    }
}
