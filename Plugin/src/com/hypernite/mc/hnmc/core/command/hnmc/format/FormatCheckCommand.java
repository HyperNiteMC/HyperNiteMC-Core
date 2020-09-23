package com.hypernite.mc.hnmc.core.command.hnmc.format;

import com.hypernite.mc.hnmc.core.chatformat.ChatFormat;
import com.hypernite.mc.hnmc.core.chatformat.FormatDatabaseManager;
import com.hypernite.mc.hnmc.core.config.implement.HNMCoreConfig;
import com.hypernite.mc.hnmc.core.main.HyperNiteMC;
import com.hypernite.mc.hnmc.core.misc.commands.CommandNode;
import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;
import java.util.List;

public class FormatCheckCommand extends CommandNode {


    public FormatCheckCommand(CommandNode parent) {
        super(parent, "check", "hypernite.format.check", "查看該群組的format", "<group>");
    }


    @Override
    public boolean executeCommand(@Nonnull CommandSender sender, @Nonnull List<String> args) {
        FormatDatabaseManager format = HyperNiteMC.getFormatDatabaseManager();
        HNMCoreConfig cf = HyperNiteMC.getHnmCoreConfig();
        if (!format.getMap().containsKey(args.get(0))) {
            sender.sendMessage(cf.getPrefix() + "§c沒有此群組。");
            return true;
        }

        ChatFormat mat = format.getMap().get(args.get(0));

        String[] list = {
                cf.getPrefix() + args.get(0) + " §e的聊天格式:§r " + mat.getChatformat(),
                cf.getPrefix() + args.get(0) + " §a的優先度:§r " + mat.getPriority()
        };

        sender.sendMessage(list);
        return true;
    }

    @Override
    public List<String> executeTabCompletion(@Nonnull CommandSender sender, @Nonnull List<String> args) {
        return null;
    }


}
