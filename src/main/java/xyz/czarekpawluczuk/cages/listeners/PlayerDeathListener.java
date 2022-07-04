package xyz.czarekpawluczuk.cages.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import xyz.czarekpawluczuk.cages.CagesPlugin;
import xyz.czarekpawluczuk.cages.data.Event;
import xyz.czarekpawluczuk.cages.enums.EventStatus;
import xyz.czarekpawluczuk.cages.helpers.ChatHelper;

public class PlayerDeathListener implements Listener {

    private CagesPlugin plugin = CagesPlugin.getPluginInstance();
    private ChatHelper chatHelper = new ChatHelper();

    @EventHandler(priority = EventPriority.MONITOR)
    public void playerDeath(PlayerDeathEvent e){
        Player player = e.getEntity().getPlayer();
        Player killer = player.getKiller();
        e.setDeathMessage(null);
        if(killer instanceof Player
                && player instanceof Player){

        }
        if(killer==null){
            Bukkit.broadcastMessage(chatHelper.color("&cGracz &7"+player.getName()+" &czginął z nieznanych przyczyn!"));
        }
        if(plugin.events.size()>0){
            Event event = plugin.events.get(0);
            if(event.getStatus().equals(EventStatus.INGAME)){
                e.getDrops().clear();
                player.setHealth(20.0);
                player.setFireTicks(0);
                player.setFoodLevel(20);
                killer.setHealth(20.0);
                killer.setFireTicks(0);
                killer.setFoodLevel(20);
                player.teleport(event.getWaitingRoomLocation());
                Bukkit.getScheduler().runTaskLater(plugin, () -> {
                    killer.getInventory().clear();
                    killer.getInventory().setArmorContents(null);
                    player.getInventory().clear();
                    killer.teleport(event.getWaitingRoomLocation());
                }, 10l);
                event.setRound(event.getRound()+1);
                event.setPlayers(event.getPlayers()-1);
                event.nextRound(killer, player);
            }
        }
    }
}
