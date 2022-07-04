package xyz.czarekpawluczuk.cages.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {

    @EventHandler
    public void blockPlace(BlockPlaceEvent e){
        Player p = e.getPlayer();
        if(!p.isOp()){
            e.setCancelled(true);
        }
    }
}
