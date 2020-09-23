package com.hypernite.mc.hnmc.core.command.hnmc;

import com.hypernite.mc.hnmc.core.command.hnmc.helplist.*;
import com.hypernite.mc.hnmc.core.misc.commands.CommandNode;
import com.hypernite.mc.hnmc.core.misc.commands.DefaultCommand;

public class HelpListMainCommand extends DefaultCommand {


    public HelpListMainCommand(CommandNode parent) {
        super(parent, "helplist", "hypernite.helplist", "查看指令幫助");
        this.addSub(new HelpListEditStaffCommand(this));
        this.addSub(new HelpListReloadCommand(this));
        this.addSub(new HelpListRemoveCommand(this));
        this.addSub(new HelpListReUploadCommand(this));
        this.addSub(new HelpListUploadCommand(this));
    }

}
