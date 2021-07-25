package com.lordskittles.nordicarcanum.common.tileentity.magic;

import com.lordskittles.arcanumapi.common.utilities.DynamicMultiblockDetector;
import com.lordskittles.arcanumapi.common.utilities.IMultiblock;
import com.lordskittles.nordicarcanum.common.block.magic.BlockSigilPodium;
import com.lordskittles.nordicarcanum.common.inventory.containers.ContainerAttunementAltar;
import com.lordskittles.nordicarcanum.common.network.PacketMultiblockFormed;
import com.lordskittles.nordicarcanum.common.registry.TileEntities;
import com.lordskittles.nordicarcanum.common.structure.StructureResources;
import com.lordskittles.nordicarcanum.common.utility.BlockUtilities;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicNames;
import com.lordskittles.nordicarcanum.magic.GameStage;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

import javax.annotation.Nullable;
import java.util.List;

public class TileEntityAttunementAltar extends BlockEntity implements MenuProvider, IMultiblock<GameStage> {

    public DynamicMultiblockDetector<TileEntityAttunementAltar, GameStage> detector;

    public boolean isMultiblockFormed = false;

    private Component title;

    public TileEntityAttunementAltar(BlockPos pos, BlockState state) {

        super(TileEntities.attunement_altar.get(), pos, state);

        this.title = new TranslatableComponent("container." + NordicNames.ATTUNEMENT_ALTAR);
        detector = new DynamicMultiblockDetector<>(this);
    }

    public List<TileEntitySigilPodium> getPodiums() {

        return BlockUtilities.getPodiums(this.level, this.getPos());
    }

    @Override
    public Component getDisplayName() {

        return title;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int windowId, Inventory playerInv, Player player) {

        return new ContainerAttunementAltar(windowId, playerInv, this);
    }

    @Override
    public void validateStructure() {

        this.isMultiblockFormed = detector.validate(this.level, this.getPos(), GameStage.Midgard);
        sendUpdatePacket();
    }

    @Override
    public BlockPos getPos() {

        return getBlockPos();
    }

    @Override
    public ResourceLocation getRegistryName() {

        return NordicArcanum.RL(NordicNames.ATTUNEMENT_ALTAR);
    }

    @Override
    public GameStage[] getValues() {

        return GameStage.values();
    }

    @Override
    public boolean isValidBlock(BlockState type) {

        return type.getBlock() instanceof BlockSigilPodium && type.getValue(BlockSigilPodium.ISCORE);
    }

    @Override
    public StructureTemplate getTemplate(StructureManager manager) {

        return manager.get(StructureResources.getTieredAltar(GameStage.Midgard)).get();
    }

    @Override
    public boolean isFormed() {

        return this.isMultiblockFormed;
    }

    @Override
    public void setFormed(boolean formed) {

        this.isMultiblockFormed = formed;
    }

    public void sendUpdatePacket() {

        sendUpdatePacket(this);
    }

    public void sendUpdatePacket(BlockEntity tracking) {

        if(this.level.isClientSide) {
            NordicArcanum.LOG.warn("Update packet call requested from client side", new Exception());
        }
        else if(isRemoved()) {
            NordicArcanum.LOG.warn("Update packet call requested for removed tile", new Exception());
        }
        else {
            NordicArcanum.PACKET_HANDLER.sendToAllTracking(new PacketMultiblockFormed(this), tracking);
        }
    }
}
