package com.lordskittles.nordicarcanum.common.tileentity.crafting;

import com.lordskittles.arcanumapi.common.tileentity.TileEntityInventory;
import com.lordskittles.arcanumapi.common.utilities.NBTUtilities;
import com.lordskittles.arcanumapi.magic.IArcanumTile;
import com.lordskittles.nordicarcanum.common.inventory.containers.ContainerCraftingCloth;
import com.lordskittles.nordicarcanum.common.registry.TileEntities;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicInventorySlots;
import com.lordskittles.nordicarcanum.core.NordicNames;
import com.lordskittles.arcanumapi.magic.ArcanumServerManager;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.data.ModelDataMap;
import net.minecraftforge.client.model.data.ModelProperty;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.lordskittles.nordicarcanum.core.NordicNBTConstants.FACADE_NBT_KEY;

public class TileEntityCraftingCloth extends TileEntityInventory<TileEntityCraftingCloth> implements IArcanumTile
{
    public static final ModelProperty<IBakedModel> FACADE_MODEL = new ModelProperty<>();
    public static final ModelProperty<BlockState> FACADE_STATE = new ModelProperty<>();

    private BlockState facadeState;

    private float currentArcanum;
    private float maximumArcanum;

    protected TileEntityCraftingCloth(TileEntityType<?> type)
    {
        super(type, NordicInventorySlots.CRAFTING_CLOTH, NordicNames.CRAFTING_CLOTH, NordicArcanum.MODID);
    }

    public TileEntityCraftingCloth()
    {
        this(TileEntities.crafting_cloth.get());
    }

    public void setFacadeState(BlockState facadeState)
    {
        this.facadeState = facadeState;
    }

    public BlockState getFacadeState()
    {
        if (this.facadeState == null)
            return net.minecraft.block.Blocks.AIR.getDefaultState();

        return this.facadeState;
    }

    @Override
    public void updateArcanum(float currentArcanum, float maximumArcanum)
    {
        this.currentArcanum = currentArcanum;
        this.maximumArcanum = maximumArcanum;
    }

    @Nonnull
    @Override
    @OnlyIn(Dist.CLIENT)
    public IModelData getModelData()
    {
        BlockState state = getFacadeState();
        IBakedModel model = Minecraft.getInstance().getBlockRendererDispatcher().getBlockModelShapes().getModel(state);

        return new ModelDataMap.Builder().withInitial(FACADE_MODEL, model).withInitial(FACADE_STATE, state).build();
    }

    @Override
    public Container createMenu(int id, PlayerInventory playerInv, PlayerEntity player)
    {
        return new ContainerCraftingCloth(id, playerInv, this);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket packet)
    {
        BlockState oldState = getFacadeState();
        CompoundNBT nbt = packet.getNbtCompound();
        super.onDataPacket(net, packet);
        read(getBlockState(), nbt);

        if (this.world != null && this.world.isRemote)
        {
            if (!getFacadeState().equals(oldState))
            {
                this.world.markChunkDirty(getPos(), this.getTileEntity());
            }
        }
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket()
    {
        CompoundNBT nbt = new CompoundNBT();
        write(nbt);
        return new SUpdateTileEntityPacket(getPos(), 1, nbt);
    }

    @Override
    public CompoundNBT getUpdateTag()
    {
        CompoundNBT nbt = super.getUpdateTag();
        write(nbt);
        return nbt;
    }

    @Override
    public CompoundNBT write(CompoundNBT compound)
    {
        CompoundNBT base = NBTUtilities.getPersistentData(NordicArcanum.MODID, compound);

        base.put(FACADE_NBT_KEY, NBTUtil.writeBlockState(this.facadeState));

        super.write(compound);

        return compound;
    }

    @Override
    public void read(BlockState state, CompoundNBT compound)
    {
        super.read(state, compound);

        CompoundNBT base = NBTUtilities.getPersistentData(NordicArcanum.MODID, compound);

        this.setFacadeState(NBTUtil.readBlockState(base.getCompound(FACADE_NBT_KEY)));
    }

    public void onOpened(PlayerEntity player)
    {
        if (!this.world.isRemote)
        {
            try
            {
                ArcanumServerManager.updateArcanum((ServerPlayerEntity)player, this, NordicArcanum.PACKET_HANDLER);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public float getCurrentArcanum()
    {
        return this.currentArcanum;
    }

    public float getMaximumArcanum()
    {
        return this.maximumArcanum;
    }

    @Override
    public void useArcanum(PlayerEntity player, float cost)
    {
        if (this.world.isRemote)
            return;

        try
        {
            ArcanumServerManager.useArcanum((ServerPlayerEntity)player, cost, this, NordicArcanum.PACKET_HANDLER);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
