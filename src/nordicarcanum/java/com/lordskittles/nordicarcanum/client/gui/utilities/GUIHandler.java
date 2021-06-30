package com.lordskittles.nordicarcanum.client.gui.utilities;

import com.lordskittles.nordicarcanum.client.gui.screen.*;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;

public class GUIHandler
{
    public static void open(GUIType id, PlayerEntity player)
    {
        Minecraft minecraft = Minecraft.getInstance();

        switch (id)
        {
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
