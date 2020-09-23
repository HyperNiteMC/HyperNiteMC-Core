package com.hypernite.mc.hnmc.core.config.implement;

import com.google.inject.Inject;
import com.hypernite.mc.hnmc.core.config.implement.yaml.*;
import com.hypernite.mc.hnmc.core.factory.CoreFactory;
import com.hypernite.mc.hnmc.core.managers.CoreConfig;
import com.hypernite.mc.hnmc.core.managers.YamlManager;
import org.bukkit.plugin.Plugin;

public class HNMCoreConfig implements CoreConfig {
    private final YamlManager manager;
    private final boolean papiEnabled;


    @Inject
    public HNMCoreConfig(Plugin plugin, CoreFactory factory) { //real constructor
        this.manager = factory.getConfigFactory(plugin)
                .register("Database.yml", DatabaseConfig.class)
                .register("Config.yml", MainConfig.class)
                .register("CancelEvent.yml", CancelEventConfig.class)
                .register("Messages.yml", MessageConfig.class)
                .register("Help.yml", HelpConfig.class).dump();
        papiEnabled = plugin.getServer().getPluginManager().isPluginEnabled("PlaceholderAPI");
    }

    public MainConfig getConfig() {
        return manager.getConfigAs(MainConfig.class);
    }

    public DatabaseConfig getDatabase() {
        return manager.getConfigAs(DatabaseConfig.class);
    }

    public HelpConfig getHelp() {
        return manager.getConfigAs(HelpConfig.class);
    }

    public CancelEventConfig getCancel() {
        return manager.getConfigAs(CancelEventConfig.class);
    }

    public boolean isPapiEnabled() {
        return papiEnabled;
    }

    public int getMostDelay() {
        return manager.getConfigAs(MainConfig.class).fallBackDelay;
    }

    public void reloadHelp() {
        getHelp().reload();
    }

    @Override
    public String getPrefix() {
        return getMessageConfig().getPrefix();
    }

    @Override
    public String getNoPerm() {
        return getMessageConfig().getPure("No-Perm");
    }

    @Override
    public String getNotPlayer() {
        return getMessageConfig().getPure("Not-Player");
    }

    @Override
    public String getNotFoundPlayer() {
        return getMessageConfig().getPure("Player-Not-Found");
    }

    public MessageConfig getMessageConfig() {
        return manager.getConfigAs(MessageConfig.class);
    }

    public String getGameStats() {
        return getDatabase().chatFormatPlaceholder;
    }
}
