package xyz.czarekpawluczuk.cages.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import xyz.czarekpawluczuk.cages.CagesPlugin;
import xyz.czarekpawluczuk.cages.data.Event;
import xyz.czarekpawluczuk.cages.helpers.ChatHelper;

public class PlayerJoinListener implements Listener {

    private CagesPlugin plugin = CagesPlugin.getPluginInstance();
    private ChatHelper chatHelper = new ChatHelper();

    @EventHandler(priority = EventPriority.MONITOR)
    public void playerJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        player.setFoodLevel(20);
        if(player.isOp()){
            player.sendMessage(chatHelper.color("&8•» &d&lCageEventPlugin &8» &fKorzystasz z aktualnej wersji pluginu!"));
        }
        if(plugin.eventIsActive()) {
            Event event = plugin.getEvent();
            player.teleport(event.getWaitingRoomLocation());
        }
    }
}
