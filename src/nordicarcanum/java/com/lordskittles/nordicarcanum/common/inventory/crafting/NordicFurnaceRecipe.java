package com.lordskittles.nordicarcanum.common.inventory.crafting;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lordskittles.arcanumapi.common.inventory.crafting.ArcaneRecipeBase;
import com.lordskittles.nordicarcanum.common.registry.RecipeSerializers;
import com.lordskittles.nordicarcanum.common.registry.RecipeType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.common.util.RecipeMatcher;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class NordicFurnaceRecipe extends ArcaneRecipeBase
{
    public final NonNullList<Ingredient> ingredients;
    public final ItemStack result;
    public final float experience;
    public final int cookTime;

    protected final ResourceLocation id;

    public NordicFurnaceRecipe(ResourceLocation id, String group, NonNullList<Ingredient> ingredients, ItemStack result, float experience, int cookTime)
    {
        super(RecipeType.nordic_furnace, group);
        this.ingredients = ingredients;
        this.result = result;
        this.experience = experience;
        this.cookTime = cookTime;

        this.id = id;
    }

    public boolean matches(ItemStack[] inputs)
    {
        NonNullList<ItemStack> items = NonNullList.withSize(inputs.length, ItemStack.EMPTY);
        items.set(0, inputs[0]);
        items.set(1, inputs[1]);

        return RecipeMatcher.findMatches(items, this.ingredients) != null;
    }

    @Override
    public NonNullList<Ingredient> getIngredients()
    {
        return ingredients;
    }

    @Override
    public ItemStack getCraftingResult(DummyIInventory inv)
    {
        return this.result;
    }

    @Override
    public boolean canFit(int width, int height)
    {
        return true;
    }

    @Override
    public ItemStack getRecipeOutput()
    {
        return result;
    }

    @Override
    public ResourceLocation getId()
    {
        return this.id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer()
    {
        return RecipeSerializers.nordic_furnace.get();
    }

    @Override
    public IRecipeType<?> getType()
    {
        return this.type;
    }

    @Override
    public String getGroup()
    {
        return this.group;
    }

    public int getCookTime()
    {
        return this.cookTime;
    }

    public static class Serializer<T extends NordicFurnaceRecipe> extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<NordicFurnaceRecipe>
    {
        @Override
        public NordicFurnaceRecipe read(ResourceLocation recipeId, JsonObject json)
        {
            String group = JSONUtils.getString(json, "group", "");
            NonNullList<Ingredient> ingredients = readIngredients(JSONUtils.getJsonArray(json, "ingredients"));

            if (!json.has("result"))
            {
                throw new com.google.gson.JsonSyntaxException("Missing result, expected to find a string or object");
            }

            ItemStack outputStack = ItemStack.EMPTY;
            if (json.get("result").isJsonObject())
            {
                outputStack = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result"));
            }
            else
            {
                String result = JSONUtils.getString(json, "result");
                ResourceLocation resultLocation = new ResourceLocation(result);
                outputStack = new ItemStack(ForgeRegistries.ITEMS.getValue(resultLocation));
            }

            float experience = JSONUtils.getFloat(json, "experience");
            int time = JSONUtils.getInt(json, "cookingtime");
            return new NordicFurnaceRecipe(recipeId, group, ingredients, outputStack, experience, time);
        }

        @Nullable
        @Override
        public NordicFurnaceRecipe read(ResourceLocation recipeId, PacketBuffer buffer)
        {
            String group = buffer.readString(32767);

            int size = buffer.readVarInt();
            NonNullList<Ingredient> ingredients = NonNullList.withSize(size, Ingredient.EMPTY);
            for (int iter = 0; iter < size; iter++)
            {
                ingredients.set(iter, Ingredient.read(buffer));
            }

            ItemStack result = buffer.readItemStack();
            float experience = buffer.readFloat();
            int time = buffer.readVarInt();
            return new NordicFurnaceRecipe(recipeId, group, ingredients, result, experience, time);
        }

        @Override
        public void write(PacketBuffer buffer, NordicFurnaceRecipe recipe)
        {
            buffer.writeString(recipe.getGroup());
            buffer.writeVarInt(recipe.ingredients.size());

            for (Ingredient ingredient : recipe.ingredients)
            {
                ingredient.write(buffer);
            }

            buffer.writeItemStack(recipe.result);
            buffer.writeFloat(recipe.experience);
            buffer.writeVarInt(recipe.cookTime);
        }

        private static NonNullList<Ingredient> readIngredients(JsonArray array)
        {
            NonNullList<Ingredient> ingredients = NonNullList.create();

            for (int iter = 0; iter < array.size(); iter++)
            {
                Ingredient ingredient = Ingredient.deserialize(array.get(iter));
                if (!ingredient.hasNoMatchingItems())
                {
                    ingredients.add(ingredient);
                }
            }

            return ingredients;
        }
    }

}
