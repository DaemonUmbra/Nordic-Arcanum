package com.lordskittles.arcanumapi.arcanum;

import net.minecraft.entity.player.PlayerEntity;

public interface IArcanumUser {

    void useArcanum(PlayerEntity player, float cost);
}
