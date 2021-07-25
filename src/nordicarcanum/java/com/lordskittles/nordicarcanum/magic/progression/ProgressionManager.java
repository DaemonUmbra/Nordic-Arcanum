package com.lordskittles.nordicarcanum.magic.progression;

import com.lordskittles.nordicarcanum.magic.schools.MagicSchool;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.fml.LogicalSide;

public class ProgressionManager {

    public static boolean resetSchools(Player player) {

        PlayerProgress progress = ProgressionHelper.getProgress(player, LogicalSide.SERVER);
        if(! progress.isValid())
            return false;

        progress.resetSchools();
        ProgressionHelper.savePlayerProgress(player);
        return true;
    }

    public static boolean learnSchool(MagicSchool school, Player player) {

        PlayerProgress progress = ProgressionHelper.getProgress(player, LogicalSide.SERVER);
        if(! progress.isValid())
            return false;

        progress.learnSchool(school.getRegistryName());

        ProgressionHelper.savePlayerProgress(player);
        return true;
    }
}
