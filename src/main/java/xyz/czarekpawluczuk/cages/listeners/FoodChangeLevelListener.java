package xyz.czarekpawluczuk.cages.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodChangeLevelListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void foodChangeLevel(FoodLevelChangeEvent e){
        e.setCancelled(true);
        e.setFoodLevel(20);
    }
}
