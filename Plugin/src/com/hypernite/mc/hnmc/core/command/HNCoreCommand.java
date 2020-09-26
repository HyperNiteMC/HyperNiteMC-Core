package com.hypernite.mc.hnmc.core.command;

import com.hypernite.mc.hnmc.core.command.hnmc.*;
import com.hypernite.mc.hnmc.core.misc.commands.DefaultCommand;

public class HNCoreCommand extends DefaultCommand {

    public HNCoreCommand() {
        super(null, "hnmc", "hypernite.use", "§bHyperNiteMC 主指令");
        this.addSub(new UpdateTagCommand(this));
        this.addSub(new UpdateListCommand(this));
        this.addSub(new FormatMainCommand(this));
        this.addSub(new HelpListMainCommand(this));
        this.addSub(new WorldMainCommand(this));
        this.addSub(new VersionMainCommand(this));
    }
}
