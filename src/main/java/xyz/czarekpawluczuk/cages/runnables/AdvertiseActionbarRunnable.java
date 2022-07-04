package xyz.czarekpawluczuk.cages.runnables;

import org.bukkit.Bukkit;
import xyz.czarekpawluczuk.cages.CagesPlugin;
import xyz.czarekpawluczuk.cages.data.Event;
import xyz.czarekpawluczuk.cages.enums.EventStatus;
import xyz.czarekpawluczuk.cages.helpers.ChatHelper;

public class AdvertiseActionbarRunnable implements Runnable {

    private ChatHelper chatHelper = new ChatHelper();
    private CagesPlugin plugin = CagesPlugin.getPluginInstance();

    private String finalAdvertiseMessage = "";
    public int x = 0;

    @Override
    public void run() {
        if(plugin.events.size()==0){
            if (x == 0) {
                finalAdvertiseMessage = "&8| &cEvent nie jest aktywny &8|";
            }else if(x==1) {
                finalAdvertiseMessage = " &8| &cEvent nie jest aktywny &8|";
            }else if(x==2) {
                finalAdvertiseMessage = "  &8| &cEvent nie jest aktywny &8|";
            }else if(x==3) {
                finalAdvertiseMessage = "   &8| &cEvent nie jest aktywny &8|";
            }else if(x==4) {
                finalAdvertiseMessage = "    &8| &cEvent nie jest aktywny &8|";
            }else if(x==5) {
                finalAdvertiseMessage = "   &8| &cEvent nie jest aktywny &8|";
            }else if(x==6) {
                finalAdvertiseMessage = "  &8| &cEvent nie jest aktywny &8|";
            }else if(x==7) {
                finalAdvertiseMessage = " &8| &cEvent nie jest aktywny &8|";
            }else if(x==8) {
                finalAdvertiseMessage = "&8| &cEvent nie jest aktywny &8|";
            }else if(x==9) {
                finalAdvertiseMessage = "&8| &cEvent nie jest aktywny &8| ";
            }else if(x==10) {
                finalAdvertiseMessage = "&8| &cEvent nie jest aktywny &8|  ";
            }else if(x==11) {
                finalAdvertiseMessage = "&8| &cEvent nie jest aktywny &8|   ";
            }else if(x==12) {
                finalAdvertiseMessage = "&8| &cEvent nie jest aktywny &8|    ";
            }else if(x==13) {
                finalAdvertiseMessage = "&8| &cEvent nie jest aktywny &8|   ";
            }else if(x==14) {
                finalAdvertiseMessage = "&8| &cEvent nie jest aktywny &8|  ";
            }else if(x==15) {
                finalAdvertiseMessage = "&8| &cEvent nie jest aktywny &8| ";
            }else if(x==16) {
                finalAdvertiseMessage = "&8| &cEvent nie jest aktywny &8|";
                x=-1;
            }
            x++;
            Bukkit.getOnlinePlayers().forEach(onlinePlayers->{chatHelper.sendActionbarMessage(onlinePlayers, finalAdvertiseMessage);});
        }else{
            Event event = plugin.events.get(0);
            if(event.getStatus().equals(EventStatus.AWAY)){
                if (x == 0) {
                    finalAdvertiseMessage = "&8| &cEvent nie jest aktywny &8|";
                }else if(x==1) {
                    finalAdvertiseMessage = " &8| &cEvent nie jest aktywny &8|";
                }else if(x==2) {
                    finalAdvertiseMessage = "  &8| &cEvent nie jest aktywny &8|";
                }else if(x==3) {
                    finalAdvertiseMessage = "   &8| &cEvent nie jest aktywny &8|";
                }else if(x==4) {
                    finalAdvertiseMessage = "    &8| &cEvent nie jest aktywny &8|";
                }else if(x==5) {
                    finalAdvertiseMessage = "   &8| &cEvent nie jest aktywny &8|";
                }else if(x==6) {
                    finalAdvertiseMessage = "  &8| &cEvent nie jest aktywny &8|";
                }else if(x==7) {
                    finalAdvertiseMessage = " &8| &cEvent nie jest aktywny &8|";
                }else if(x==8) {
                    finalAdvertiseMessage = "&8| &cEvent nie jest aktywny &8|";
                }else if(x==9) {
                    finalAdvertiseMessage = "&8| &cEvent nie jest aktywny &8| ";
                }else if(x==10) {
                    finalAdvertiseMessage = "&8| &cEvent nie jest aktywny &8|  ";
                }else if(x==11) {
                    finalAdvertiseMessage = "&8| &cEvent nie jest aktywny &8|   ";
                }else if(x==12) {
                    finalAdvertiseMessage = "&8| &cEvent nie jest aktywny &8|    ";
                }else if(x==13) {
                    finalAdvertiseMessage = "&8| &cEvent nie jest aktywny &8|   ";
                }else if(x==14) {
                    finalAdvertiseMessage = "&8| &cEvent nie jest aktywny &8|  ";
                }else if(x==15) {
                    finalAdvertiseMessage = "&8| &cEvent nie jest aktywny &8| ";
                }else if(x==16) {
                    finalAdvertiseMessage = "&8| &cEvent nie jest aktywny &8|";
                    x=-1;
                }
                x++;
                Bukkit.getOnlinePlayers().forEach(onlinePlayers->{chatHelper.sendActionbarMessage(onlinePlayers, finalAdvertiseMessage);});
            }else if(event.getStatus().equals(EventStatus.INGAME)){
                if(event.getCurrent().size()>=2) {
                    if (x == 0) {
                        finalAdvertiseMessage = "&8| &fWALKA: &b" + event.getCurrent().get(0).getName() + " &7VS &c" + event.getCurrent().get(1).getName() + " &8|";
                    } else if (x == 1) {
                        finalAdvertiseMessage = " &8| &fWALKA: &b" + event.getCurrent().get(0).getName() + " &7VS &c" + event.getCurrent().get(1).getName() + " &8|";
                    } else if (x == 2) {
                        finalAdvertiseMessage = "  &8| &fWALKA: &b" + event.getCurrent().get(0).getName() + " &7VS &c" + event.getCurrent().get(1).getName() + " &8|";
                    } else if (x == 3) {
                        finalAdvertiseMessage = "   &8| &fWALKA: &b" + event.getCurrent().get(0).getName() + " &7VS &c" + event.getCurrent().get(1).getName() + " &8|";
                    } else if (x == 4) {
                        finalAdvertiseMessage = "    &8| &fWALKA: &b" + event.getCurrent().get(0).getName() + " &7VS &c" + event.getCurrent().get(1).getName() + " &8|";
                    } else if (x == 5) {
                        finalAdvertiseMessage = "   &8| &fWALKA: &b" + event.getCurrent().get(0).getName() + " &7VS &c" + event.getCurrent().get(1).getName() + " &8|";
                    } else if (x == 6) {
                        finalAdvertiseMessage = "  &8| &fWALKA: &b" + event.getCurrent().get(0).getName() + " &7VS &c" + event.getCurrent().get(1).getName() + " &8|";
                    } else if (x == 7) {
                        finalAdvertiseMessage = " &8| &fWALKA: &b" + event.getCurrent().get(0).getName() + " &7VS &c" + event.getCurrent().get(1).getName() + " &8|";
                    } else if (x == 8) {
                        finalAdvertiseMessage = "&8| &fWALKA: &b" + event.getCurrent().get(0).getName() + " &7VS &c" + event.getCurrent().get(1).getName() + " &8|";
                    } else if (x == 9) {
                        finalAdvertiseMessage = "&8| &fWALKA: &b" + event.getCurrent().get(0).getName() + " &7VS &c" + event.getCurrent().get(1).getName() + " &8| ";
                    } else if (x == 10) {
                        finalAdvertiseMessage = "&8| &fWALKA: &b" + event.getCurrent().get(0).getName() + " &7VS &c" + event.getCurrent().get(1).getName() + " &8|  ";
                    } else if (x == 11) {
                        finalAdvertiseMessage = "&8| &fWALKA: &b" + event.getCurrent().get(0).getName() + " &7VS &c" + event.getCurrent().get(1).getName() + " &8|   ";
                    } else if (x == 12) {
                        finalAdvertiseMessage = "&8| &fWALKA: &b" + event.getCurrent().get(0).getName() + " &7VS &c" + event.getCurrent().get(1).getName() + " &8|    ";
                    } else if (x == 13) {
                        finalAdvertiseMessage = "&8| &fWALKA: &b" + event.getCurrent().get(0).getName() + " &7VS &c" + event.getCurrent().get(1).getName() + " &8|   ";
                    } else if (x == 14) {
                        finalAdvertiseMessage = "&8| &fWALKA: &b" + event.getCurrent().get(0).getName() + " &7VS &c" + event.getCurrent().get(1).getName() + " &8|  ";
                    } else if (x == 15) {
                        finalAdvertiseMessage = "&8| &fWALKA: &b" + event.getCurrent().get(0).getName() + " &7VS &c" + event.getCurrent().get(1).getName() + " &8| ";
                    } else if (x == 16) {
                        finalAdvertiseMessage = "&8| &fWALKA: &b" + event.getCurrent().get(0).getName() + " &7VS &c" + event.getCurrent().get(1).getName() + " &8|";
                        x = -1;
                    }
                }
                x++;
                Bukkit.getOnlinePlayers().forEach(onlinePlayers->{chatHelper.sendActionbarMessage(onlinePlayers, finalAdvertiseMessage);});
            }else if(event.getStatus().equals(EventStatus.WAITING)){
                if (x == 0) {
                    finalAdvertiseMessage = "&8| &fOczekiwanie na graczy.. &7("+event.getPlayers()+") &8|";
                }else if(x==1) {
                    finalAdvertiseMessage = " &8/ &fOczekiwanie na graczy.. &7("+event.getPlayers()+") &8/";
                }else if(x==2) {
                    finalAdvertiseMessage = "  &8- &fOczekiwanie na graczy.. &7("+event.getPlayers()+") &8-";
                }else if(x==3) {
                    finalAdvertiseMessage = "   &8o &fOczekiwanie na graczy.. &7("+event.getPlayers()+") &8o";
                }else if(x==4) {
                    finalAdvertiseMessage = "    &8/ &fOczekiwanie na graczy.. &7("+event.getPlayers()+") &8/";
                }else if(x==5) {
                    finalAdvertiseMessage = "   &8- &fOczekiwanie na graczy.. &7("+event.getPlayers()+") &8-";
                }else if(x==6) {
                    finalAdvertiseMessage = "  &8O &fOczekiwanie na graczy.. &7("+event.getPlayers()+") &8O";
                }else if(x==7) {
                    finalAdvertiseMessage = " &8| &fOczekiwanie na graczy.. &7("+event.getPlayers()+") &8|";
                }else if(x==8) {
                    finalAdvertiseMessage = "&8/ &fOczekiwanie na graczy.. &7("+event.getPlayers()+") &8/";
                }else if(x==9) {
                    finalAdvertiseMessage = "&8- &fOczekiwanie na graczy.. &7("+event.getPlayers()+") &8- ";
                }else if(x==10) {
                    finalAdvertiseMessage = "&8o &fOczekiwanie na graczy.. &7("+event.getPlayers()+") &8o  ";
                }else if(x==11) {
                    finalAdvertiseMessage = "&8| &fOczekiwanie na graczy.. &7("+event.getPlayers()+") &8|   ";
                }else if(x==12) {
                    finalAdvertiseMessage = "&8/ &fOczekiwanie na graczy.. &7("+event.getPlayers()+") &8/    ";
                }else if(x==13) {
                    finalAdvertiseMessage = "&8- &fOczekiwanie na graczy.. &7("+event.getPlayers()+") &8-   ";
                }else if(x==14) {
                    finalAdvertiseMessage = "&8o &fOczekiwanie na graczy.. &7("+event.getPlayers()+") &8o  ";
                }else if(x==15) {
                    finalAdvertiseMessage = "&8| &fOczekiwanie na graczy.. &7("+event.getPlayers()+") &8| ";
                }else if(x==16) {
                    finalAdvertiseMessage = "&8/ &fOczekiwanie na graczy.. &7("+event.getPlayers()+") &8/";
                    x=-1;
                }
                x++;
                Bukkit.getOnlinePlayers().forEach(onlinePlayers->{chatHelper.sendActionbarMessage(onlinePlayers, finalAdvertiseMessage);});
            }
        }
    }
}
