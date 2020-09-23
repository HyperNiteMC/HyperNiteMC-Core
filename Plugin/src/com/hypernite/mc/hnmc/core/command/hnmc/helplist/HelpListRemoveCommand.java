package com.hypernite.mc.hnmc.core.command.hnmc.helplist;

import com.hypernite.mc.hnmc.core.config.implement.HNMCoreConfig;
import com.hypernite.mc.hnmc.core.main.HyperNiteMC;
import com.hypernite.mc.hnmc.core.managers.HelpPagesManager;
import com.hypernite.mc.hnmc.core.misc.commands.CommandNode;
import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;
import java.util.List;

public class HelpListRemoveCommand extends CommandNode {


    public HelpListRemoveCommand(CommandNode parent) {
        super(parent, "remove", "hypernite.helplist.remove", "刪除指定的頁面或其特定行數", "<page> [line]");
    }

    @Override
    public boolean executeCommand(@Nonnull CommandSender sender, @Nonnull List<String> args) {
        HelpPagesManager helpPagesManager = HyperNiteMC.getHelpPagesManager();
        HNMCoreConfig config = HyperNiteMC.getHnmCoreConfig();
        String page = args.get(0).toLowerCase();

        HyperNiteMC.getAPI().getCoreScheduler().runAsync(() -> {
            boolean done;
            if (args.size() < 2) {
                done = helpPagesManager.removePage(page);
            } else {
                try {
                    int line = Integer.parseInt(args.get(1));
                    done = helpPagesManager.removePage(page, line);
                } catch (NumberFormatException e) {
                    sender.sendMessage(config.getPrefix() + "§c無效數值。");
                    return;
                }
            }
            //Success message
            String success = config.getPrefix() + "§a更改成功。";

            //Fail message
            String fail = config.getPrefix() + "§c更改失敗。";

            sender.sendMessage(done ? success : fail);

        });

        return true;
    }

    @Override
    public List<String> executeTabCompletion(@Nonnull CommandSender sender, @Nonnull List<String> args) {
        return null;
    }
}
