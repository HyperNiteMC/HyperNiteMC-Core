package com.hypernite.mc.hnmc.core.command.hnmc.world;

import com.hypernite.mc.hnmc.core.main.HyperNiteMC;
import com.hypernite.mc.hnmc.core.misc.commands.CommandNode;
import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;
import java.util.List;

public class WorldListCommand extends WorldCommandNode {

    public WorldListCommand(CommandNode parent) {
        super(parent, "list", "hypernite.world.list", "顯示世界列表", null);
    }

    @Override
    public boolean executeCommand(@Nonnull CommandSender sender, @Nonnull List<String> args) {
        String[] msg = HyperNiteMC.getHnmcWorldManager().listWorldMessages();
        sender.sendMessage(msg);
        return true;
    }

}
