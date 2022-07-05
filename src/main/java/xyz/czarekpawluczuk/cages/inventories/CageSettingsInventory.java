package xyz.czarekpawluczuk.cages.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import xyz.czarekpawluczuk.cages.helpers.ChatHelper;
import xyz.czarekpawluczuk.cages.helpers.builders.ItemBuilder;

import java.util.Arrays;

public class CageSettingsInventory {

    private ChatHelper chatHelper = new ChatHelper();

    public InventoryView open(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 1, chatHelper.color("&d&lCageEventPlugin &8- &cUstawienia"));
        ItemBuilder kitsItem = new ItemBuilder(Material.ITEM_FRAME, 1)
                .setTitle("&cZmień zestaw")
                .addLores(Arrays.asList(
                        chatHelper.color(" "),
                        chatHelper.color("   &7Kliknij tutaj, aby zmienić"),
                        chatHelper.color(" &7obecnie ustawiony zestaw do walk."),
                        chatHelper.color(" "),
                        chatHelper.color("    &f&lObecny zestaw: &dDifult"),
                        chatHelper.color(" ")
                        ));
        inventory.setItem(0, kitsItem.build());
        return player.openInventory(inventory);
    }
}
