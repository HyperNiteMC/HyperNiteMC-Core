package com.hypernite.mc.hnmc.core.command.hnmc;

import com.hypernite.mc.hnmc.core.command.hnmc.format.*;
import com.hypernite.mc.hnmc.core.misc.commands.CommandNode;
import com.hypernite.mc.hnmc.core.misc.commands.DefaultCommand;

public class FormatMainCommand extends DefaultCommand {


    public FormatMainCommand(CommandNode parent) {
        super(parent, "format", "hypernite.format", "查看指令幫助");
        this.addSub(new FormatAddCommand(this));
        this.addSub(new FormatCheckCommand(this));
        this.addSub(new FormatEditChatCommand(this));
        this.addSub(new FormatEditLevelCommand(this));
        this.addSub(new FormatRemoveCommand(this));
    }
}
