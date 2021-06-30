package com.lordskittles.nordicarcanum.plugins.jei;

import com.google.common.collect.ImmutableList;
import com.lordskittles.arcanumapi.common.utilities.MagicUtilities;
import com.lordskittles.nordicarcanum.common.inventory.crafting.CraftingClothRecipe;
import com.lordskittles.nordicarcanum.common.registry.Items;
import com.lordskittles.nordicarcanum.core.NordicTextures;
import com.mojang.blaze3d.matrix.MatrixStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import com.lordskittles.nordicarcanum.common.registry.Blocks;

import java.util.List;


public class JeiCraftingClothCategory implements IRecipeCategory<CraftingClothRecipe>
{
    private final String localizedName;
    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableStatic arcanumBarForeground;
    private IDrawableStatic arcanumBar;

    private final float maxArcanum;

    JeiCraftingClothCategory()
    {
        localizedName = I18n.format(Blocks.crafting_cloth.get().getTranslationKey());
        background = NordicArcanumJeiPlugin.jeiHelpers.getGuiHelper().createDrawable(NordicTextures.JEI_CRAFTING_CLOTH, 0, 0, 176, 97);
        icon = NordicArcanumJeiPlugin.jeiHelpers.getGuiHelper().createDrawableIngredient(new ItemStack(Items.crafting_cloth_item.get()));
        arcanumBarForeground = NordicArcanumJeiPlugin.jeiHelpers.getGuiHelper().createDrawable(NordicTextures.JEI_CRAFTING_CLOTH, 0, 97, 63, 14);

        maxArcanum = MagicUtilities.getCurrentArcanum(Minecraft.getInstance().player);
    }

    @Override
    public ResourceLocation getUid()
    {
        return NordicCategoryID.CRAFTING_CLOTH;
    }

    @Override
    public Class<? extends CraftingClothRecipe> getRecipeClass()
    {
        return CraftingClothRecipe.class;
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
    public void setIngredients(CraftingClothRecipe recipe, IIngredients ingredients)
    {
        ingredients.setInputIngredients(recipe.getIngredients());
        ingredients.setOutputs(VanillaTypes.ITEM, ImmutableList.of(recipe.getRecipeOutput()));
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, CraftingClothRecipe recipe, IIngredients ingredients)
    {
        IGuiItemStackGroup guiStacks = recipeLayout.getItemStacks();
        List<List<ItemStack>> inputs = ingredients.getInputs(VanillaTypes.ITEM);
        List<List<ItemStack>> outputs = ingredients.getOutputs(VanillaTypes.ITEM);

        guiStacks.init(0, false, 128, 50);
        guiStacks.set(0, outputs.get(0));

        for (int x = 0; x < recipe.getWidth(); ++x)
        {
            for (int y = 0; y < recipe.getHeight(); ++y)
            {
                int xPos = 19 + x * 20;
                int yPos = 19 + y * 20;

                int index = 1 + (y * recipe.getHeight() + x);
                int ingIndex = x + y * recipe.getWidth();

                guiStacks.init(index, true, xPos, yPos);
                guiStacks.set(index, inputs.get(ingIndex));
            }
        }
    }

    @Override
    public void draw(CraftingClothRecipe recipe, MatrixStack stack, double mouseX, double mouseY)
    {
        arcanumBar = NordicArcanumJeiPlugin.jeiHelpers.getGuiHelper().createDrawable(NordicTextures.JEI_CRAFTING_CLOTH, 63, 97, (int)(61 * (recipe.getArcanum() / maxArcanum)), 12);

        arcanumBar.draw(stack, 105, 13);
        arcanumBarForeground.draw(stack, 104, 12);
    }
}
