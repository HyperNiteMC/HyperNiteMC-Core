package com.hypernite.mc.hnmc.core.command.hnmc.format;

import com.hypernite.mc.hnmc.core.chatformat.FormatDatabaseManager;
import com.hypernite.mc.hnmc.core.config.implement.HNMCoreConfig;
import com.hypernite.mc.hnmc.core.main.HyperNiteMC;
import com.hypernite.mc.hnmc.core.misc.commands.CommandNode;
import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;
import java.util.List;

public class FormatEditLevelCommand extends CommandNode {


    public FormatEditLevelCommand(CommandNode parent) {
        super(parent, "editlevel", "hypernite.format.editlevel", "更改優先度", "<group> <priority>");
    }

    @Override
    public boolean executeCommand(@Nonnull CommandSender sender, @Nonnull List<String> args) {
        FormatDatabaseManager format = HyperNiteMC.getFormatDatabaseManager();
        HNMCoreConfig cf = HyperNiteMC.getHnmCoreConfig();
        try {
            String group = args.get(0);
            if (!format.getMap().containsKey(group)) {
                sender.sendMessage(cf.getPrefix() + "§c沒有此群組。");
                return true;
            }
            int priority = Integer.parseInt(args.get(1));
            if (priority == format.getMap().get(group).getPriority()) {
                sender.sendMessage(cf.getPrefix() + "§e你所輸入的優先度與資料庫中的相同。");
                return true;
            }
            //Success message
            String success = cf.getPrefix() + "§a更改成功。";

            //Fail message
            String fail = cf.getPrefix() + "§c更改失敗。";

            HyperNiteMC.getAPI().getCoreScheduler().runAsync(() -> {
                boolean done = format.editPriority(group, priority);
                sender.sendMessage(done ? success : fail);
            });

        } catch (NumberFormatException e) {
            sender.sendMessage(cf.getPrefix() + "§c無效數值。");
        }

        return true;
    }

    @Override
    public List<String> executeTabCompletion(@Nonnull CommandSender sender, @Nonnull List<String> args) {
        return null;
    }
}
