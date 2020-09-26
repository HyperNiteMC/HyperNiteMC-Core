package com.hypernite.mc.hnmc.core.config.implement.yaml;

import com.hypernite.mc.hnmc.core.config.yaml.Configuration;
import com.hypernite.mc.hnmc.core.config.yaml.Resource;

import java.util.Map;

@Resource(locate = "version-checker.yml")
public class VersionCheckerConfig extends Configuration {

    public long intervalHours;

    public boolean enabled_spigot_check;

    public Map<String, Long> resourceId_to_checks;

    public boolean ignore_unknown;

    public boolean use_unequal_check;

}
