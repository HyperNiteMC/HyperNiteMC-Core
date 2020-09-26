package com.hypernite.mc.hnmc.core.command.hnmc;

import com.hypernite.mc.hnmc.core.command.hnmc.version.VersionCheckCommand;
import com.hypernite.mc.hnmc.core.command.hnmc.version.VersionFetchCommand;
import com.hypernite.mc.hnmc.core.command.hnmc.version.VersionUpdateCommand;
import com.hypernite.mc.hnmc.core.misc.commands.CommandNode;
import com.hypernite.mc.hnmc.core.misc.commands.DefaultCommand;
import com.hypernite.mc.hnmc.core.misc.permission.Perm;

public class VersionMainCommand extends DefaultCommand {

    public VersionMainCommand(CommandNode parent) {
        super(parent, "version", Perm.DEVELOPER, "插件版本指令", "v", "ver");
        this.addSub(new VersionCheckCommand(this));
        this.addSub(new VersionFetchCommand(this));
        this.addSub(new VersionUpdateCommand(this));
    }
}
