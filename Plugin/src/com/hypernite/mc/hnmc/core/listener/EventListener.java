package com.hypernite.mc.hnmc.core.listener;

import com.google.inject.Inject;
import com.hypernite.mc.hnmc.core.config.implement.HNMCoreConfig;
import com.hypernite.mc.hnmc.core.main.HyperNiteMC;
import com.hypernite.mc.hnmc.core.managers.ChatFormatManager;
import com.hypernite.mc.hnmc.core.managers.NameTagManager;
import com.hypernite.mc.hnmc.core.managers.NickManager;
import com.hypernite.mc.hnmc.core.managers.VaultAPI;
import com.hypernite.mc.hnmc.core.misc.permission.Perm;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.TabCompleteEvent;

import java.util.HashSet;
import java.util.List;

public class EventListener implements Listener {

    private final Chat chat;
    private HNMCoreConfig hnmCoreConfig = HyperNiteMC.getHnmCoreConfig();
    @Inject
    private NameTagManager nameTagManager;
    @Inject
    private ChatFormatManager chatFormatManager;


    @Inject
    public EventListener(VaultAPI api) {
        chat = api.getChat();
    }

    @EventHandler
    public void JoinHideMSG(PlayerJoinEvent e) {
        e.setJoinMessage("");
        Player player = e.getPlayer();
        if (chat != null && !hnmCoreConfig.getConfig().useOwnScoreboard) {
            chatFormatManager.updatePlayerList(player); //update player list
            nameTagManager.addPlayer(player); //update name tag
        }
    }

    //Nick Test
    @EventHandler
    public void onCommandPrepross(PlayerCommandPreprocessEvent e) {
        HashSet<Player> nicks = NickManager.getInstance().getNicks();
        nicks.forEach(nick -> e.setMessage(e.getMessage().replaceAll(nick.getDisplayName(), nick.getName())));
    }

    @EventHandler
    public void onTabComplete(TabCompleteEvent e) {
        List<String> tabs = e.getCompletions();
        for (Player nick : NickManager.getInstance().getNicks()) {
            if (tabs.contains(nick.getName())) {
                tabs.remove(nick.getName());
                tabs.add(nick.getDisplayName());
            }
        }
        e.setCompletions(tabs);
    }
    //

    @EventHandler
    public void LeaveHideMSG(PlayerQuitEvent e) {
        e.setQuitMessage("");
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if (Perm.hasPermission(event.getPlayer(), Perm.DONOR)) {
            event.setMessage(ChatColor.translateAlternateColorCodes('&', event.getMessage()));
        }
        //Nick Test
        String msg = event.getMessage();
        for (Player nick : NickManager.getInstance().getNicks()) {
            if (msg.contains(nick.getName())) {
                event.setCancelled(true);
                event.getPlayer().sendMessage("??c???????????????????????????????????????????????????????????????????????????????????????");
            }
        }
    }

}
