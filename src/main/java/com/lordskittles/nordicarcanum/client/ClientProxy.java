package com.lordskittles.nordicarcanum.client;

import com.lordskittles.arcanumapi.client.render.item.ItemModelWrapper;
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
import com.lordskittles.nordicarcanum.magic.schools.IMagicSchool;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.item.ItemPropertyFunction;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
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

        ItemBlockRenderTypes.setRenderLayer(Blocks.yew_sapling.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(Blocks.pine_sapling.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(Blocks.juniper_sapling.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(Blocks.yew_door.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Fluids.liquid_arcanum.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Fluids.liquid_arcanum_flowing.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Blocks.arcane_glass.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Blocks.arcane_tank.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Blocks.alchemy_table.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Blocks.staff_workbench.get(), RenderType.translucent());

        ItemBlockRenderTypes.setRenderLayer(Blocks.crafting_cloth.get(), (layer) ->
                layer == RenderType.cutout() || layer == RenderType.translucent());

        ((BlockCraftingCloth) Blocks.crafting_cloth.get()).initColorHandler(Minecraft.getInstance().getBlockColors());

        registerEntityRenders();
        registerTERenders();
        registerItemOverrides();
    }

    @SubscribeEvent
    public static void onModelBake(ModelBakeEvent event) {

        Map<ResourceLocation, BakedModel> registry = event.getModelRegistry();

        ResourceLocation blockLoc = Blocks.crafting_cloth.get().getRegistryName();
        event.getModelRegistry().put(new ModelResourceLocation(blockLoc, ""), new CraftingClothModel());

//        registerItemStackModel(registry, NordicNames.ARCANE_CHEST, model -> ItemStackArcaneChestRender.model = model);
//        registerItemStackModel(registry, NordicNames.IMPOSSIBLE_CHEST, model -> ItemStackImpossibleChestRender.model = model);
//        registerItemStackModel(registry, NordicNames.SIGIL_PODIUM, model -> ItemStackSigilPodiumRender.model = model);
//        registerItemStackModel(registry, NordicNames.ATTUNEMENT_ALTAR, model -> ItemStackAttunementAltarRender.model = model);
//        registerItemStackModel(registry, NordicNames.CRYSTAL_MATRIX, model -> ItemStackCrystalMatrixRender.model = model);
//        registerItemStackModel(registry, NordicNames.NORDIC_ANVIL, model -> ItemStackNordicAnvilRender.model = model);
//        registerItemStackModel(registry, NordicNames.ARCANE_INFUSER, model -> ItemStackArcaneInfuserRender.model = model);
//        registerItemStackModel(registry, NordicNames.ARCANE_TANK, model -> ItemStackArcaneTankRender.model = model);

        for(BindingMaterial bindingMaterial : BindingMaterial.values()) {
            registerBinding(registry, bindingMaterial.name);
        }

        for(RodMaterial rodMaterial : RodMaterial.values()) {
            registerRod(registry, rodMaterial.name);
        }
    }

    private static void registerGuiFactories() {

        MenuScreens.register(Containers.arcane_chest.get(), ContainerScreenArcaneChest::new);
        MenuScreens.register(Containers.staff_workbench.get(), ContainerScreenStaffWorkbench::new);
        MenuScreens.register(Containers.crafting_cloth.get(), ContainerScreenCraftingCloth::new);
        MenuScreens.register(Containers.alchemy_table.get(), ContainerScreenAlchemyTable::new);
        MenuScreens.register(Containers.nordic_furnace.get(), ContainerScreenNordicFurnace::new);
        MenuScreens.register(Containers.attunement_altar.get(), ContainerScreenAttunementAltar::new);
    }

    private static void registerEntityRenders() {

    }

    private static void registerTERenders() {

//        ClientRegistry.bindTileEntityRenderer(BlockEntities.sigil_podium.get(), TileRendererSigilPodium::new);
//        ClientRegistry.bindTileEntityRenderer(BlockEntities.arcane_chest.get(), TileRendererArcaneChest::new);
//        ClientRegistry.bindTileEntityRenderer(BlockEntities.attunement_altar.get(), TileRendererAttunementAltar::new);
//        ClientRegistry.bindTileEntityRenderer(BlockEntities.crystal_matrix.get(), TileRendererCrystalMatrix::new);
//        ClientRegistry.bindTileEntityRenderer(BlockEntities.norse_anvil.get(), TileRendererNordicAnvil::new);
//        ClientRegistry.bindTileEntityRenderer(BlockEntities.arcane_infuser.get(), TileRendererArcaneInfuser::new);
//        ClientRegistry.bindTileEntityRenderer(BlockEntities.arcane_tank.get(), TileRendererArcaneTank::new);
    }

    private static void registerItemOverrides() {

        ItemProperties.register(Items.school_sigil.get(), NordicResourceLocations.SIGIL_OVERRIDE, new ItemPropertyFunction() {

            @Override
            public float call(ItemStack stack, @Nullable ClientLevel world, @Nullable LivingEntity entity, int something) {

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

    public static void registerBinding(Map<ResourceLocation, BakedModel> registry, String name) {

        registerItemStackModel(registry, name + NordicNames.BINDING_SUFFIX, model -> ItemStackBindingRender.model = model);
    }

    public static void registerRod(Map<ResourceLocation, BakedModel> registry, String name) {

        registerItemStackModel(registry, name + NordicNames.ROD_SUFFIX, model -> ItemStackRodRender.model = model);
    }

    private static void registerItemStackModel(Map<ResourceLocation, BakedModel> modelRegistry, String name, Function<ItemModelWrapper, BakedModel> modelSet) {

        ModelResourceLocation location = MRL(NordicArcanum::RL, name);
        modelRegistry.put(location, modelSet.apply(new ItemModelWrapper(modelRegistry.get(location))));
    }
}
