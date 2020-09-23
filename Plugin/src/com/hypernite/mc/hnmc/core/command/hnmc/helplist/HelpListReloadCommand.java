package com.hypernite.mc.hnmc.core.command.hnmc.helplist;

import com.hypernite.mc.hnmc.core.main.HyperNiteMC;
import com.hypernite.mc.hnmc.core.misc.commands.CommandNode;
import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;
import java.util.List;

public class HelpListReloadCommand extends CommandNode {

    public HelpListReloadCommand(CommandNode parent) {
        super(parent, "reload", "hypernite.helplist.reload", "重新載入Help.yml", null);
    }


    @Override
    public boolean executeCommand(@Nonnull CommandSender sender, @Nonnull List<String> args) {
        HyperNiteMC.getHnmCoreConfig().reloadHelp();
        sender.sendMessage(HyperNiteMC.getHnmCoreConfig().getPrefix() + "§a重載完成。");
        return true;
    }

    @Override
    public List<String> executeTabCompletion(@Nonnull CommandSender sender, @Nonnull List<String> args) {
        return null;
    }

}
