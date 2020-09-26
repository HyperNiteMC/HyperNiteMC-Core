package com.hypernite.mc.hnmc.core.exception;

/**
 * @see com.hypernite.mc.hnmc.core.managers.ResourceManager
 */
public class PluginNotFoundException extends Exception{
    private final String plugin;

    public PluginNotFoundException(String plugin) {
        super("找不到插件 "+plugin);
        this.plugin = plugin;
    }

    public String getPlugin() {
        return plugin;
    }
}
