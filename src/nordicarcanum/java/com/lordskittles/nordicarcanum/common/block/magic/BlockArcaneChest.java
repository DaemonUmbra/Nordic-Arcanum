package com.lordskittles.nordicarcanum.common.block.magic;

import com.lordskittles.arcanumapi.common.block.BlockMagicChest;
import com.lordskittles.arcanumapi.common.block.IItemBlockOverride;
import com.lordskittles.arcanumapi.common.item.block.ItemBlockBase;
import com.lordskittles.arcanumapi.common.network.PacketHandlerBase;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicItemGroup;
import com.lordskittles.nordicarcanum.client.render.item.ItemStackArcaneChestRender;
import com.lordskittles.nordicarcanum.common.registry.BlockEntities;
import com.lordskittles.nordicarcanum.common.blockentity.magic.BlockEntityArcaneChest;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicInventorySlots;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.BlockGetter;

public class BlockArcaneChest extends BlockMagicChest<BlockEntityArcaneChest> implements IItemBlockOverride {

    public BlockArcaneChest() {

        super(BlockEntityArcaneChest.class, NordicItemGroup.INSTANCE, NordicArcanum.MODID, NordicInventorySlots.ARCANE_CHEST);
    }

    @Override
    protected PacketHandlerBase getPacketHandler() {

        return NordicArcanum.PACKET_HANDLER;
    }

    @Override
    public BlockItem getOverride() {

        return new ItemBlockBase(this, new Item.Properties().tab(group()));//.setISTER(() -> ItemStackArcaneChestRender::new));
    }
}
