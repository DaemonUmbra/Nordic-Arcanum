package com.lordskittles.arcanumapi.common.registry;

import com.lordskittles.arcanumapi.common.command.CommandResetSchools;
import com.lordskittles.arcanumapi.core.ArcanumAPI;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.MinecraftForge;

public class Commands
{
    public static LiteralArgumentBuilder<CommandSource> register()
    {
        return net.minecraft.command.Commands.literal(ArcanumAPI.MODID)
                .requires(cs -> cs.getEntity() instanceof ServerPlayerEntity)
                .then(CommandResetSchools.register());
    }

    public static ArgumentBuilder<CommandSource, ?> register(String name, Class<?> clazz)
    {
        MinecraftForge.EVENT_BUS.register(clazz);
        return net.minecraft.command.Commands.literal(name);
    }

    public static ITextComponent getCommandFeedback(String name)
    {
        return new TranslationTextComponent(ArcanumAPI.MODID + ".command." + name);
    }
}
