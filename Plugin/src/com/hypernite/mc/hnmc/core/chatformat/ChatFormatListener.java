package com.hypernite.mc.hnmc.core.chatformat;

import com.google.inject.Inject;
import com.hypernite.mc.hnmc.core.main.HyperNiteMC;
import com.hypernite.mc.hnmc.core.managers.ChatFormatManager;
import com.hypernite.mc.hnmc.core.misc.permission.Perm;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatFormatListener implements Listener {

    @Inject
    private ChatFormatManager format;


    @EventHandler
    public void onPlayerChat(final AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();

        final boolean papiEnabled = HyperNiteMC.getHnmCoreConfig().isPapiEnabled();

        final String finalMessage = player.hasPermission(Perm.DONOR) ? ChatColor.translateAlternateColorCodes('&', e.getMessage()) : e.getMessage();

        final String finalFormat = format.getChatFormat(player);

        if (finalFormat.isEmpty()) return;

        String format;

        if (papiEnabled) {
            format = PlaceholderAPI.setPlaceholders(player, finalFormat);
        } else {
            format = finalFormat;
        }

        e.setFormat(format);
        e.setMessage(finalMessage);
    }

}
