package com.lordskittles.nordicarcanum.common.registry;

import com.lordskittles.nordicarcanum.common.tileentity.crafting.*;
import com.lordskittles.nordicarcanum.common.tileentity.magic.*;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicNames;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntities
{
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, NordicArcanum.MODID);

    public static final RegistryObject<TileEntityType<TileEntityStaffWorkbench>> staff_workbench = TILE_ENTITIES.register(NordicNames.STAFF_WORKBENCH, () -> TileEntityType.Builder.create(TileEntityStaffWorkbench::new, Blocks.staff_workbench.get()).build(null));
    public static final RegistryObject<TileEntityType<TileEntityArcaneChest>> arcane_chest = TILE_ENTITIES.register(NordicNames.ARCANE_CHEST, () -> TileEntityType.Builder.create(TileEntityArcaneChest::new, Blocks.arcane_chest.get()).build(null));
    public static final RegistryObject<TileEntityType<TileEntityCraftingCloth>> crafting_cloth = TILE_ENTITIES.register(NordicNames.CRAFTING_CLOTH, () -> TileEntityType.Builder.create(TileEntityCraftingCloth::new, Blocks.crafting_cloth.get()).build(null));
    public static final RegistryObject<TileEntityType<TileEntityAlchemyTable>> alchemy_table = TILE_ENTITIES.register(NordicNames.ALCHEMY_TABLE, () -> TileEntityType.Builder.create(TileEntityAlchemyTable::new, Blocks.alchemy_table.get()).build(null));
    public static final RegistryObject<TileEntityType<TileEntityNordicAnvil>> norse_anvil = TILE_ENTITIES.register(NordicNames.NORDIC_ANVIL, () -> TileEntityType.Builder.create(TileEntityNordicAnvil::new, Blocks.norse_anvil.get()).build(null));
    public static final RegistryObject<TileEntityType<TileEntityNordicFurnace>> nordic_furnace = TILE_ENTITIES.register(NordicNames.NORDIC_FURNACE, () -> TileEntityType.Builder.create(TileEntityNordicFurnace::new, Blocks.nordic_furnace.get()).build(null));
    public static final RegistryObject<TileEntityType<TileEntitySigilPodium>> sigil_podium = TILE_ENTITIES.register(NordicNames.SIGIL_PODIUM, () -> TileEntityType.Builder.create(TileEntitySigilPodium::new, Blocks.sigil_podium.get()).build(null));
    public static final RegistryObject<TileEntityType<TileEntityAttunementAltar>> attunement_altar = TILE_ENTITIES.register(NordicNames.ATTUNEMENT_ALTAR, () -> TileEntityType.Builder.create(TileEntityAttunementAltar::new, Blocks.attunement_altar.get()).build(null));
    public static final RegistryObject<TileEntityType<TileEntityCrystalMatrix>> crystal_matrix = TILE_ENTITIES.register(NordicNames.CRYSTAL_MATRIX, () -> TileEntityType.Builder.create(TileEntityCrystalMatrix::new, Blocks.crystal_matrix.get()).build(null));
    public static final RegistryObject<TileEntityType<TileEntityArcaneInfuser>> arcane_infuser = TILE_ENTITIES.register(NordicNames.ARCANE_INFUSER, () -> TileEntityType.Builder.create(TileEntityArcaneInfuser::new, Blocks.arcane_infuser.get()).build(null));
    public static final RegistryObject<TileEntityType<TileEntityArcaneTank>> arcane_tank = TILE_ENTITIES.register(NordicNames.ARCANE_TANK, () -> TileEntityType.Builder.create(TileEntityArcaneTank::new, Blocks.arcane_tank.get()).build(null));
}