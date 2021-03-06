package com.hypernite.mc.hnmc.core.command.hnmc.world;

import com.hypernite.mc.hnmc.core.main.HyperNiteMC;
import com.hypernite.mc.hnmc.core.misc.commands.CommandNode;
import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class WorldCommandNode extends CommandNode {

    protected final String prefix;

    public WorldCommandNode(CommandNode parent, @Nonnull String command, String permission, @Nonnull String description, String placeholder, String... alias) {
        super(parent, command, permission, description, placeholder, alias);
        this.prefix = HyperNiteMC.getAPI().getCoreConfig().getPrefix();
    }


    @Override
    public List<String> executeTabCompletion(@Nonnull CommandSender sender, @Nonnull List<String> args) {
        if (this.getPlaceholder() == null) return null;
        List<Integer> worldTab = new LinkedList<>();
        String[] papis = this.getPlaceholder().split(" ");
        for (int i = 0; i < papis.length; i++) {
            if (papis[i].contains("world")) {
                worldTab.add(i);
            }
        }
        return worldTab.contains(args.size() - 1) ? new ArrayList<>(HyperNiteMC.getHnmcWorldManager().getWorldList().keySet()) : null;
    }
}
