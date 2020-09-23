package com.hypernite.mc.hnmc.core.command.hnmc.format;

import com.hypernite.mc.hnmc.core.chatformat.FormatDatabaseManager;
import com.hypernite.mc.hnmc.core.config.implement.HNMCoreConfig;
import com.hypernite.mc.hnmc.core.main.HyperNiteMC;
import com.hypernite.mc.hnmc.core.misc.commands.CommandNode;
import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;
import java.util.List;

public class FormatEditChatCommand extends CommandNode {


    public FormatEditChatCommand(CommandNode parent) {
        super(parent, "editchat", "hypernite.format.editchat", "更改聊天格式", "<group> <format>");
    }


    @Override
    public boolean executeCommand(@Nonnull CommandSender sender, @Nonnull List<String> args) {
        FormatDatabaseManager format = HyperNiteMC.getFormatDatabaseManager();
        HNMCoreConfig cf = HyperNiteMC.getHnmCoreConfig();
        String group = args.get(0);
        if (!format.getMap().containsKey(group)) {
            sender.sendMessage(cf.getPrefix() + "§c沒有此群組。");
            return true;
        }
        args.remove(0);
        String pattern = String.join(" ", args);

        //Success message
        String success = cf.getPrefix() + "§a更改成功。";

        //Fail message
        String fail = cf.getPrefix() + "§c更改失敗。";

        HyperNiteMC.getAPI().getCoreScheduler().runAsync(() -> {
            boolean done = format.editChatformat(group, pattern);
            sender.sendMessage(done ? success : fail);
        });

        return true;
    }

    @Override
    public List<String> executeTabCompletion(@Nonnull CommandSender sender, @Nonnull List<String> args) {
        return null;
    }
}
