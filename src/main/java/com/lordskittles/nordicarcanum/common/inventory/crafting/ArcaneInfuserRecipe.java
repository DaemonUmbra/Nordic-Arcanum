package com.lordskittles.nordicarcanum.common.inventory.crafting;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.lordskittles.arcanumapi.common.inventory.crafting.ArcaneRecipeBase;
import com.lordskittles.arcanumapi.common.utilities.JsonUtilities;
import com.lordskittles.nordicarcanum.common.registry.RecipeSerializers;
import com.lordskittles.nordicarcanum.common.registry.RecipeType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.util.GsonHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class ArcaneInfuserRecipe extends ArcaneRecipeBase {

    public final Ingredient ingredient;
    public final FluidIngredient fluidIngredient;
    public final ItemStack result;
    public final int time;

    protected final ResourceLocation id;

    public ArcaneInfuserRecipe(ResourceLocation location, String group, Ingredient ingredient, FluidIngredient fluidIngredient, ItemStack result, int time) {

        super(RecipeType.arcane_infuser, group);

        this.ingredient = ingredient;
        this.fluidIngredient = fluidIngredient;
        this.result = result;
        this.time = time;
        this.id = location;
    }

    public boolean matches(ItemStack itemInput, FluidStack fluidInput) {

        return this.ingredient.test(itemInput) && this.fluidIngredient.testFluid(fluidInput);
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

        return RecipeSerializers.arcane_infuser.get();
    }

    @Override
    public net.minecraft.world.item.crafting.RecipeType<?> getType() {

        return this.type;
    }

    public static class Serializer<T extends ArcaneInfuserRecipe> extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<ArcaneInfuserRecipe> {

        @Override
        public ArcaneInfuserRecipe fromJson(ResourceLocation recipeId, JsonObject json) {

            String group = GsonHelper.getAsString(json, "group", "");
            JsonElement ingElement = (JsonElement) (GsonHelper.isArrayNode(json, "ingredient") ? GsonHelper.getAsJsonArray(json, "ingredient") : GsonHelper.getAsJsonObject(json, "ingredient"));
            Ingredient ingredient = Ingredient.fromJson(ingElement);

            Ingredient fluidIng;

            try {
                JsonElement fluidIngElement = (JsonElement) (GsonHelper.isArrayNode(json, "fluid_ingredient") ? GsonHelper.getAsJsonArray(json, "fluid_ingredient") : GsonHelper.getAsJsonObject(json, "fluid_ingredient"));
                fluidIng = FluidIngredient.fromJson(fluidIngElement);
            }
            catch(JsonParseException exception) {
                FluidStack stack = JsonUtilities.fluidStackFromJson(json.get("fluid_ingredient").getAsJsonObject());
                fluidIng = FluidIngredient.of(stack.getAmount(), stack.getFluid());
            }

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

            int time = GsonHelper.getAsInt(json, "time");
            return new ArcaneInfuserRecipe(recipeId, group, ingredient, (FluidIngredient) fluidIng, outputStack, time);
        }

        @Nullable
        @Override
        public ArcaneInfuserRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {

            String group = buffer.readUtf(32767);
            Ingredient ingredient = Ingredient.fromNetwork(buffer);
            Ingredient fluidIng = Ingredient.fromNetwork(buffer);
            ItemStack result = buffer.readItem();
            int time = buffer.readVarInt();
            return new ArcaneInfuserRecipe(recipeId, group, ingredient, (FluidIngredient) fluidIng, result, time);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, ArcaneInfuserRecipe recipe) {

            buffer.writeUtf(recipe.getGroup());
            recipe.ingredient.toNetwork(buffer);
            recipe.fluidIngredient.toNetwork(buffer);
            buffer.writeItemStack(recipe.result, true);
            buffer.writeVarInt(recipe.time);
        }
    }
}
