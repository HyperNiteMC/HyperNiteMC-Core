package com.hypernite.mc.hnmc.core.command.hnmc.world;

import com.hypernite.mc.hnmc.core.main.HyperNiteMC;
import com.hypernite.mc.hnmc.core.misc.commands.CommandNode;
import com.hypernite.mc.hnmc.core.misc.world.WorldLoadedException;
import com.hypernite.mc.hnmc.core.misc.world.WorldNonExistException;
import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;
import java.util.List;

public class WorldEnableCommand extends WorldCommandNode {

    public WorldEnableCommand(CommandNode parent) {
        super(parent, "enable", "hypernite.world.enable", "開啟該世界的自動加載", "<world>");
    }

    @Override
    public boolean executeCommand(@Nonnull CommandSender sender, @Nonnull List<String> args) {
        String name = args.get(0);
        try {
            sender.sendMessage(prefix + "正在啟用並加載世界...");
            HyperNiteMC.getHnmcWorldManager().enableWorld(name).whenComplete((result, ex) -> {
                if (ex != null) ex.printStackTrace();
                sender.sendMessage(prefix + "世界 " + name + " 啟用自動加載 " + (result ? "成功並已加載" : "失敗") + "。");
            });

        } catch (WorldNonExistException e) {
            sender.sendMessage(HyperNiteMC.getHnmCoreConfig().getPrefix() + "§c世界 " + e.getWorld() + " 不存在!");
        } catch (IllegalStateException e) {
            sender.sendMessage(HyperNiteMC.getHnmCoreConfig().getPrefix() + "§c該世界沒有被關閉自動加載。");
        } catch (WorldLoadedException e) {
            sender.sendMessage(HyperNiteMC.getHnmCoreConfig().getPrefix() + "§a世界已被加載。");
        }
        return false;
    }

}
