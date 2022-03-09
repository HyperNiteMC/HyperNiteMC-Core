package com.hypernite.mc.hnmc.core.main;


import com.google.inject.Guice;
import com.google.inject.Injector;
import com.hypernite.mc.hnmc.core.HyperNiteMCAPI;
import com.hypernite.mc.hnmc.core.ModuleImplmentor;
import com.hypernite.mc.hnmc.core.caxerx.ItemBuilderEventListener;
import com.hypernite.mc.hnmc.core.chatformat.ChatFormatListener;
import com.hypernite.mc.hnmc.core.chatformat.FormatDatabaseManager;
import com.hypernite.mc.hnmc.core.chatformat.NameTagHandler;
import com.hypernite.mc.hnmc.core.command.HNCoreCommand;
import com.hypernite.mc.hnmc.core.command.HelpCommand;
import com.hypernite.mc.hnmc.core.config.implement.HNMCoreConfig;
import com.hypernite.mc.hnmc.core.ericlam.ChatRunnerHandler;
import com.hypernite.mc.hnmc.core.factory.CoreFactory;
import com.hypernite.mc.hnmc.core.listener.*;
import com.hypernite.mc.hnmc.core.listener.cancelevent.OptionalListener;
import com.hypernite.mc.hnmc.core.managers.*;
import com.hypernite.mc.hnmc.core.misc.world.WorldLoadedException;
import com.hypernite.mc.hnmc.core.misc.world.WorldNonExistException;
import com.hypernite.mc.hnmc.core.updater.HyperNiteResourceManager;
import com.hypernite.mc.hnmc.core.updater.SpigotResourceManager;
import com.hypernite.mc.hnmc.core.worlds.BukkitWorldHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

public class HyperNiteMC extends JavaPlugin implements HyperNiteMCAPI {
    public static Plugin plugin;
    private static HyperNiteMCAPI api;
    private static HNMCoreConfig hnmCoreConfig;
    private static SkinDatabaseManager skinDatabaseManager;
    private static HelpPagesManager helpPagesManager;
    private static FormatDatabaseManager formatDatabaseManager;
    private static BukkitWorldHandler hnmcWorldManager;
    private static ChatRunnerHandler chatRunnerHandler;
    private static ItemBuilderEventListener itemEventManager;
    private final ModuleImplmentor moduleImplmentor = new ModuleImplmentor();
    private BungeeManager bungeeManager;
    private ChatFormatManager chatFormatManager;
    private CoreConfig coreConfig;
    private CoreScheduler coreScheduler;
    private NameTagManager nameTagManager;
    private PlayerSkinManager playerSkinManager;
    private SQLDataSource sqlDataSource;
    private TabListManager tabListManager;
    private CommandRegister commandRegister;
    private VaultAPI vaultAPI;
    private EventCancelManager eventCancelManager;
    private WorldManager worldManager;
    private CoreFactory coreFactory;
    private RedisDataSource redisDataSource;
    private Injector injector;
    private SpigotResourceManager spigotResourceManager;
    private HyperNiteResourceManager hyperniteResourceManager;

    public static HNMCoreConfig getHnmCoreConfig() {
        return hnmCoreConfig;
    }

    public static SkinDatabaseManager getSkinDatabaseManager() {
        return skinDatabaseManager;
    }

    public static ChatRunnerHandler getChatRunnerHandler() {
        return chatRunnerHandler;
    }

    public static ItemBuilderEventListener getItemEventManager() {
        return itemEventManager;
    }

    public static HelpPagesManager getHelpPagesManager() {
        return helpPagesManager;
    }

    public static FormatDatabaseManager getFormatDatabaseManager() {
        return formatDatabaseManager;
    }

    public static HyperNiteMCAPI getAPI() {
        return api;
    }

    public static BukkitWorldHandler getHnmcWorldManager() {
        return hnmcWorldManager;
    }

    @Override
    public void onLoad() {
        moduleImplmentor.register(Plugin.class, this);
        injector = Guice.createInjector(moduleImplmentor);
        api = this;

        plugin = injector.getInstance(Plugin.class);
        coreConfig = injector.getInstance(CoreConfig.class);
        bungeeManager = injector.getInstance(BungeeManager.class);
        coreScheduler = injector.getInstance(CoreScheduler.class);
        nameTagManager = injector.getInstance(NameTagManager.class);
        chatFormatManager = injector.getInstance(ChatFormatManager.class);
        playerSkinManager = injector.getInstance(PlayerSkinManager.class);
        sqlDataSource = injector.getInstance(SQLDataSource.class);
        tabListManager = injector.getInstance(TabListManager.class);
        commandRegister = injector.getInstance(CommandRegister.class);
        worldManager = injector.getInstance(WorldManager.class);
        chatRunnerHandler = injector.getInstance(ChatRunnerHandler.class);
        itemEventManager = injector.getInstance(ItemBuilderEventListener.class);
        coreFactory = injector.getInstance(CoreFactory.class);
        helpPagesManager = injector.getInstance(HelpPagesManager.class);
        formatDatabaseManager = injector.getInstance(FormatDatabaseManager.class);
        skinDatabaseManager = injector.getInstance(SkinDatabaseManager.class);
        eventCancelManager = injector.getInstance(EventCancelManager.class);
        vaultAPI = injector.getInstance(VaultAPI.class);
        spigotResourceManager = injector.getInstance(SpigotResourceManager.class);
        hyperniteResourceManager = injector.getInstance(HyperNiteResourceManager.class);

        hnmCoreConfig = (HNMCoreConfig) coreConfig;
        hnmcWorldManager = (BukkitWorldHandler) worldManager;

        if (hnmCoreConfig.getDatabase().redis.enabled) {
            redisDataSource = injector.getInstance(RedisDataSource.class);
        }


    }

    @Override
    public void onEnable() {
        ((Format) chatFormatManager).setup();
        ((NameTagHandler) nameTagManager).setup();
        Bukkit.getScheduler().runTask(plugin, () -> {
            getServer().getPluginManager().registerEvents(injector.getInstance(EventListener.class), this);
            Optional.ofNullable(this.getCommand("help")).ifPresent(c -> c.setExecutor(injector.getInstance(HelpCommand.class)));
            getServer().getPluginManager().registerEvents(injector.getInstance(ChatFormatListener.class), this);
        });

        ConsoleCommandSender console = getServer().getConsoleSender();
        console.sendMessage(ChatColor.GREEN + "Initializing HyperNiteMC Libraries");
        if (getServer().getPluginManager().isPluginEnabled("Vault")) {
            console.sendMessage(ChatColor.AQUA + "Successfully hooked Vault plugin.");
        }
        getServer().getPluginManager().registerEvents(chatRunnerHandler, this);
        getServer().getPluginManager().registerEvents(itemEventManager, this);
        Random random = new Random();
        var enableds = Arrays.stream(getServer().getPluginManager().getPlugins()).filter(Plugin::isEnabled).toArray(Plugin[]::new);
        int index = random.nextInt(enableds.length);
        getServer().getPluginManager().registerEvents(new WorldListeners(), this);

        commandRegister.registerCommand(this, new HNCoreCommand());

        if (hnmCoreConfig.getCancel().cancelEventsEnabled) {
            getServer().getPluginManager().registerEvents(new OptionalListener(), this);
        }

        console.sendMessage(ChatColor.YELLOW + "Connecting to mysql to get skin database.......");

        if (hnmCoreConfig.getDatabase().host == null) {
            getLogger().warning("Seems the Database.yml hasn't been loaded properly, restarting server...");
            getServer().spigot().restart();
            return;
        }

        console.sendMessage(ChatColor.GREEN + "Successfully connected to mysql.");

        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            formatDatabaseManager.getChatformat(); //Get the chatformat from mysql
            helpPagesManager.getPages(); //Get the help pages from mysql
        });

        this.getServer().getPluginManager().registerEvents(new VersionUpdateListener(this), this);


        hnmcWorldManager.loadDefaultWorld();
        if (hnmCoreConfig.getConfig().autoLoadExtraWorlds) {
            hnmcWorldManager.getWorldList().entrySet().stream()
                    .filter(Map.Entry::getValue)
                    .filter(en -> Bukkit.getWorld(en.getKey()) != null)
                    .map(Map.Entry::getKey)
                    .forEach(world -> {
                        try {
                            hnmcWorldManager.loadWorld(world).whenComplete((w, ex) -> {
                                if (ex != null) {
                                    ex.printStackTrace();
                                }
                                var result = w != null;
                                getLogger().info("世界 " + world + " 加載 " + (result ? "成功" : "失敗") + "。");
                            });
                        } catch (WorldNonExistException e) {
                            getLogger().warning("加載世界 " + e.getWorld() + " 失敗, 世界不存在。");
                        } catch (WorldLoadedException e) {
                            getLogger().warning("加載世界 " + e.getWorld() + " 失敗, 世界已加載。");
                        }
                    });
        }
    }

    @Override
    public void onDisable() {
        this.getServer().getMessenger().unregisterOutgoingPluginChannel(this);
        getLogger().info("HyperNiteMC Libraries Disabled");
        hnmcWorldManager.saveAll();
    }

    @Override
    public BungeeManager getBungeeManager() {
        return bungeeManager;
    }

    @Override
    public ChatFormatManager getChatFormatManager() {
        return chatFormatManager;
    }

    @Override
    public CoreFactory getFactory() {
        return coreFactory;
    }

    @Override
    public CoreScheduler getCoreScheduler() {
        return coreScheduler;
    }

    @Override
    public CommandRegister getCommandRegister() {
        return commandRegister;
    }

    @Override
    public NameTagManager getNameTagManager() {
        return nameTagManager;
    }

    @Override
    public PlayerSkinManager getPlayerSkinManager() {
        return playerSkinManager;
    }

    @Override
    public SQLDataSource getSQLDataSource() {
        return sqlDataSource;
    }

    @Override
    public RedisDataSource getRedisDataSource() {
        return Optional.ofNullable(redisDataSource).orElseThrow(() -> new IllegalStateException("Redis has not enabled in config"));
    }

    @Override
    public TabListManager getTabListManager() {
        return tabListManager;
    }

    @Override
    public CoreConfig getCoreConfig() {
        return coreConfig;
    }

    @Override
    public VaultAPI getVaultAPI() {
        return vaultAPI;
    }

    @Override
    public WorldManager getWorldManager() {
        return worldManager;
    }

    @Override
    public EventCancelManager getEventCancelManager() {
        return eventCancelManager;
    }

    @Override
    public ResourceManager getResourceManager(ResourceManager.Type type) {
        return type == ResourceManager.Type.SPIGOT ? spigotResourceManager : hyperniteResourceManager;
    }
}
