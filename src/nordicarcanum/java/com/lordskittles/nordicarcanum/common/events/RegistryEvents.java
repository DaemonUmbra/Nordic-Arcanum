package com.lordskittles.nordicarcanum.common.events;

import com.lordskittles.arcanumapi.common.block.IItemBlockOverride;
import com.lordskittles.arcanumapi.common.block.IItemGroupHolder;
import com.lordskittles.nordicarcanum.common.inventory.crafting.FluidIngredient;
import com.lordskittles.nordicarcanum.common.registry.FoliageType;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicItemGroup;
import com.lordskittles.nordicarcanum.common.registry.Blocks;
import com.lordskittles.nordicarcanum.common.registry.RecipeType;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;
import net.minecraft.world.gen.trunkplacer.TrunkPlacerType;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = NordicArcanum.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistryEvents
{
    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event)
    {
        final IForgeRegistry<Item> registry = event.getRegistry();
        Blocks.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block ->
        {
            ItemGroup group = null;
            BlockItem blockItem = null;
            if (block instanceof IItemGroupHolder)
            {
                IItemGroupHolder holder = (IItemGroupHolder)block;
                group = holder.group();
            }
            else
            {
                if (!(block instanceof FlowingFluidBlock))
                {
                    group = NordicItemGroup.INSTANCE;
                }
            }

            if (block instanceof IItemBlockOverride)
            {
                IItemBlockOverride override = (IItemBlockOverride)block;
                blockItem = override.getOverride();
            }

            final Item.Properties properties = new Item.Properties().group(group);
            blockItem = blockItem == null ? new BlockItem(block, properties) : blockItem;
            blockItem.setRegistryName(block.getRegistryName());
            registry.register(blockItem);
        });
    }

    @SubscribeEvent
    public static void onRegisterRecipeSerializers(RegistryEvent.Register<IRecipeSerializer<?>> event)
    {
        RecipeType.registerRecipeTypes(event.getRegistry());

        CraftingHelper.register(FluidIngredient.Serializer.ID, FluidIngredient.Serializer.INSTANCE);
    }

    @SubscribeEvent
    public static void onRegisterFoliagePlacers(RegistryEvent.Register<FoliagePlacerType<?>> event)
    {
        FoliageType.registerFoliageTypes(event.getRegistry());
    }
}
