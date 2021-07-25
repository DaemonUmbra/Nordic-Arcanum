package com.lordskittles.nordicarcanum.client.gui.screen;

import com.lordskittles.arcanumapi.client.gui.screen.ScreenBase;
import com.lordskittles.nordicarcanum.core.NordicNames;
import net.minecraft.world.entity.player.Player;

public class ScreenArcanumBar extends ScreenBase {

    public ScreenArcanumBar(Player player) {

        super(NordicNames.ARCANUM_BAR, player);
    }
}
