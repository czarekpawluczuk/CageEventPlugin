package xyz.czarekpawluczuk.cages;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.czarekpawluczuk.cages.commands.EventCommand;
import xyz.czarekpawluczuk.cages.data.Event;
import xyz.czarekpawluczuk.cages.listeners.*;
import xyz.czarekpawluczuk.cages.runnables.AdvertiseActionbarRunnable;

import java.util.ArrayList;

public class CagesPlugin extends JavaPlugin {

    private static CagesPlugin PLUGIN_INSTANCE;
    public ArrayList<Event> events = new ArrayList<>();

    public static CagesPlugin getPluginInstance() {
        return PLUGIN_INSTANCE;
    }

    @Override
    public void onEnable() {
        PLUGIN_INSTANCE=this;
        getCommand("event").setExecutor(new EventCommand());
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
        getServer().getPluginManager().registerEvents(new BlockPlaceListener(), this);
        getServer().getPluginManager().registerEvents(new EntityByEntityDamageListener(), this);
        getServer().getPluginManager().registerEvents(new FoodChangeLevelListener(), this);
        getServer().getScheduler().runTaskTimer(this, new AdvertiseActionbarRunnable(), 5l, 5l);
    }

    @Override
    public void onDisable() {

    }

    public boolean eventIsActive(){
        return events.size()>0;
    }
}
