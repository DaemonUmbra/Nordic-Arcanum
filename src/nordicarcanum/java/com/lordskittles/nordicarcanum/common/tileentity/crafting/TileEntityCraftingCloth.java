package com.lordskittles.nordicarcanum.common.tileentity.crafting;

import com.lordskittles.arcanumapi.arcanum.ArcanumServerManager;
import com.lordskittles.arcanumapi.arcanum.IArcanumTile;
import com.lordskittles.arcanumapi.common.tileentity.TileEntityInventory;
import com.lordskittles.arcanumapi.common.utilities.NBTUtilities;
import com.lordskittles.nordicarcanum.common.inventory.containers.ContainerCraftingCloth;
import com.lordskittles.nordicarcanum.common.registry.TileEntities;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicInventorySlots;
import com.lordskittles.nordicarcanum.core.NordicNames;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.data.ModelDataMap;
import net.minecraftforge.client.model.data.ModelProperty;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.lordskittles.nordicarcanum.core.NordicNBTConstants.FACADE_NBT_KEY;

public class TileEntityCraftingCloth extends TileEntityInventory<TileEntityCraftingCloth> implements IArcanumTile {

    public static final ModelProperty<BakedModel> FACADE_MODEL = new ModelProperty<>();
    public static final ModelProperty<BlockState> FACADE_STATE = new ModelProperty<>();

    private BlockState facadeState;

    private float currentArcanum;
    private float maximumArcanum;

    public TileEntityCraftingCloth(BlockPos pos, BlockState state) {

        super(TileEntities.crafting_cloth.get(), pos, state, NordicInventorySlots.CRAFTING_CLOTH, NordicNames.CRAFTING_CLOTH, NordicArcanum.MODID);
    }

    public void setFacadeState(BlockState facadeState) {

        this.facadeState = facadeState;
    }

    public BlockState getFacadeState() {

        if(this.facadeState == null)
            return net.minecraft.world.level.block.Blocks.AIR.defaultBlockState();

        return this.facadeState;
    }

    @Override
    public void updateArcanum(float currentArcanum, float maximumArcanum) {

        this.currentArcanum = currentArcanum;
        this.maximumArcanum = maximumArcanum;
    }

    @Nonnull
    @Override
    @OnlyIn(Dist.CLIENT)
    public IModelData getModelData() {

        BlockState state = getFacadeState();
        BakedModel model = Minecraft.getInstance().getModelManager().getBlockModelShaper().getBlockModel(state);

        return new ModelDataMap.Builder().withInitial(FACADE_MODEL, model).withInitial(FACADE_STATE, state).build();
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory playerInv, Player player) {

        return new ContainerCraftingCloth(id, playerInv, this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket packet) {

        BlockState oldState = getFacadeState();
        CompoundTag nbt = packet.getTag();
        super.onDataPacket(net, packet);
        load(nbt);

        if(this.level != null && this.level.isClientSide) {
            if(! getFacadeState().equals(oldState)) {
                this.level.blockEntityChanged(getBlockPos());
            }
        }
    }

    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {

        CompoundTag nbt = new CompoundTag();
        save(nbt);
        return new ClientboundBlockEntityDataPacket(getBlockPos(), 1, nbt);
    }

    @Override
    public CompoundTag getUpdateTag() {

        CompoundTag nbt = super.getUpdateTag();
        save(nbt);
        return nbt;
    }

    @Override
    public CompoundTag save(CompoundTag compound) {

        CompoundTag base = NBTUtilities.getPersistentData(NordicArcanum.MODID, compound);

        base.put(FACADE_NBT_KEY, NbtUtils.writeBlockState(this.facadeState));

        super.save(compound);

        return compound;
    }

    @Override
    public void load(CompoundTag compound) {

        super.load(compound);

        CompoundTag base = NBTUtilities.getPersistentData(NordicArcanum.MODID, compound);

        this.setFacadeState(NbtUtils.readBlockState(base.getCompound(FACADE_NBT_KEY)));
    }

    public void onOpened(Player player) {

        if(! this.level.isClientSide) {
            try {
                ArcanumServerManager.updateArcanum((ServerPlayer) player, this, NordicArcanum.PACKET_HANDLER);
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    public float getCurrentArcanum() {

        return this.currentArcanum;
    }

    public float getMaximumArcanum() {

        return this.maximumArcanum;
    }

    @Override
    public void useArcanum(Player player, float cost) {

        if(this.level.isClientSide)
            return;

        try {
            ArcanumServerManager.useArcanum((ServerPlayer) player, cost, this, NordicArcanum.PACKET_HANDLER);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
