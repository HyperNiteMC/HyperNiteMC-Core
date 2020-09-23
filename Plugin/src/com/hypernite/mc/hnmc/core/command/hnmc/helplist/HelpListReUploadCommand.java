package com.hypernite.mc.hnmc.core.command.hnmc.helplist;

import com.hypernite.mc.hnmc.core.config.implement.HNMCoreConfig;
import com.hypernite.mc.hnmc.core.main.HyperNiteMC;
import com.hypernite.mc.hnmc.core.misc.commands.CommandNode;
import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;
import java.util.List;

public class HelpListReUploadCommand extends CommandNode {


    public HelpListReUploadCommand(CommandNode parent) {
        super(parent, "reupload", "hyperinte.helplist.reupload", "重新上傳內容到指定頁面", "<page> <是否為Staff頁面>");
    }


    @Override
    public boolean executeCommand(@Nonnull CommandSender sender, @Nonnull List<String> args) {
        HNMCoreConfig config = HyperNiteMC.getHnmCoreConfig();
        List<String> list = HelpListUploadCommand.getPages(args.toArray(String[]::new), config, sender);
        if (list == null) return false;
        boolean isStaff = Boolean.parseBoolean(args.get(1));
        String page = args.get(0).toLowerCase();
        //Success message
        String success = config.getPrefix() + "§a更改成功。";

        //Fail message
        String fail = config.getPrefix() + "§c更改失敗。";

        HyperNiteMC.getAPI().getCoreScheduler().runAsync(() -> {
            boolean done = HyperNiteMC.getHelpPagesManager().replacePage(page, list, isStaff);
            sender.sendMessage(done ? success : fail);
        });
        return true;
    }

    @Override
    public List<String> executeTabCompletion(@Nonnull CommandSender sender, @Nonnull List<String> args) {
        return null;
    }
}
