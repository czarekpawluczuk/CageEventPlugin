package xyz.czarekpawluczuk.cages.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import xyz.czarekpawluczuk.cages.CagesPlugin;
import xyz.czarekpawluczuk.cages.data.Event;
import xyz.czarekpawluczuk.cages.enums.EventStatus;
import xyz.czarekpawluczuk.cages.helpers.ChatHelper;

public class EntityByEntityDamageListener implements Listener {

    private CagesPlugin plugin = CagesPlugin.getPluginInstance();

    @EventHandler(priority = EventPriority.MONITOR)
    public void entityDamageByEntity(EntityDamageByEntityEvent e){
        if(e.getEntity() instanceof Player) {
            Player player = (Player) e.getEntity();
            if (plugin.events.size() > 0) {
                Event event = plugin.events.get(0);
                if (event.getStatus().equals(EventStatus.INGAME) && event.getCurrent().contains(player)) return;
            }
            e.setCancelled(true);
        }
    }
}
