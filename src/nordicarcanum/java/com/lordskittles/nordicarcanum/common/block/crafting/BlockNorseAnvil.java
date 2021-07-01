package com.lordskittles.nordicarcanum.common.block.crafting;

import com.lordskittles.arcanumapi.common.block.BlockMod;
import com.lordskittles.arcanumapi.common.block.IItemBlockOverride;
import com.lordskittles.arcanumapi.common.item.block.ItemBlockBase;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicItemGroup;
import com.lordskittles.nordicarcanum.client.render.item.ItemStackNordicAnvilRender;
import com.lordskittles.nordicarcanum.common.block.voxelshapes.VoxelsNorseAnvil;
import com.lordskittles.nordicarcanum.common.item.crafting.ItemBindingCast;
import com.lordskittles.nordicarcanum.common.registry.Sounds;
import com.lordskittles.nordicarcanum.common.registry.TileEntities;
import com.lordskittles.nordicarcanum.common.tileentity.crafting.TileEntityNordicAnvil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class BlockNorseAnvil extends BlockMod implements IItemBlockOverride {

    public BlockNorseAnvil() {

        super(Block.Properties.create(Material.ANVIL, MaterialColor.IRON)
                .hardnessAndResistance(5.0F, 1200.0F)
                .sound(SoundType.ANVIL)
                .harvestLevel(ItemTier.STONE.getHarvestLevel())
                .harvestTool(ToolType.PICKAXE));

        this.group = NordicItemGroup.INSTANCE;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {

        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {

        return TileEntities.norse_anvil.get().create();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {

        ItemStack handStack = player.getItemStackFromSlot(EquipmentSlotType.MAINHAND);

        TileEntity tile = world.getTileEntity(pos);
        if(tile instanceof TileEntityNordicAnvil) {
            TileEntityNordicAnvil anvil = (TileEntityNordicAnvil) tile;
            boolean playSound = false;

            if(! (handStack.getItem() instanceof AirItem)) {
                ItemStack heldStack = ItemStack.EMPTY;

                if(handStack.getItem() instanceof ItemBindingCast) {
                    handStack = anvil.setCast(handStack);
                }
                else {
                    heldStack = anvil.setHeldItem(handStack);
                }

                player.setItemStackToSlot(EquipmentSlotType.MAINHAND, heldStack);
                playSound = true;
            }
            else
                if(handStack.getItem() instanceof AirItem && player.isSneaking()) {
                    player.setItemStackToSlot(EquipmentSlotType.MAINHAND, anvil.removeItems());
                    playSound = true;
                }

            if(playSound) {
                Sounds.play(SoundEvents.BLOCK_ANVIL_HIT, world, pos, 0.5F);
            }
        }

        return ActionResultType.SUCCESS;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {

        return VoxelsNorseAnvil.NORTH_SOUTH.get();
    }

    @Override
    public BlockItem getOverride() {

        return new ItemBlockBase(this, new Item.Properties().group(group()).setISTER(() -> ItemStackNordicAnvilRender::new));
    }
}
