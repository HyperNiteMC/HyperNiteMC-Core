package com.hypernite.mc.hnmc.core.command.hnmc.format;

import com.hypernite.mc.hnmc.core.chatformat.FormatDatabaseManager;
import com.hypernite.mc.hnmc.core.config.implement.HNMCoreConfig;
import com.hypernite.mc.hnmc.core.main.HyperNiteMC;
import com.hypernite.mc.hnmc.core.misc.commands.CommandNode;
import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;
import java.util.List;

public class FormatRemoveCommand extends CommandNode {


    public FormatRemoveCommand(CommandNode parent) {
        super(parent, "remove", "hypernite.format.remove", "刪除該群組的聊天格式", "<group>");
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

        //Success message
        String success = cf.getPrefix() + "§a更改成功。";

        //Fail message
        String fail = cf.getPrefix() + "§c更改失敗。";

        HyperNiteMC.getAPI().getCoreScheduler().runAsync(() -> sender.sendMessage(format.removeChatformat(group) ? success : fail));

        return true;
    }

    @Override
    public List<String> executeTabCompletion(@Nonnull CommandSender sender, @Nonnull List<String> args) {
        return null;
    }
}
