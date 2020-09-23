package com.hypernite.mc.hnmc.core.ericlam;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.hypernite.mc.hnmc.core.main.HyperNiteMC;
import com.hypernite.mc.hnmc.core.managers.TabListManager;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

public class TablistBuilder implements TabListManager {

    private PacketContainer packet;

    public TablistBuilder() {
        packet = new PacketContainer(PacketType.Play.Server.PLAYER_LIST_HEADER_FOOTER);
    }

    @Override
    public void setHeader(String header, Player player) {
        packet.getChatComponents().write(0, WrappedChatComponent.fromText(header.replace('&', '§')));
        packet.getChatComponents().write(1, WrappedChatComponent.fromText("§eplay.hypernite.com"));

        try {
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, packet);
        } catch (InvocationTargetException | NullPointerException e) {
            HyperNiteMC.plugin.getLogger().info("Failed to update header/footer");
            HyperNiteMC.plugin.getLogger().info("Try check your configuration");
        }
    }

    @Override
    public void setHeaderFooter(String header, String footer, Player player) {

        packet.getChatComponents().write(0, WrappedChatComponent.fromText(header.replace('&', '§')));
        packet.getChatComponents().write(1, WrappedChatComponent.fromText(footer.replace('&', '§')));

        try {
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, packet);
        } catch (InvocationTargetException | NullPointerException e) {
            HyperNiteMC.plugin.getLogger().info("Failed to update header/footer");
            HyperNiteMC.plugin.getLogger().info("Try check your configuration");

        }
    }
}
