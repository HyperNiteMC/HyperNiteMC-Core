package com.hypernite.mc.hnmc.core.command.hnmc;

import com.hypernite.mc.hnmc.core.command.hnmc.world.*;
import com.hypernite.mc.hnmc.core.command.hnmc.world.setter.WorldSetMainCommand;
import com.hypernite.mc.hnmc.core.misc.commands.CommandNode;
import com.hypernite.mc.hnmc.core.misc.commands.DefaultCommand;

public class WorldMainCommand extends DefaultCommand {

    public WorldMainCommand(CommandNode parent) {
        super(parent, "world", "hypernite.world", "管理伺服器的世界", "w");
        this.addSub(new WorldCreateCommand(this));
        this.addSub(new WorldCreateVoidCommand(this));
        this.addSub(new WorldDeleteCommand(this));
        this.addSub(new WorldDisableCommand(this));
        this.addSub(new WorldEnableCommand(this));
        this.addSub(new WorldListCommand(this));
        this.addSub(new WorldLoadCommand(this));
        this.addSub(new WorldUnloadCommand(this));
        this.addSub(new WorldTpCommand(this));
        this.addSub(new WorldSetMainCommand(this));
        this.addSub(new WorldSetSpawnCommand(this));
    }
}
