package com.hypernite.mc.hnmc.core.command.hnmc.world;

import com.hypernite.mc.hnmc.core.main.HyperNiteMC;
import com.hypernite.mc.hnmc.core.misc.commands.CommandNode;
import com.hypernite.mc.hnmc.core.misc.world.WorldLoadedException;
import com.hypernite.mc.hnmc.core.misc.world.WorldNonExistException;
import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;
import java.util.List;

public class WorldLoadCommand extends WorldCommandNode {

    public WorldLoadCommand(CommandNode parent) {
        super(parent, "load", "hypernite.world.import", "匯入世界", "<world>", "import");
    }

    @Override
    public boolean executeCommand(@Nonnull CommandSender sender, @Nonnull List<String> args) {
        String name = args.get(0);
        try {
            sender.sendMessage(prefix + "正在加載世界...");
            HyperNiteMC.getHnmcWorldManager().loadWorld(name).whenComplete((w, ex) -> {
                if (ex != null) ex.printStackTrace();
                var result = w != null;
                sender.sendMessage(prefix + "§e世界加載 " + (result ? "成功" : "失敗"));
            });
        } catch (WorldNonExistException e) {
            sender.sendMessage(prefix + "§c世界 " + e.getWorld() + " 不存在!");
        } catch (WorldLoadedException e) {
            sender.sendMessage(prefix + "§a世界已被加載。");
        }

        return true;
    }
}
