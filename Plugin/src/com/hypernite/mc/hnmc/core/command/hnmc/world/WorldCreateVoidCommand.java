package com.hypernite.mc.hnmc.core.command.hnmc.world;

import com.hypernite.mc.hnmc.core.main.HyperNiteMC;
import com.hypernite.mc.hnmc.core.misc.commands.CommandNode;
import com.hypernite.mc.hnmc.core.misc.world.WorldExistException;
import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;
import java.util.List;

public class WorldCreateVoidCommand extends WorldCommandNode {

    public WorldCreateVoidCommand(CommandNode parent) {
        super(parent, "createvoid", "hypernite.world.createvoid", "創建虛空世界", "<world>", "void", "cv");
    }

    @Override
    public boolean executeCommand(@Nonnull CommandSender sender, @Nonnull List<String> args) {
        final String worldName = args.get(0);
        try {
            sender.sendMessage(HyperNiteMC.getHnmCoreConfig().getPrefix() + "§e正在創建虛空世界....");
            HyperNiteMC.getHnmcWorldManager().createVoidWorld(worldName).whenComplete((w, ex) -> {
                if (ex != null) ex.printStackTrace();
                var result = w != null;
                sender.sendMessage(HyperNiteMC.getHnmCoreConfig().getPrefix() + "§e世界 " + worldName + " 創建 " + (result ? "成功" : "失敗") + "。");
            });
        } catch (WorldExistException e) {
            sender.sendMessage(HyperNiteMC.getAPI().getCoreConfig().getPrefix() + "§c世界已存在。");
        }
        return true;
    }
}
