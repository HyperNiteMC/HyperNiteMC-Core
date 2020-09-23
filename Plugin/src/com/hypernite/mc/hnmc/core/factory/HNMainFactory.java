package com.hypernite.mc.hnmc.core.factory;

import com.google.inject.Inject;
import com.hypernite.mc.hnmc.core.config.ConfigBuilder;
import com.hypernite.mc.hnmc.core.config.ConfigFactory;
import com.hypernite.mc.hnmc.core.managers.builder.Builder;
import org.bukkit.plugin.Plugin;

public final class HNMainFactory implements CoreFactory {

    @Inject
    private Builder builder;

    @Override
    public ReflectionFactory getReflectionFactory(final String className) {
        return new ReflectionBuilder(className);
    }

    @Override
    public ConfigFactory getConfigFactory(Plugin plugin) {
        return new ConfigBuilder(plugin);
    }

    @Override
    public Builder getBuilder() {
        return builder;
    }

}
