package com.hypernite.mc.hnmc.core.command.hnmc;

import com.hypernite.mc.hnmc.core.config.implement.HNMCoreConfig;
import com.hypernite.mc.hnmc.core.main.HyperNiteMC;
import com.hypernite.mc.hnmc.core.managers.NameTagManager;
import com.hypernite.mc.hnmc.core.misc.commands.CommandNode;
import com.hypernite.mc.hnmc.core.misc.permission.Perm;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;
import java.util.List;

public class UpdateTagCommand extends CommandNode {

    public UpdateTagCommand(CommandNode parent) {
        super(parent, "update-tag", Perm.ADMIN, "更新所有玩家的NameTag名稱", null);
    }


    @Override
    public boolean executeCommand(@Nonnull CommandSender sender, @Nonnull List<String> args) {
        NameTagManager nameTagManager = HyperNiteMC.getAPI().getNameTagManager();
        HNMCoreConfig coreConfig = HyperNiteMC.getHnmCoreConfig();
        Bukkit.getOnlinePlayers().forEach(nameTagManager::updatePlayer);
        sender.sendMessage(coreConfig.getPrefix() + "§aNameTag 名稱更新成功。");
        return true;
    }

    @Override
    public List<String> executeTabCompletion(@Nonnull CommandSender sender, @Nonnull List<String> args) {
        return null;
    }

}
