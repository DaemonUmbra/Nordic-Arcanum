package com.lordskittles.nordicarcanum.client.gui.screen;

import com.lordskittles.arcanumapi.client.gui.screen.ScreenBase;
import com.lordskittles.nordicarcanum.core.NordicNames;
import net.minecraft.world.entity.player.Player;

public class ScreenSpellDisplay extends ScreenBase {

    public ScreenSpellDisplay(Player player) {

        super(NordicNames.SPELL_DISPLAY, player);
    }
}
