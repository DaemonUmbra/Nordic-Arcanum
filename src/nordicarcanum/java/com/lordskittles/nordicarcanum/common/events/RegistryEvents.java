package com.lordskittles.nordicarcanum.common.events;

import com.lordskittles.arcanumapi.common.block.IItemBlockOverride;
import com.lordskittles.arcanumapi.common.block.IItemGroupHolder;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicItemGroup;
import com.lordskittles.nordicarcanum.common.inventory.crafting.FluidIngredient;
import com.lordskittles.nordicarcanum.common.registry.Blocks;
import com.lordskittles.nordicarcanum.common.registry.FoliageType;
import com.lordskittles.nordicarcanum.common.registry.RecipeType;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = NordicArcanum.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistryEvents {

    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event) {

        final IForgeRegistry<Item> registry = event.getRegistry();
        Blocks.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block ->
        {
            CreativeModeTab group = null;
            BlockItem blockItem = null;
            if(block instanceof IItemGroupHolder) {
                IItemGroupHolder holder = (IItemGroupHolder) block;
                group = holder.group();
            }
            else {
                if(! (block instanceof LiquidBlock)) {
                    group = NordicItemGroup.INSTANCE;
                }
            }

            if(block instanceof IItemBlockOverride) {
                IItemBlockOverride override = (IItemBlockOverride) block;
                blockItem = override.getOverride();
            }

            final Item.Properties properties = new Item.Properties().tab(group);
            blockItem = blockItem == null ? new BlockItem(block, properties) : blockItem;
            blockItem.setRegistryName(block.getRegistryName());
            registry.register(blockItem);
        });
    }

    @SubscribeEvent
    public static void onRegisterRecipeSerializers(RegistryEvent.Register<RecipeSerializer<?>> event) {

        RecipeType.registerRecipeTypes(event.getRegistry());

        CraftingHelper.register(FluidIngredient.Serializer.ID, FluidIngredient.Serializer.INSTANCE);
    }

    @SubscribeEvent
    public static void onRegisterFoliagePlacers(RegistryEvent.Register<FoliagePlacerType<?>> event) {

        FoliageType.registerFoliageTypes(event.getRegistry());
    }
}
