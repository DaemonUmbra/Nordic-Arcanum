package com.lordskittles.nordicarcanum.client.render.tileentity;

import com.lordskittles.arcanumapi.client.render.tileentity.TileEntityRendererBase;
import com.lordskittles.nordicarcanum.client.render.tileentity.model.ModelArcaneChest;
import com.lordskittles.nordicarcanum.common.blockentity.magic.BlockEntityArcaneChest;
import com.lordskittles.nordicarcanum.core.NordicResourceLocations;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;

public class TileRendererArcaneChest extends TileEntityRendererBase<BlockEntityArcaneChest> {

    private final ModelArcaneChest model = new ModelArcaneChest(NordicResourceLocations.ARCANE_CHEST);

    private int tick;
    private final float multiplier = 0.0125f;

    public TileRendererArcaneChest(BlockEntityRenderDispatcher rendererDispatcherIn) {

        super(rendererDispatcherIn);

        tick = 0;
    }

    @Override
    public void render(BlockEntityArcaneChest tileEntity, float partialTicks, PoseStack stack, MultiBufferSource buffer, int light, int destroyStage) {

//        this.tick++;
//
//        Level level = tileEntity.getWorld();
//        boolean levelExists = level != null;
//        BlockState state = levelExists ? tileEntity.getBlockState() : Blocks.arcane_chest.get().getDefaultState().with(BlockMagicChest.FACING, Direction.SOUTH);
//        Block block = state.getBlock();
//
//        if(block instanceof BlockArcaneChest) {
//            stack.push();
//
//            // Rotate chest the correct way
//            float angle = state.get(ChestBlock.FACING).getOpposite().rotateY().getHorizontalAngle();
//            stack.translate(0.5D, 0.5D, 0.5D);
//            stack.rotate(Vector3f.YP.rotationDegrees(- angle));
//            stack.translate(- 0.5D, - 0.5D, - 0.5D);
//
//            // Align chest with bounds
//            stack.translate(0.5D, 1.4375D, 0.5D);
//            stack.rotate(Vector3f.XN.rotationDegrees(180));
//
//            // Calculate bob
//            float delta = this.tick * 0.0125f;
//            float bob = MathHelper.sin(delta) * multiplier;
//            stack.translate(0D, bob, 0D);
//
//            // Calculate rotation offset
//            float rot = MathHelper.cos(delta) * multiplier;
//            Quaternion rotation = Vector3f.ZP.rotation(rot);
//            rotation.multiply(Vector3f.XP.rotation(rot));
//            rotation.multiply(Vector3f.YP.rotation(rot));
//            stack.rotate(rotation);
//
//            model.render(stack, buffer, light, destroyStage, tileEntity, partialTicks);
//
//            stack.pop();
//        }
    }
}