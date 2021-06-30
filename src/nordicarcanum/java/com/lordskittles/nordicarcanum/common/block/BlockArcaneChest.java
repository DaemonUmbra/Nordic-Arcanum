package com.lordskittles.nordicarcanum.common.block;

import com.lordskittles.nordicarcanum.client.itemgroups.NordicItemGroup;
import com.lordskittles.arcanumapi.common.block.BlockMagicChest;
import com.lordskittles.arcanumapi.common.block.IItemBlockOverride;
import com.lordskittles.arcanumapi.common.item.block.ItemBlockBase;
import com.lordskittles.arcanumapi.common.network.PacketHandlerBase;
import com.lordskittles.nordicarcanum.client.render.item.ItemStackArcaneChestRender;
import com.lordskittles.nordicarcanum.common.registry.TileEntities;
import com.lordskittles.nordicarcanum.common.tileentity.TileEntityArcaneChest;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicInventorySlots;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class BlockArcaneChest extends BlockMagicChest<TileEntityArcaneChest> implements IItemBlockOverride
{
    public BlockArcaneChest()
    {
        super(TileEntityArcaneChest.class, NordicItemGroup.INSTANCE, NordicArcanum.MODID, NordicInventorySlots.ARCANE_CHEST);
    }

    @Override
    protected PacketHandlerBase getPacketHandler()
    {
        return NordicArcanum.PACKET_HANDLER;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world)
    {
        return TileEntities.arcane_chest.get().create();
    }

    @Override
    public BlockItem getOverride()
    {
        return new ItemBlockBase(this, new Item.Properties().group(group()).setISTER(() -> ItemStackArcaneChestRender::new));
    }
}
