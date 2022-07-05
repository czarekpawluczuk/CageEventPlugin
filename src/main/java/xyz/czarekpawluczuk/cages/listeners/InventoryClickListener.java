package xyz.czarekpawluczuk.cages.listeners;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import xyz.czarekpawluczuk.cages.CagesPlugin;
import xyz.czarekpawluczuk.cages.data.Event;
import xyz.czarekpawluczuk.cages.enums.KitType;
import xyz.czarekpawluczuk.cages.helpers.ChatHelper;
import xyz.czarekpawluczuk.cages.inventories.CageSettingsInventory;

public class InventoryClickListener implements Listener {

    private ChatHelper chatHelper = new ChatHelper();
    private CagesPlugin plugin = CagesPlugin.getPluginInstance();
    private CageSettingsInventory settingsInventory = new CageSettingsInventory();

    @EventHandler(priority = EventPriority.MONITOR)
    public void inventoryClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        if(e.getCurrentItem()==null)return;
        ItemStack currentItem = e.getCurrentItem();
        if(e.getView().getTitle().equalsIgnoreCase(chatHelper.color("&d&lCageEventPlugin &cUstawienia"))) {
            e.setCancelled(true);
            if(currentItem.hasItemMeta() && currentItem.getItemMeta().getDisplayName().equalsIgnoreCase(chatHelper.color("&cZmień zestaw"))){
                settingsInventory.openChangeKit(player);
            }
        }else if(e.getView().getTitle().equalsIgnoreCase(chatHelper.color("&d&lCageEventPlugin &cZmien kit"))){
            e.setCancelled(true);
            if(plugin.eventIsActive()){
                player.sendMessage(chatHelper.color("&cW trakcie trwania eventu nie możesz zmieniać zestawu."));
                return;
            }
            Event event = plugin.getEvent();
            if(currentItem.hasItemMeta() && currentItem.getItemMeta().getDisplayName().equalsIgnoreCase(chatHelper.color("&aDomyślny zestaw"))){
                event.setKitType(KitType.DEFAULT);
                player.playSound(player.getLocation(), Sound.SUCCESSFUL_HIT, 1.0f, 1.0f);
                player.sendMessage(chatHelper.color("&7Ustawiono zestaw &fDomyślny zestaw"));
            }else if(currentItem.hasItemMeta() && currentItem.getItemMeta().getDisplayName().equalsIgnoreCase(chatHelper.color("&5Czarodziejski zestaw"))){
                event.setKitType(KitType.POTION);
                player.playSound(player.getLocation(), Sound.SUCCESSFUL_HIT, 1.0f, 1.0f);
                player.sendMessage(chatHelper.color("&7Ustawiono zestaw &fCzarodziejski zestaw"));
            }else if(currentItem.hasItemMeta() && currentItem.getItemMeta().getDisplayName().equalsIgnoreCase(chatHelper.color("&7Żelazny zestaw"))){
                event.setKitType(KitType.IRON);
                player.playSound(player.getLocation(), Sound.SUCCESSFUL_HIT, 1.0f, 1.0f);
                player.sendMessage(chatHelper.color("&7Ustawiono zestaw &fŻelazny zestaw"));
            }
        }
    }
}
