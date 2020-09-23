package com.hypernite.mc.hnmc.core;

import org.bukkit.event.Event;

import java.util.function.Function;

public interface GetterFunction<T extends Event, R> extends Function<T, R> {
    @Override
    R apply(T t);
}
