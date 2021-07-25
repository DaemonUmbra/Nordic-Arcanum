package com.lordskittles.arcanumapi.common.world.feature.structure;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.levelgen.feature.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.IglooPieces;
import net.minecraft.world.level.levelgen.structure.TemplateStructurePiece;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;

public abstract class StructurePieceBase extends TemplateStructurePiece {

    protected ResourceLocation templateLoc;
    protected Rotation rotation;
    protected StructurePlaceSettings placement;
    @Nullable
    protected ResourceLocation loot_table = null;
    @Nullable
    protected BlockState structure_block_replacement = null;

    public StructurePieceBase(StructurePieceType type, int genDepth, StructureManager manager, ResourceLocation template, String templateName, StructurePlaceSettings settings, BlockPos templatePosition, Map<ResourceLocation, BlockPos> offsetMap) {

        super(type, genDepth, manager, template, templateName, settings, templatePosition);

        this.rotation = settings.getRotation();
        this.templateLoc = template;
        BlockPos offset = offsetMap.get(this.templateLoc);
        this.templatePosition = templatePosition.offset(offset.getX(), offset.getY(), offset.getZ());
        this.init(manager);
    }

    public StructurePieceBase(StructurePieceType type, CompoundTag nbt, ServerLevel level, Function<ResourceLocation, StructurePlaceSettings> placeSettingsFunction) {

        super(type, nbt, level, placeSettingsFunction);

        this.templateLoc = new ResourceLocation(nbt.getString("TemplateLocation"));
        this.rotation = Rotation.valueOf(nbt.getString("Rotation"));
        if(nbt.contains("Loot")) {
            this.loot_table = new ResourceLocation(nbt.getString("Loot"));
        }
//        this.init(level.getStructureManager());
    }

    protected static StructurePlaceSettings makeSettings(Rotation rotation) {
        return (new StructurePlaceSettings()).setRotation(rotation).setMirror(Mirror.NONE).addProcessor(BlockIgnoreProcessor.STRUCTURE_BLOCK);
    }

    @Override
    protected void addAdditionalSaveData(ServerLevel level, CompoundTag tagCompound) {

        super.addAdditionalSaveData(level, tagCompound);
        tagCompound.putString("TemplateLocation", this.templateLoc.toString());
        tagCompound.putString("Rotation", this.rotation.name());
        if(this.loot_table != null) {
            tagCompound.putString("Loot", this.loot_table.toString());
        }
    }

    protected void init(StructureManager manager) {

        this.placement = (new StructurePlaceSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).addProcessor(BlockIgnoreProcessor.STRUCTURE_BLOCK);
//        this.setup(this.template, this.templatePosition, this.placement);
    }

    @Override
    protected void handleDataMarker(String function, BlockPos pos, ServerLevelAccessor world, Random rand, BoundingBox sbb) {

        if("chest".equals(function)) {
            world.setBlock(pos, this.structure_block_replacement != null ? this.structure_block_replacement : Blocks.AIR.defaultBlockState(), 2);
            BlockEntity tile = world.getBlockEntity(pos.below());
            if(tile instanceof ChestBlockEntity && this.loot_table != null) {
                ChestBlockEntity chest = (ChestBlockEntity) tile;
                chest.setLootTable(this.loot_table, rand.nextLong());
            }
        }
    }
}
