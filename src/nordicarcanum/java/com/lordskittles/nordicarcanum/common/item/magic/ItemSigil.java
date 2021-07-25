package com.lordskittles.nordicarcanum.common.item.magic;

import com.lordskittles.arcanumapi.common.item.ItemMod;
import com.lordskittles.nordicarcanum.common.registry.MagicSchools;
import com.lordskittles.arcanumapi.common.utilities.MiscUtilities;
import com.lordskittles.arcanumapi.common.utilities.NBTUtilities;
import com.lordskittles.nordicarcanum.magic.progression.PlayerProgress;
import com.lordskittles.nordicarcanum.magic.progression.ProgressionHelper;
import com.lordskittles.nordicarcanum.magic.progression.ProgressionManager;
import com.lordskittles.nordicarcanum.magic.schools.IMagicSchool;
import com.lordskittles.nordicarcanum.magic.schools.MagicSchool;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicItemGroup;
import com.lordskittles.nordicarcanum.common.advancements.Advancements;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.LogicalSide;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ItemSigil extends ItemMod {

    public ItemSigil() {

        super(new Item.Properties().tab(NordicItemGroup.INSTANCE).stacksTo(1));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {

        IMagicSchool school = getSchool(stack);

        if(school != null) {
            tooltip.add(school.getDiscoveredText());
        }
        else {
            tooltip.add(IMagicSchool.getUndiscoveredText());
        }

        super.appendHoverText(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int itemSlot, boolean isSelected) {

        if(world.isClientSide || ! (entity instanceof Player))
            return;

        Player player = (Player) entity;

        IMagicSchool school = getSchool(stack);
        if(school == null) {
            PlayerProgress progress = ProgressionHelper.getProgress((Player) entity, LogicalSide.SERVER);

            List<MagicSchool> schools = new ArrayList<>(MagicSchools.getIdSchools());

            for(ResourceLocation sLoc : progress.getKnownSchools()) {
                MagicSchool s = MagicSchools.getSchool(sLoc);
                if(s != null) {
                    schools.remove(s);
                }
            }

            MagicSchool newSchool = MiscUtilities.getRandomEntry(schools, world.getRandom());
            if(newSchool != null) {
                setSchool(stack, newSchool);
                ProgressionManager.learnSchool(newSchool, player);

                if(player instanceof ServerPlayer) {
                    Advancements.sigil_found.trigger((ServerPlayer) player);
                }

                player.displayClientMessage(IMagicSchool.getDiscoveredChatMessage(newSchool), true);
            }
        }

        super.inventoryTick(stack, world, entity, itemSlot, isSelected);
    }

    public IMagicSchool getSchool(ItemStack stack) {

        return IMagicSchool.readFromNBT(NBTUtilities.getPersistentData(NordicArcanum.MODID, stack));
    }

    public void setSchool(ItemStack stack, IMagicSchool school) {

        school.save(NBTUtilities.getPersistentData(NordicArcanum.MODID, stack));
    }
}
