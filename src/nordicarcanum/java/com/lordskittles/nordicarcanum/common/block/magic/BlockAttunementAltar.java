package com.lordskittles.nordicarcanum.common.block.magic;

import com.lordskittles.arcanumapi.common.block.BlockMod;
import com.lordskittles.arcanumapi.common.block.IItemBlockOverride;
import com.lordskittles.arcanumapi.common.item.block.ItemBlockBase;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicItemGroup;
import com.lordskittles.nordicarcanum.common.block.voxelshapes.VoxelsAttunementAltar;
import com.lordskittles.nordicarcanum.common.blockentity.magic.BlockEntityAttunementAltar;
import com.lordskittles.nordicarcanum.common.blockentity.magic.BlockEntitySigilPodium;
import com.lordskittles.nordicarcanum.common.registry.Particles;
import com.lordskittles.nordicarcanum.magic.schools.IMagicSchool;
import com.lordskittles.nordicarcanum.magic.schools.SchoolType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fmllegacy.network.NetworkHooks;

import java.util.Random;

public class BlockAttunementAltar extends BlockMod implements IItemBlockOverride {

    public BlockAttunementAltar() {

        super(Block.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
                .strength(1.5f, 6.0f)
                .sound(SoundType.STONE)
                .harvestLevel(Tiers.STONE.getLevel())
                .harvestTool(ToolType.PICKAXE), 4);

        this.group = NordicItemGroup.INSTANCE;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {

        return VoxelsAttunementAltar.SHAPE.get();
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {

        if(! world.isClientSide) {
            BlockEntity block = world.getBlockEntity(pos);
            if(block instanceof BlockEntityAttunementAltar) {
                BlockEntityAttunementAltar altar = (BlockEntityAttunementAltar) block; altar.validateStructure();
                NetworkHooks.openGui((ServerPlayer) player, altar, pos);
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public BlockItem getOverride() {

        return new ItemBlockBase(this, new Item.Properties().tab(group()));//.setISTER(() -> ItemStackAttunementAltarRender::new));
    }

    private static final BlockPos[] offsets = new BlockPos[] { new BlockPos(3, 1, 0), new BlockPos(- 3, 1, 0), new BlockPos(0, 1, 3), new BlockPos(0, 1, - 3) };

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, Random rand) {

        super.animateTick(stateIn, worldIn, pos, rand);

        for(BlockPos offset : offsets) {
            BlockPos blockPos = pos.offset(offset);
            BlockEntity entity = worldIn.getBlockEntity(blockPos);
            if(entity instanceof BlockEntitySigilPodium) {
                BlockEntitySigilPodium podium = (BlockEntitySigilPodium) entity;
                if(podium != null && podium.getSchool() != null && podium.getSchool().getSchool() != SchoolType.Undiscovered && rand.nextInt(5) == 0) {
                    worldIn.addParticle(getParticleFromSchool(podium.getSchool()), (double) pos.getX() + 0.5D, (double) pos.getY() + 2.5D, (double) pos.getZ() + 0.5D, offset.getX() + rand.nextFloat() - 0.5D, rand.nextFloat(), offset.getZ() + rand.nextFloat() - 0.5D);
                }
            }
        }
    }

    private ParticleOptions getParticleFromSchool(IMagicSchool school) {

        switch(school.getSchool()) {
            case Ur:
                return Particles.ur_particle.get();
            case Kaun:
                return Particles.kaun_particle.get();
            case Ar:
                return Particles.ar_particle.get();
            case Hagal:
                return Particles.hagal_particle.get();
            case Yr:
                return Particles.yr_particle.get();
            case Fe:
                return Particles.fe_particle.get();
        }

        return null;
    }
}
