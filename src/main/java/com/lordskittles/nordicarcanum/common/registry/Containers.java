package com.lordskittles.nordicarcanum.common.registry;

import com.lordskittles.nordicarcanum.common.inventory.containers.*;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicNames;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Containers {

    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, NordicArcanum.MODID);

    public static final RegistryObject<MenuType<ContainerStaffWorkbench>> staff_workbench = CONTAINERS.register(NordicNames.STAFF_WORKBENCH, () -> IForgeContainerType.create(ContainerStaffWorkbench::new));
    public static final RegistryObject<MenuType<ContainerArcaneChest>> arcane_chest = CONTAINERS.register(NordicNames.ARCANE_CHEST, () -> IForgeContainerType.create(ContainerArcaneChest::new));
    public static final RegistryObject<MenuType<ContainerCraftingCloth>> crafting_cloth = CONTAINERS.register(NordicNames.CRAFTING_CLOTH, () -> IForgeContainerType.create(ContainerCraftingCloth::new));
    public static final RegistryObject<MenuType<ContainerAlchemyTable>> alchemy_table = CONTAINERS.register(NordicNames.ALCHEMY_TABLE, () -> IForgeContainerType.create(ContainerAlchemyTable::new));
    public static final RegistryObject<MenuType<ContainerNordicFurnace>> nordic_furnace = CONTAINERS.register(NordicNames.NORDIC_FURNACE, () -> IForgeContainerType.create(ContainerNordicFurnace::new));
    public static final RegistryObject<MenuType<ContainerAttunementAltar>> attunement_altar = CONTAINERS.register(NordicNames.ATTUNEMENT_ALTAR, () -> IForgeContainerType.create(ContainerAttunementAltar::new));
}
