package com.lordskittles.arcanumapi.common.utilities;

import com.lordskittles.arcanumapi.core.ArcanumAPI;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.StructureMode;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
import net.minecraft.server.level.ServerLevel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.StructureBlock;
import net.minecraft.world.level.block.state.BlockState;

public class DynamicMultiblockDetector<M extends IMultiblock, E extends Enum> {

    private class MultiblockStartInfo {

        public StructurePlaceSettings placement;
        public BlockPos position;

        public MultiblockStartInfo(StructurePlaceSettings settings, BlockPos position) {

            this.placement = settings;
            this.position = position;
        }

        @Override
        public String toString() {

            return "Pos: " + position.toString() + " Placement: " + placement.getMirror().toString() + " - " + placement.getRotation().toString();
        }
    }

    private final HashMap<E, BlockPos> coreOffset = new HashMap<>();
    private final HashMap<E, BlockPos> referenceOffset = new HashMap<>();
    private final HashMap<E, Block[]> structureBlocks = new HashMap<>();
    private final IMultiblock<E> multiblock;

    public DynamicMultiblockDetector(IMultiblock multiblock) {

        this.multiblock = multiblock;
    }

    public boolean validate(LevelAccessor world, BlockPos altar, E stage) {

        tryCacheTemplateInfo((ServerLevel) world);

        if(! structureBlocks.containsKey(stage) || ! coreOffset.containsKey(stage) || ! referenceOffset.containsKey(stage))
            return false;

        MultiblockStartInfo start = getStartFor(altar, world, stage);

        if(start == null)
            return false;

        StructureManager templateManager = ((ServerLevel) world).getStructureManager();
        StructureTemplate template = multiblock.getTemplate(templateManager);

        for(Block structureBlock : structureBlocks.get(stage)) {
            for(StructureTemplate.StructureBlockInfo block : template.filterBlocks(start.position, start.placement, structureBlock)) {
                if(block != null && ! block.state.isAir()) {
                    BlockState stateAt = world.getBlockState(block.pos);
                    if(stateAt != block.state && ! (block.state.getBlock() instanceof AirBlock))
                        return false;
                }
            }
        }

        return true;
    }

    private void tryCacheTemplateInfo(ServerLevel world) {

        for(E iter : multiblock.getValues()) {
            tryCacheTemplateInfo(world, iter);
        }
    }

    private void tryCacheTemplateInfo(ServerLevel world, E stage) {

        if(structureBlocks.containsKey(stage))
            return;

        StructureManager templateManager = world.getStructureManager();
        StructureTemplate template = multiblock.getTemplate(templateManager);

        structureBlocks.put(stage, getStructureBlocks(template));

        if(coreOffset.containsKey(stage) && referenceOffset.containsKey(stage))
            return;

        StructurePlaceSettings placement = new StructurePlaceSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE);
        BlockPos start = null;
        BlockPos core = null;
        BlockPos reference = null;

        for(StructureTemplate.StructureBlockInfo block : template.filterBlocks(BlockPos.ZERO, placement, Blocks.STRUCTURE_BLOCK)) {
            if(block.nbt != null) {
                StructureMode structureMode = StructureMode.valueOf(block.nbt.getString("mode"));
                if(structureMode == StructureMode.DATA) {
                    String function = block.nbt.getString("metadata");

                    if(start == null && function.equals("start"))
                        start = block.pos;

                    if(core == null && function.equals("core"))
                        core = block.pos;

                    if(reference == null && function.equals("reference"))
                        reference = block.pos;

                    if(start != null && core != null && reference != null)
                        break;
                }
            }
        }

        if(start == null || core == null || reference == null) {
            ArcanumAPI.LOG.fatal(multiblock.getRegistryName() + " structure for stage: " + stage.toString() + " is missing one of the following structure blocks: start, core, or reference.");
            return;
        }

        coreOffset.put(stage, core);
        referenceOffset.put(stage, core.subtract(reference));
    }

    private Block[] getStructureBlocks(StructureTemplate template) {

        List<StructureTemplate.StructureBlockInfo> blockInfos = new ArrayList<StructureTemplate.StructureBlockInfo>(template.palettes.get(0).blocks());
        List<Block> blocks = new ArrayList<>();

        for(StructureTemplate.StructureBlockInfo blockInfo : blockInfos) {
            if(blockInfo.state == Blocks.AIR.defaultBlockState() || blockInfo.state.getBlock() instanceof StructureBlock)
                continue;

            if(! blocks.contains(blockInfo.state.getBlock()))
                blocks.add(blockInfo.state.getBlock());
        }

        Block[] blockArray = new Block[blocks.size()];
        for(int i = 0; i < blockArray.length; i++) {
            blockArray[i] = blocks.get(i);
        }

        return blockArray;
    }

    private MultiblockStartInfo getStartFor(BlockPos altar, LevelAccessor world, E stage) {

        for(Rotation rotation : Rotation.values()) {
            BlockPos pos = referenceOffset.get(stage).rotate(rotation);
            BlockState state = world.getBlockState(altar.east(pos.getX()).below(pos.getY()).south(pos.getZ()));
            if(multiblock.isValidBlock(state)) {
                pos = coreOffset.get(stage).rotate(rotation);
                return new MultiblockStartInfo(new StructurePlaceSettings().setRotation(rotation.getRotated(Rotation.CLOCKWISE_180)).setMirror(Mirror.NONE), altar.east(pos.getX()).below(pos.getY()).south(pos.getZ()));
            }
        }

        return null;
    }
}
