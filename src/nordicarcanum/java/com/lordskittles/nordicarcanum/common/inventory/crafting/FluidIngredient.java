package com.lordskittles.nordicarcanum.common.inventory.crafting;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.IIngredientSerializer;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FluidIngredient extends Ingredient
{
    private List<Fluid> fluids;
    private final int amount;
    private ItemStack[] cachedStacks;
    private final ResourceLocation tagId;

    private FluidIngredient()
    {
        super(Stream.empty());
        this.fluids = Collections.emptyList();
        this.amount = 0;
        this.tagId = null;
    }

    private FluidIngredient(ResourceLocation tagId, int amount)
    {
        super(Stream.empty());
        this.fluids = null; // will be filled out lazily
        this.amount = amount;
        this.tagId = tagId;
    }

    public FluidIngredient(Collection<Fluid> fluids, int amount)
    {
        super(Stream.empty());
        this.fluids = ImmutableList.copyOf(fluids);
        this.amount = amount;
        this.tagId = null;
    }

    public static FluidIngredient of(int amount, Fluid... fluids)
    {
        return new FluidIngredient(Arrays.asList(fluids), amount);
    }

    public static FluidIngredient of(int amount, ITag.INamedTag<Fluid> fluid)
    {
        return new FluidIngredient(fluid.getName(), amount);
    }

    private Collection<Fluid> getFluidList()
    {
        if (fluids == null && tagId != null)
        {
            ITag<Fluid> tag = FluidTags.getCollection().get(tagId);
            fluids = tag == null ? Collections.emptyList() : ImmutableList.copyOf(tag.getAllElements());
        }
        return fluids;
    }

    @Override
    public boolean isSimple()
    {
        return false;
    }

    @Override
    public boolean hasNoMatchingItems() {
        return getFluidList().isEmpty();
    }

    @Override
    public boolean test(@Nullable ItemStack stack)
    {
        return FluidUtil.getFluidContained(stack).map(this::testFluid).orElse(false);
    }

    @Override
    public ItemStack[] getMatchingStacks()
    {
        if (cachedStacks == null)
        {
            cachedStacks = getFluidList().stream()
                    .map(fluid -> FluidUtil.getFilledBucket(new FluidStack(fluid, 1000)))
                    .filter(stack -> !stack.isEmpty())
                    .toArray(ItemStack[]::new);
        }
        return cachedStacks;
    }

    public boolean testFluid(FluidStack fluidStack)
    {
        return getFluidList().stream().anyMatch(f -> fluidStack.getFluid() == f && fluidStack.getAmount() >= amount);
    }

    public boolean testFluid(Fluid otherFluid) {
        return getFluidList().stream().anyMatch(f -> f == otherFluid);
    }

    @Override
    public JsonElement serialize()
    {
        JsonObject json = new JsonObject();
        json.addProperty("type", Serializer.ID.toString());
        if (tagId == null)
        {
            json.addProperty("fluid", fluids.get(0).getRegistryName().toString());
        }
        else
        {
            json.addProperty("tag", tagId.toString());
        }
        json.addProperty("amount", amount);
        return json;
    }

    @Override
    public IIngredientSerializer<? extends Ingredient> getSerializer()
    {
        return Serializer.INSTANCE;
    }

    public int getAmount()
    {
        return amount;
    }

    public List<FluidStack> getFluidStacks()
    {
        return getFluidList().stream().map(fluid -> new FluidStack(fluid, amount)).collect(Collectors.toList());
    }

    public static class Serializer implements IIngredientSerializer<FluidIngredient>
    {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation("nordicarcanum:fluid");

        @Override
        public FluidIngredient parse(PacketBuffer buffer)
        {
            int n = buffer.readVarInt();
            int amount = buffer.readVarInt();
            Set<Fluid> fluids = new HashSet<>();
            for (int i = 0; i < n; i++)
            {
                fluids.add(buffer.readRegistryId());
            }
            return new FluidIngredient(fluids, amount);
        }

        @Override
        public void write(PacketBuffer buffer, FluidIngredient ingredient)
        {
            buffer.writeVarInt(ingredient.getFluidList().size());
            buffer.writeVarInt(ingredient.amount);
            for (Fluid fluid : ingredient.getFluidList())
            {
                buffer.writeRegistryId(fluid);
            }
        }

        @Override
        public FluidIngredient parse(JsonObject json)
        {
            int amount = JSONUtils.getInt(json, "amount", 1000);
            if (json.has("tag"))
            {
                ResourceLocation rl = new ResourceLocation(JSONUtils.getString(json, "tag"));
                if (FluidTags.getCollection().get(rl) == null)
                {
                    throw new JsonSyntaxException("Unknown fluid tag '" + rl + "'");
                }
                return new FluidIngredient(rl, amount);
            }
            else if (json.has("fluid"))
            {
                ResourceLocation fluidName = new ResourceLocation(JSONUtils.getString(json, "fluid"));
                Fluid fluid = ForgeRegistries.FLUIDS.getValue(fluidName);
                if (fluid == null || fluid == Fluids.EMPTY)
                {
                    throw new JsonSyntaxException("Unknown fluid '" + fluidName + "'");
                }
                return new FluidIngredient(Collections.singletonList(fluid), amount);
            }
            else
            {
                throw new JsonSyntaxException("fluid ingredient must have 'fluid' or 'tag' field!");
            }
        }
    }
}
