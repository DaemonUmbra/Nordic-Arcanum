package com.lordskittles.nordicarcanum.common.inventory.crafting;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lordskittles.arcanumapi.common.inventory.crafting.ArcaneRecipeBase;
import com.lordskittles.nordicarcanum.common.registry.RecipeSerializers;
import com.lordskittles.nordicarcanum.common.registry.RecipeType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class VikingSawRecipe extends ArcaneRecipeBase
{
    public final Ingredient ingredient;
    public final ItemStack result;
    public final int cuts;

    protected final ResourceLocation id;

    public VikingSawRecipe(ResourceLocation id, String group, Ingredient ingredient, ItemStack result, int cuts)
    {
        super(RecipeType.viking_saw, group);

        this.ingredient = ingredient;
        this.result = result;
        this.cuts = cuts;

        this.id = id;
    }

    public boolean matches(ItemStack input)
    {
        return this.ingredient.test(input);
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
        return this.result;
    }

    @Override
    public ResourceLocation getId()
    {
        return this.id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer()
    {
        return RecipeSerializers.viking_saw.get();
    }

    @Override
    public IRecipeType<?> getType()
    {
        return this.type;
    }

    public static class Serializer<T extends VikingSawRecipe> extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<VikingSawRecipe>
    {
        @Override
        public VikingSawRecipe read(ResourceLocation recipeId, JsonObject json)
        {
            String group = JSONUtils.getString(json, "group", "");
            JsonElement ingElement = (JsonElement)(JSONUtils.isJsonArray(json, "ingredient") ? JSONUtils.getJsonArray(json, "ingredient") : JSONUtils.getJsonObject(json, "ingredient"));
            Ingredient ingredient = Ingredient.deserialize(ingElement);

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

            int hits = JSONUtils.getInt(json, "required_cuts");
            return new VikingSawRecipe(recipeId, group, ingredient, outputStack, hits);
        }

        @Nullable
        @Override
        public VikingSawRecipe read(ResourceLocation recipeId, PacketBuffer buffer)
        {
            String group = buffer.readString(32767);
            Ingredient ingredient = Ingredient.read(buffer);
            ItemStack result = buffer.readItemStack();
            int hits = buffer.readVarInt();
            return new VikingSawRecipe(recipeId, group, ingredient, result, hits);
        }

        @Override
        public void write(PacketBuffer buffer, VikingSawRecipe recipe)
        {
            buffer.writeString(recipe.getGroup());
            recipe.ingredient.write(buffer);
            buffer.writeItemStack(recipe.result);
            buffer.writeVarInt(recipe.cuts);
        }
    }
}
