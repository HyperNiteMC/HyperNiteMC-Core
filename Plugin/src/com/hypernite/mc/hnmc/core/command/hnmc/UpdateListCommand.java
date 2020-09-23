package com.hypernite.mc.hnmc.core.command.hnmc;

import com.hypernite.mc.hnmc.core.main.HyperNiteMC;
import com.hypernite.mc.hnmc.core.managers.ChatFormatManager;
import com.hypernite.mc.hnmc.core.managers.CoreConfig;
import com.hypernite.mc.hnmc.core.misc.commands.CommandNode;
import com.hypernite.mc.hnmc.core.misc.permission.Perm;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;
import java.util.List;

public class UpdateListCommand extends CommandNode {

    public UpdateListCommand(CommandNode parent) {
        super(parent, "update-list", Perm.ADMIN, "更新所有玩家的TabList名稱", null);
    }


    @Override
    public boolean executeCommand(@Nonnull CommandSender sender, @Nonnull List<String> args) {
        ChatFormatManager format = HyperNiteMC.getAPI().getChatFormatManager();
        CoreConfig coreConfig = HyperNiteMC.getHnmCoreConfig();
        Bukkit.getOnlinePlayers().forEach(format::updatePlayerList);
        sender.sendMessage(coreConfig.getPrefix() + "§aTabList 名稱更新成功。");
        return true;
    }

    @Override
    public List<String> executeTabCompletion(@Nonnull CommandSender sender, @Nonnull List<String> args) {
        return null;
    }
}
