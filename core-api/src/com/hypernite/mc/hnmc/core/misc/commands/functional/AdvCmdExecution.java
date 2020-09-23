package com.hypernite.mc.hnmc.core.misc.commands.functional;

import org.bukkit.command.CommandSender;

import java.util.List;

/**
 * @see com.hypernite.mc.hnmc.core.misc.commands.CommandNodeBuilder
 */
@FunctionalInterface
public interface AdvCmdExecution<T, D extends CommandSender> {
    T execute(D sender, List<String> args);
}
