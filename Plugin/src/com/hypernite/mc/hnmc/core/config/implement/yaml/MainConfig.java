package com.hypernite.mc.hnmc.core.config.implement.yaml;

import com.hypernite.mc.hnmc.core.config.yaml.Configuration;
import com.hypernite.mc.hnmc.core.config.yaml.Resource;


@Resource(locate = "Config.yml")
public class MainConfig extends Configuration {

    public boolean useOwnScoreboard;

    public int fallBackDelay;

    public boolean interactEventDefaultCancelled;

    public boolean clickEventDefaultCancelled;

    public boolean autoLoadExtraWorlds;

}
