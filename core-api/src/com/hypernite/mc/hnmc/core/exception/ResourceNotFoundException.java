package com.hypernite.mc.hnmc.core.exception;

/**
 * @see com.hypernite.mc.hnmc.core.managers.ResourceManager
 */
public class ResourceNotFoundException extends Exception {
    private final String plugin;

    public ResourceNotFoundException(String plugin) {
        super("找不到插件 "+plugin+" 的遠端資源");
        this.plugin = plugin;
    }

    public String getPlugin() {
        return plugin;
    }
}
