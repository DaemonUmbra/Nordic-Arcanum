package com.lordskittles.nordicarcanum.client.gui.utilities;

import com.lordskittles.nordicarcanum.client.gui.screen.ScreenArcanumBar;
import com.lordskittles.nordicarcanum.client.gui.screen.ScreenChangeSchool;
import com.lordskittles.nordicarcanum.client.gui.screen.ScreenSpellDisplay;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

public class GUIHandler {

    public static void open(GUIType id, Player player) {

        Minecraft minecraft = Minecraft.getInstance();

        switch(id) {
            case ChangeSpell:
                minecraft.displayGuiScreen(new ScreenChangeSchool(player));
                break;
            case SpellDisplay:
                minecraft.displayGuiScreen(new ScreenSpellDisplay(player));
                break;
            case ArcanumBar:
                minecraft.displayGuiScreen(new ScreenArcanumBar(player));
                break;
        }
    }
}
