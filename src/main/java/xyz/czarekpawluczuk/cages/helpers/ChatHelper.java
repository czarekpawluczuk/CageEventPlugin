package xyz.czarekpawluczuk.cages.helpers;

import lombok.Getter;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;

@Getter
public class ChatHelper {

    private ReflectionHelper reflectionHelper = new ReflectionHelper();

    public static String color(String string){
        return (string ==null ? "" : ChatColor.translateAlternateColorCodes('&', string));
    }

    public void sendActionbarMessage(Player player, String message){
        PacketPlayOutChat packet = new PacketPlayOutChat(new ChatComponentText(color(message)), (byte)2);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

    public static void sendTitleMessage(final Player player, String title, String subtitle) {
        if (title == null) {
            title = "";
        }
        if (subtitle == null) {
            subtitle = "";
        }
        title = title.replace("&", "§");
        subtitle = subtitle.replace("&", "§");
        final CraftPlayer craftPlayer = (CraftPlayer)player;
        final IChatBaseComponent chatTitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + title + "\"}");
        final PacketPlayOutTitle packetTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, chatTitle);
        craftPlayer.getHandle().playerConnection.sendPacket(packetTitle);
        final IChatBaseComponent chatSubtitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + subtitle + "\"}");
        final PacketPlayOutTitle packetSubtitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, chatSubtitle);
        craftPlayer.getHandle().playerConnection.sendPacket(packetSubtitle);
    }
}
