package com.lordskittles.nordicarcanum.common.command;

import com.lordskittles.nordicarcanum.common.registry.Commands;
import com.lordskittles.nordicarcanum.core.NordicNames;
import com.lordskittles.nordicarcanum.magic.progression.ProgressionManager;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class CommandResetSchools {

    public static ArgumentBuilder<CommandSourceStack, ?> register() {

        return Commands.register(NordicNames.RESET_SCHOOLS_COMMAND, CommandResetSchools.class)
                .requires(cs -> cs.hasPermission(4))
                .executes(CommandResetSchools::execute);
    }

    private static int execute(CommandContext<CommandSourceStack> context) {

        CommandSourceStack sourceStack = context.getSource();
        Entity entity = sourceStack.getEntity();

        if(entity instanceof Player) {
            ProgressionManager.resetSchools((Player) entity);
            sourceStack.sendSuccess(Commands.getCommandFeedback(NordicNames.RESET_SCHOOLS_COMMAND), true);
        }

        return 0;
    }
}
