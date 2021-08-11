package com.lordskittles.arcanumapi.client.gui.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.chat.TextComponent;

public class ScreenBase extends Screen {

    protected final Minecraft minecraft;
    protected final Player player;

    public ScreenBase(String name, Player player) {

        super(new TextComponent(name));

        this.player = player;
        this.minecraft = Minecraft.getInstance();
    }

    @Override
    public boolean isPauseScreen() {

        return false;
    }
}
