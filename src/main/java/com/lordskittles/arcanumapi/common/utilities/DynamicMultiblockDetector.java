package com.lordskittles.arcanumapi.common.utilities;

import com.lordskittles.arcanumapi.core.ArcanumAPI;
import net.minecraft.block.*;
import net.minecraft.state.properties.StructureMode;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.server.ServerWorld;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DynamicMultiblockDetector<M extends IMultiblock, E extends Enum>
{
    private class MultiblockStartInfo
    {
        public PlacementSettings placement;
        public BlockPos position;

        public MultiblockStartInfo(PlacementSettings settings, BlockPos position)
        {
            this.placement = settings;
            this.position = position;
        }

        @Override
        public String toString()
        {
            return "Pos: " + position.toString() + " Placement: " + placement.getMirror().toString() + " - " + placement.getRotation().toString();
        }
    }

    private final HashMap<E, BlockPos> coreOffset = new HashMap<>();
    private final HashMap<E, BlockPos> referenceOffset = new HashMap<>();
    private final HashMap<E, Block[]> structureBlocks = new HashMap<>();
    private final IMultiblock<E> multiblock;

    public DynamicMultiblockDetector(IMultiblock multiblock)
    {
        this.multiblock = multiblock;
    }

    public boolean validate(IWorld world, BlockPos altar, E stage)
    {
        tryCacheTemplateInfo((ServerWorld)world);

        if(!structureBlocks.containsKey(stage) || !coreOffset.containsKey(stage) || !referenceOffset.containsKey(stage))
            return false;

        MultiblockStartInfo start = getStartFor(altar, world, stage);

        if(start == null)
            return false;

        TemplateManager templateManager = ((ServerWorld)world).getStructureTemplateManager();
        Template template = multiblock.getTemplate(templateManager);

        for(Block structureBlock : structureBlocks.get(stage))
        {
            for(Template.BlockInfo block : template.func_215381_a(start.position, start.placement, structureBlock))
            {
                if(block != null && !block.state.isAir())
                {
                    BlockState stateAt = world.getBlockState(block.pos);
                    if(stateAt != block.state && !(block.state.getBlock() instanceof AirBlock))
                        return false;
                }
            }
        }

        return true;
    }

    private void tryCacheTemplateInfo(ServerWorld world)
    {
        for(E iter : multiblock.getValues())
        {
            tryCacheTemplateInfo(world, iter);
        }
    }

    private void tryCacheTemplateInfo(ServerWorld world, E stage)
    {
        if(structureBlocks.containsKey(stage))
            return;

        TemplateManager templateManager = world.getStructureTemplateManager();
        Template template = multiblock.getTemplate(templateManager);

        structureBlocks.put(stage, getStructureBlocks(template));

        if(coreOffset.containsKey(stage) && referenceOffset.containsKey(stage))
            return;

        PlacementSettings placement = new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE);
        BlockPos start = null;
        BlockPos core = null;
        BlockPos reference = null;

        for(Template.BlockInfo block : template.func_215381_a(BlockPos.ZERO, placement, Blocks.STRUCTURE_BLOCK))
        {
            if(block.nbt != null)
            {
                StructureMode structureMode = StructureMode.valueOf(block.nbt.getString("mode"));
                if(structureMode == StructureMode.DATA)
                {
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

        if(start == null || core == null || reference == null)
        {
            ArcanumAPI.LOG.fatal(multiblock.getRegistryName() + " structure for stage: " + stage.toString() + " is missing one of the following structure blocks: start, core, or reference.");
            return;
        }

        coreOffset.put(stage, core);
        referenceOffset.put(stage, core.subtract(reference));
    }

    private Block[] getStructureBlocks(Template template)
    {
        List<Template.BlockInfo> blockInfos = new ArrayList<Template.BlockInfo>(template.blocks.get(0).func_237157_a_());
        List<Block> blocks = new ArrayList<>();

        for(Template.BlockInfo blockInfo : blockInfos)
        {
            if(blockInfo.state == Blocks.AIR.getDefaultState() || blockInfo.state.getBlock() instanceof StructureBlock)
                continue;

            if(!blocks.contains(blockInfo.state.getBlock()))
                blocks.add(blockInfo.state.getBlock());
        }

        Block[] blockArray = new Block[blocks.size()];
        for(int i = 0; i < blockArray.length; i++)
        {
            blockArray[i] = blocks.get(i);
        }

        return blockArray;
    }

    private MultiblockStartInfo getStartFor(BlockPos altar, IWorld world, E stage)
    {
        for(Rotation rotation : Rotation.values())
        {
            BlockPos pos = referenceOffset.get(stage).rotate(rotation);
            BlockState state = world.getBlockState(altar.east(pos.getX()).down(pos.getY()).south(pos.getZ()));
            if(multiblock.isValidBlock(state))
            {
                pos = coreOffset.get(stage).rotate(rotation);
                return new MultiblockStartInfo(new PlacementSettings().setRotation(rotation.add(Rotation.CLOCKWISE_180)).setMirror(Mirror.NONE), altar.east(pos.getX()).down(pos.getY()).south(pos.getZ()));
            }
        }

        return null;
    }
}
