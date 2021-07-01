package com.lordskittles.nordicarcanum.common.item;

import com.lordskittles.nordicarcanum.common.registry.Items;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.LazyValue;

import java.util.function.Supplier;

public enum ItemTiers implements IItemTier {
    NORSE(2, 750, 6.0F, 2.5F, 10, () -> {
        return Ingredient.fromItems(Items.norse_ingot.get());
    }),
    THULITE(2, 1000, 7.0F, 3.0F, 10, () -> {
        return Ingredient.fromTag(ItemTags.makeWrapperTag("thulite"));
    }),
    CARNELIAN(2, 1000, 7.0F, 3.0F, 10, () -> {
        return Ingredient.fromTag(ItemTags.makeWrapperTag("carnelian"));
    }),
    GARNET(2, 1000, 7.0F, 3.0F, 10, () -> {
        return Ingredient.fromTag(ItemTags.makeWrapperTag("garnet"));
    });

    private final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private final float attackDamage;
    private final int enchantability;
    private final LazyValue<Ingredient> repairMaterial;

    private ItemTiers(int harvestLevelIn, int maxUsesIn, float efficiencyIn, float attackDamageIn, int enchantabilityIn, Supplier<Ingredient> repairMaterialIn) {

        this.harvestLevel = harvestLevelIn;
        this.maxUses = maxUsesIn;
        this.efficiency = efficiencyIn;
        this.attackDamage = attackDamageIn;
        this.enchantability = enchantabilityIn;
        this.repairMaterial = new LazyValue<>(repairMaterialIn);
    }

    @Override
    public int getMaxUses() {

        return this.maxUses;
    }

    @Override
    public float getEfficiency() {

        return this.efficiency;
    }

    @Override
    public float getAttackDamage() {

        return this.attackDamage;
    }

    @Override
    public int getHarvestLevel() {

        return this.harvestLevel;
    }

    @Override
    public int getEnchantability() {

        return this.enchantability;
    }

    @Override
    public Ingredient getRepairMaterial() {

        return this.repairMaterial.getValue();
    }
}
