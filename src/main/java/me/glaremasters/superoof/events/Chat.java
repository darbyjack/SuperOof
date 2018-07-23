package me.glaremasters.superoof.events;

import me.glaremasters.superoof.SuperOof;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

/**
 * Created by GlareMasters on 5/2/2018.
 */
public class Chat implements Listener {

    private SuperOof oof;

    public Chat(SuperOof oof) {
        this.oof = oof;
    }


    @EventHandler
    public void onChat(PlayerChatEvent event) {
        if (event.getPlayer().hasPermission("superoof.oof")) {
            if (event.getMessage().contains("oof")) {
                for (Player recipient : event.getRecipients()) {
                    recipient.getWorld().strikeLightningEffect(recipient.getLocation());
                }
            }
            for (String command : oof.getConfig().getStringList("commands")) {
                String cmd = command.replace("{player}", event.getPlayer().getName());
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), cmd);
            }
        }
    }

}
