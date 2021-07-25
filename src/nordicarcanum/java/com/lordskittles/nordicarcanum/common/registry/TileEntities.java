package com.lordskittles.nordicarcanum.common.registry;

import com.lordskittles.nordicarcanum.common.tileentity.crafting.*;
import com.lordskittles.nordicarcanum.common.tileentity.magic.*;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicNames;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntities {

    public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, NordicArcanum.MODID);

    public static final RegistryObject<BlockEntityType<TileEntityStaffWorkbench>> staff_workbench = TILE_ENTITIES.register(NordicNames.STAFF_WORKBENCH, () -> BlockEntityType.Builder.of(TileEntityStaffWorkbench::new, Blocks.staff_workbench.get()).build(null));
    public static final RegistryObject<BlockEntityType<TileEntityArcaneChest>> arcane_chest = TILE_ENTITIES.register(NordicNames.ARCANE_CHEST, () -> BlockEntityType.Builder.of(TileEntityArcaneChest::new, Blocks.arcane_chest.get()).build(null));
    public static final RegistryObject<BlockEntityType<TileEntityCraftingCloth>> crafting_cloth = TILE_ENTITIES.register(NordicNames.CRAFTING_CLOTH, () -> BlockEntityType.Builder.of(TileEntityCraftingCloth::new, Blocks.crafting_cloth.get()).build(null));
    public static final RegistryObject<BlockEntityType<TileEntityAlchemyTable>> alchemy_table = TILE_ENTITIES.register(NordicNames.ALCHEMY_TABLE, () -> BlockEntityType.Builder.of(TileEntityAlchemyTable::new, Blocks.alchemy_table.get()).build(null));
    public static final RegistryObject<BlockEntityType<TileEntityNordicAnvil>> norse_anvil = TILE_ENTITIES.register(NordicNames.NORDIC_ANVIL, () -> BlockEntityType.Builder.of(TileEntityNordicAnvil::new, Blocks.norse_anvil.get()).build(null));
    public static final RegistryObject<BlockEntityType<TileEntityNordicFurnace>> nordic_furnace = TILE_ENTITIES.register(NordicNames.NORDIC_FURNACE, () -> BlockEntityType.Builder.of(TileEntityNordicFurnace::new, Blocks.nordic_furnace.get()).build(null));
    public static final RegistryObject<BlockEntityType<TileEntitySigilPodium>> sigil_podium = TILE_ENTITIES.register(NordicNames.SIGIL_PODIUM, () -> BlockEntityType.Builder.of(TileEntitySigilPodium::new, Blocks.sigil_podium.get()).build(null));
    public static final RegistryObject<BlockEntityType<TileEntityAttunementAltar>> attunement_altar = TILE_ENTITIES.register(NordicNames.ATTUNEMENT_ALTAR, () -> BlockEntityType.Builder.of(TileEntityAttunementAltar::new, Blocks.attunement_altar.get()).build(null));
    public static final RegistryObject<BlockEntityType<TileEntityCrystalMatrix>> crystal_matrix = TILE_ENTITIES.register(NordicNames.CRYSTAL_MATRIX, () -> BlockEntityType.Builder.of(TileEntityCrystalMatrix::new, Blocks.crystal_matrix.get()).build(null));
    public static final RegistryObject<BlockEntityType<TileEntityArcaneInfuser>> arcane_infuser = TILE_ENTITIES.register(NordicNames.ARCANE_INFUSER, () -> BlockEntityType.Builder.of(TileEntityArcaneInfuser::new, Blocks.arcane_infuser.get()).build(null));
    public static final RegistryObject<BlockEntityType<TileEntityArcaneTank>> arcane_tank = TILE_ENTITIES.register(NordicNames.ARCANE_TANK, () -> BlockEntityType.Builder.of(TileEntityArcaneTank::new, Blocks.arcane_tank.get()).build(null));
}