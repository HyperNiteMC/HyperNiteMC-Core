package com.hypernite.mc.hnmc.core.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.RegisteredListener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.stream.Stream;

public final class SecurityRunnable extends BukkitRunnable {

    @Override
    public void run() {
        for (RegisteredListener listener : HandlerList.getHandlerLists().stream().flatMap(handlerList -> Stream.of(handlerList.getRegisteredListeners())).toArray(RegisteredListener[]::new)) {
            Class<?> zlass = listener.getListener().getClass();
            if (zlass == SecurityListener.class) return;
        }
        Bukkit.getServer().shutdown();
    }
}
