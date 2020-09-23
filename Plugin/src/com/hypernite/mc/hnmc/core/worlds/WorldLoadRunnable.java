package com.hypernite.mc.hnmc.core.worlds;

import com.ericlam.mc.async.create.world.main.AsyncCreateWorld;
import com.hypernite.mc.hnmc.core.main.HyperNiteMC;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.function.Consumer;

public final class WorldLoadRunnable extends BukkitRunnable {

    private final boolean asyncEnabled;
    private final WorldCreator worldCreator;
    private final Consumer<World> asyncRunner;

    public WorldLoadRunnable(boolean asyncEnabled, WorldCreator worldCreator, Consumer<World> asyncRunner) {
        this.asyncEnabled = asyncEnabled;
        this.worldCreator = worldCreator;
        this.asyncRunner = asyncRunner;
    }


    @Override
    public void run() {
        World world;
        if (asyncEnabled) {
            world = AsyncCreateWorld.getApi().getWorldCreator().createWorld(worldCreator);
        } else {
            world = worldCreator.createWorld();
        }
        HyperNiteMC.getAPI().getCoreScheduler().runAsync(() -> asyncRunner.accept(world));
    }
}
