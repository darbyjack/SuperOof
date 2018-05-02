package me.glaremasters.superoof.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Created by GlareMasters on 5/2/2018.
 */
public class Chat implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if (event.getPlayer().hasPermission("superoof.oof")) {
            if (event.getMessage().contains("oof")) {
                for (Player recipient : event.getRecipients()) {
                    recipient.getWorld().strikeLightningEffect(recipient.getLocation());
                }
            }

        }
    }

}
