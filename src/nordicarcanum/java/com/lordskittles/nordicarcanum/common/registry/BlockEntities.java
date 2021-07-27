package com.lordskittles.nordicarcanum.common.registry;

import com.lordskittles.nordicarcanum.common.blockentity.crafting.*;
import com.lordskittles.nordicarcanum.common.blockentity.magic.*;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicNames;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, NordicArcanum.MODID);

    public static final RegistryObject<BlockEntityType<BlockEntityStaffWorkbench>> staff_workbench = BLOCK_ENTITIES.register(NordicNames.STAFF_WORKBENCH, () -> BlockEntityType.Builder.of(BlockEntityStaffWorkbench::new, Blocks.staff_workbench.get()).build(null));
    public static final RegistryObject<BlockEntityType<BlockEntityArcaneChest>> arcane_chest = BLOCK_ENTITIES.register(NordicNames.ARCANE_CHEST, () -> BlockEntityType.Builder.of(BlockEntityArcaneChest::new, Blocks.arcane_chest.get()).build(null));
    public static final RegistryObject<BlockEntityType<BlockEntityCraftingCloth>> crafting_cloth = BLOCK_ENTITIES.register(NordicNames.CRAFTING_CLOTH, () -> BlockEntityType.Builder.of(BlockEntityCraftingCloth::new, Blocks.crafting_cloth.get()).build(null));
    public static final RegistryObject<BlockEntityType<BlockEntityAlchemyTable>> alchemy_table = BLOCK_ENTITIES.register(NordicNames.ALCHEMY_TABLE, () -> BlockEntityType.Builder.of(BlockEntityAlchemyTable::new, Blocks.alchemy_table.get()).build(null));
    public static final RegistryObject<BlockEntityType<BlockEntityNordicAnvil>> norse_anvil = BLOCK_ENTITIES.register(NordicNames.NORDIC_ANVIL, () -> BlockEntityType.Builder.of(BlockEntityNordicAnvil::new, Blocks.norse_anvil.get()).build(null));
    public static final RegistryObject<BlockEntityType<BlockEntityNordicFurnace>> nordic_furnace = BLOCK_ENTITIES.register(NordicNames.NORDIC_FURNACE, () -> BlockEntityType.Builder.of(BlockEntityNordicFurnace::new, Blocks.nordic_furnace.get()).build(null));
    public static final RegistryObject<BlockEntityType<BlockEntitySigilPodium>> sigil_podium = BLOCK_ENTITIES.register(NordicNames.SIGIL_PODIUM, () -> BlockEntityType.Builder.of(BlockEntitySigilPodium::new, Blocks.sigil_podium.get()).build(null));
    public static final RegistryObject<BlockEntityType<BlockEntityAttunementAltar>> attunement_altar = BLOCK_ENTITIES.register(NordicNames.ATTUNEMENT_ALTAR, () -> BlockEntityType.Builder.of(BlockEntityAttunementAltar::new, Blocks.attunement_altar.get()).build(null));
    public static final RegistryObject<BlockEntityType<BlockEntityCrystalMatrix>> crystal_matrix = BLOCK_ENTITIES.register(NordicNames.CRYSTAL_MATRIX, () -> BlockEntityType.Builder.of(BlockEntityCrystalMatrix::new, Blocks.crystal_matrix.get()).build(null));
    public static final RegistryObject<BlockEntityType<BlockEntityArcaneInfuser>> arcane_infuser = BLOCK_ENTITIES.register(NordicNames.ARCANE_INFUSER, () -> BlockEntityType.Builder.of(BlockEntityArcaneInfuser::new, Blocks.arcane_infuser.get()).build(null));
    public static final RegistryObject<BlockEntityType<BlockEntityArcaneTank>> arcane_tank = BLOCK_ENTITIES.register(NordicNames.ARCANE_TANK, () -> BlockEntityType.Builder.of(BlockEntityArcaneTank::new, Blocks.arcane_tank.get()).build(null));
}