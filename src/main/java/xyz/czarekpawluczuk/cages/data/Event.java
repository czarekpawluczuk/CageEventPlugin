package xyz.czarekpawluczuk.cages.data;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;
import xyz.czarekpawluczuk.cages.CagesPlugin;
import xyz.czarekpawluczuk.cages.enums.EventStatus;
import xyz.czarekpawluczuk.cages.enums.KitType;
import xyz.czarekpawluczuk.cages.helpers.ChatHelper;
import xyz.czarekpawluczuk.cages.helpers.builders.ItemBuilder;

import java.util.ArrayList;
import java.util.Arrays;

@Getter
@Setter
public class Event {

    private ChatHelper chatHelper = new ChatHelper();
    private CagesPlugin plugin = CagesPlugin.getPluginInstance();

    private EventStatus status;
    private String owner;
    private int players;
    private int round;
    private int secondsToStart;
    private KitType kitType;
    private Location waitingRoomLocation;
    private Location firstPosition;
    private Location secondPosition;
    private ArrayList<Player> winners;
    private ArrayList<Player> current;
    private ArrayList<Player> waiting;

    public Event(){
        this.status = EventStatus.AWAY;
        this.owner = null;
        this.players = 0;
        this.round = 0;
        this.secondsToStart = 20;
        this.kitType = KitType.DEFAULT;
        this.waitingRoomLocation = new Location(Bukkit.getWorld("world"), 1374.5, 16.0, -44.5);
        this.firstPosition = new Location(Bukkit.getWorld("world"), 1374.5, 4.0, -65.5);
        this.secondPosition = new Location(Bukkit.getWorld("world"), 1374.5, 4.0, -23.5, 180f, 0f);
        this.winners = new ArrayList<>();
        this.current = new ArrayList<>();
        this.waiting = new ArrayList<>();
        plugin.setEvent(this);
    }


    public void startGame(){
        setStatus(EventStatus.WAITING);
        Bukkit.broadcastMessage(chatHelper.color(" "));
        Bukkit.broadcastMessage(chatHelper.color("&8» &fZapisy na event &cWalki Gladiatorów &fwystartowały!"));
        Bukkit.broadcastMessage(chatHelper.color("&8» &fAktywowany został przez &a"+getOwner()));
        Bukkit.broadcastMessage(chatHelper.color("&8» &fZostało Ci &d&l60 sekund &fna zapisanie się!"));
        Bukkit.broadcastMessage(chatHelper.color(" "));
        Bukkit.broadcastMessage(chatHelper.color("&8» &fAby się zapisać użyj: &7/event join"));
        Bukkit.broadcastMessage(chatHelper.color(" "));
        new BukkitRunnable(){
            @Override
            public void run() {
                int secondsMessageArray[] = {1, 2, 3, 4, 5, 50, 40, 30, 20, 10};
                if(getSecondsToStart()>0) {
                    setSecondsToStart(getSecondsToStart() - 1);
                    if(Arrays.stream(secondsMessageArray).anyMatch(i -> i == getSecondsToStart())){
                        Bukkit.broadcastMessage(chatHelper.color(" "));
                        Bukkit.broadcastMessage(chatHelper.color("&8» &fZapisy na event &cWalki Gladiatorów &ftrwają!"));
                        Bukkit.broadcastMessage(chatHelper.color("&8» &fZostało Ci jeszcze &d&l"+getSecondsToStart()+" sekund&f, aby się zapisać!"));
                        Bukkit.broadcastMessage(chatHelper.color(" "));
                        Bukkit.getOnlinePlayers().forEach(onlinePlayers->{
                            if(current.contains(onlinePlayers)) return;
                            onlinePlayers.playSound(onlinePlayers.getLocation(), Sound.SUCCESSFUL_HIT, 1.0f, 1.0f);
                        });
                    }
                }else{
                    Bukkit.broadcastMessage(chatHelper.color(" "));
                    if(getPlayers()>=2) {
                        Bukkit.broadcastMessage(chatHelper.color("&8» &fZapisy na event &cWalki Gladiatorów &fzakończone!"));
                        Bukkit.broadcastMessage(chatHelper.color("&8» &fZapisało się &e&l" +getPlayers() + " graczy&f, a więc event"));
                        Bukkit.broadcastMessage(chatHelper.color("                    &2WYSTARTOWAŁ"));
                        Bukkit.broadcastMessage(chatHelper.color(" "));
                        Bukkit.broadcastMessage(chatHelper.color("          &a&lŻyczymy wszystkim powodzenia!"));
                        Bukkit.getScheduler().runTaskLater(plugin, () -> {
                            Player firstPlayer = waiting.get(0);
                            Player secondPlayer = waiting.get(1);
                            waiting.remove(firstPlayer);
                            waiting.remove(secondPlayer);
                            current.add(firstPlayer);
                            current.add(secondPlayer);
                            setStatus(EventStatus.INGAME);
                            giveItems(firstPlayer, secondPlayer);
                            Bukkit.getOnlinePlayers().forEach(onlinePlayers -> {chatHelper.sendTitleMessage(onlinePlayers, "&c&lPierwsza walka!", "&f"+firstPlayer.getName()+" &4VS &f"+secondPlayer.getName());});
                        }, 40l);
                    }else{
                        setStatus(EventStatus.AWAY);
                        Bukkit.broadcastMessage(chatHelper.color("&8» &fZapisy na event &cWalki Gladiatorów &fzakończone!"));
                        Bukkit.broadcastMessage(chatHelper.color("&8» &fZapisało się &e&l" + getPlayers() + " graczy&f, a więc event"));
                        Bukkit.broadcastMessage(chatHelper.color("                    &cNIE WYSTARTOWAŁ"));
                        plugin.setEvent(null);
                    }
                    Bukkit.broadcastMessage(chatHelper.color(" "));
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 20l, 20l);
    }

    public void joinTheGame(Player player){
        player.teleport(waitingRoomLocation);
        waiting.add(player);
        setPlayers(getPlayers()+1);
        player.sendMessage(chatHelper.color("&8» &fZostałeś &5zapisany &fna event &cWalki Gladiatorów&f!"));
        player.sendMessage(chatHelper.color("&8» &fW przypadku opuszczenia gry, zostajesz automatycznie usunięty"));
        player.sendMessage(chatHelper.color("&8» &fz listy zapisanych graczy do eventu!"));
        player.playSound(player.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
        Bukkit.getOnlinePlayers().forEach(onlinePlayers->{
            if(waiting.contains(onlinePlayers)
                    ||current.contains(onlinePlayers)
                    || winners.contains(onlinePlayers)){
                onlinePlayers.sendMessage(chatHelper.color("&8•» &c&lWalki Gladiatorów &8» &fGracz &6"+ player.getName()+" &fdołączył do eventu! &8("+getPlayers()+")"));
            }
        });
        player.setHealth(20.0);
        player.setFoodLevel(20);
        player.setFireTicks(0);
        for(PotionEffect potionEffect : player.getActivePotionEffects()){
            player.removePotionEffect(potionEffect.getType());
        }
    }

    public void nextRound(Player winner){
        if(getPlayers()>1){
            winners.add(winner);
            if(getWaiting().size()<=1){
                waiting.addAll(winners);
                winners.clear();
                current.add(waiting.get(0));
                current.add(waiting.get(1));
                waiting.remove(current.get(0));
                waiting.remove(current.get(1));
                winner.teleport(getWaitingRoomLocation());
                Player firstPlayer = current.get(0);
                Player secondPlayer = current.get(1);
                Bukkit.getScheduler().runTaskLater(plugin, () -> giveItems(firstPlayer, secondPlayer), 30l);
            }else{
                Player firstPlayer = waiting.get(0);
                Player secondPlayer = waiting.get(1);
                waiting.remove(firstPlayer);
                waiting.remove(secondPlayer);
                current.add(firstPlayer);
                current.add(secondPlayer);
                Bukkit.getScheduler().runTaskLater(plugin, () -> giveItems(firstPlayer, secondPlayer), 30l);
            }
            Bukkit.getOnlinePlayers().forEach(onlinePlayers -> {chatHelper.sendTitleMessage(onlinePlayers, "&c&lWalka!", "&f"+current.get(0).getName()+" &4VS &f"+current.get(1).getName());});
        }else{
            Bukkit.broadcastMessage(chatHelper.color(" "));
            Bukkit.broadcastMessage(chatHelper.color("             &8» &d&lCageEventPlugin &8«"));
            Bukkit.broadcastMessage(chatHelper.color(" "));
            Bukkit.broadcastMessage(chatHelper.color("          &b&lMamy zwyzięzcę eventu!"));
            Bukkit.broadcastMessage(chatHelper.color("      &8» &fGratulacje dla &2&l"+winner.getName()+" &8«"));
            Bukkit.broadcastMessage(chatHelper.color("    "));
            Bukkit.broadcastMessage(chatHelper.color("       &8• &fEvent trwał przez &e&l"+round+" rund &8•"));
            Bukkit.broadcastMessage(chatHelper.color("     &ePrzegranym życzymy powodzenia w następnym evencie!"));
            Bukkit.broadcastMessage(chatHelper.color(" "));
            Bukkit.getOnlinePlayers().forEach(onlinePlayers -> {
                chatHelper.sendTitleMessage(onlinePlayers, "&a&lTurniej wygrał/a", "&f"+winner.getName());
            });
            winners.remove(winner);
            current.remove(winner);
            setStatus(EventStatus.AWAY);
            setSecondsToStart(20);
            setPlayers(0);
            setRound(0);
            Bukkit.broadcastMessage("waiting: "+getWaiting());
            Bukkit.broadcastMessage("current: "+getCurrent());
            Bukkit.broadcastMessage("winners: "+getWinners());
            current.clear();
            waiting.clear();
            winners.clear();
        }
    }

    public void giveItems(Player firstPlayer, Player secondPlayer){
        ItemBuilder helmet = null;
        ItemBuilder chestplate = null;
        ItemBuilder leggings = null;
        ItemBuilder boots = null;
        ItemBuilder sword = null;

        ItemBuilder potion = null;

        Event event = plugin.getEvent();
        if(event.getKitType().equals(KitType.DEFAULT)) {
            helmet = new ItemBuilder(Material.DIAMOND_HELMET)
                    .setTitle("&d&lCageEventPlugin")
                    .addLore(chatHelper.color("&7Author: &fCzarek Pawluczuk"));
            chestplate = new ItemBuilder(Material.DIAMOND_CHESTPLATE)
                    .setTitle("&d&lCageEventPlugin")
                    .addLore(chatHelper.color("&7Author: &fCzarek Pawluczuk"));
            leggings = new ItemBuilder(Material.DIAMOND_LEGGINGS)
                    .setTitle("&d&lCageEventPlugin")
                    .addLore(chatHelper.color("&7Author: &fCzarek Pawluczuk"));
            boots = new ItemBuilder(Material.DIAMOND_BOOTS)
                    .setTitle("&d&lCageEventPlugin")
                    .addLore(chatHelper.color("&7Author: &fCzarek Pawluczuk"));
            sword = new ItemBuilder(Material.IRON_SWORD)
                    .setTitle("&d&lCageEventPlugin")
                    .addLore(chatHelper.color("&7Author: &fCzarek Pawluczuk"));
        }else if(event.getKitType().equals(KitType.POTION)){
            helmet = new ItemBuilder(Material.DIAMOND_HELMET)
                    .setTitle("&d&lCageEventPlugin")
                    .addEnchantment(Enchantment.DURABILITY, 3)
                    .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                    .addLore(chatHelper.color("&7Author: &fCzarek Pawluczuk"));
            chestplate = new ItemBuilder(Material.DIAMOND_CHESTPLATE)
                    .setTitle("&d&lCageEventPlugin")
                    .addEnchantment(Enchantment.DURABILITY, 3)
                    .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                    .addLore(chatHelper.color("&7Author: &fCzarek Pawluczuk"));
            leggings = new ItemBuilder(Material.DIAMOND_LEGGINGS)
                    .setTitle("&d&lCageEventPlugin")
                    .addEnchantment(Enchantment.DURABILITY, 3)
                    .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                    .addLore(chatHelper.color("&7Author: &fCzarek Pawluczuk"));
            boots = new ItemBuilder(Material.DIAMOND_BOOTS)
                    .setTitle("&d&lCageEventPlugin")
                    .addEnchantment(Enchantment.DURABILITY, 3)
                    .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                    .addLore(chatHelper.color("&7Author: &fCzarek Pawluczuk"));
            sword = new ItemBuilder(Material.DIAMOND_SWORD)
                    .setTitle("&d&lCageEventPlugin")
                    .addEnchantment(Enchantment.DAMAGE_ALL, 5)
                    .addEnchantment(Enchantment.FIRE_ASPECT, 2)
                    .addLore(chatHelper.color("&7Author: &fCzarek Pawluczuk"));
            potion = new ItemBuilder(Material.POTION, 1, (short) 16453)
                    .setTitle("&d&lCageEventPlugin")
                    .addLore(chatHelper.color("&7Author: &fCzarek Pawluczuk"));
        }else if(event.getKitType().equals(KitType.IRON)){
            helmet = new ItemBuilder(Material.IRON_HELMET)
                    .setTitle("&d&lCageEventPlugin")
                    .addLore(chatHelper.color("&7Author: &fCzarek Pawluczuk"));
            chestplate = new ItemBuilder(Material.IRON_CHESTPLATE)
                    .setTitle("&d&lCageEventPlugin")
                    .addLore(chatHelper.color("&7Author: &fCzarek Pawluczuk"));
            leggings = new ItemBuilder(Material.IRON_LEGGINGS)
                    .setTitle("&d&lCageEventPlugin")
                    .addLore(chatHelper.color("&7Author: &fCzarek Pawluczuk"));
            boots = new ItemBuilder(Material.IRON_BOOTS)
                    .setTitle("&d&lCageEventPlugin")
                    .addLore(chatHelper.color("&7Author: &fCzarek Pawluczuk"));
            sword = new ItemBuilder(Material.IRON_SWORD)
                    .setTitle("&d&lCageEventPlugin")
                    .addLore(chatHelper.color("&7Author: &fCzarek Pawluczuk"));
        }
        firstPlayer.getInventory().clear();
        firstPlayer.getInventory().setHelmet(helmet.build());
        firstPlayer.getInventory().setChestplate(chestplate.build());
        firstPlayer.getInventory().setLeggings(leggings.build());
        firstPlayer.getInventory().setBoots(boots.build());

        secondPlayer.getInventory().clear();
        secondPlayer.getInventory().setHelmet(helmet.build());
        secondPlayer.getInventory().setChestplate(chestplate.build());
        secondPlayer.getInventory().setLeggings(leggings.build());
        secondPlayer.getInventory().setBoots(boots.build());

        firstPlayer.getInventory().setHeldItemSlot(0);
        firstPlayer.getInventory().setItem(0, sword.build());
        if(potion!=null) {
            firstPlayer.getInventory().addItem(potion.build());
        }
        secondPlayer.getInventory().setHeldItemSlot(0);
        secondPlayer.getInventory().setItem(0, sword.build());
        if(potion!=null) {
            secondPlayer.getInventory().addItem(potion.build());
        }
        firstPlayer.teleport(getFirstPosition());
        secondPlayer.teleport(getSecondPosition());
    }
}
