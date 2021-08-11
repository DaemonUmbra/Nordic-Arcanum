package com.lordskittles.nordicarcanum.common.blockentity.magic;

import com.lordskittles.arcanumapi.common.utilities.NBTUtilities;
import com.lordskittles.nordicarcanum.magic.schools.IMagicSchool;
import com.lordskittles.nordicarcanum.common.item.magic.ItemSigil;
import com.lordskittles.nordicarcanum.common.registry.Sounds;
import com.lordskittles.nordicarcanum.common.registry.BlockEntities;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.AirItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class BlockEntitySigilPodium extends BlockEntity /*implements TickableBlockEntity*/ {

    private ItemStack heldSigil = ItemStack.EMPTY;
    protected int ticksExisted = 0;

    public BlockEntitySigilPodium(BlockPos pos, BlockState state) {

        super(BlockEntities.sigil_podium.get(), pos, state);
    }

    public ItemStack getHeldSigil() {

        return this.heldSigil;
    }

    @Nullable
    public IMagicSchool getSchool() {

        if(this.heldSigil != ItemStack.EMPTY && ! (this.heldSigil.getItem() instanceof AirItem)) {
            return ((ItemSigil) this.heldSigil.getItem()).getSchool(this.heldSigil);
        }

        return null;
    }

    public ItemStack setHeldSigil(ItemStack sigil) {

        if(this.heldSigil == ItemStack.EMPTY) {
            Sounds.play(SoundEvents.ITEM_PICKUP, this.level, this.getBlockPos(), 0.5f);
            this.heldSigil = sigil;
            return ItemStack.EMPTY;
        }
        else {
            ItemStack prevSigil = this.heldSigil;
            this.heldSigil = sigil;
            return prevSigil;
        }
    }

    public ItemStack removeSigil() {

        ItemStack returned = ItemStack.EMPTY;
        if(this.heldSigil != ItemStack.EMPTY) {
            returned = this.heldSigil;
            this.heldSigil = ItemStack.EMPTY;
        }

        return returned;
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket packet) {

        ItemStack stack = this.heldSigil;
        CompoundTag nbt = packet.getTag();
        super.onDataPacket(net, packet);
        load(nbt);

        if(this.level != null && this.level.isClientSide) {
            if(! this.heldSigil.equals(stack)) {
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

        super.save(compound);

        this.heldSigil.save(NBTUtilities.getPersistentData(NordicArcanum.MODID, compound));

        return compound;
    }

    @Override
    public void load(CompoundTag compound) {

        super.load(compound);

        this.heldSigil = ItemStack.of(NBTUtilities.getPersistentData(NordicArcanum.MODID, compound));
    }

//    @Override
//    public void tick() {
//
//        ticksExisted++;
//    }

    public int getTicksExisted() {

        return this.ticksExisted;
    }
}
