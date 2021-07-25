package com.lordskittles.nordicarcanum.common.inventory.crafting;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lordskittles.arcanumapi.common.inventory.crafting.ArcaneRecipeBase;
import com.lordskittles.nordicarcanum.common.registry.RecipeSerializers;
import com.lordskittles.nordicarcanum.common.registry.RecipeType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.GsonHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

import com.lordskittles.arcanumapi.common.inventory.crafting.ArcaneRecipeBase.DummyIInventory;

public class NordicAnvilRecipe extends ArcaneRecipeBase {

    public final Ingredient ingredient;
    public final ItemStack result;
    public final int requiredAmount;
    public final int hits;

    protected final ResourceLocation id;

    public NordicAnvilRecipe(ResourceLocation id, String group, Ingredient ingredient, ItemStack result, int requiredAmount, int hits) {

        super(RecipeType.nordic_anvil, group);

        this.ingredient = ingredient;
        this.result = result;
        this.requiredAmount = requiredAmount;
        this.hits = hits;

        this.id = id;
    }

    public boolean matches(ItemStack input) {

        return this.ingredient.test(input);
    }

    @Override
    public ItemStack getCraftingResult(DummyIInventory inv) {

        return this.result;
    }

    @Override
    public boolean canFit(int width, int height) {

        return true;
    }

    @Override
    public ItemStack getRecipeOutput() {

        return this.result;
    }

    @Override
    public ResourceLocation getId() {

        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {

        return RecipeSerializers.nordic_anvil.get();
    }

    @Override
    public RecipeType<?> getType() {

        return this.type;
    }

    public static class Serializer<T extends NordicAnvilRecipe> extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<NordicAnvilRecipe> {

        @Override
        public NordicAnvilRecipe read(ResourceLocation recipeId, JsonObject json) {

            String group = GsonHelper.getString(json, "group", "");
            JsonElement ingElement = (JsonElement) (JSONUtils.isJsonArray(json, "ingredient") ? JSONUtils.getJsonArray(json, "ingredient") : JSONUtils.getJsonObject(json, "ingredient"));
            Ingredient ingredient = Ingredient.deserialize(ingElement);

            if(! json.has("result")) {
                throw new com.google.gson.JsonSyntaxException("Missing result, expected to find a string or object");
            }

            ItemStack outputStack = ItemStack.EMPTY;
            if(json.get("result").isJsonObject()) {
                outputStack = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result"));
            }
            else {
                String result = JSONUtils.getString(json, "result");
                ResourceLocation resultLocation = new ResourceLocation(result);
                outputStack = new ItemStack(ForgeRegistries.ITEMS.getValue(resultLocation));
            }

            int count = JSONUtils.getInt(json, "required_count");
            int hits = JSONUtils.getInt(json, "required_hits");
            return new NordicAnvilRecipe(recipeId, group, ingredient, outputStack, hits, count);
        }

        @Nullable
        @Override
        public NordicAnvilRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {

            String group = buffer.readString(32767);
            Ingredient ingredient = Ingredient.read(buffer);
            ItemStack result = buffer.readItemStack();
            int count = buffer.readVarInt();
            int hits = buffer.readVarInt();
            return new NordicAnvilRecipe(recipeId, group, ingredient, result, hits, count);
        }

        @Override
        public void write(PacketBuffer buffer, NordicAnvilRecipe recipe) {

            buffer.writeString(recipe.getGroup());
            recipe.ingredient.write(buffer);
            buffer.writeItemStack(recipe.result);
            buffer.writeVarInt(recipe.requiredAmount);
            buffer.writeVarInt(recipe.hits);
        }
    }
}
