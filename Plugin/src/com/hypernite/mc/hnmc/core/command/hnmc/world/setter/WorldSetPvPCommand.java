package com.hypernite.mc.hnmc.core.command.hnmc.world.setter;

import com.hypernite.mc.hnmc.core.command.hnmc.world.WorldCommandNode;
import com.hypernite.mc.hnmc.core.main.HyperNiteMC;
import com.hypernite.mc.hnmc.core.misc.commands.CommandNode;
import com.hypernite.mc.hnmc.core.misc.world.WorldNonExistException;
import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;
import java.util.List;

public class WorldSetPvPCommand extends WorldCommandNode {

    public WorldSetPvPCommand(CommandNode parent) {
        super(parent, "pvp", "hypernite.world.set.pvp", "設置是否開啟pvp", "<world> <pvp>");
    }

    @Override
    public boolean executeCommand(@Nonnull CommandSender sender, @Nonnull List<String> args) {
        String name = args.get(0);
        if (!args.get(1).equalsIgnoreCase("true") && !args.get(1).equals("false")) {
            sender.sendMessage(HyperNiteMC.getAPI().getCoreConfig().getPrefix() + "§c不是布爾值！");
            return true;
        }
        boolean pvp = Boolean.parseBoolean(args.get(1));

        try {
            HyperNiteMC.getHnmcWorldManager().updateWorldProperties(name, p -> p.setPvp(pvp));
            sender.sendMessage(prefix + "§a設置成功。");
        } catch (WorldNonExistException e) {
            sender.sendMessage(prefix + "§c該世界尚未加載或不存在。");
        }
        return true;
    }
}
