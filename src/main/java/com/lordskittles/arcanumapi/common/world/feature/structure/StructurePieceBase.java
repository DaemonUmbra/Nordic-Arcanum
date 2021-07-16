package com.lordskittles.arcanumapi.common.world.feature.structure;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Random;

public abstract class StructurePieceBase extends TemplateStructurePiece {

    protected final ResourceLocation structure;
    protected final Rotation rotation;
    protected PlacementSettings placement;
    @Nullable
    protected ResourceLocation loot_table = null;
    @Nullable
    protected BlockState structure_block_replacement = null;

    public StructurePieceBase(TemplateManager manager, ResourceLocation structure, BlockPos pos, Rotation rotation, Map<ResourceLocation, BlockPos> offsetMap, IStructurePieceType structurePieceTypeIn, int componentTypeIn) {

        super(structurePieceTypeIn, componentTypeIn);

        this.structure = structure;
        this.rotation = rotation;
        BlockPos offset = offsetMap.get(this.structure);
        this.templatePosition = pos.add(offset.getX(), offset.getY(), offset.getZ());
        this.init(manager);
    }

    public StructurePieceBase(TemplateManager manager, CompoundNBT nbt, IStructurePieceType type) {

        super(type, nbt);

        this.structure = new ResourceLocation(nbt.getString("Structure"));
        this.rotation = Rotation.valueOf(nbt.getString("Rotation"));
        if(nbt.contains("Loot")) {
            this.loot_table = new ResourceLocation(nbt.getString("Loot"));
        }
        this.init(manager);
    }

    @Override
    protected void readAdditional(CompoundNBT tagCompound) {

        super.readAdditional(tagCompound);
        tagCompound.putString("Structure", this.structure.toString());
        tagCompound.putString("Rotation", this.rotation.name());
        if(this.loot_table != null) {
            tagCompound.putString("Loot", this.loot_table.toString());
        }
    }

    protected void init(TemplateManager manager) {

        Template template = manager.getTemplate(this.structure);
        this.placement = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
        this.setup(template, this.templatePosition, this.placement);
    }

    @Override
    protected void handleDataMarker(String function, BlockPos pos, IServerWorld world, Random rand, MutableBoundingBox sbb) {

        if("chest".equals(function)) {
            world.setBlockState(pos, this.structure_block_replacement != null ? this.structure_block_replacement : Blocks.AIR.getDefaultState(), 2);
            TileEntity tile = world.getTileEntity(pos.down());
            if(tile instanceof ChestTileEntity && this.loot_table != null) {
                ChestTileEntity chest = (ChestTileEntity) tile;
                chest.setLootTable(this.loot_table, rand.nextLong());
            }
        }
    }
}
