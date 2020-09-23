package com.hypernite.mc.hnmc.core.command.hnmc.world.setter;

import com.hypernite.mc.hnmc.core.command.hnmc.world.WorldCommandNode;
import com.hypernite.mc.hnmc.core.main.HyperNiteMC;
import com.hypernite.mc.hnmc.core.misc.commands.CommandNode;
import com.hypernite.mc.hnmc.core.misc.world.WorldNonExistException;
import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;
import java.util.List;

public class WorldSetPvECommand extends WorldCommandNode {

    public WorldSetPvECommand(CommandNode parent) {
        super(parent, "pve", "hypernite.world.set.pve", "是否開啟 PVE", "<world> <pve>");
    }

    @Override
    public boolean executeCommand(@Nonnull CommandSender sender, @Nonnull List<String> args) {
        String name = args.get(0);
        if (!args.get(1).equalsIgnoreCase("true") && !args.get(1).equals("false")) {
            sender.sendMessage(prefix + "§c不是布爾值！");
            return true;
        }
        boolean pve = Boolean.parseBoolean(args.get(1));

        try {
            HyperNiteMC.getHnmcWorldManager().updateWorldProperties(name, p -> p.setPve(pve));
            sender.sendMessage(prefix + "§a設置成功。");
        } catch (WorldNonExistException e) {
            sender.sendMessage(prefix + "§c該世界尚未加載或不存在。");
        }
        return true;
    }
}
