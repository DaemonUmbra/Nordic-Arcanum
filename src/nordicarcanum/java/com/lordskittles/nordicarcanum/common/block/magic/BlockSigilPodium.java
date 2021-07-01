package com.lordskittles.nordicarcanum.common.block.magic;

import com.lordskittles.arcanumapi.common.block.BlockMod;
import com.lordskittles.arcanumapi.common.block.IItemBlockOverride;
import com.lordskittles.arcanumapi.common.item.block.ItemBlockBase;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicItemGroup;
import com.lordskittles.nordicarcanum.client.render.item.ItemStackSigilPodiumRender;
import com.lordskittles.nordicarcanum.common.block.voxelshapes.VoxelsSigilPodium;
import com.lordskittles.nordicarcanum.common.item.magic.ItemSigil;
import com.lordskittles.nordicarcanum.common.registry.TileEntities;
import com.lordskittles.nordicarcanum.common.tileentity.magic.TileEntitySigilPodium;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTier;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class BlockSigilPodium extends BlockMod implements IItemBlockOverride {

    public static final BooleanProperty ISCORE = BooleanProperty.create("is_core");

    public BlockSigilPodium() {

        super(Block.Properties.create(Material.ROCK, MaterialColor.GRAY)
                .hardnessAndResistance(1.5f, 6.0f)
                .sound(SoundType.STONE)
                .harvestLevel(ItemTier.STONE.getHarvestLevel())
                .harvestTool(ToolType.PICKAXE), 4);

        this.setDefaultState(this.stateContainer.getBaseState().with(ISCORE, false));
        this.group = NordicItemGroup.INSTANCE;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {

        builder.add(ISCORE);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {

        return TileEntities.sigil_podium.get().create();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {

        return true;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {

        return VoxelsSigilPodium.SHAPE.get();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {

        TileEntity tile = world.getTileEntity(pos);
        ItemStack stack = player.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
        if(tile instanceof TileEntitySigilPodium) {
            boolean updateSlot = false;
            TileEntitySigilPodium podium = (TileEntitySigilPodium) tile;
            ItemStack heldStack = ItemStack.EMPTY;

            if(stack != ItemStack.EMPTY) {
                if(stack.getItem() instanceof ItemSigil) {
                    heldStack = podium.setHeldSigil(stack);
                    updateSlot = true;
                }
            }
            else {
                if(player.isSneaking()) {
                    heldStack = podium.removeSigil();
                    updateSlot = true;
                }
            }

            if(updateSlot) {
                player.setItemStackToSlot(EquipmentSlotType.MAINHAND, heldStack);
            }
        }

        return ActionResultType.SUCCESS;
    }

    @Override
    public BlockItem getOverride() {

        return new ItemBlockBase(this, new Item.Properties().group(group()).setISTER(() -> ItemStackSigilPodiumRender::new));
    }
}
