package com.lordskittles.arcanumapi.magic;

import net.minecraft.entity.player.PlayerEntity;

public interface IArcanumUser
{
    public void useArcanum(PlayerEntity player, float cost);
}
