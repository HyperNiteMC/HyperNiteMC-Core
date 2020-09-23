package com.hypernite.mc.hnmc.core.misc.commands.functional;

import org.bukkit.command.CommandSender;

import java.util.List;

/**
 * @see com.hypernite.mc.hnmc.core.misc.commands.CommandNodeBuilder
 */
@FunctionalInterface
public interface CmdExecution<T> {
    T execute(CommandSender sender, List<String> args);
}
