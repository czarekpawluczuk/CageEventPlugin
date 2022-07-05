package xyz.czarekpawluczuk.cages.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.czarekpawluczuk.cages.CagesPlugin;
import xyz.czarekpawluczuk.cages.data.Event;
import xyz.czarekpawluczuk.cages.enums.EventStatus;
import xyz.czarekpawluczuk.cages.helpers.ChatHelper;
import xyz.czarekpawluczuk.cages.inventories.CageSettingsInventory;

public class EventCommand implements CommandExecutor {

    private ChatHelper chatHelper = new ChatHelper();
    private CageSettingsInventory cageInventory = new CageSettingsInventory();
    private CagesPlugin plugin = CagesPlugin.getPluginInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(!(sender instanceof Player)){
            Bukkit.getLogger().severe("KOMENDA DOSTEPNA TYLKO DLA GRACZY");
            return true;
        }
        Player player = (Player) sender;
        if(args.length<1){
            correctUsage(player);
            return true;
        }
        if(args[0].equalsIgnoreCase("start")){
            if(!player.hasPermission("cageeventplugin-admin")){
                player.sendMessage(chatHelper.color("&cBrak uprawnień do użycia tej komendy!"));
                return true;
            }
            if(plugin.eventIsActive()) {
                player.sendMessage(chatHelper.color("Event jest już aktywny!"));
                return true;
            }
            Event event = plugin.getEvent();
            event.setOwner(player.getName());
            event.startGame();
        }else if(args[0].equalsIgnoreCase("join")){
            if(!plugin.eventIsActive()){
                player.sendMessage(chatHelper.color("Event nie jest aktywny!"));
                return true;
            }
            Event event = plugin.getEvent();
            if(event.getWaiting().contains(player)
                    ||event.getCurrent().contains(player)
                    ||event.getWinners().contains(player)){
                player.sendMessage(chatHelper.color("Jesteś już zapisany na event!"));
                return true;
            }
            if(event.getStatus().equals(EventStatus.INGAME)){
                player.sendMessage(chatHelper.color("Event już wystartował!"));
                return true;
            }
            event.joinTheGame(player);
        }else if(args[0].equalsIgnoreCase("settings")) {
            if(!player.hasPermission("cageeventplugin-admin")){
                player.sendMessage(chatHelper.color("&cBrak uprawnień do użycia tej komendy!"));
                return true;
            }
            cageInventory.openMain(player);
        }else if(args[0].equalsIgnoreCase("kit")) {
            Event event = plugin.getEvent();
            player.sendMessage("obecny kit="+event.getKitType());
        }else{
            correctUsage(player);
        }
        return false;
    }

    public void correctUsage(Player player){
        player.sendMessage(chatHelper.color("/event join"));
        if(player.hasPermission("cageeventplugin-admin")) {
            player.sendMessage(chatHelper.color("&c/event start"));
            player.sendMessage(chatHelper.color("&c/event settings"));
        }
    }
}
