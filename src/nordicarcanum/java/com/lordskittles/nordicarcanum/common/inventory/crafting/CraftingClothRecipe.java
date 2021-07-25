package com.lordskittles.nordicarcanum.common.inventory.crafting;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.*;
import com.lordskittles.arcanumapi.common.inventory.crafting.ArcaneRecipeBase;
import com.lordskittles.arcanumapi.common.utilities.MagicUtilities;
import com.lordskittles.nordicarcanum.common.registry.RecipeSerializers;
import com.lordskittles.nordicarcanum.common.registry.RecipeType;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicNames;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.lordskittles.arcanumapi.common.inventory.crafting.ArcaneRecipeBase.DummyIInventory;

public class CraftingClothRecipe extends ArcaneRecipeBase {

    static int MAX_WIDTH = 3;
    static int MAX_HEIGHT = 3;

    public static void setCraftingSize(int width, int height) {

        if(MAX_WIDTH < width) MAX_WIDTH = width;
        if(MAX_HEIGHT < height) MAX_HEIGHT = height;
    }

    private final int width;
    private final int height;
    private final float arcanum;
    private final NonNullList<Ingredient> ingredients;
    private final ItemStack result;
    private final ResourceLocation id;

    public CraftingClothRecipe(ResourceLocation id, String group, int width, int height, float arcanum, NonNullList<Ingredient> ingredients, ItemStack result) {

        super(RecipeType.crafting_cloth, group);

        this.id = id;
        this.width = width;
        this.height = height;
        this.arcanum = arcanum;
        this.ingredients = ingredients;
        this.result = result;
    }

    public float getArcanum() {

        return this.arcanum;
    }

    public int getWidth() {

        return this.width;
    }

    public int getHeight() {

        return this.height;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {

        return ingredients;
    }

    @Override
    public ItemStack getCraftingResult(DummyIInventory inv) {

        return this.getRecipeOutput().copy();
    }

    @Override
    public boolean canFit(int width, int height) {

        return width >= this.width && height >= this.height;
    }

    @Override
    public ItemStack getRecipeOutput() {

        return this.result;
    }

    @Override
    public ResourceLocation getId() {

        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {

        return RecipeSerializers.crafting_cloth.get();
    }

    @Override
    public RecipeType<?> getType() {

        return this.type;
    }

    public boolean matchesNoArcanum(CraftingContainer inv, Player player) {

        for(int x = 0; x <= inv.getWidth() - getWidth(); ++ x) {
            for(int y = 0; y <= inv.getHeight() - getHeight(); ++ y) {
                if(this.checkMatch(inv, x, y, true))
                    return true;

                if(this.checkMatch(inv, x, y, false))
                    return true;
            }
        }

        return false;
    }

    public boolean matches(CraftingContainer inv, Player player) {

        if(! MagicUtilities.canUseArcanum(player, this.arcanum))
            return false;

        return matchesNoArcanum(inv, player);
    }

    private boolean checkMatch(CraftingContainer craftingInventory, int xInput, int yInput, boolean p_77573_4_) {

        for(int x = 0; x < craftingInventory.getWidth(); ++ x) {
            for(int y = 0; y < craftingInventory.getHeight(); ++ y) {
                int xPos = x - xInput;
                int yPos = y - yInput;

                Ingredient ingredient = Ingredient.EMPTY;
                if(xPos >= 0 && yPos >= 0 && xPos < getWidth() && yPos < getHeight()) {
                    if(p_77573_4_) {
                        ingredient = this.ingredients.get(getWidth() - xPos - 1 + yPos * getWidth());
                    }
                    else {
                        ingredient = this.ingredients.get(xPos + yPos * getWidth());
                    }
                }

                if(! ingredient.test(craftingInventory.getStackInSlot(x + y * craftingInventory.getWidth()))) {
                    return false;
                }
            }
        }

        return true;
    }

    @VisibleForTesting
    static String[] shrink(String... toShrink) {

        int i = Integer.MAX_VALUE;
        int j = 0;
        int k = 0;
        int l = 0;

        for(int i1 = 0; i1 < toShrink.length; ++ i1) {
            String s = toShrink[i1];
            i = Math.min(i, firstNonSpace(s));
            int j1 = lastNonSpace(s);
            j = Math.max(j, j1);
            if(j1 < 0) {
                if(k == i1) {
                    ++ k;
                }

                ++ l;
            }
            else {
                l = 0;
            }
        }

        if(toShrink.length == l) {
            return new String[0];
        }
        else {
            String[] astring = new String[toShrink.length - l - k];

            for(int k1 = 0; k1 < astring.length; ++ k1) {
                astring[k1] = toShrink[k1 + k].substring(i, j + 1);
            }

            return astring;
        }
    }

    private static int firstNonSpace(String str) {

        int i;
        for(i = 0; i < str.length() && str.charAt(i) == ' '; ++ i) { }

        return i;
    }

    private static int lastNonSpace(String str) {

        int i;
        for(i = str.length() - 1; i >= 0 && str.charAt(i) == ' '; -- i) {
            ;
        }

        return i;
    }

    private static String[] patternFromJson(JsonArray jsonArr) {

        String[] astring = new String[jsonArr.size()];
        if(astring.length > MAX_HEIGHT) {
            throw new JsonSyntaxException("Invalid pattern: too many rows, " + MAX_HEIGHT + " is maximum");
        }
        else
            if(astring.length == 0) {
                throw new JsonSyntaxException("Invalid pattern: empty pattern not allowed");
            }
            else {
                for(int i = 0; i < astring.length; ++ i) {
                    String s = JSONUtils.getString(jsonArr.get(i), "pattern[" + i + "]");
                    if(s.length() > MAX_WIDTH) {
                        throw new JsonSyntaxException("Invalid pattern: too many columns, " + MAX_WIDTH + " is maximum");
                    }

                    if(i > 0 && astring[0].length() != s.length()) {
                        throw new JsonSyntaxException("Invalid pattern: each row must be the same width");
                    }

                    astring[i] = s;
                }

                return astring;
            }
    }

    private static NonNullList<Ingredient> deserializeIngredients(String[] pattern, Map<String, Ingredient> keys, int patternWidth, int patternHeight) {

        NonNullList<Ingredient> nonnulllist = NonNullList.withSize(patternWidth * patternHeight, Ingredient.EMPTY);
        Set<String> set = Sets.newHashSet(keys.keySet());
        set.remove(" ");

        for(int x = 0; x < pattern.length; ++ x) {
            for(int y = 0; y < pattern[x].length(); ++ y) {
                String s = pattern[x].substring(y, y + 1);
                Ingredient ingredient = keys.get(s);
                if(ingredient == null) {
                    throw new JsonSyntaxException("Pattern references symbol '" + s + "' but it's not defined in the key");
                }

                set.remove(s);
                nonnulllist.set(y + patternWidth * x, ingredient);
            }
        }

        if(! set.isEmpty()) {
            throw new JsonSyntaxException("Key defines symbols that aren't used in pattern: " + set);
        }
        else {
            return nonnulllist;
        }
    }

    private static Map<String, Ingredient> deserializeKey(JsonObject json) {

        Map<String, Ingredient> map = Maps.newHashMap();

        for(Entry<String, JsonElement> entry : json.entrySet()) {
            if(entry.getKey().length() != 1) {
                throw new JsonSyntaxException("Invalid key entry: '" + (String) entry.getKey() + "' is an invalid symbol (must be 1 character only).");
            }

            if(" ".equals(entry.getKey())) {
                throw new JsonSyntaxException("Invalid key entry: ' ' is a reserved symbol.");
            }

            map.put(entry.getKey(), Ingredient.deserialize(entry.getValue()));
        }

        map.put(" ", Ingredient.EMPTY);
        return map;
    }

    public static ItemStack deserializeItem(JsonObject json) {

        String itemLoc = JSONUtils.getString(json, "item");
        Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemLoc));

        if(json.has("data")) {
            throw new JsonParseException("Disallowed data tag found");
        }
        else {
            return CraftingHelper.getItemStack(json, true);
        }
    }

    public static class Serializer<T extends CraftingClothRecipe> extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<CraftingClothRecipe> {

        private static final ResourceLocation NAME = NordicArcanum.RL(NordicNames.CRAFTING_CLOTH);

        @Override
        public CraftingClothRecipe read(ResourceLocation recipeId, JsonObject json) {

            String group = JSONUtils.getString(json, "group", "");
            Map<String, Ingredient> map = CraftingClothRecipe.deserializeKey(JSONUtils.getJsonObject(json, "key"));
            String[] pattern = CraftingClothRecipe.shrink(CraftingClothRecipe.patternFromJson(JSONUtils.getJsonArray(json, "pattern")));

            int rowLength = pattern[0].length();
            int colLength = pattern.length;
            NonNullList<Ingredient> ingredients = CraftingClothRecipe.deserializeIngredients(pattern, map, rowLength, colLength);
            ItemStack result = CraftingClothRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result"));
            float arcanum = JSONUtils.getFloat(json, "arcanum");

            return new CraftingClothRecipe(recipeId, group, rowLength, colLength, arcanum, ingredients, result);
        }

        @Nullable
        @Override
        public CraftingClothRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {

            int width = buffer.readVarInt();
            int height = buffer.readVarInt();
            String group = buffer.readString(32767);

            NonNullList<Ingredient> ingredients = NonNullList.withSize(width * height, Ingredient.EMPTY);
            for(int index = 0; index < ingredients.size(); index++) {
                ingredients.set(index, Ingredient.read(buffer));
            }

            ItemStack result = buffer.readItemStack();
            float arcanum = buffer.readFloat();
            return new CraftingClothRecipe(recipeId, group, width, height, arcanum, ingredients, result);
        }

        @Override
        public void write(PacketBuffer buffer, CraftingClothRecipe recipe) {

            buffer.writeVarInt(recipe.width);
            buffer.writeVarInt(recipe.height);
            buffer.writeString(recipe.group);

            for(Ingredient ingredient : recipe.ingredients) {
                ingredient.write(buffer);
            }

            buffer.writeItemStack(recipe.result);
            buffer.writeFloat(recipe.arcanum);
        }
    }
}
