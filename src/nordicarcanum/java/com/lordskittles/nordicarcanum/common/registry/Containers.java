package com.lordskittles.nordicarcanum.common.registry;

import com.lordskittles.nordicarcanum.common.inventory.containers.*;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicNames;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Containers {

    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, NordicArcanum.MODID);

    public static final RegistryObject<ContainerType<ContainerStaffWorkbench>> staff_workbench = CONTAINERS.register(NordicNames.STAFF_WORKBENCH, () -> IForgeContainerType.create(ContainerStaffWorkbench::new));
    public static final RegistryObject<ContainerType<ContainerArcaneChest>> arcane_chest = CONTAINERS.register(NordicNames.ARCANE_CHEST, () -> IForgeContainerType.create(ContainerArcaneChest::new));
    public static final RegistryObject<ContainerType<ContainerCraftingCloth>> crafting_cloth = CONTAINERS.register(NordicNames.CRAFTING_CLOTH, () -> IForgeContainerType.create(ContainerCraftingCloth::new));
    public static final RegistryObject<ContainerType<ContainerAlchemyTable>> alchemy_table = CONTAINERS.register(NordicNames.ALCHEMY_TABLE, () -> IForgeContainerType.create(ContainerAlchemyTable::new));
    public static final RegistryObject<ContainerType<ContainerNordicFurnace>> nordic_furnace = CONTAINERS.register(NordicNames.NORDIC_FURNACE, () -> IForgeContainerType.create(ContainerNordicFurnace::new));
    public static final RegistryObject<ContainerType<ContainerAttunementAltar>> attunement_altar = CONTAINERS.register(NordicNames.ATTUNEMENT_ALTAR, () -> IForgeContainerType.create(ContainerAttunementAltar::new));
}
