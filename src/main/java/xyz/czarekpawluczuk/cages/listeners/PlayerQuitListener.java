package xyz.czarekpawluczuk.cages.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import xyz.czarekpawluczuk.cages.CagesPlugin;
import xyz.czarekpawluczuk.cages.data.Event;
import xyz.czarekpawluczuk.cages.enums.EventStatus;
import xyz.czarekpawluczuk.cages.helpers.ChatHelper;

public class PlayerQuitListener implements Listener {

    private ChatHelper chatHelper = new ChatHelper();
    private CagesPlugin plugin = CagesPlugin.getPluginInstance();

    @EventHandler(priority = EventPriority.MONITOR)
    public void playerQuit(PlayerQuitEvent e){
        Player player = e.getPlayer();
        if(plugin.eventIsActive()){
            Event event = plugin.getEvent();
            if((event.getStatus().equals(EventStatus.INGAME) || event.getStatus().equals(EventStatus.WAITING))
                    && (event.getWaiting().contains(player)
                    || event.getCurrent().contains(player)
                    || event.getWinners().contains(player))){
                Bukkit.getOnlinePlayers().forEach(onlinePlayers->{
                    if(event.getWinners().contains(onlinePlayers)
                            ||event.getCurrent().contains(onlinePlayers)
                            || event.getWinners().contains(onlinePlayers)){
                        onlinePlayers.sendMessage(chatHelper.color("&8•» &c&lWalki Gladiatorów &8» &fGracz &6"+ player.getName()+" &fopuścił event! &8("+event.getPlayers()+")"));
                    }
                });
                event.getWaiting().remove(player);
                event.getCurrent().remove(player);
                event.getWinners().remove(player);
                event.setPlayers(event.getPlayers()-1);
            }
        }
    }
}
