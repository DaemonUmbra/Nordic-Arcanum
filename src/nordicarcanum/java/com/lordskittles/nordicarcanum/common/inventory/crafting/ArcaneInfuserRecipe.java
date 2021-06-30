package com.lordskittles.nordicarcanum.common.inventory.crafting;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.lordskittles.arcanumapi.common.inventory.crafting.ArcaneRecipeBase;
import com.lordskittles.arcanumapi.common.utilities.JsonUtilities;
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
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class ArcaneInfuserRecipe extends ArcaneRecipeBase
{
    public final Ingredient ingredient;
    public final FluidIngredient fluidIngredient;
    public final ItemStack result;
    public final int time;

    protected final ResourceLocation id;

    public ArcaneInfuserRecipe(ResourceLocation location, String group, Ingredient ingredient, FluidIngredient fluidIngredient, ItemStack result, int time)
    {
        super(RecipeType.arcane_infuser, group);

        this.ingredient = ingredient;
        this.fluidIngredient = fluidIngredient;
        this.result = result;
        this.time = time;
        this.id = location;
    }

    public boolean matches(ItemStack itemInput, FluidStack fluidInput)
    {
        return this.ingredient.test(itemInput) && this.fluidIngredient.testFluid(fluidInput);
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
        return RecipeSerializers.arcane_infuser.get();
    }

    @Override
    public IRecipeType<?> getType()
    {
        return this.type;
    }

    public static class Serializer<T extends ArcaneInfuserRecipe> extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<ArcaneInfuserRecipe>
    {
        @Override
        public ArcaneInfuserRecipe read(ResourceLocation recipeId, JsonObject json)
        {
            String group = JSONUtils.getString(json, "group", "");
            JsonElement ingElement = (JsonElement)(JSONUtils.isJsonArray(json, "ingredient") ? JSONUtils.getJsonArray(json, "ingredient") : JSONUtils.getJsonObject(json, "ingredient"));
            Ingredient ingredient = Ingredient.deserialize(ingElement);

            Ingredient fluidIng;

            try
            {
                JsonElement fluidIngElement = (JsonElement)(JSONUtils.isJsonArray(json, "fluid_ingredient") ? JSONUtils.getJsonArray(json, "fluid_ingredient") : JSONUtils.getJsonObject(json, "fluid_ingredient"));
                fluidIng = FluidIngredient.deserialize(fluidIngElement);
            }
            catch (JsonParseException exception)
            {
                FluidStack stack = JsonUtilities.fluidStackFromJson(json.get("fluid_ingredient").getAsJsonObject());
                fluidIng = FluidIngredient.of(stack.getAmount(), stack.getFluid());
            }

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

            int time = JSONUtils.getInt(json, "time");
            return new ArcaneInfuserRecipe(recipeId, group, ingredient, (FluidIngredient) fluidIng, outputStack, time);
        }

        @Nullable
        @Override
        public ArcaneInfuserRecipe read(ResourceLocation recipeId, PacketBuffer buffer)
        {
            String group = buffer.readString(32767);
            Ingredient ingredient = Ingredient.read(buffer);
            Ingredient fluidIng = Ingredient.read(buffer);
            ItemStack result = buffer.readItemStack();
            int time = buffer.readVarInt();
            return new ArcaneInfuserRecipe(recipeId, group, ingredient, (FluidIngredient)fluidIng, result, time);
        }

        @Override
        public void write(PacketBuffer buffer, ArcaneInfuserRecipe recipe)
        {
            buffer.writeString(recipe.getGroup());
            recipe.ingredient.write(buffer);
            recipe.fluidIngredient.write(buffer);
            buffer.writeItemStack(recipe.result);
            buffer.writeVarInt(recipe.time);
        }
    }
}
