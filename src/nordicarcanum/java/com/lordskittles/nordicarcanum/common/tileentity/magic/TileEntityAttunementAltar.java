package com.lordskittles.nordicarcanum.common.tileentity.magic;

import com.lordskittles.arcanumapi.common.utilities.DynamicMultiblockDetector;
import com.lordskittles.arcanumapi.common.utilities.IMultiblock;
import com.lordskittles.nordicarcanum.common.block.magic.BlockSigilPodium;
import com.lordskittles.nordicarcanum.common.inventory.containers.ContainerAttunementAltar;
import com.lordskittles.nordicarcanum.common.network.PacketMultiblockFormed;
import com.lordskittles.nordicarcanum.common.structure.StructureResources;
import com.lordskittles.nordicarcanum.common.utility.BlockUtilities;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicNames;
import com.lordskittles.nordicarcanum.common.registry.TileEntities;
import com.lordskittles.nordicarcanum.magic.GameStage;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

import javax.annotation.Nullable;
import java.util.List;

public class TileEntityAttunementAltar extends TileEntity implements INamedContainerProvider, IMultiblock<GameStage>
{
    public DynamicMultiblockDetector<TileEntityAttunementAltar, GameStage> detector;

    public boolean isMultiblockFormed = false;

    private ITextComponent title;

    public TileEntityAttunementAltar(TileEntityType<?> type)
    {
        super(type);

        this.title = new TranslationTextComponent("container." + NordicNames.ATTUNEMENT_ALTAR);
    }

    public TileEntityAttunementAltar()
    {
        super(TileEntities.attunement_altar.get());

        this.title = new TranslationTextComponent("container." + NordicNames.ATTUNEMENT_ALTAR);
        detector = new DynamicMultiblockDetector<>(this);
    }

    public List<TileEntitySigilPodium> getPodiums()
    {
        return BlockUtilities.getPodiums(this.world, this.pos);
    }

    @Override
    public ITextComponent getDisplayName()
    {
        return title;
    }

    @Nullable
    @Override
    public Container createMenu(int windowId, PlayerInventory playerInv, PlayerEntity player)
    {
        return new ContainerAttunementAltar(windowId, playerInv, this);
    }

    @Override
    public void validateStructure()
    {
        this.isMultiblockFormed = detector.validate(this.world, this.pos, GameStage.Midgard);
        sendUpdatePacket();
    }

    @Override
    public ResourceLocation getRegistryName()
    {
        return NordicArcanum.RL(NordicNames.ATTUNEMENT_ALTAR);
    }

    @Override
    public GameStage[] getValues()
    {
        return GameStage.values();
    }

    @Override
    public boolean isValidBlock(BlockState type)
    {
        return type.getBlock() instanceof BlockSigilPodium && type.get(BlockSigilPodium.ISCORE);
    }

    @Override
    public Template getTemplate(TemplateManager templateManager)
    {
        return templateManager.getTemplate(StructureResources.getTieredAltar(GameStage.Midgard));
    }

    @Override
    public boolean isFormed()
    {
        return this.isMultiblockFormed;
    }

    @Override
    public void setFormed(boolean formed)
    {
        this.isMultiblockFormed = formed;
    }

    public void sendUpdatePacket()
    {
        sendUpdatePacket(this);
    }

    public void sendUpdatePacket(TileEntity tracking)
    {
        if (this.world.isRemote)
        {
            NordicArcanum.LOG.warn("Update packet call requested from client side", new Exception());
        }
        else if (isRemoved())
        {
            NordicArcanum.LOG.warn("Update packet call requested for removed tile", new Exception());
        }
        else
        {
            NordicArcanum.PACKET_HANDLER.sendToAllTracking(new PacketMultiblockFormed(this), tracking);
        }
    }
}
