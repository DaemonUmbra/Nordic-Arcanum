package com.lordskittles.arcanumapi.client.gui.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;

public class ScreenBase extends Screen
{
    protected final Minecraft minecraft;
    protected final PlayerEntity player;

    public ScreenBase(String name, PlayerEntity player)
    {
        super(new StringTextComponent(name));

        this.player = player;
        this.minecraft = Minecraft.getInstance();
    }

    @Override
    public boolean isPauseScreen()
    {
        return false;
    }
}
