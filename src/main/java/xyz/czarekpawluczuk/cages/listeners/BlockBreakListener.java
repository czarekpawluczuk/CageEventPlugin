package xyz.czarekpawluczuk.cages.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

    @EventHandler
    public void blockBreak(BlockBreakEvent e){
        Player p = e.getPlayer();
        if(!p.isOp()){
            e.setCancelled(true);
        }
    }
}
