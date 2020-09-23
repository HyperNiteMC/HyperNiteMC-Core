package com.hypernite.mc.hnmc.core;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Scopes;
import com.hypernite.mc.hnmc.core.bungeecord.Bungee;
import com.hypernite.mc.hnmc.core.caxerx.CommandHandler;
import com.hypernite.mc.hnmc.core.caxerx.ItemBuilderEventListener;
import com.hypernite.mc.hnmc.core.chatformat.ChatFormatListener;
import com.hypernite.mc.hnmc.core.chatformat.FormatDatabaseManager;
import com.hypernite.mc.hnmc.core.chatformat.NameTagHandler;
import com.hypernite.mc.hnmc.core.command.HelpCommand;
import com.hypernite.mc.hnmc.core.config.SchedulerManager;
import com.hypernite.mc.hnmc.core.config.implement.HNMCoreConfig;
import com.hypernite.mc.hnmc.core.ericlam.ChatRunnerHandler;
import com.hypernite.mc.hnmc.core.ericlam.TablistBuilder;
import com.hypernite.mc.hnmc.core.factory.CoreFactory;
import com.hypernite.mc.hnmc.core.factory.HNMainFactory;
import com.hypernite.mc.hnmc.core.factory.builder.FactoryBuilder;
import com.hypernite.mc.hnmc.core.listener.EventListener;
import com.hypernite.mc.hnmc.core.listener.cancelevent.CancelEventManager;
import com.hypernite.mc.hnmc.core.managers.*;
import com.hypernite.mc.hnmc.core.managers.builder.Builder;
import com.hypernite.mc.hnmc.core.mysql.SQLDataSourceManager;
import com.hypernite.mc.hnmc.core.skin.PlayerSkinHandler;
import com.hypernite.mc.hnmc.core.worlds.BukkitWorldHandler;
import com.hypernite.mc.hnmc.core.worlds.WorldPropertiesManager;

import java.util.HashMap;
import java.util.Map;

public class ModuleImplmentor implements Module {

    private Map<Class, Object> preImplement = new HashMap<>();

    @Override
    public void configure(Binder binder) {
        binder.bind(BungeeManager.class).to(Bungee.class).in(Scopes.SINGLETON);
        binder.bind(ChatFormatManager.class).to(Format.class).in(Scopes.SINGLETON);
        binder.bind(CoreConfig.class).to(HNMCoreConfig.class).in(Scopes.SINGLETON);
        binder.bind(CommandRegister.class).to(CommandHandler.class).in(Scopes.SINGLETON);
        binder.bind(CoreScheduler.class).to(SchedulerManager.class).in(Scopes.SINGLETON);
        binder.bind(NameTagManager.class).to(NameTagHandler.class).in(Scopes.SINGLETON);
        binder.bind(PlayerSkinManager.class).to(PlayerSkinHandler.class).in(Scopes.SINGLETON);
        binder.bind(SQLDataSource.class).to(SQLDataSourceManager.class).in(Scopes.SINGLETON);
        binder.bind(TabListManager.class).to(TablistBuilder.class).in(Scopes.SINGLETON);
        binder.bind(VaultAPI.class).to(VaultHandler.class).in(Scopes.SINGLETON);
        binder.bind(WorldManager.class).to(BukkitWorldHandler.class).in(Scopes.SINGLETON);
        binder.bind(CoreFactory.class).to(HNMainFactory.class).in(Scopes.SINGLETON);
        binder.bind(Builder.class).to(FactoryBuilder.class).in(Scopes.SINGLETON);
        binder.bind(RedisDataSource.class).to(RedisManager.class).in(Scopes.SINGLETON);
        binder.bind(EventCancelManager.class).to(CancelEventManager.class).in(Scopes.SINGLETON);

        /*
            Not API use but for singleton
         */
        binder.bind(WorldPropertiesManager.class).in(Scopes.SINGLETON);
        binder.bind(SkinDatabaseManager.class).in(Scopes.SINGLETON);
        binder.bind(HelpPagesManager.class).in(Scopes.SINGLETON);
        binder.bind(FormatDatabaseManager.class).in(Scopes.SINGLETON);
        binder.bind(ChatRunnerHandler.class).in(Scopes.SINGLETON);
        binder.bind(ItemBuilderEventListener.class).in(Scopes.SINGLETON);


        /*
            Not API use but for listener / commands
         */
        binder.bind(EventListener.class).in(Scopes.NO_SCOPE);
        binder.bind(ChatFormatListener.class).in(Scopes.NO_SCOPE);
        binder.bind(HelpCommand.class).in(Scopes.NO_SCOPE);

        preImplement.forEach((c, o) -> binder.bind(c).toInstance(o));
    }

    public void register(Class cls, Object obj) {
        preImplement.put(cls, obj);
    }
}
