package com.lordskittles.nordicarcanum.common.inventory.crafting;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lordskittles.arcanumapi.common.inventory.crafting.ArcaneRecipeBase;
import com.lordskittles.nordicarcanum.common.registry.RecipeSerializers;
import com.lordskittles.nordicarcanum.common.registry.RecipeType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.util.GsonHelper;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraftforge.common.util.RecipeMatcher;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class NordicFurnaceRecipe extends ArcaneRecipeBase {

    public final NonNullList<Ingredient> ingredients;
    public final ItemStack result;
    public final float experience;
    public final int cookTime;

    protected final ResourceLocation id;

    public NordicFurnaceRecipe(ResourceLocation id, String group, NonNullList<Ingredient> ingredients, ItemStack result, float experience, int cookTime) {

        super(RecipeType.nordic_furnace, group);
        this.ingredients = ingredients;
        this.result = result;
        this.experience = experience;
        this.cookTime = cookTime;

        this.id = id;
    }

    public boolean matches(ItemStack[] inputs) {

        NonNullList<ItemStack> items = NonNullList.withSize(inputs.length, ItemStack.EMPTY);
        items.set(0, inputs[0]);
        items.set(1, inputs[1]);

        return RecipeMatcher.findMatches(items, this.ingredients) != null;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {

        return ingredients;
    }

    @Override
    public ItemStack assemble(DummyIInventory inv) {

        return this.result;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {

        return true;
    }

    @Override
    public ItemStack getResultItem() {

        return result;
    }

    @Override
    public ResourceLocation getId() {

        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {

        return RecipeSerializers.nordic_furnace.get();
    }

    @Override
    public net.minecraft.world.item.crafting.RecipeType<?> getType() {

        return this.type;
    }

    @Override
    public String getGroup() {

        return this.group;
    }

    public int getCookTime() {

        return this.cookTime;
    }

    public static class Serializer<T extends NordicFurnaceRecipe> extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<NordicFurnaceRecipe> {

        @Override
        public NordicFurnaceRecipe fromJson(ResourceLocation recipeId, JsonObject json) {

            String group = GsonHelper.getAsString(json, "group", "");
            NonNullList<Ingredient> ingredients = readIngredients(GsonHelper.getAsJsonArray(json, "ingredients"));

            if(! json.has("result")) {
                throw new com.google.gson.JsonSyntaxException("Missing result, expected to find a string or object");
            }

            ItemStack outputStack = ItemStack.EMPTY;
            if(json.get("result").isJsonObject()) {
                outputStack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
            }
            else {
                String result = GsonHelper.getAsString(json, "result");
                ResourceLocation resultLocation = new ResourceLocation(result);
                outputStack = new ItemStack(ForgeRegistries.ITEMS.getValue(resultLocation));
            }

            float experience = GsonHelper.getAsFloat(json, "experience");
            int time = GsonHelper.getAsInt(json, "cookingtime");
            return new NordicFurnaceRecipe(recipeId, group, ingredients, outputStack, experience, time);
        }

        @Nullable
        @Override
        public NordicFurnaceRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {

            String group = buffer.readUtf(32767);

            int size = buffer.readVarInt();
            NonNullList<Ingredient> ingredients = NonNullList.withSize(size, Ingredient.EMPTY);
            for(int iter = 0; iter < size; iter++) {
                ingredients.set(iter, Ingredient.fromNetwork(buffer));
            }

            ItemStack result = buffer.readItem();
            float experience = buffer.readFloat();
            int time = buffer.readVarInt();
            return new NordicFurnaceRecipe(recipeId, group, ingredients, result, experience, time);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, NordicFurnaceRecipe recipe) {

            buffer.writeUtf(recipe.getGroup());
            buffer.writeVarInt(recipe.ingredients.size());

            for(Ingredient ingredient : recipe.ingredients) {
                ingredient.toNetwork(buffer);
            }

            buffer.writeItemStack(recipe.result, true);
            buffer.writeFloat(recipe.experience);
            buffer.writeVarInt(recipe.cookTime);
        }

        private static NonNullList<Ingredient> readIngredients(JsonArray array) {

            NonNullList<Ingredient> ingredients = NonNullList.create();

            for(int iter = 0; iter < array.size(); iter++) {
                Ingredient ingredient = Ingredient.fromJson(array.get(iter));
                if(! ingredient.isEmpty()) {
                    ingredients.add(ingredient);
                }
            }

            return ingredients;
        }
    }

}
