package com.lordskittles.nordicarcanum.plugins.jei;

import com.google.common.collect.ImmutableList;
import com.lordskittles.nordicarcanum.common.inventory.crafting.NordicFurnaceRecipe;
import com.lordskittles.nordicarcanum.common.registry.Blocks;
import com.lordskittles.nordicarcanum.core.NordicTextures;
import com.mojang.blaze3d.matrix.MatrixStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class JeiNordicFurnaceCategory implements IRecipeCategory<NordicFurnaceRecipe>
{
    private final String localizedName;
    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableAnimated progressBar;

    JeiNordicFurnaceCategory()
    {
        localizedName = I18n.format(Blocks.nordic_furnace.get().getTranslationKey());
        background = NordicArcanumJeiPlugin.jeiHelpers.getGuiHelper().createDrawable(NordicTextures.JEI_NORDIC_FURNACE, 0, 0, 176, 97);
        icon = NordicArcanumJeiPlugin.jeiHelpers.getGuiHelper().createDrawableIngredient(new ItemStack(Blocks.nordic_furnace.get()));

        IDrawableStatic staticBar = NordicArcanumJeiPlugin.jeiHelpers.getGuiHelper().createDrawable(NordicTextures.JEI_NORDIC_FURNACE, 0, 177, 24, 17);
        progressBar = NordicArcanumJeiPlugin.jeiHelpers.getGuiHelper().createAnimatedDrawable(staticBar, 60, IDrawableAnimated.StartDirection.LEFT, false);
    }

    @Override
    public ResourceLocation getUid()
    {
        return NordicCategoryID.NORDIC_FURNACE;
    }

    @Override
    public Class<? extends NordicFurnaceRecipe> getRecipeClass()
    {
        return NordicFurnaceRecipe.class;
    }

    @Override
    public String getTitle()
    {
        return localizedName;
    }

    @Override
    public IDrawable getBackground()
    {
        return background;
    }

    @Override
    public IDrawable getIcon()
    {
        return icon;
    }

    @Override
    public void setIngredients(NordicFurnaceRecipe recipe, IIngredients ingredients)
    {
        ingredients.setInputIngredients(recipe.getIngredients());
        ingredients.setOutputs(VanillaTypes.ITEM, ImmutableList.of(recipe.getRecipeOutput()));
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, NordicFurnaceRecipe recipe, IIngredients ingredients)
    {
        IGuiItemStackGroup guiStacks = recipeLayout.getItemStacks();
        List<List<ItemStack>> inputs = ingredients.getInputs(VanillaTypes.ITEM);
        List<List<ItemStack>> outputs = ingredients.getOutputs(VanillaTypes.ITEM);

        guiStacks.init(0, true, 43, 16);
        guiStacks.set(0, inputs.get(0));

        guiStacks.init(1, true, 67, 16);
        guiStacks.set(1, inputs.get(1));

        guiStacks.init(2, false, 115, 34);
        guiStacks.set(2, outputs.get(0));
    }

    @Override
    public void draw(NordicFurnaceRecipe recipe, MatrixStack stack, double mouseX, double mouseY)
    {
        progressBar.draw(stack, 81, 25);
    }
}
