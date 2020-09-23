package com.hypernite.mc.hnmc.core.config.implement.yaml;

import com.hypernite.mc.hnmc.core.config.yaml.MessageConfiguration;
import com.hypernite.mc.hnmc.core.config.yaml.Prefix;
import com.hypernite.mc.hnmc.core.config.yaml.Resource;


@Resource(locate = "Messages.yml")
@Prefix(path = "Prefix")
public class MessageConfig extends MessageConfiguration {
}
