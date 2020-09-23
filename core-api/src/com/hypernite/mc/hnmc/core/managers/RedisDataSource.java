package com.hypernite.mc.hnmc.core.managers;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public interface RedisDataSource {
    Jedis getJedis();

    JedisPool getJedisPool();
}
