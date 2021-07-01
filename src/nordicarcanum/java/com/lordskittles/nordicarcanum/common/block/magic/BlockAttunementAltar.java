package com.lordskittles.nordicarcanum.common.block.magic;

import com.lordskittles.arcanumapi.common.block.BlockMod;
import com.lordskittles.arcanumapi.common.block.IItemBlockOverride;
import com.lordskittles.arcanumapi.common.item.block.ItemBlockBase;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicItemGroup;
import com.lordskittles.nordicarcanum.client.render.item.ItemStackAttunementAltarRender;
import com.lordskittles.nordicarcanum.common.block.voxelshapes.VoxelsAttunementAltar;
import com.lordskittles.nordicarcanum.common.registry.Particles;
import com.lordskittles.nordicarcanum.common.registry.TileEntities;
import com.lordskittles.nordicarcanum.common.tileentity.magic.TileEntityAttunementAltar;
import com.lordskittles.nordicarcanum.common.tileentity.magic.TileEntitySigilPodium;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockAttunementAltar extends BlockMod implements IItemBlockOverride {

    public BlockAttunementAltar() {

        super(Block.Properties.create(Material.ROCK, MaterialColor.GRAY)
                .hardnessAndResistance(1.5f, 6.0f)
                .sound(SoundType.STONE)
                .harvestLevel(ItemTier.STONE.getHarvestLevel())
                .harvestTool(ToolType.PICKAXE), 4);

        this.group = NordicItemGroup.INSTANCE;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {

        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {

        return TileEntities.attunement_altar.get().create();
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {

        return VoxelsAttunementAltar.SHAPE.get();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {

        if(! world.isRemote) {
            TileEntity tile = world.getTileEntity(pos); if(tile instanceof TileEntityAttunementAltar) {
                TileEntityAttunementAltar altar = (TileEntityAttunementAltar) tile; altar.validateStructure();
                NetworkHooks.openGui((ServerPlayerEntity) player, altar, pos);
            }
        } return ActionResultType.SUCCESS;
    }

    @Override
    public BlockItem getOverride() {

        return new ItemBlockBase(this, new Item.Properties().group(group()).setISTER(() -> ItemStackAttunementAltarRender::new));
    }

    private static final BlockPos[] offsets = new BlockPos[] { new BlockPos(3, 1, 0), new BlockPos(- 3, 1, 0), new BlockPos(0, 1, 3), new BlockPos(0, 1, - 3) };

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {

        super.animateTick(stateIn, worldIn, pos, rand);

        for(BlockPos offset : offsets) {
            BlockPos blockPos = pos.add(offset);
            TileEntitySigilPodium podium = (TileEntitySigilPodium) worldIn.getTileEntity(blockPos);
            if(podium != null && podium.getSchool() != null && rand.nextInt(5) == 0) {
                worldIn.addParticle(Particles.attunement_altar_particle.get(), (double) pos.getX() + 0.5D, (double) pos.getY() + 2.5D, (double) pos.getZ() + 0.5D, offset.getX() + rand.nextFloat() - 0.5D, rand.nextFloat(), offset.getZ() + rand.nextFloat() - 0.5D);
            }
        }

        // Retaining Enchantment table code for when we optimise the code above
//        for (int i = -2; i <= 2; ++i) {
//            for (int j = -2; j <= 2; ++j) {
//                if (i > -2 && i < 2 && j == -1) {
//                    j = 2;
//                }
//
//                if (rand.nextInt(16) == 0) {
//                    for (int k = 0; k <= 1; ++k) {
//                        BlockPos blockpos = pos.add(i, k, j);
//                        if (worldIn.getBlockState(blockpos).getBlock() instanceof  BlockSigilPodium) {
//                            if (!worldIn.isAirBlock(pos.add(i / 2, 0, j / 2))) {
//                                break;
//                            }
//
//                            worldIn.addParticle(Particles.attunement_altar_particle.get(), (double) pos.getX() + 0.5D, (double) pos.getY() + 2.0D, (double) pos.getZ() + 0.5D, (double) ((float) i + rand.nextFloat()) - 0.5D, (double) ((float) k - rand.nextFloat() - 1.0F), (double) ((float) j + rand.nextFloat()) - 0.5D);
//                        }
//                    }
//                }
//            }
//        }
    }
}
