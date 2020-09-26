package com.hypernite.mc.hnmc.core.command.hnmc.version;

import com.hypernite.mc.hnmc.core.exception.PluginNotFoundException;
import com.hypernite.mc.hnmc.core.exception.ResourceNotFoundException;
import com.hypernite.mc.hnmc.core.managers.ResourceManager;
import com.hypernite.mc.hnmc.core.misc.commands.CommandNode;
import com.hypernite.mc.hnmc.core.misc.permission.Perm;
import org.bukkit.command.CommandSender;

public class VersionUpdateCommand extends VersionCommandNode {

    public VersionUpdateCommand(CommandNode parent) {
        super(parent, "update", Perm.DEVELOPER, "更新插件到最新版本", "<plugin>", "download");
    }

    @Override
    public void executeChecker(CommandSender sender, ResourceManager manager, String plugin, String version) throws ResourceNotFoundException, PluginNotFoundException {
        sender.sendMessage(config.getPrefix()+"§c暫不支援此功能。");
    }
}
