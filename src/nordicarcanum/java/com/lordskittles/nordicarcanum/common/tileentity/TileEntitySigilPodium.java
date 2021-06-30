package com.lordskittles.nordicarcanum.common.tileentity;

import com.lordskittles.arcanumapi.common.utilities.NBTUtilities;
import com.lordskittles.arcanumapi.magic.schools.IMagicSchool;
import com.lordskittles.nordicarcanum.common.registry.Sounds;
import com.lordskittles.nordicarcanum.common.registry.TileEntities;
import com.lordskittles.nordicarcanum.common.item.ItemSigil;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import net.minecraft.block.BlockState;
import net.minecraft.item.AirItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.SoundEvents;

import javax.annotation.Nullable;

public class TileEntitySigilPodium extends TileEntity implements ITickableTileEntity
{
    private ItemStack heldSigil = ItemStack.EMPTY;
    protected int ticksExisted = 0;

    public TileEntitySigilPodium(TileEntityType<?> type)
    {
        super(type);
    }

    public TileEntitySigilPodium()
    {
        super(TileEntities.sigil_podium.get());
    }

    public ItemStack getHeldSigil()
    {
        return this.heldSigil;
    }

    @Nullable
    public IMagicSchool getSchool()
    {
        if (this.heldSigil != ItemStack.EMPTY && !(this.heldSigil.getItem() instanceof AirItem))
        {
            return ((ItemSigil)this.heldSigil.getItem()).getSchool(this.heldSigil);
        }

        return null;
    }

    public ItemStack setHeldSigil(ItemStack sigil)
    {
        if (this.heldSigil == ItemStack.EMPTY)
        {
            Sounds.play(SoundEvents.ENTITY_ITEM_PICKUP, this.world, this.pos, 0.5f);
            this.heldSigil = sigil;
            return ItemStack.EMPTY;
        }
        else
        {
            ItemStack prevSigil = this.heldSigil;
            this.heldSigil = sigil;
            return prevSigil;
        }
    }

    public ItemStack removeSigil()
    {
        ItemStack returned = ItemStack.EMPTY;
        if (this.heldSigil != ItemStack.EMPTY)
        {
            returned = this.heldSigil;
            this.heldSigil = ItemStack.EMPTY;
        }

        return returned;
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket packet)
    {
        ItemStack stack = this.heldSigil;
        CompoundNBT nbt = packet.getNbtCompound();
        super.onDataPacket(net, packet);
        read(getBlockState(), nbt);

        if (this.world != null && this.world.isRemote)
        {
            if (!this.heldSigil.equals(stack))
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
        super.write(compound);

        this.heldSigil.write(NBTUtilities.getPersistentData(NordicArcanum.MODID, compound));

        return compound;
    }

    @Override
    public void read(BlockState state, CompoundNBT compound)
    {
        super.read(state, compound);

        this.heldSigil = ItemStack.read(NBTUtilities.getPersistentData(NordicArcanum.MODID, compound));
    }

    @Override
    public void tick()
    {
        ticksExisted++;
    }

    public int getTicksExisted()
    {
        return this.ticksExisted;
    }
}
