package com.lordskittles.nordicarcanum.common.command;

import com.lordskittles.nordicarcanum.common.registry.Commands;
import com.lordskittles.nordicarcanum.core.NordicNames;
import com.lordskittles.nordicarcanum.magic.progression.ProgressionManager;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

public class CommandResetSchools {

    public static ArgumentBuilder<CommandSource, ?> register() {

        return Commands.register(NordicNames.RESET_SCHOOLS_COMMAND, CommandResetSchools.class)
                .requires(cs -> cs.hasPermissionLevel(4))
                .executes(CommandResetSchools::execute);
    }

    private static int execute(CommandContext<CommandSource> context) {

        CommandSource source = context.getSource();
        Entity entity = source.getEntity();

        if(entity instanceof PlayerEntity) {
            ProgressionManager.resetSchools((PlayerEntity) entity);
            source.sendFeedback(Commands.getCommandFeedback(NordicNames.RESET_SCHOOLS_COMMAND), true);
        }

        return 0;
    }
}
