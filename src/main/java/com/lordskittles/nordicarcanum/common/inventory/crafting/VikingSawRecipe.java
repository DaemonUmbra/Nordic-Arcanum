package com.lordskittles.nordicarcanum.common.inventory.crafting;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lordskittles.arcanumapi.common.inventory.crafting.ArcaneRecipeBase;
import com.lordskittles.nordicarcanum.common.registry.RecipeSerializers;
import com.lordskittles.nordicarcanum.common.registry.RecipeType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.util.GsonHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class VikingSawRecipe extends ArcaneRecipeBase {

    public final Ingredient ingredient;
    public final ItemStack result;
    public final int cuts;

    protected final ResourceLocation id;

    public VikingSawRecipe(ResourceLocation id, String group, Ingredient ingredient, ItemStack result, int cuts) {

        super(RecipeType.viking_saw, group);

        this.ingredient = ingredient;
        this.result = result;
        this.cuts = cuts;

        this.id = id;
    }

    public boolean matches(ItemStack input) {

        return this.ingredient.test(input);
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

        return this.result;
    }

    @Override
    public ResourceLocation getId() {

        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {

        return RecipeSerializers.viking_saw.get();
    }

    @Override
    public net.minecraft.world.item.crafting.RecipeType<?> getType() {

        return this.type;
    }

    public static class Serializer<T extends VikingSawRecipe> extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<VikingSawRecipe> {

        @Override
        public VikingSawRecipe fromJson(ResourceLocation recipeId, JsonObject json) {

            String group = GsonHelper.getAsString(json, "group", "");
            JsonElement ingElement = (JsonElement) (GsonHelper.isArrayNode(json, "ingredient") ? GsonHelper.getAsJsonArray(json, "ingredient") : GsonHelper.getAsJsonObject(json, "ingredient"));
            Ingredient ingredient = Ingredient.fromJson(ingElement);

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

            int hits = GsonHelper.getAsInt(json, "required_cuts");
            return new VikingSawRecipe(recipeId, group, ingredient, outputStack, hits);
        }

        @Nullable
        @Override
        public VikingSawRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {

            String group = buffer.readUtf(32767);
            Ingredient ingredient = Ingredient.fromNetwork(buffer);
            ItemStack result = buffer.readItem();
            int hits = buffer.readVarInt();
            return new VikingSawRecipe(recipeId, group, ingredient, result, hits);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, VikingSawRecipe recipe) {

            buffer.writeUtf(recipe.getGroup());
            recipe.ingredient.toNetwork(buffer);
            buffer.writeItemStack(recipe.result, true);
            buffer.writeVarInt(recipe.cuts);
        }
    }
}
