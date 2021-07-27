package com.lordskittles.nordicarcanum.common.block.crafting;

import com.lordskittles.arcanumapi.common.block.BlockMod;
import com.lordskittles.arcanumapi.common.block.IItemBlockOverride;
import com.lordskittles.arcanumapi.common.item.block.ItemBlockBase;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicItemGroup;
import com.lordskittles.nordicarcanum.common.block.voxelshapes.VoxelsNorseAnvil;
import com.lordskittles.nordicarcanum.common.blockentity.crafting.BlockEntityNordicAnvil;
import com.lordskittles.nordicarcanum.common.item.crafting.ItemBindingCast;
import com.lordskittles.nordicarcanum.common.registry.Sounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ToolType;

public class BlockNorseAnvil extends BlockMod implements IItemBlockOverride {

    public BlockNorseAnvil() {

        super(Block.Properties.of(Material.HEAVY_METAL, MaterialColor.METAL)
                .strength(5.0F, 1200.0F)
                .sound(SoundType.ANVIL)
                .harvestLevel(Tiers.STONE.getLevel())
                .harvestTool(ToolType.PICKAXE));

        this.group = NordicItemGroup.INSTANCE;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {

        ItemStack handStack = player.getItemBySlot(EquipmentSlot.MAINHAND);

        BlockEntity entity = level.getBlockEntity(pos);
        if(entity instanceof BlockEntityNordicAnvil) {
            BlockEntityNordicAnvil anvil = (BlockEntityNordicAnvil) entity;
            boolean playSound = false;

            if(! (handStack.getItem() instanceof AirItem)) {
                ItemStack heldStack = ItemStack.EMPTY;

                if(handStack.getItem() instanceof ItemBindingCast) {
                    handStack = anvil.setCast(handStack);
                }
                else {
                    heldStack = anvil.setHeldItem(handStack);
                }

                player.setItemSlot(EquipmentSlot.MAINHAND, heldStack);
                playSound = true;
            }
            else
                if(handStack.getItem() instanceof AirItem && player.isShiftKeyDown()) {
                    player.setItemSlot(EquipmentSlot.MAINHAND, anvil.removeItems());
                    playSound = true;
                }

            if(playSound) {
                Sounds.play(SoundEvents.ANVIL_HIT, level, pos, 0.5F);
            }
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {

        return VoxelsNorseAnvil.NORTH_SOUTH.get();
    }

    @Override
    public BlockItem getOverride() {

        return new ItemBlockBase(this, new Item.Properties().tab(group()));//.setISTER(() -> ItemStackNordicAnvilRender::new));
    }
}
