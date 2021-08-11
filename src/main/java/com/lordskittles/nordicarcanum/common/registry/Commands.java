package com.lordskittles.nordicarcanum.common.registry;

import com.lordskittles.nordicarcanum.common.command.CommandResetSchools;
import com.lordskittles.arcanumapi.core.ArcanumAPI;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.common.MinecraftForge;

public class Commands {

    public static LiteralArgumentBuilder<CommandSourceStack> register() {

        return net.minecraft.commands.Commands.literal(NordicArcanum.MODID)
                .requires(cs -> cs.getEntity() instanceof ServerPlayer)
                .then(CommandResetSchools.register());
    }

    public static ArgumentBuilder<CommandSourceStack, ?> register(String name, Class<?> clazz) {

        MinecraftForge.EVENT_BUS.register(clazz);
        return net.minecraft.commands.Commands.literal(name);
    }

    public static Component getCommandFeedback(String name) {

        return new TranslatableComponent(NordicArcanum.MODID + ".command." + name);
    }
}
