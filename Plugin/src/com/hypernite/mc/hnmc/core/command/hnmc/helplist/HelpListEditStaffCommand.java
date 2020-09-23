package com.hypernite.mc.hnmc.core.command.hnmc.helplist;

import com.hypernite.mc.hnmc.core.config.implement.HNMCoreConfig;
import com.hypernite.mc.hnmc.core.main.HyperNiteMC;
import com.hypernite.mc.hnmc.core.managers.HelpPagesManager;
import com.hypernite.mc.hnmc.core.misc.commands.CommandNode;
import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;
import java.util.List;

public class HelpListEditStaffCommand extends CommandNode {

    public HelpListEditStaffCommand(CommandNode parent) {
        super(parent, "editstaff", "hypernite.helplist.editstaff", "設定該頁面是否為管理員頁面(權限: group.helper)", "<page> <是否為Staff頁面>");
    }

    public boolean executeCommand(@Nonnull CommandSender sender, @Nonnull List<String> args) {
        HNMCoreConfig cf = HyperNiteMC.getHnmCoreConfig();
        switch (args.get(1)) {
            case "true":
            case "false":
                break;
            default:
                sender.sendMessage(cf.getPrefix() + "§a無效的布爾值。");
                sender.sendMessage(cf.getPrefix() + "§e請輸入 true / false");
                return true;
        }
        String page = args.get(0).toLowerCase();
        boolean isStaff = Boolean.parseBoolean(args.get(1));
        HelpPagesManager helpPagesManager = HyperNiteMC.getHelpPagesManager();
        HyperNiteMC.getAPI().getCoreScheduler().runAsync(() -> {
            boolean done = helpPagesManager.editStaffPage(page, isStaff);

            //Success message
            String success = cf.getPrefix() + "§a更改成功。";

            //Fail message
            String fail = cf.getPrefix() + "§c更改失敗。";

            sender.sendMessage(done ? success : fail);
        });
        return true;
    }

    @Override
    public List<String> executeTabCompletion(@Nonnull CommandSender sender, @Nonnull List<String> args) {
        return null;
    }
}
