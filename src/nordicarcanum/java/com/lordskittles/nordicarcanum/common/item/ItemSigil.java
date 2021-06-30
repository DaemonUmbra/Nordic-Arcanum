package com.lordskittles.nordicarcanum.common.item;

import com.lordskittles.nordicarcanum.client.itemgroups.NordicItemGroup;
import com.lordskittles.nordicarcanum.common.advancements.Advancements;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.arcanumapi.common.item.ItemMod;
import com.lordskittles.arcanumapi.common.utilities.MiscUtilities;
import com.lordskittles.arcanumapi.common.utilities.NBTUtilities;
import com.lordskittles.arcanumapi.magic.schools.MagicSchool;
import com.lordskittles.arcanumapi.common.registry.MagicSchools;
import com.lordskittles.arcanumapi.magic.schools.IMagicSchool;
import com.lordskittles.arcanumapi.magic.progression.PlayerProgress;
import com.lordskittles.arcanumapi.magic.progression.ProgressionHelper;
import com.lordskittles.arcanumapi.magic.progression.ProgressionManager;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.*;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.LogicalSide;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ItemSigil extends ItemMod
{
    public ItemSigil()
    {
        super(new Item.Properties().group(NordicItemGroup.INSTANCE).maxStackSize(1));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {
        IMagicSchool school = getSchool(stack);

        if (school != null)
        {
            tooltip.add(school.getDiscoveredText());
        }
        else
        {
            tooltip.add(IMagicSchool.getUndiscoveredText());
        }

        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected)
    {
        if (world.isRemote || !(entity instanceof PlayerEntity))
            return;

        PlayerEntity player = (PlayerEntity) entity;

        IMagicSchool school = getSchool(stack);
        if (school == null)
        {
            PlayerProgress progress = ProgressionHelper.getProgress((PlayerEntity)entity, LogicalSide.SERVER);

            List<MagicSchool> schools = new ArrayList<>(MagicSchools.getIdSchools());

            for (ResourceLocation sLoc : progress.getKnownSchools())
            {
                MagicSchool s = MagicSchools.getSchool(sLoc);
                if (s != null)
                {
                    schools.remove(s);
                }
            }

            MagicSchool newSchool = MiscUtilities.getRandomEntry(schools, world.rand);
            if (newSchool != null)
            {
                setSchool(stack, newSchool);
                ProgressionManager.learnSchool(newSchool, player);

                if (player instanceof ServerPlayerEntity)
                {
                    Advancements.sigil_found.trigger((ServerPlayerEntity) player);
                }

                player.sendStatusMessage(IMagicSchool.getDiscoveredChatMessage(newSchool),true);
            }
        }

        super.inventoryTick(stack, world, entity, itemSlot, isSelected);
    }

    public IMagicSchool getSchool(ItemStack stack)
    {
        return IMagicSchool.readFromNBT(NBTUtilities.getPersistentData(NordicArcanum.MODID, stack));
    }

    public void setSchool(ItemStack stack, IMagicSchool school)
    {
        school.writeToNBT(NBTUtilities.getPersistentData(NordicArcanum.MODID, stack));
    }
}
