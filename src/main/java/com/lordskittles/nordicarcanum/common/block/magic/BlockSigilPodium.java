package com.lordskittles.nordicarcanum.common.block.magic;

import com.lordskittles.arcanumapi.common.block.BlockMod;
import com.lordskittles.arcanumapi.common.block.IItemBlockOverride;
import com.lordskittles.arcanumapi.common.item.block.ItemBlockBase;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicItemGroup;
import com.lordskittles.nordicarcanum.client.render.item.ItemStackSigilPodiumRender;
import com.lordskittles.nordicarcanum.common.block.IInfusable;
import com.lordskittles.nordicarcanum.common.block.voxelshapes.VoxelsSigilPodium;
import com.lordskittles.nordicarcanum.common.blockentity.magic.BlockEntitySigilPodium;
import com.lordskittles.nordicarcanum.common.item.magic.ItemSigil;
import com.lordskittles.nordicarcanum.common.registry.BlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class BlockSigilPodium extends BlockMod implements IItemBlockOverride, IInfusable {

    public static final BooleanProperty ISCORE = BooleanProperty.create("is_core");

    public BlockSigilPodium() {

        super(Block.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
                .strength(1.5f, 6.0f)
                .sound(SoundType.STONE)
                .harvestLevel(Tiers.STONE.getLevel())
                .harvestTool(ToolType.PICKAXE), 4);

        this.registerDefaultState(this.getStateDefinition().any().setValue(ISCORE, false));
        this.group = NordicItemGroup.INSTANCE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {

        builder.add(ISCORE);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {

        return VoxelsSigilPodium.SHAPE.get();
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {

        BlockEntity entity = level.getBlockEntity(pos);
        ItemStack stack = player.getItemBySlot(EquipmentSlot.MAINHAND);
        if(entity instanceof BlockEntitySigilPodium) {
            boolean updateSlot = false;
            BlockEntitySigilPodium podium = (BlockEntitySigilPodium) entity;
            ItemStack heldStack = ItemStack.EMPTY;

            if(stack != ItemStack.EMPTY) {
                if(stack.getItem() instanceof ItemSigil) {
                    heldStack = podium.setHeldSigil(stack);
                    updateSlot = true;
                }
            }
            else {
                if(player.isShiftKeyDown()) {
                    heldStack = podium.removeSigil();
                    updateSlot = true;
                }
            }

            if(updateSlot) {
                player.setItemSlot(EquipmentSlot.MAINHAND, heldStack);
            }
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public BlockItem getOverride() {

        return new ItemBlockBase(this, new Item.Properties().tab(group()));//.setISTER(() -> ItemStackSigilPodiumRender::new));
    }

    @Override
    public boolean isValid(LevelReader level, BlockPos pos, BlockPos right, BlockState state) {

        return ! state.getValue(ISCORE);
    }

    @Override
    public void infuse(Level level, BlockPos pos, BlockPos right, BlockState state, Direction direction) {

        level.setBlock(pos, state.setValue(BlockSigilPodium.ISCORE, true), 19);
    }

    @Override
    public BlockPos[] getInfusedPositions(Level world, BlockPos pos, BlockPos right, BlockState state, Direction direction) {

        return new BlockPos[] { pos };
    }
}
