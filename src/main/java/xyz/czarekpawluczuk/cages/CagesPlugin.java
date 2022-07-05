package xyz.czarekpawluczuk.cages;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.czarekpawluczuk.cages.commands.EventCommand;
import xyz.czarekpawluczuk.cages.data.Event;
import xyz.czarekpawluczuk.cages.enums.EventStatus;
import xyz.czarekpawluczuk.cages.enums.KitType;
import xyz.czarekpawluczuk.cages.helpers.ChatHelper;
import xyz.czarekpawluczuk.cages.listeners.*;
import xyz.czarekpawluczuk.cages.runnables.AdvertiseActionbarRunnable;

public class CagesPlugin extends JavaPlugin {

    private static CagesPlugin PLUGIN_INSTANCE;
    private ChatHelper chatHelper = new ChatHelper();

    @Getter @Setter
    public Event event = null;

    public static CagesPlugin getPluginInstance() {
        return PLUGIN_INSTANCE;
    }

    @Override
    public void onEnable() {
        PLUGIN_INSTANCE=this;
        getCommand("event").setExecutor(new EventCommand());
        new Event();
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
        getServer().getPluginManager().registerEvents(new BlockPlaceListener(), this);
        getServer().getPluginManager().registerEvents(new EntityByEntityDamageListener(), this);
        getServer().getPluginManager().registerEvents(new FoodChangeLevelListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
        getServer().getScheduler().runTaskTimer(this, new AdvertiseActionbarRunnable(), 5l, 5l);
    }

    @Override
    public void onDisable() {
/*        for(Player onlinePlayers : Bukkit.getOnlinePlayers()){
            onlinePlayers.kickPlayer(chatHelper.color("&d&lCageEventPlugin" +
                    "\n" +
                    "  &7Serwer korzysta z pluginu w wersji &f1.0\n"+
                    "&aDziękujemy za korzystanie z naszego pluginu\n"));
        }*/
    }

    public boolean eventIsActive(){
        return !event.getStatus().equals(EventStatus.AWAY);
    }
}
