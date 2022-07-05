package xyz.czarekpawluczuk.cages.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import xyz.czarekpawluczuk.cages.CagesPlugin;
import xyz.czarekpawluczuk.cages.data.Event;
import xyz.czarekpawluczuk.cages.helpers.ChatHelper;
import xyz.czarekpawluczuk.cages.helpers.builders.ItemBuilder;

import java.util.Arrays;

public class CageSettingsInventory {

    private ChatHelper chatHelper = new ChatHelper();
    private CagesPlugin plugin = CagesPlugin.getPluginInstance();

    public InventoryView openMain(Player player) {
        Event event = plugin.getEvent();
        Inventory inventory = Bukkit.createInventory(null, 9, chatHelper.color("&d&lCageEventPlugin &cUstawienia"));
        ItemBuilder kitsItem = new ItemBuilder(Material.ITEM_FRAME, 1)
                .setTitle("&cZmień zestaw")
                .addLores(Arrays.asList(
                        chatHelper.color(" "),
                        chatHelper.color("     &7Kliknij tutaj, aby zmienić"),
                        chatHelper.color(" &7obecnie ustawiony zestaw do walk."),
                        chatHelper.color(" "),
                        chatHelper.color("    &f&lObecny zestaw: &dDifult"),
                        chatHelper.color(" ")
                ));

        ItemBuilder eventInfoItem = new ItemBuilder(Material.DOUBLE_PLANT, 1)
                .setTitle("&aInformacje o evencie")
                .addLores(Arrays.asList(
                        chatHelper.color("&8» &7Zestaw: &f"+event.getKitType()),
                        chatHelper.color("&8» &7Zapisanych: &f"+event.getPlayers()),
                        chatHelper.color("&8» &7Status: &f"+event.getStatus()),
                        chatHelper.color("&8» &7Sekundy oczekiwania: &f"+event.getSecondsToStart()+"sek.")
                ));
        inventory.setItem(0, kitsItem.build());
        inventory.setItem(8, eventInfoItem.build());
        return player.openInventory(inventory);
    }

    public InventoryView openChangeKit(Player player){
        Inventory inventory = Bukkit.createInventory(null, 9, chatHelper.color("&d&lCageEventPlugin &cZmien kit"));
        ItemBuilder refreshConfigKits = new ItemBuilder(Material.DOUBLE_PLANT, 1)
                .setTitle(chatHelper.color("&ePrzeładuj config zestawów"))
                .addLores(Arrays.asList(
                        chatHelper.color(" "),
                        chatHelper.color("     &7Klikając tutaj wszystkie"),
                        chatHelper.color(" &7ustawienia zestawów zaktualizują się."),
                        chatHelper.color(" ")
                ));
        ItemBuilder defaultKit = new ItemBuilder(Material.DIAMOND_HELMET, 1)
                .setTitle(chatHelper.color("&aDomyślny zestaw"))
                .addLores(Arrays.asList(
                        chatHelper.color(" "),
                        chatHelper.color("         &7Klikając tutaj ustawisz"),
                        chatHelper.color(" &7jako zestaw do eventu &fDomyślny zestaw"),
                        chatHelper.color(" ")
                ));
        ItemBuilder potionKit = new ItemBuilder(Material.POTION, 1, (short)16453)
                .setTitle(chatHelper.color("&5Czarodziejski zestaw"))
                .addLores(Arrays.asList(
                        chatHelper.color(" "),
                        chatHelper.color("          &7Klikając tutaj ustawisz"),
                        chatHelper.color(" &7jako zestaw do eventu &fCzarodziejski zestaw"),
                        chatHelper.color(" ")
                ));
        ItemBuilder ironKit = new ItemBuilder(Material.IRON_SWORD, 1)
                .setTitle(chatHelper.color("&7Żelazny zestaw"))
                .addLores(Arrays.asList(
                        chatHelper.color(" "),
                        chatHelper.color("        &7Klikając tutaj ustawisz"),
                        chatHelper.color(" &7jako zestaw do eventu &fŻelazny zestaw"),
                        chatHelper.color(" ")
                ));
        inventory.setItem(0, defaultKit.build());
        inventory.setItem(1, potionKit.build());
        inventory.setItem(2, ironKit.build());
        inventory.setItem(8, refreshConfigKits.build());
        return player.openInventory(inventory);
    }
}
