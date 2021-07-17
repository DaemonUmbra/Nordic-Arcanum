package com.lordskittles.nordicarcanum.common.registry;

import com.lordskittles.nordicarcanum.client.itemgroups.NordicItemGroup;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicResourcesItemGroup;
import com.lordskittles.nordicarcanum.common.block.SidePlacement;
import com.lordskittles.nordicarcanum.common.block.crafting.*;
import com.lordskittles.nordicarcanum.common.block.decoration.*;
import com.lordskittles.nordicarcanum.common.block.magic.*;
import com.lordskittles.nordicarcanum.common.block.voxelshapes.VoxelsAlfheimLight;
import com.lordskittles.nordicarcanum.common.block.voxelshapes.VoxelsCrystalLamp;
import com.lordskittles.nordicarcanum.common.block.voxelshapes.VoxelsCrystalLight;
import com.lordskittles.nordicarcanum.common.block.world.*;
import com.lordskittles.nordicarcanum.common.block.world.trees.TreeJuniper;
import com.lordskittles.nordicarcanum.common.block.world.trees.TreePine;
import com.lordskittles.nordicarcanum.common.block.world.trees.TreeYew;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicNames;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.item.ItemTier;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Blocks {

    private static Block.Properties getFluidProperties() {

        return Block.Properties.create(Material.WATER).doesNotBlockMovement().noDrops();
    }

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, NordicArcanum.MODID);

    //WORLD BLOCKS
    public static final RegistryObject<Block> arcane_powder_ore = BLOCKS.register(NordicNames.ARCANE_POWDER + NordicNames.ORE_SUFFIX, () -> new BlockOre(ItemTier.STONE.getHarvestLevel(), 4));
    public static final RegistryObject<Block> feldspar = BLOCKS.register(NordicNames.FELDSPAR, () -> new BlockOre(ItemTier.STONE.getHarvestLevel(), 0, 1.5F));
    public static final RegistryObject<Block> carnelian_ore = BLOCKS.register(NordicNames.CARNELIAN + NordicNames.ORE_SUFFIX, () -> new BlockOre(ItemTier.IRON.getHarvestLevel(), 0));
    public static final RegistryObject<Block> garnet_ore = BLOCKS.register(NordicNames.GARNET + NordicNames.ORE_SUFFIX, () -> new BlockOre(ItemTier.IRON.getHarvestLevel(), 0));
    public static final RegistryObject<Block> thulite_ore = BLOCKS.register(NordicNames.THULITE + NordicNames.ORE_SUFFIX, () -> new BlockOre(ItemTier.IRON.getHarvestLevel(), 0));

    public static final RegistryObject<Block> bismuth_ore = BLOCKS.register(NordicNames.BISMUTH + NordicNames.ORE_SUFFIX, () -> new BlockOre(ItemTier.STONE.getHarvestLevel(), 0));
    public static final RegistryObject<Block> nickle_ore = BLOCKS.register(NordicNames.NICKLE + NordicNames.ORE_SUFFIX, () -> new BlockOre(ItemTier.IRON.getHarvestLevel(), 0));
    public static final RegistryObject<Block> silver_ore = BLOCKS.register(NordicNames.SILVER + NordicNames.ORE_SUFFIX, () -> new BlockOre(ItemTier.IRON.getHarvestLevel(), 0));

    public static final RegistryObject<Block> arcane_powder_deepslate_ore = BLOCKS.register(NordicNames.ARCANE_POWDER + NordicNames.DEEPSLATE_ORE_SUFFIX, () -> new BlockOre(ItemTier.STONE.getHarvestLevel(), 4));
    public static final RegistryObject<Block> carnelian_deepslate_ore = BLOCKS.register(NordicNames.CARNELIAN + NordicNames.DEEPSLATE_ORE_SUFFIX, () -> new BlockOre(ItemTier.IRON.getHarvestLevel(), 0));
    public static final RegistryObject<Block> garnet_deepslate_ore = BLOCKS.register(NordicNames.GARNET + NordicNames.DEEPSLATE_ORE_SUFFIX, () -> new BlockOre(ItemTier.IRON.getHarvestLevel(), 0));
    public static final RegistryObject<Block> thulite_deepslate_ore = BLOCKS.register(NordicNames.THULITE + NordicNames.DEEPSLATE_ORE_SUFFIX, () -> new BlockOre(ItemTier.IRON.getHarvestLevel(), 0));

    public static final RegistryObject<Block> bismuth_deepslate_ore = BLOCKS.register(NordicNames.BISMUTH + NordicNames.DEEPSLATE_ORE_SUFFIX, () -> new BlockOre(ItemTier.STONE.getHarvestLevel(), 0));
    public static final RegistryObject<Block> nickle_deepslate_ore = BLOCKS.register(NordicNames.NICKLE + NordicNames.DEEPSLATE_ORE_SUFFIX, () -> new BlockOre(ItemTier.IRON.getHarvestLevel(), 0));
    public static final RegistryObject<Block> silver_deepslate_ore = BLOCKS.register(NordicNames.SILVER + NordicNames.DEEPSLATE_ORE_SUFFIX, () -> new BlockOre(ItemTier.IRON.getHarvestLevel(), 0));

    public static final RegistryObject<Block> arcane_dust_compact = BLOCKS.register(NordicNames.ARCANE_DUST_COMPACT, BlockCompactResource::new);
    public static final RegistryObject<Block> carnelian_crystal_compact = BLOCKS.register(NordicNames.CARNELIAN_COMPACT, BlockCompactResource::new);
    public static final RegistryObject<Block> garnet_crystal_compact = BLOCKS.register(NordicNames.GARNET_COMPACT, BlockCompactResource::new);
    public static final RegistryObject<Block> thulite_crystal_compact = BLOCKS.register(NordicNames.THULITE_COMPACT, BlockCompactResource::new);

    public static final RegistryObject<Block> bismuth_metal_compact = BLOCKS.register(NordicNames.BISMUTH_COMPACT, BlockCompactResource::new);
    public static final RegistryObject<Block> nickle_metal_compact = BLOCKS.register(NordicNames.NICKLE_COMPACT, BlockCompactResource::new);
    public static final RegistryObject<Block> silver_metal_compact = BLOCKS.register(NordicNames.SILVER_COMPACT, BlockCompactResource::new);
    public static final RegistryObject<Block> steel_metal_compact = BLOCKS.register(NordicNames.STEEL_COMPACT, () -> new BlockInfusable(Material.IRON, MaterialColor.GRAY, BlockInfusable.InfusionType.STAFF_WORKBENCH));
    public static final RegistryObject<Block> norse_compact = BLOCKS.register(NordicNames.NORSE_COMPACT, BlockCompactResource::new);
    public static final RegistryObject<Block> electrum_compact = BLOCKS.register(NordicNames.ELECTRUM_COMPACT, BlockCompactResource::new);
    public static final RegistryObject<Block> bronze_compact = BLOCKS.register(NordicNames.BRONZE_COMPACT, BlockCompactResource::new);
    public static final RegistryObject<Block> mithril_compact = BLOCKS.register(NordicNames.MITHRIL_COMPACT, BlockCompactResource::new);
    public static final RegistryObject<Block> billon_compact = BLOCKS.register(NordicNames.BILLON_COMPACT, BlockCompactResource::new);
    public static final RegistryObject<Block> sun_steel_compact = BLOCKS.register(NordicNames.SUN_STEEL_COMPACT, BlockCompactResource::new);
    public static final RegistryObject<Block> sea_steel_compact = BLOCKS.register(NordicNames.SEA_STEEL_COMPACT, BlockCompactResource::new);

    public static final RegistryObject<Block> yew_sapling = BLOCKS.register(NordicNames.YEW + NordicNames.SAPLING_SUFFIX, () -> new BlockSapling(TreeYew::new));
    public static final RegistryObject<Block> pine_sapling = BLOCKS.register(NordicNames.PINE + NordicNames.SAPLING_SUFFIX, () -> new BlockSapling(TreePine::new));
    public static final RegistryObject<Block> juniper_sapling = BLOCKS.register(NordicNames.JUNIPER + NordicNames.SAPLING_SUFFIX, () -> new BlockSapling(TreeJuniper::new));

    public static final RegistryObject<BlockLog> yew_log = BLOCKS.register(NordicNames.YEW + NordicNames.LOG_SUFFIX, BlockLog::new);
    public static final RegistryObject<BlockLog> juniper_log = BLOCKS.register(NordicNames.JUNIPER + NordicNames.LOG_SUFFIX, BlockLog::new);
    public static final RegistryObject<BlockLog> pine_log = BLOCKS.register(NordicNames.PINE + NordicNames.LOG_SUFFIX, BlockLog::new);

    public static final RegistryObject<BlockLog> yew_log_stripped = BLOCKS.register(NordicNames.YEW + NordicNames.STRIPPED_LOG_SUFFIX, BlockLog::new);
    public static final RegistryObject<BlockLog> juniper_log_stripped = BLOCKS.register(NordicNames.JUNIPER + NordicNames.STRIPPED_LOG_SUFFIX, BlockLog::new);
    public static final RegistryObject<BlockLog> pine_log_stripped = BLOCKS.register(NordicNames.PINE + NordicNames.STRIPPED_LOG_SUFFIX, BlockLog::new);

    public static final RegistryObject<Block> yew_leaves = BLOCKS.register(NordicNames.YEW + NordicNames.LEAVES_SUFFIX, BlockLeaves::new);
    public static final RegistryObject<Block> juniper_leaves = BLOCKS.register(NordicNames.JUNIPER + NordicNames.LEAVES_SUFFIX, BlockLeaves::new);
    public static final RegistryObject<Block> pine_leaves = BLOCKS.register(NordicNames.PINE + NordicNames.LEAVES_SUFFIX, BlockLeaves::new);

    public static final RegistryObject<Block> yew_plank = BLOCKS.register(NordicNames.YEW + NordicNames.PLANKS_SUFFIX, BlockPlank::new);
    public static final RegistryObject<Block> juniper_plank = BLOCKS.register(NordicNames.JUNIPER + NordicNames.PLANKS_SUFFIX, BlockPlank::new);
    public static final RegistryObject<Block> pine_plank = BLOCKS.register(NordicNames.PINE + NordicNames.PLANKS_SUFFIX, BlockPlank::new);

    public static final RegistryObject<Block> polished_feldspar = BLOCKS.register(NordicNames.POLISHED_FELDSPAR, () -> new BlockDecoration(Material.ROCK, MaterialColor.WOOD));
    public static final RegistryObject<Block> feldspar_brick = BLOCKS.register(NordicNames.FELDSPAR_BRICK + NordicNames.DECO_SUFFIX, () -> new BlockDecoration(Material.ROCK, MaterialColor.WOOD));
    public static final RegistryObject<Block> chiseled_feldspar_brick = BLOCKS.register(NordicNames.CHISELED_FELDSPAR_BRICK + NordicNames.DECO_SUFFIX, () -> new BlockDecoration(Material.ROCK, MaterialColor.WOOD));
    public static final RegistryObject<Block> feldspar_pillar = BLOCKS.register(NordicNames.FELDSPAR + NordicNames.PILLAR_SUFFIX, BlockPillar::new);

    public static final RegistryObject<Block> cracked_feldspar = BLOCKS.register(NordicNames.CRACKED_PREFIX + NordicNames.FELDSPAR, () -> new BlockDecoration(Material.ROCK, MaterialColor.WOOD));
    public static final RegistryObject<Block> cracked_polished_feldspar = BLOCKS.register(NordicNames.CRACKED_PREFIX + NordicNames.POLISHED_FELDSPAR, () -> new BlockDecoration(Material.ROCK, MaterialColor.WOOD));
    public static final RegistryObject<Block> cracked_feldspar_brick = BLOCKS.register(NordicNames.CRACKED_PREFIX + NordicNames.FELDSPAR_BRICK, () -> new BlockDecoration(Material.ROCK, MaterialColor.WOOD));

    public static final RegistryObject<Block> mossy_feldspar = BLOCKS.register(NordicNames.MOSSY_PREFIX + NordicNames.FELDSPAR, () -> new BlockDecoration(Material.ROCK, MaterialColor.WOOD));
    public static final RegistryObject<Block> mossy_polished_feldspar = BLOCKS.register(NordicNames.MOSSY_PREFIX + NordicNames.POLISHED_FELDSPAR, () -> new BlockDecoration(Material.ROCK, MaterialColor.WOOD));
    public static final RegistryObject<Block> mossy_feldspar_brick = BLOCKS.register(NordicNames.MOSSY_PREFIX + NordicNames.FELDSPAR_BRICK, () -> new BlockDecoration(Material.ROCK, MaterialColor.WOOD));

    public static final RegistryObject<Block> carved_deepslate_brick = BLOCKS.register(NordicNames.CARVED_DEEPSLATE, () -> new BlockInfusable(Material.ROCK, MaterialColor.STONE, BlockInfusable.InfusionType.SIGIL_PODIUM));
    public static final RegistryObject<Block> deepslate_pillar = BLOCKS.register(NordicNames.DEEPSLATE_PILLAR, BlockInfusablePillar::new);

    public static final RegistryObject<Block> yew_fence = BLOCKS.register(NordicNames.YEW + NordicNames.FENCE_SUFFIX, BlockFence::new);
    public static final RegistryObject<Block> juniper_fence = BLOCKS.register(NordicNames.JUNIPER + NordicNames.FENCE_SUFFIX, BlockFence::new);
    public static final RegistryObject<Block> pine_fence = BLOCKS.register(NordicNames.PINE + NordicNames.FENCE_SUFFIX, BlockFence::new);
    public static final RegistryObject<Block> feldspar_wall = BLOCKS.register(NordicNames.FELDSPAR + NordicNames.WALL_SUFFIX, BlockWall::new);

    public static final RegistryObject<Block> yew_slab = BLOCKS.register(NordicNames.YEW + NordicNames.SLAB_SUFFIX, () -> new BlockSlab(Block.Properties.from(net.minecraft.block.Blocks.OAK_SLAB)));
    public static final RegistryObject<Block> juniper_slab = BLOCKS.register(NordicNames.JUNIPER + NordicNames.SLAB_SUFFIX, () -> new BlockSlab(Block.Properties.from(net.minecraft.block.Blocks.OAK_SLAB)));
    public static final RegistryObject<Block> pine_slab = BLOCKS.register(NordicNames.PINE + NordicNames.SLAB_SUFFIX, () -> new BlockSlab(Block.Properties.from(net.minecraft.block.Blocks.OAK_SLAB)));
    public static final RegistryObject<Block> norse_metal_slab = BLOCKS.register(NordicNames.NORSE + NordicNames.METAL_SUFFIX + NordicNames.SLAB_SUFFIX, () -> new BlockSlab(AbstractBlock.Properties.from(norse_compact.get())));

    public static final RegistryObject<Block> feldspar_slab = BLOCKS.register(NordicNames.FELDSPAR + NordicNames.SLAB_SUFFIX, BlockSlab::new);
    public static final RegistryObject<Block> polish_feldspar_slab = BLOCKS.register(NordicNames.POLISHED_FELDSPAR + NordicNames.SLAB_SUFFIX, BlockSlab::new);
    public static final RegistryObject<Block> feldspar_brick_slab = BLOCKS.register(NordicNames.FELDSPAR_BRICK + NordicNames.SLAB_SUFFIX, BlockSlab::new);

    public static final RegistryObject<Block> cracked_feldspar_slab = BLOCKS.register(NordicNames.CRACKED_PREFIX + NordicNames.FELDSPAR + NordicNames.SLAB_SUFFIX, BlockSlab::new);
    public static final RegistryObject<Block> cracked_polish_feldspar_slab = BLOCKS.register(NordicNames.CRACKED_PREFIX + NordicNames.POLISHED_FELDSPAR + NordicNames.SLAB_SUFFIX, BlockSlab::new);
    public static final RegistryObject<Block> cracked_feldspar_brick_slab = BLOCKS.register(NordicNames.CRACKED_PREFIX + NordicNames.FELDSPAR_BRICK + NordicNames.SLAB_SUFFIX, BlockSlab::new);

    public static final RegistryObject<Block> mossy_feldspar_slab = BLOCKS.register(NordicNames.MOSSY_PREFIX + NordicNames.FELDSPAR + NordicNames.SLAB_SUFFIX, BlockSlab::new);
    public static final RegistryObject<Block> mossy_polish_feldspar_slab = BLOCKS.register(NordicNames.MOSSY_PREFIX + NordicNames.POLISHED_FELDSPAR + NordicNames.SLAB_SUFFIX, BlockSlab::new);
    public static final RegistryObject<Block> mossy_feldspar_brick_slab = BLOCKS.register(NordicNames.MOSSY_PREFIX + NordicNames.FELDSPAR_BRICK + NordicNames.SLAB_SUFFIX, BlockSlab::new);

    public static final RegistryObject<Block> yew_stairs = BLOCKS.register(NordicNames.YEW + NordicNames.STAIRS_SUFFIX, () -> new BlockStairs(yew_plank.get(), Material.WOOD, SoundType.WOOD, ItemTier.WOOD.getHarvestLevel(), ToolType.AXE, 3f));
    public static final RegistryObject<Block> juniper_stairs = BLOCKS.register(NordicNames.JUNIPER + NordicNames.STAIRS_SUFFIX, () -> new BlockStairs(juniper_plank.get(), Material.WOOD, SoundType.WOOD, ItemTier.WOOD.getHarvestLevel(), ToolType.AXE, 3f));
    public static final RegistryObject<Block> pine_stairs = BLOCKS.register(NordicNames.PINE + NordicNames.STAIRS_SUFFIX, () -> new BlockStairs(pine_plank.get(), Material.WOOD, SoundType.WOOD, ItemTier.WOOD.getHarvestLevel(), ToolType.AXE, 3f));
    public static final RegistryObject<Block> norse_metal_stair = BLOCKS.register(NordicNames.NORSE + NordicNames.METAL_SUFFIX + NordicNames.STAIRS_SUFFIX, () -> new BlockStairs(norse_compact.get(), Material.IRON, SoundType.METAL));

    public static final RegistryObject<Block> feldspar_stairs = BLOCKS.register(NordicNames.FELDSPAR + NordicNames.STAIRS_SUFFIX, () -> new BlockStairs(feldspar.get()));
    public static final RegistryObject<Block> polished_feldspar_stairs = BLOCKS.register(NordicNames.POLISHED_FELDSPAR + NordicNames.STAIRS_SUFFIX, () -> new BlockStairs(polished_feldspar.get()));
    public static final RegistryObject<Block> feldspar_brick_stairs = BLOCKS.register(NordicNames.FELDSPAR_BRICK + NordicNames.STAIRS_SUFFIX, () -> new BlockStairs(feldspar_brick.get()));

    public static final RegistryObject<Block> cracked_feldspar_stairs = BLOCKS.register(NordicNames.CRACKED_PREFIX + NordicNames.FELDSPAR + NordicNames.STAIRS_SUFFIX, () -> new BlockStairs(cracked_feldspar.get()));
    public static final RegistryObject<Block> cracked_polished_feldspar_stairs = BLOCKS.register(NordicNames.CRACKED_PREFIX + NordicNames.POLISHED_FELDSPAR + NordicNames.STAIRS_SUFFIX, () -> new BlockStairs(cracked_polished_feldspar.get()));
    public static final RegistryObject<Block> cracked_feldspar_brick_stairs = BLOCKS.register(NordicNames.CRACKED_PREFIX + NordicNames.FELDSPAR_BRICK + NordicNames.STAIRS_SUFFIX, () -> new BlockStairs(cracked_feldspar_brick.get()));

    public static final RegistryObject<Block> mossy_feldspar_stairs = BLOCKS.register(NordicNames.MOSSY_PREFIX + NordicNames.FELDSPAR + NordicNames.STAIRS_SUFFIX, () -> new BlockStairs(mossy_feldspar.get()));
    public static final RegistryObject<Block> mossy_polished_feldspar_stairs = BLOCKS.register(NordicNames.MOSSY_PREFIX + NordicNames.POLISHED_FELDSPAR + NordicNames.STAIRS_SUFFIX, () -> new BlockStairs(mossy_polished_feldspar.get()));
    public static final RegistryObject<Block> mossy_feldspar_brick_stairs = BLOCKS.register(NordicNames.MOSSY_PREFIX + NordicNames.FELDSPAR_BRICK + NordicNames.STAIRS_SUFFIX, () -> new BlockStairs(mossy_feldspar_brick.get()));

    public static final RegistryObject<Block> yew_door = BLOCKS.register(NordicNames.YEW + NordicNames.DOOR_SUFFIX, BlockDoor::new);
    public static final RegistryObject<Block> juniper_door = BLOCKS.register(NordicNames.JUNIPER + NordicNames.DOOR_SUFFIX, BlockDoor::new);
    public static final RegistryObject<Block> pine_door = BLOCKS.register(NordicNames.PINE + NordicNames.DOOR_SUFFIX, BlockDoor::new);

    // Main Mod Blocks
    public static final RegistryObject<Block> crafting_cloth = BLOCKS.register(NordicNames.CRAFTING_CLOTH, BlockCraftingCloth::new);
    public static final RegistryObject<Block> staff_workbench = BLOCKS.register(NordicNames.STAFF_WORKBENCH, BlockStaffWorkbench::new);
    public static final RegistryObject<Block> alchemy_table = BLOCKS.register(NordicNames.ALCHEMY_TABLE, BlockAlchemyTable::new);
    public static final RegistryObject<Block> sigil_podium = BLOCKS.register(NordicNames.SIGIL_PODIUM, BlockSigilPodium::new);
    public static final RegistryObject<Block> attunement_altar = BLOCKS.register(NordicNames.ATTUNEMENT_ALTAR, BlockAttunementAltar::new);
    public static final RegistryObject<Block> crystal_matrix = BLOCKS.register(NordicNames.CRYSTAL_MATRIX, BlockCrystalMatrix::new);

    public static final RegistryObject<Block> arcane_chest = BLOCKS.register(NordicNames.ARCANE_CHEST, BlockArcaneChest::new);

    public static final RegistryObject<Block> norse_anvil = BLOCKS.register(NordicNames.NORDIC_ANVIL, BlockNorseAnvil::new);
    public static final RegistryObject<Block> nordic_furnace = BLOCKS.register(NordicNames.NORDIC_FURNACE, BlockNordicFurnace::new);

    public static final RegistryObject<Block> arcane_infuser = BLOCKS.register(NordicNames.ARCANE_INFUSER, BlockArcaneInfuser::new);
    public static final RegistryObject<Block> arcane_tank = BLOCKS.register(NordicNames.ARCANE_TANK, BlockArcaneTank::new);

    public static final RegistryObject<Block> arcane_glass = BLOCKS.register(NordicNames.ARCANE_GLASS, BlockArcaneGlass::new);
    public static final RegistryObject<Block> crystal_lamp = BLOCKS.register(NordicNames.CRYSTAL_LAMP, () -> new BlockLamp(SidePlacement.TOP_ONLY, VoxelsCrystalLamp.SHAPE.get()));
    public static final RegistryObject<Block> crystal_light = BLOCKS.register(NordicNames.CRYSTAL_LIGHT, () -> new BlockLamp(SidePlacement.BOTTOM_ONLY, VoxelsCrystalLight.SHAPE.get()));
    public static final RegistryObject<Block> alfheim_light = BLOCKS.register(NordicNames.ALFHEIM_LIGHT, () -> new BlockLamp(SidePlacement.NORMAL, VoxelsAlfheimLight.SHAPE.get()));

    public static final RegistryObject<Block> infused_arcane_dust_compact = BLOCKS.register(NordicNames.INFUSED + NordicNames.ARCANE_DUST_COMPACT, () -> new BlockDecoration(AbstractBlock.Properties.from(arcane_dust_compact.get()), NordicResourcesItemGroup.INSTANCE));
    public static final RegistryObject<Block> infused_norse_metal = BLOCKS.register(NordicNames.INFUSED + NordicNames.NORSE_COMPACT, () -> new BlockDecoration(AbstractBlock.Properties.from(norse_compact.get()), NordicItemGroup.INSTANCE));
    public static final RegistryObject<Block> infused_feldspar_brick = BLOCKS.register(NordicNames.INFUSED + NordicNames.FELDSPAR_BRICK, () -> new BlockDecoration(AbstractBlock.Properties.from(feldspar_brick.get()), NordicItemGroup.INSTANCE));

    public static final RegistryObject<Block> bragi_statue = BLOCKS.register(NordicNames.BRAGI_STATUE, () -> new BlockStatue(carnelian_crystal_compact.get()));
    public static final RegistryObject<Block> freya_statue = BLOCKS.register(NordicNames.FREYA_STATUE, () -> new BlockStatue(silver_metal_compact.get()));
    public static final RegistryObject<Block> hel_statue = BLOCKS.register(NordicNames.HEL_STATUE, () -> new BlockStatue(norse_compact.get()));
    public static final RegistryObject<Block> loki_statue = BLOCKS.register(NordicNames.LOKI_STATUE, () -> new BlockStatue(bismuth_metal_compact.get()));
    public static final RegistryObject<Block> njord_statue = BLOCKS.register(NordicNames.NJORD_STATUE, () -> new BlockStatue(thulite_crystal_compact.get()));
    public static final RegistryObject<Block> odin_statue = BLOCKS.register(NordicNames.ODIN_STATUE, () -> new BlockStatue(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> thor_statue = BLOCKS.register(NordicNames.THOR_STATUE, () -> new BlockStatue(steel_metal_compact.get()));
    public static final RegistryObject<Block> tyr_statue = BLOCKS.register(NordicNames.TYR_STATUE, () -> new BlockStatue(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> bifrost = BLOCKS.register(NordicNames.BIFROST, () -> new BlockDecoration(Block.Properties.create(Material.ROCK, MaterialColor.ICE).sound(SoundType.STONE).hardnessAndResistance(40F, 1000F).setRequiresTool().harvestLevel(ItemTier.DIAMOND.getHarvestLevel()).harvestTool(ToolType.PICKAXE), NordicResourcesItemGroup.INSTANCE));
    public static final RegistryObject<Block> ruined_bifrost = BLOCKS.register(NordicNames.RUINED_BIFROST, () -> new BlockDecoration(Block.Properties.create(Material.ROCK, MaterialColor.ICE).sound(SoundType.STONE).hardnessAndResistance(40F, 1000F).setRequiresTool().harvestLevel(ItemTier.DIAMOND.getHarvestLevel()).harvestTool(ToolType.PICKAXE), NordicResourcesItemGroup.INSTANCE));

    public static final RegistryObject<FlowingFluidBlock> liquid_arcanum = BLOCKS.register(NordicNames.LIQUID_ARCANUM, () -> new FlowingFluidBlock(() -> (FlowingFluid) Fluids.liquid_arcanum.get(), getFluidProperties()));

}
