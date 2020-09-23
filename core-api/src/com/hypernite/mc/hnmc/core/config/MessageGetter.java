package com.hypernite.mc.hnmc.core.config;

import java.util.List;

/**
 * 訊息獲取器
 *
 * @see com.hypernite.mc.hnmc.core.config.yaml.MessageConfiguration
 */
public interface MessageGetter {

    String getPrefix();

    String get(String path);

    String getPure(String path);

    List<String> getList(String path);

    List<String> getPureList(String path);

}
