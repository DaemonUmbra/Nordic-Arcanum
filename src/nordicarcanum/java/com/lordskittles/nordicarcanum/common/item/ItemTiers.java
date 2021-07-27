package com.lordskittles.nordicarcanum.common.item;

import com.lordskittles.nordicarcanum.common.registry.Items;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;

import java.util.function.Supplier;

public enum ItemTiers implements Tier {
    NORSE(2, 750, 6.0F, 2.5F, 10, () -> {
        return Ingredient.of(Items.norse_ingot.get());
    }),
    THULITE(2, 1000, 7.0F, 3.0F, 10, () -> {
        return Ingredient.of(ItemTags.bind("thulite"));
    }),
    CARNELIAN(2, 1000, 7.0F, 3.0F, 10, () -> {
        return Ingredient.of(ItemTags.bind("carnelian"));
    }),
    GARNET(2, 1000, 7.0F, 3.0F, 10, () -> {
        return Ingredient.of(ItemTags.bind("garnet"));
    });

    private final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private final float attackDamage;
    private final int enchantability;
    private final LazyLoadedValue<Ingredient> repairMaterial;

    private ItemTiers(int harvestLevelIn, int maxUsesIn, float efficiencyIn, float attackDamageIn, int enchantabilityIn, Supplier<Ingredient> repairMaterialIn) {

        this.harvestLevel = harvestLevelIn;
        this.maxUses = maxUsesIn;
        this.efficiency = efficiencyIn;
        this.attackDamage = attackDamageIn;
        this.enchantability = enchantabilityIn;
        this.repairMaterial = new LazyLoadedValue<>(repairMaterialIn);
    }

    @Override
    public int getUses() {

        return this.maxUses;
    }

    @Override
    public float getSpeed() {

        return this.efficiency;
    }

    @Override
    public float getAttackDamageBonus() {

        return this.attackDamage;
    }

    @Override
    public int getLevel() {

        return this.harvestLevel;
    }

    @Override
    public int getEnchantmentValue() {

        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {

        return this.repairMaterial.get();
    }
}
