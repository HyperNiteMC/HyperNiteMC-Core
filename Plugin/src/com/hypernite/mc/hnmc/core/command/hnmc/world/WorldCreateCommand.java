package com.hypernite.mc.hnmc.core.command.hnmc.world;

import com.hypernite.mc.hnmc.core.main.HyperNiteMC;
import com.hypernite.mc.hnmc.core.misc.commands.CommandNode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.List;

public class WorldCreateCommand extends WorldCommandNode {

    public WorldCreateCommand(CommandNode parent) {
        super(parent, "create", "hypernite.world.create", "創建世界", "<world> [是否生成建築]", "c");
    }

    @Override
    public boolean executeCommand(@Nonnull CommandSender sender, @Nonnull List<String> args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(prefix + HyperNiteMC.getAPI().getCoreConfig().getNotPlayer());
            return true;
        }
        Player player = (Player) sender;
        String worldName = args.get(0);
        if (args.size() < 2) {
            new WorldCreationBuilder(worldName, player);
        } else {
            if (!args.get(1).equalsIgnoreCase("true") && !args.get(1).equalsIgnoreCase("false")) {
                sender.sendMessage(prefix + "§c不是布爾值！");
                return true;
            }
            boolean generateStructures = Boolean.parseBoolean(args.get(1));
            new WorldCreationBuilder(worldName, generateStructures, player);
        }
        return true;
    }
}
