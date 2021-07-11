package com.lordskittles.nordicarcanum.client;

import com.lordskittles.arcanumapi.client.render.item.ItemModelWrapper;
import com.lordskittles.nordicarcanum.magic.schools.IMagicSchool;
import com.lordskittles.nordicarcanum.client.gui.container.*;
import com.lordskittles.nordicarcanum.client.render.block.model.CraftingClothModel;
import com.lordskittles.nordicarcanum.client.render.item.*;
import com.lordskittles.nordicarcanum.client.render.tileentity.*;
import com.lordskittles.nordicarcanum.common.block.crafting.BlockCraftingCloth;
import com.lordskittles.nordicarcanum.common.item.crafting.BindingMaterial;
import com.lordskittles.nordicarcanum.common.item.crafting.RodMaterial;
import com.lordskittles.nordicarcanum.common.item.magic.ItemSigil;
import com.lordskittles.nordicarcanum.common.registry.*;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicNames;
import com.lordskittles.nordicarcanum.core.NordicResourceLocations;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.function.Function;

@Mod.EventBusSubscriber(modid = NordicArcanum.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientProxy {

    public static ModelResourceLocation MRL(Function<String, ResourceLocation> creator, String name) {

        return new ModelResourceLocation(creator.apply(name), "inventory");
    }

    @SubscribeEvent
    public static void setup(final FMLClientSetupEvent event) {

        Keys.registerKeys();

        registerGuiFactories();

        RenderTypeLookup.setRenderLayer(Blocks.yew_sapling.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(Blocks.pine_sapling.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(Blocks.juniper_sapling.get(), RenderType.getCutout());

        RenderTypeLookup.setRenderLayer(Blocks.yew_door.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(Fluids.liquid_arcanum.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(Fluids.liquid_arcanum_flowing.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(Blocks.arcane_glass.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(Blocks.arcane_tank.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(Blocks.alchemy_table.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(Blocks.staff_workbench.get(), RenderType.getTranslucent());

        RenderTypeLookup.setRenderLayer(Blocks.crafting_cloth.get(), (layer) ->
                layer == RenderType.getCutout() || layer == RenderType.getTranslucent());

        ((BlockCraftingCloth) Blocks.crafting_cloth.get()).initColorHandler(Minecraft.getInstance().getBlockColors());

        registerEntityRenders();
        registerTERenders();
        registerItemOverrides();
    }

    @SubscribeEvent
    public static void onModelBake(ModelBakeEvent event) {

        Map<ResourceLocation, IBakedModel> registry = event.getModelRegistry();

        ResourceLocation blockLoc = Blocks.crafting_cloth.get().getRegistryName();
        event.getModelRegistry().put(new ModelResourceLocation(blockLoc, ""), new CraftingClothModel());

        registerItemStackModel(registry, NordicNames.ARCANE_CHEST, model -> ItemStackArcaneChestRender.model = model);
        registerItemStackModel(registry, NordicNames.IMPOSSIBLE_CHEST, model -> ItemStackImpossibleChestRender.model = model);
        registerItemStackModel(registry, NordicNames.SIGIL_PODIUM, model -> ItemStackSigilPodiumRender.model = model);
        registerItemStackModel(registry, NordicNames.ATTUNEMENT_ALTAR, model -> ItemStackAttunementAltarRender.model = model);
        registerItemStackModel(registry, NordicNames.CRYSTAL_MATRIX, model -> ItemStackCrystalMatrixRender.model = model);
        registerItemStackModel(registry, NordicNames.NORDIC_ANVIL, model -> ItemStackNordicAnvilRender.model = model);
        registerItemStackModel(registry, NordicNames.ARCANE_INFUSER, model -> ItemStackArcaneInfuserRender.model = model);
        registerItemStackModel(registry, NordicNames.ARCANE_TANK, model -> ItemStackArcaneTankRender.model = model);

        for(BindingMaterial bindingMaterial : BindingMaterial.values()) {
            registerBinding(registry, bindingMaterial.name);
        }

        for(RodMaterial rodMaterial : RodMaterial.values()) {
            registerRod(registry, rodMaterial.name);
        }
    }

    private static void registerGuiFactories() {

        ScreenManager.registerFactory(Containers.arcane_chest.get(), ContainerScreenArcaneChest::new);
        ScreenManager.registerFactory(Containers.staff_workbench.get(), ContainerScreenStaffWorkbench::new);
        ScreenManager.registerFactory(Containers.crafting_cloth.get(), ContainerScreenCraftingCloth::new);
        ScreenManager.registerFactory(Containers.alchemy_table.get(), ContainerScreenAlchemyTable::new);
        ScreenManager.registerFactory(Containers.nordic_furnace.get(), ContainerScreenNordicFurnace::new);
        ScreenManager.registerFactory(Containers.attunement_altar.get(), ContainerScreenAttunementAltar::new);
    }

    private static void registerEntityRenders() {

    }

    private static void registerTERenders() {

        ClientRegistry.bindTileEntityRenderer(TileEntities.sigil_podium.get(), TileRendererSigilPodium::new);
        ClientRegistry.bindTileEntityRenderer(TileEntities.arcane_chest.get(), TileRendererArcaneChest::new);
        ClientRegistry.bindTileEntityRenderer(TileEntities.attunement_altar.get(), TileRendererAttunementAltar::new);
        ClientRegistry.bindTileEntityRenderer(TileEntities.crystal_matrix.get(), TileRendererCrystalMatrix::new);
        ClientRegistry.bindTileEntityRenderer(TileEntities.norse_anvil.get(), TileRendererNordicAnvil::new);
        ClientRegistry.bindTileEntityRenderer(TileEntities.arcane_infuser.get(), TileRendererArcaneInfuser::new);
        ClientRegistry.bindTileEntityRenderer(TileEntities.arcane_tank.get(), TileRendererArcaneTank::new);
    }

    private static void registerItemOverrides() {

        ItemModelsProperties.registerProperty(Items.school_sigil.get(), NordicResourceLocations.SIGIL_OVERRIDE, new IItemPropertyGetter() {

            @Override
            public float call(ItemStack stack, @Nullable ClientWorld world, @Nullable LivingEntity entity) {

                Item item = stack.getItem();
                if(item instanceof ItemSigil) {
                    ItemSigil sigil = (ItemSigil) item;
                    IMagicSchool school = sigil.getSchool(stack);

                    if(school != null) {
                        switch(school.getSchool()) {
                            case Ur:
                                return 0.1f;
                            case Kaun:
                                return 0.2f;
                            case Ar:
                                return 0.3f;
                            case Hagal:
                                return 0.4f;
                            case Yr:
                                return 0.5f;
                            case Fe:
                                return 1.0f;
                        }
                    }
                }
                return 0;
            }
        });
    }

    public static void registerBinding(Map<ResourceLocation, IBakedModel> registry, String name) {

        registerItemStackModel(registry, name + NordicNames.BINDING_SUFFIX, model -> ItemStackBindingRender.model = model);
    }

    public static void registerRod(Map<ResourceLocation, IBakedModel> registry, String name) {

        registerItemStackModel(registry, name + NordicNames.ROD_SUFFIX, model -> ItemStackRodRender.model = model);
    }

    private static void registerItemStackModel(Map<ResourceLocation, IBakedModel> modelRegistry, String name, Function<ItemModelWrapper, IBakedModel> modelSet) {

        ModelResourceLocation location = MRL(NordicArcanum::RL, name);
        modelRegistry.put(location, modelSet.apply(new ItemModelWrapper(modelRegistry.get(location))));
    }
}
