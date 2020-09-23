package com.hypernite.mc.hnmc.core.command.hnmc.world.setter;

import com.hypernite.mc.hnmc.core.misc.commands.CommandNode;
import com.hypernite.mc.hnmc.core.misc.commands.DefaultCommand;

public class WorldSetMainCommand extends DefaultCommand {

    public WorldSetMainCommand(CommandNode parent) {
        super(parent, "set", "hypernite.world.set", "世界設置指令");
        this.addSub(new WorldSetPvECommand(this));
        this.addSub(new WorldSetPvPCommand(this));
        this.addSub(new WorldSetVulnerCommand(this));
    }
}
