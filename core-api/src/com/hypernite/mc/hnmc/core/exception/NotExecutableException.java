package com.hypernite.mc.hnmc.core.exception;

import com.hypernite.mc.hnmc.core.misc.commands.functional.CmdExecution;

/**
 * @see com.hypernite.mc.hnmc.core.misc.commands.CommandNodeBuilder#execute(CmdExecution)
 */
public class NotExecutableException extends RuntimeException {
    private final String command;

    public NotExecutableException(String command) {
        super("執行函式 為 Null");
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
